package com.example.hranalytics.repositories;

import com.example.hranalytics.domain.model.ProjectCameTo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectCameToRepository extends JpaRepository<ProjectCameTo, Integer> {
    List<ProjectCameTo> findByProject_Id(int projectId);
}
