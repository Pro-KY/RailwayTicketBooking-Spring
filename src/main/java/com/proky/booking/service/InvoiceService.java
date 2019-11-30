package com.proky.booking.service;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TrainBookingDto;
import com.proky.booking.dto.TrainDto;
import com.proky.booking.persistence.dao.IInvoiceDao;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.entities.Invoice;
import com.proky.booking.persistence.entities.Train;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.util.PasswordEncryptor;
import com.proky.booking.util.properties.Message;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;


@Transactional(readOnly = true)
@Service
@AllArgsConstructor
public class InvoiceService {
    private IInvoiceDao invoiceDao;
    private TrainService trainService;
    private ModelMapper modelMapper;
    private UserService userService;

    public InvoiceDto calculateInvoice(TrainBookingDto ticketBookingDto) {
        final boolean seatsAmountIsEmpty = ticketBookingDto.getSeatsAmount().isEmpty();
        if (seatsAmountIsEmpty) {
            ticketBookingDto.setSeatsAmount("1");
        }

        final Long trainId = Long.parseLong(ticketBookingDto.getTrainId());
        final TrainDto trainDto = trainService.getTrainDtoById(trainId);

        final InvoiceDto invoiceDto = modelMapper.map(trainDto, InvoiceDto.class);

        final BigDecimal seatsAmount = BigDecimal.valueOf(Long.parseLong(ticketBookingDto.getSeatsAmount()));
        final BigDecimal seatPrice = trainDto.getTrainSeatPrice();
        final BigDecimal routeLengthFactor = BigDecimal.valueOf(trainDto.getRouteLengthFactor());
        BigDecimal sum = seatsAmount.multiply(seatPrice).multiply(routeLengthFactor).setScale(2, RoundingMode.CEILING);
        invoiceDto.setSum(sum);
        invoiceDto.setSeatsAmount(seatsAmount);
        return invoiceDto;
    }

    @Transactional
    public void saveInvoice(final InvoiceDto invoiceDto) {
        final User user = userService.findById(invoiceDto.getUserId());
        final Train train = trainService.findTrainById(invoiceDto.getTrainId());

        final Invoice invoice = Invoice.builder()
                .user(user)
                .train(train)
                .seatsAmount(invoiceDto.getSeatsAmount().intValue())
                .price(invoiceDto.getSum())
                .dateTime(new Timestamp(System.currentTimeMillis()))
                .build();

        invoiceDao.save(invoice);
    }

}
