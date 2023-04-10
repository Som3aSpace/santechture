package com.santechture.api.validation;

import com.santechture.api.annotation.SecurityValid;
import com.santechture.api.dto.admin.AuthenticationRequest;
import com.santechture.api.repository.AdminRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecurityValidator implements ConstraintValidator<SecurityValid, AuthenticationRequest> {
    private final AdminRepository adminRepository;

    public SecurityValidator(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void initialize(SecurityValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AuthenticationRequest authenticationRequest, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if(adminRepository.findByUsernameIgnoreCase(authenticationRequest.getUsername()) != null){

            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("User already exists!")
                    .addPropertyNode("username").addConstraintViolation();
            return false;
        }
        return true;
    }
}
