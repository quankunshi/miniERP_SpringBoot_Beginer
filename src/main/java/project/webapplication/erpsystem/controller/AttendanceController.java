package project.webapplication.erpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.webapplication.erpsystem.dto.AttendanceDto;
import project.webapplication.erpsystem.dto.EmployeeDto;
import project.webapplication.erpsystem.dto.PositionDto;
import project.webapplication.erpsystem.models.Attendance;
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

        model.addAttribute("attendanceNew",new AttendanceDto());
        return "attendance";
    }


    @PostMapping("/add-attendance")
    public String add(@ModelAttribute("attendanceNew") AttendanceDto attendanceDto, RedirectAttributes redirectAttributes){
        try {
            attendanceService.save(attendanceDto);
            redirectAttributes.addFlashAttribute("success","Chấm công thành công!");

        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed","Lỗi! không thể chấm công chức vụ!");
        }
        return "redirect:/attendance";
    }



    @GetMapping("/attendanceDetail")
    public String detailEmployee(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        EmployeeDto employeeDto = new EmployeeDto();
        if (model.containsAttribute("employeeDetail")) {
            employeeDto = (EmployeeDto) model.getAttribute("employeeDetail");
            model.addAttribute("employeeDetail", employeeDto);
        }
        model.addAttribute("title","Chi Tiết Chấm công");
        return "attendanceDetail";
    }
    @GetMapping("/attendanceDetail/{id}")
    public String findById(@PathVariable("id") String id, RedirectAttributes redirectAttributes){
        EmployeeDto employeeDto =employeeService.findById(id);
        redirectAttributes.addFlashAttribute("employeeDetail", employeeDto);
        List<AttendanceDto> attendanceDtoList = attendanceService.findByEmployeeId(id);
        redirectAttributes.addFlashAttribute("attendanceDtoList",attendanceDtoList);
        System.out.println(attendanceService.countDaysByMonthAndYearAndEmployeeId(6,2023,id));
        return "redirect:/attendanceDetail";
    }

}
