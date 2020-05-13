package com.ss.lms.orchestrator.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.db.UserDAO;
import com.ss.lms.orchestrator.entity.User;

@RestController
public class SecurityControlledAPIs {
    private UserDAO userDao;

    public SecurityControlledAPIs(UserDAO userDao){
        this.userDao = userDao;
    }
    
    // Available to ROLE_ADMIN
    @GetMapping("admin/users")
    public List<User> users(){
        return this.userDao.findAll();
    }
}