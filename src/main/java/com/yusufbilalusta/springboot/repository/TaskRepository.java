package com.yusufbilalusta.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yusufbilalusta.springboot.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
