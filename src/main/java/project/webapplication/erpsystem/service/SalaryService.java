package project.webapplication.erpsystem.service;

import org.springframework.stereotype.Service;
import project.webapplication.erpsystem.dto.SalaryDto;
import project.webapplication.erpsystem.models.Salary;

import java.util.Date;
import java.util.List;


public interface SalaryService {
    List<SalaryDto> findEmployeeSalary();

    boolean exitSalary(String id, Date date);

    Salary save(Salary salary);
}
