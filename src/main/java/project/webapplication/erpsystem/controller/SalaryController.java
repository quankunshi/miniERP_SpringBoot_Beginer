package project.webapplication.erpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.webapplication.erpsystem.dto.SalaryDto;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.models.Salary;
import project.webapplication.erpsystem.service.SalaryService;

import java.security.Principal;

@Controller
public class SalaryController {
    @Autowired
    SalaryService salaryService;
    @GetMapping("/salary")
    public String Salary(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("salaryList",salaryService.findEmployeeSalary());
        model.addAttribute("title","Danh Sách Lương Nhân Viên");
        model.addAttribute("salary_add",new SalaryDto());
        model.addAttribute("salary_edit", new SalaryDto());
        model.addAttribute("salary_delete", new SalaryDto());
        return "salary";
    }
    @PostMapping("/add-salary")
    public String add(@ModelAttribute("salary_add") SalaryDto salaryDto, RedirectAttributes redirectAttributes){
        try {
            if (!salaryService.exitSalary(salaryDto.getEmployee().getEmployeeId(), salaryDto.getDate())){
                salaryService.save(salaryDto);
                redirectAttributes.addFlashAttribute("success","Thêm thành công!");
            }else {
                redirectAttributes.addFlashAttribute("failed","Mã nhân viên này đã tồn tại");
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed","Lỗi! không thể thêm nhân viên!");
        }
        return "redirect:/salary";
    }

    @PostMapping("/edit-salary")
    public String edit(@ModelAttribute("salary_edit") SalaryDto salaryDto, RedirectAttributes redirectAttributes){
        try {
            if (!salaryService.exitSalary(salaryDto.getEmployee().getEmployeeId(), salaryDto.getDate())){
                salaryService.update(salaryDto);
                redirectAttributes.addFlashAttribute("success","Lưu thành công!");
            }else {
                redirectAttributes.addFlashAttribute("failed","Mã nhân viên và Thời Gian không đúng");
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed","Lỗi! không thể cập nhật!");
        }
        return "redirect:/salary";
    }

    @PostMapping("/delete-salary")
    public String delete(@ModelAttribute("salary_delete") SalaryDto salaryDto, RedirectAttributes redirectAttributes){
        try {
            if (!salaryService.exitSalary(salaryDto.getEmployee().getEmployeeId(), salaryDto.getDate())){
                salaryService.delete(salaryDto);
                redirectAttributes.addFlashAttribute("success","Xóa thành công!");
            }else {
                redirectAttributes.addFlashAttribute("failed","Mã nhân viên và Thời Gian không đúng");
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed","Lỗi! không thể xóa!");
        }
        return "redirect:/salary";
    }


}
