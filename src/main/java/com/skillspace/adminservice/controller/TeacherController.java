package com.skillspace.adminservice.controller;


import com.skillspace.adminservice.model.Users;
import com.skillspace.adminservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/getAllTeacher")
    public Map<String, List<Users>> getAllTeacher(){
        return teacherService.getAllTeachers();
    }
    @PostMapping("/addTeacher")
    public Map<String,String> addTeacher(@RequestBody Map<String,String> map){
        return teacherService.addTeacher(map.get("email"),map.get("password"));
    }
}
