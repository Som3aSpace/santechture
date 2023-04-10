package com.santechture.api.service;

import com.santechture.api.entity.Admin;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final AdminService adminService;

    public MyUserDetailsService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getByUsername(username);
        return new User(admin.getUsername(), admin.getPassword(), new ArrayDeque<>());
    }
}
