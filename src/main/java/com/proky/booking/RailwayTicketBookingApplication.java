package com.proky.booking;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class RailwayTicketBookingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RailwayTicketBookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}

