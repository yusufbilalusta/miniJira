package com.yusufbilalusta.springboot.repository;

import com.yusufbilalusta.springboot.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
