package project.webapplication.erpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.webapplication.erpsystem.dto.EmployeeDto;
import project.webapplication.erpsystem.dto.PositionDto;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.models.Position;
import project.webapplication.erpsystem.service.EmployeeService;
import project.webapplication.erpsystem.service.PositionService;

import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PositionService positionService;

    @GetMapping("/employeeList")
    public String EmployeeListX(Model model, Principal principal){
        if (principal == null){
           return "redirect:/login";
        }
        model.addAttribute("employeeList",employeeService.findAll());
        model.addAttribute("title","Danh Sách Nhân Viên");
        model.addAttribute("newEmployee", new EmployeeDto());
        List<PositionDto> positions = positionService.findAll();
        model.addAttribute("positionList", positions);
        model.addAttribute("editEmployee", new EmployeeDto());
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

    @GetMapping("/employee")
    public String detailEmployee(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        EmployeeDto employeeDto = new EmployeeDto();
        if (model.containsAttribute("employeeDetail")) {
            employeeDto = (EmployeeDto) model.getAttribute("employeeDetail");
            model.addAttribute("employeeDetail", employeeDto);
        }
        System.out.println(employeeDto);
        model.addAttribute("title","Chi Tiết Nhân Viên");
        return "detail";
    }
    @GetMapping("/employee/{id}")
    public String findById(@PathVariable("id") String id,RedirectAttributes redirectAttributes){
        EmployeeDto employeeDto =employeeService.findById(id);
        redirectAttributes.addFlashAttribute("employeeDetail", employeeDto);
        return "redirect:/employee";
    }
}
