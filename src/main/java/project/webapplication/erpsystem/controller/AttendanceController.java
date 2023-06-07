package project.webapplication.erpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.webapplication.erpsystem.dto.AttendanceDto;
import project.webapplication.erpsystem.service.AttendanceService;

import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;

    @GetMapping("/attendance")
    public String AttendanceList(Model model, Principal principal){
        if (principal ==null){
            return "redirect:/login";
        }
        model.addAttribute("title","Chấm Công");
        List<AttendanceDto> attendanceDtoList = attendanceService.findAll();
        model.addAttribute("attendanceList",attendanceDtoList);
        model.addAttribute("dateList",attendanceService.findDateList());
        return "attendance";
    }

}
