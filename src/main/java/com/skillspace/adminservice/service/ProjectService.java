package com.skillspace.adminservice.service;

import com.skillspace.adminservice.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Map<String,Integer> getTotalProjectCount(){
        Map<String,Integer> totalProjectCount = new HashMap<>();
        totalProjectCount.put("count",projectRepository.totalProject());
        return totalProjectCount;
    }
    public Map<String,Integer> getOngoingProjectCount(){
        Map<String,Integer> ongoingProjectCount = new HashMap<>();
        ongoingProjectCount.put("count",projectRepository.ongoingProject());
        return ongoingProjectCount;
    }
    public Map<String,Integer> getCompletedProjectCount(){
        Map<String,Integer> completedProject = new HashMap<>();
        completedProject.put("count",projectRepository.completedProject());
        return completedProject;
    }


}
