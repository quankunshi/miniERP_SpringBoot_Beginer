package project.webapplication.erpsystem.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.webapplication.erpsystem.dto.AdminDto;
import project.webapplication.erpsystem.models.Admin.Admin;
import project.webapplication.erpsystem.repository.AdminRepository;
import project.webapplication.erpsystem.repository.RoleRepository;
import project.webapplication.erpsystem.service.AdminService;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFullName(adminDto.getFullName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
        admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);
    }
}
