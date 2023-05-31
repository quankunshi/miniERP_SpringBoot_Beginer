package project.webapplication.erpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.webapplication.erpsystem.dto.PositionDto;
import project.webapplication.erpsystem.service.EmployeeService;
import project.webapplication.erpsystem.service.PositionService;

import java.security.Principal;

@Controller
@PreAuthorize("isAuthenticated()")
public class PositionController {
    @Autowired
    PositionService positionService;
    @Autowired
    EmployeeService employeeService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/position")
    public String EmployeeListX(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        model.addAttribute("title","Chức Vụ");
        model.addAttribute("positionList",employeeService.findAll());
        model.addAttribute("positionNew",new PositionDto());
        model.addAttribute("ListCounts",employeeService.findEmployeePositionCounts());
        return "position";
    }

    @PostMapping("/add-position")
    public String add(@ModelAttribute("positionNew") PositionDto positionDto, RedirectAttributes redirectAttributes){
        try {
            if (!positionService.existsByPositionName(positionDto.getPositionName())){
                positionService.save(positionDto);
                redirectAttributes.addFlashAttribute("success","Thêm thành công!");
            }else {
                redirectAttributes.addFlashAttribute("failed","Tên chức vụ này đã tồn tại");
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed","Lỗi! không thể thêm chức vụ!");
        }
        return "redirect:/position";
    }

    @PostMapping("/delete-position")
    public String delete(@ModelAttribute("positionNew") PositionDto positionDto, RedirectAttributes redirectAttributes){
        try {
            if (positionService.existsByPositionName(positionDto.getPositionName())){
                positionService.delete(positionDto);
                redirectAttributes.addFlashAttribute("success","Xóa thành công!");
            }else {
                redirectAttributes.addFlashAttribute("failed","Tên chức vụ này đã tồn tại");
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed","Lỗi! không thể xóa chức vụ!");
        }
        return "redirect:/position";
    }
}
