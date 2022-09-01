// package org.zerock.decommi.repository;

// import java.util.stream.IntStream;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.zerock.decommi.entity.Member;
// import org.zerock.decommi.entity.ToDo;

// @SpringBootTest
// public class TodoRepositoryTests {
// @Autowired
// TodoRepository todoRepository;
// @Autowired
// MemberRepository memberRepository;

// @Test
// public void insertToDoDummies() {
// IntStream.rangeClosed(1, 100).forEach(i -> {
// Member writer = Member.builder().email("user" + i + "@decommi.com").build();
// int count = (int) (Math.random() * 5) + 1;
// for (int j = 0; j < count; j++) {
// ToDo todo = ToDo.builder()
// .writer(writer)
// .todoTitle("title" + j)
// .todoContent("content" + j)
// .todoStatus(false)
// .build();
// todoRepository.save(todo);
// }
// });
// }
// }
