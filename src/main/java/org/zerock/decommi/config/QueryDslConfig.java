package org.zerock.decommi.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class QueryDslConfig {
<<<<<<< HEAD
  @PersistenceContext
  private EntityManager entityManager;

  @Bean
  public JPAQueryFactory jpaQueryFactory(){
    return new JPAQueryFactory(entityManager);
  }
=======
@PersistenceContext
private EntityManager entityManager;

@Bean
public JPAQueryFactory jpaQueryFactory(){
return new JPAQueryFactory(entityManager);
}
>>>>>>> 2010378dacc4f8633f655f21789c4419a739af5b
}
