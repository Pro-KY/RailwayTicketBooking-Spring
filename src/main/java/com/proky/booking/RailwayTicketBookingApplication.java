package com.proky.booking;

import com.proky.booking.persistence.entities.User;
import com.proky.booking.persistence.repository.UserRepository;
import com.proky.booking.service.TestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
@Log4j2
public class RailwayTicketBookingApplication implements CommandLineRunner {
//public class RailwayTicketBookingApplication {
	@Autowired
	TestService testService;

	public static void main(String[] args) {
		SpringApplication.run(RailwayTicketBookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testService.testRoute();
//		testService.test_2();
	}
}

