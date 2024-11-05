package com.skillspace.adminservice.controller;


import com.skillspace.adminservice.model.Users;
import com.skillspace.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/getAllAdmin")
    public Map<String, List<Users>> getAllAdmins(){
        return adminService.getAllAdmins();
    }
    @PostMapping("/addAdmin")
    public Map<String,String> addAdmin(@RequestBody Map<String,String> request){
        return adminService.addAdmin(request.get("email"),request.get("password"));
    }
    @DeleteMapping("/deleteAdmin")
    public Map<String,String> deleteAdmin(){
        return adminService.deleteAdmin();
    }
}
