package com.example.rest.service;

import com.example.rest.controller.dto.AdminDto;
import com.example.rest.controller.dto.ClientDto;
import com.example.rest.model.Admin;
import com.example.rest.model.Client;
import com.example.rest.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService  {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }



   public  Admin findAdminByPassword(String password) {
        return adminRepository.findAdminByPassword(password);
    }

    public void saveAdministratorToDB(AdminDto dto) {
        String encodedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());

        Admin admin = new Admin(dto.getName(), encodedPassword,
                dto.getRole());


        adminRepository.save(admin);

    }

}
