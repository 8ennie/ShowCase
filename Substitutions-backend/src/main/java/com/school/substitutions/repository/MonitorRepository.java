package com.school.substitutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.school.substitutions.model.data.Monitor;

@RepositoryRestResource()
public interface MonitorRepository extends JpaRepository<Monitor, Long>{

}
