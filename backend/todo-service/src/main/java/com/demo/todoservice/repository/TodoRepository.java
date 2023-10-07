package com.demo.todoservice.repository;

import com.demo.todoservice.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {

    Page<Todo> findAllByCreatorId(UUID creatorId, PageRequest pageRequest);
}
