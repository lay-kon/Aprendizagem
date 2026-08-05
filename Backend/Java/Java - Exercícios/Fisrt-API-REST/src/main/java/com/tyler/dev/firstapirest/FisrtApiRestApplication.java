package com.tyler.dev.firstapirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FisrtApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FisrtApiRestApplication.class, args);
		System.out.print("\nAbra no navegador por aqui: http://localhost:8080/\n\n\n");
	}
}
