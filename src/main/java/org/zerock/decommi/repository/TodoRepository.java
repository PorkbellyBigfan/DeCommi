package org.zerock.decommi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.member.ToDo;

public interface TodoRepository extends JpaRepository<ToDo, Long> {

}
