package org.zerock.decommi.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.decommi.entity.Member;
import org.zerock.decommi.entity.ServiceCenter;

@SpringBootTest
public class ServiceCenterRepositoryTests {
    @Autowired
    ServiceCenterRepository scRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertSCDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member writer = Member.builder().email("user" + i + "@decommi.com").build();

            int count = (int) (Math.random() * 5) + 1;
            for (int j = 0; j < count; j++) {
                ServiceCenter serviceCenter = ServiceCenter.builder()
                        .writer(writer)
                        .title("title" + j)
                        .content("content" + j)
                        .build();
                scRepository.save(serviceCenter);
            }
        });
    }
}
