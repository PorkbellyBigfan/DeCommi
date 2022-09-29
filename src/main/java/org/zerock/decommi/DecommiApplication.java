package org.zerock.decommi;

import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootApplication
@EnableJpaAuditing
public class DecommiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecommiApplication.class, args);
	}
	
	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}
}
