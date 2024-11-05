package com.skillspace.adminservice.controller;


import com.skillspace.adminservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @PostMapping("/getTotalProject")
    public Map<String,Integer> getTotalProject(){
        return projectService.getTotalProjectCount();
    }
    @PostMapping("/getOngoingProject")
    public Map<String,Integer> getOngoingProject(){
        return projectService.getOngoingProjectCount();
    }
    @PostMapping("/getCompletedProject")
    public Map<String,Integer> getCompletedProject(){
        return projectService.getCompletedProjectCount();
    }
}
