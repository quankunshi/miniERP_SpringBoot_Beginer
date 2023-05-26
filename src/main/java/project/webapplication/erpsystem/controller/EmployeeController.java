package project.webapplication.erpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.webapplication.erpsystem.dto.EmployeeDto;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.service.EmployeeService;

import java.security.Principal;

@Controller
@PreAuthorize("isAuthenticated()")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @GetMapping("/employeeList")
    public String EmployeeListX(Model model, Principal principal){
        if (principal == null){
           return "redirect:/login";
        }
        model.addAttribute("employeeList",employeeService.findAll());
        model.addAttribute("title","Danh Sách Nhân Viên");
        model.addAttribute("newEmployee", new Employees());
        model.addAttribute("editEmployee", new Employees());
        return "employeeList";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("newEmployee") EmployeeDto employeeDto, RedirectAttributes redirectAttributes){
        try {
            if (!employeeService.existsById(employeeDto.getEmployeeId())){
                employeeService.save(employeeDto);
                redirectAttributes.addFlashAttribute("success","Thêm thành công!");
            }else {
                redirectAttributes.addFlashAttribute("failed","Mã nhân viên này đã tồn tại");
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed","Lỗi! không thể thêm nhân viên!");
        }
        return "redirect:/employeeList";
    }
    @GetMapping("/edit")
    public String edit(@ModelAttribute("editEmployee")EmployeeDto employeesDto, RedirectAttributes redirectAttributes){
        try {
            if (employeeService.existsById(employeesDto.getEmployeeId())){
                employeeService.update(employeesDto);
                redirectAttributes.addFlashAttribute("success","Cập nhật thành công!");
            }else {
                redirectAttributes.addFlashAttribute("failed","Mã nhân viên này không tồn tại");
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed","Lỗi! không thể cập nhật nhân viên!");
        }
        return "redirect:/employeeList";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String idDelete, RedirectAttributes redirectAttributes){
        try {
            if (employeeService.existsById(idDelete)){
                employeeService.deleteById(idDelete);
                redirectAttributes.addFlashAttribute("success","Xóa thành công!");
            }else {
                redirectAttributes.addFlashAttribute("failed","Mã nhân viên này không tồn tại");
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed","Lỗi! không thể xóa nhân viên!");
        }
        return "redirect:/employeeList";
    }

}
