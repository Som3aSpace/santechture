package com.santechture.api.service;

import com.santechture.api.dto.GeneralResponse;
import com.santechture.api.dto.admin.AdminDto;
import com.santechture.api.dto.admin.AuthenticationRequest;
import com.santechture.api.entity.Admin;
import com.santechture.api.exception.BusinessExceptions;
import com.santechture.api.repository.AdminRepository;
import com.santechture.api.validation.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminService {
    private final AdminRepository adminRepository;


    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // deprecated
    public ResponseEntity<GeneralResponse> login(LoginRequest request) throws BusinessExceptions {

        Admin admin = adminRepository.findByUsernameIgnoreCase(request.getUsername());

        if(Objects.isNull(admin) || !admin.getPassword().equals(request.getPassword())){
            throw new BusinessExceptions("login.credentials.not.match");
        }

        return new GeneralResponse().response(new AdminDto(admin));
    }

    public Admin getByUsername(String username) {
        return adminRepository.findByUsernameIgnoreCase(username);
    }

    public ResponseEntity<GeneralResponse> register(AuthenticationRequest authenticationRequest){
        Admin admin = new Admin();
        admin.setUsername(authenticationRequest.getUsername());
        admin.setPassword(new BCryptPasswordEncoder().encode(authenticationRequest.getPassword()));

        return new GeneralResponse().response(new AdminDto(adminRepository.save(admin)));
    }

}
