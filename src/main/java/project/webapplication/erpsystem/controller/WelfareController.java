package project.webapplication.erpsystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@PreAuthorize("isAuthenticated()")
public class WelfareController {
    @GetMapping("/welfare")
    public String Insurance(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        model.addAttribute("title","Phúc Lợi");
        return "welfare";
    }
}
