// package org.zerock.decommi.repository;

// import java.util.stream.IntStream;

<<<<<<< HEAD:src/test/java/org/zerock/decommi/repository/HelpRepositoryTests.java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.decommi.entity.Help;
import org.zerock.decommi.entity.Member;
import org.zerock.decommi.entity.Help.HelpType;

@SpringBootTest
public class HelpRepositoryTests {
    @Autowired
    HelpRepository helpRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertSCDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member writer = Member.builder().email("user" + i + "@decommi.com").build();
            Help help = Help.builder()
                    .user(writer)
                    .title("title" + i)
                    .content("content" + i)
                    .build();
            if (i < 50)
                help.addHelpType(HelpType.NOTICE);
            else if (i >= 50)
                help.addHelpType(HelpType.FQA);
            helpRepository.save(help);
        });
    }
}
=======
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.zerock.decommi.entity.Member;
// import org.zerock.decommi.entity.ServiceCenter;

// @SpringBootTest
// public class ServiceCenterRepositoryTests {
//     @Autowired
//     ServiceCenterRepository scRepository;
//     @Autowired
//     MemberRepository memberRepository;

//     @Test
//     public void insertSCDummies() {
//         IntStream.rangeClosed(1, 100).forEach(i -> {
//             Member writer = Member.builder().email("user" + i + "@decommi.com").build();

//             int count = (int) (Math.random() * 5) + 1;
//             for (int j = 0; j < count; j++) {
//                 ServiceCenter serviceCenter = ServiceCenter.builder()
//                         .writer(writer)
//                         .title("title" + j)
//                         .content("content" + j)
//                         .build();
//                 scRepository.save(serviceCenter);
//             }
//         });
//     }
// }
>>>>>>> 86f259577e2bd2105f97c1bc3bc0df6ff2de4bb2:src/test/java/org/zerock/decommi/repository/ServiceCenterRepositoryTests.java
