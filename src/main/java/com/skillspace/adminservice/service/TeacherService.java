package com.skillspace.adminservice.service;

import com.skillspace.adminservice.model.Users;
import com.skillspace.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {
    @Autowired
    private UserRepository userRepository;

    public Map<String, List<Users>> getAllTeachers(){
        HashMap<String,List<Users>> teachers = new HashMap<>();
        teachers.put("teachers",userRepository.getAllTeacher());
        return teachers;
    }
}
