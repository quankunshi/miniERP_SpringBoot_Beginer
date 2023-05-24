package project.webapplication.erpsystem.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @RequestMapping(value = "/index")
    public String home(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        model.addAttribute("title","Trang Chính");
        return "index";
    }
}
