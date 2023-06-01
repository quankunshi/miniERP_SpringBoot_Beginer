package project.webapplication.erpsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class VehicleController {
    @GetMapping("/vehicle")
    public String vehicle(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        return "updateLater";
    }
    @GetMapping("/vehicle-type")
    public String vehicleType(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        return "updateLater";
    }
    @GetMapping("/vehicle-use")
    public String vehicleUse(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        return "updateLater";
    }
}
