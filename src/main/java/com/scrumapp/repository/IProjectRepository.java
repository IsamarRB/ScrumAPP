package com.scrumapp.repository;

import com.scrumapp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository  extends JpaRepository<Project, Integer> {
}
