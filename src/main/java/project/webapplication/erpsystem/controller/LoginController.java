package project.webapplication.erpsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.webapplication.erpsystem.service.imple.AdminServiceImpl;

@Controller
public class LoginController {

    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("title","Login");
        return "login";
    }


}
