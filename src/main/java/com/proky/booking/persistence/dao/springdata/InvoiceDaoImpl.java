package com.proky.booking.persistence.dao.springdata;

import com.proky.booking.persistence.dao.IInvoiceDao;
import com.proky.booking.persistence.entities.Invoice;
import com.proky.booking.persistence.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class InvoiceDaoImpl implements IInvoiceDao {
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice save(Invoice entity) {
        return invoiceRepository.save(entity);
    }

    @Override
    public Invoice update(Invoice entity) {
        return invoiceRepository.save(entity);
    }

    @Override
    public void delete(Invoice entity) {
        invoiceRepository.delete(entity);
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }
}
