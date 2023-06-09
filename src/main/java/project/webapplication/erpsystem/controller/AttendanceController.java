package project.webapplication.erpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.webapplication.erpsystem.dto.AttendanceDto;
import project.webapplication.erpsystem.dto.EmployeeDto;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.service.AttendanceService;
import project.webapplication.erpsystem.service.EmployeeService;

import java.awt.print.Pageable;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@PreAuthorize("isAuthenticated()")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/attendance")
    public String AttendanceList(Model model, Principal principal){
        if (principal ==null){
            return "redirect:/login";
        }
        /*Add attribute to view*/
        model.addAttribute("title","Chấm Công");

        //EmployeeDto List
        List<EmployeeDto> employeeDtoList = employeeService.findAll();
        model.addAttribute("employeeDtoList",employeeDtoList);

        //AttendanceDto List
        List<AttendanceDto> attendanceDtoList = attendanceService.findAll();
        model.addAttribute("attendanceList",attendanceDtoList);
        return "attendance";
    }

}
