package com.christian.microcurso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicrocursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrocursoApplication.class, args);
	}

}
