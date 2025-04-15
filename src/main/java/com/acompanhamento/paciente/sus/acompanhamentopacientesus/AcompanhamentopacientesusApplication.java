package com.acompanhamento.paciente.sus.acompanhamentopacientesus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AcompanhamentopacientesusApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcompanhamentopacientesusApplication.class, args);
	}

}
