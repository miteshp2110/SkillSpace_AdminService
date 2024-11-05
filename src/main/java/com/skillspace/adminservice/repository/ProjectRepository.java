package com.skillspace.adminservice.repository;


import com.skillspace.adminservice.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query(value = "select count(*) from project",nativeQuery = true)
    public int totalProject();

    @Query(value = "select count(*) from project where status_level=4",nativeQuery = true)
    public int completedProject();

    @Query(value = "select count(*) from project where status_level!=4",nativeQuery = true)
    public int ongoingProject();
}
