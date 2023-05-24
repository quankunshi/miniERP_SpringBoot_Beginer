package project.webapplication.erpsystem.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.webapplication.erpsystem.dto.SalaryDto;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.models.Position;
import project.webapplication.erpsystem.models.Salary;
import project.webapplication.erpsystem.repository.EmployeesRepository;
import project.webapplication.erpsystem.repository.SalaryRepository;
import project.webapplication.erpsystem.service.SalaryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    SalaryRepository salaryRepository;
    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public List<SalaryDto> findEmployeeSalary() {
        List<Salary> salaryList = salaryRepository.findAll();
        System.out.println(salaryList);
        List<SalaryDto> salaryDtoList = new ArrayList<>();
        for (Salary salary : salaryList){
            String positionList ="";
            for (Position position: salary.getEmployee().getPositions()){
                positionList += position.getPositionName() +" ";
            }
            SalaryDto salaryDto = new SalaryDto(salary.getSalaryId(),salary.getEmployee().getEmployeeId(),
                    salary.getEmployee().getEmployeeName(),positionList,salary.getSalaryAmount());
            salaryDtoList.add(salaryDto);
        }

        return salaryDtoList;
    }

    @Override
    public boolean exitSalary(String id, Date date) {
        List<Salary> salaryList = salaryRepository.findAll();
        for (Salary salary : salaryList){
            if (salary.getEmployee().getEmployeeId().equals(id) && salary.getDate()==date){
                return true;
            }
        }
        return false;
    }


    @Override
    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }
}
