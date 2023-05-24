package project.webapplication.erpsystem.service;

import project.webapplication.erpsystem.dto.AdminDto;
import project.webapplication.erpsystem.models.Admin.Admin;

import java.util.List;

public interface AdminService {
    Admin findByUsername(String username);

    List<Admin> findAll();

    Admin save(AdminDto adminDto);




}
