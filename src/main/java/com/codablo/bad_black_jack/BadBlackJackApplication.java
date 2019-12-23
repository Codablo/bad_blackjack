package com.codablo.bad_black_jack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class BadBlackJackApplication implements CommandLineRunner {

	private static Logger _logger = LoggerFactory
			.getLogger(BadBlackJackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BadBlackJackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Black Jack app.");
		var blackJack = new BlackJack();
		blackJack.play();
		System.out.println("Shutting down Black Jack app.");
	}
}
