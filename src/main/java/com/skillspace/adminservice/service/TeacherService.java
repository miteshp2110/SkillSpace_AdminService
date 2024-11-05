package com.skillspace.adminservice.service;

import com.skillspace.adminservice.model.Users;
import com.skillspace.adminservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {
    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Map<String, List<Users>> getAllTeachers(){
        HashMap<String,List<Users>> teachers = new HashMap<>();
        teachers.put("teachers",userRepository.getAllTeacher());
        return teachers;
    }
    public Map<String,String> addTeacher(String email,String password){
        Users teacher = new Users();
        teacher.setEmail(email);
        teacher.setPassword(passwordEncoder.encode(password));
        teacher.setRole("teacher");
        userRepository.save(teacher);
        Map<String,String> teachers = new HashMap<>();
        teachers.put("Message","Success");
        return teachers;
    }
}
