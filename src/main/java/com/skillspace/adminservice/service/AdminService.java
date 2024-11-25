package com.skillspace.adminservice.service;

import com.skillspace.adminservice.model.Users;
import com.skillspace.adminservice.repository.UserRepository;
import com.skillspace.adminservice.util.UsernameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UsernameUtil usernameUtil;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Map<String,List<Users>> getAllAdmins(){
        HashMap<String,List<Users>> admins = new HashMap<>();
        admins.put("admins",userRepository.getAllAdmin());
        return admins;
    }

    public ResponseEntity<String> addAdmin(String email,String password){
        Users admin = new Users();
        int cnt = userRepository.ifExistEmail(email);
        if(cnt==1){

            return ResponseEntity.status(403).body("Email Already Exist");
        }
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRole("admin");
        admin.setEmailStatus(false);
        admin.setProfileStatus(false);
        userRepository.save(admin);
        return ResponseEntity.status(201).body("Success");
    }

    public boolean getEmailStatus(String email){
        return userRepository.getEmailStatus(email);
    }
    public Map<String,String> deleteAdmin(){
        String email = usernameUtil.getUsername();
        Map<String,String> admins = new HashMap<>();
        userRepository.deleteEmail(email);
        admins.put("Message","Success");
        return admins;
    }


}
