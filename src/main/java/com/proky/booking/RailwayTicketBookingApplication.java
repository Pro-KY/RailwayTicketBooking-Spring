package com.proky.booking;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
@Log4j2
@AllArgsConstructor
public class RailwayTicketBookingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RailwayTicketBookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}

