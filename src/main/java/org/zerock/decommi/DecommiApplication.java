package org.zerock.decommi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DecommiApplication {
	public static void main(String[] args) {
		SpringApplication.run(DecommiApplication.class, args);
	}
}