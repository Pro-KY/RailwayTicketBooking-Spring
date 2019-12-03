package com.proky.booking;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PreDestroy;

@SpringBootApplication
@Log4j2
@AllArgsConstructor
public class RailwayTicketBookingApplication implements CommandLineRunner {
//	BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(RailwayTicketBookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(bCryptPasswordEncoder.encode("pass_2"));
	}

}

