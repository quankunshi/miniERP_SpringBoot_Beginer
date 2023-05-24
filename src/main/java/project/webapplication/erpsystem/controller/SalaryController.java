package project.webapplication.erpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        model.addAttribute("salary_add",new Salary());
        return "salary";
    }
    @PostMapping("/add-salary")
    public String add(@ModelAttribute("salary_add") Salary salary, RedirectAttributes redirectAttributes){
        try {
            if (!salaryService.exitSalary(salary.getEmployee().getEmployeeId(), salary.getDate())){
                salaryService.save(salary);
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
}
