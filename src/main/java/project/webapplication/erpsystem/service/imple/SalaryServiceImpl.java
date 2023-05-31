package project.webapplication.erpsystem.service.imple;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
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
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    SalaryRepository salaryRepository;
    ModelMapper modelMapper = new ModelMapper();

    public SalaryServiceImpl() {
    }

    public SalaryServiceImpl(SalaryRepository salaryRepository, ModelMapper modelMapper) {
        this.salaryRepository = salaryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SalaryDto> findEmployeeSalary() {
        List<Salary> salaryList = salaryRepository.findAll();
        return salaryList.stream().map(salary -> modelMapper.map(salary,SalaryDto.class)).collect(Collectors.toList());
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
    public void save(SalaryDto salaryDto) {
       Salary salary = modelMapper.map(salaryDto,Salary.class);
       salaryRepository.save(salary);
    }

    @Override
    public SalaryDto findSalaryByEmployeeIdAndDate(String employeeId, Date date) {
        Salary salary =  salaryRepository.findSalaryByEmployeeIdAndDate(employeeId,date);
        return modelMapper.map(salary,SalaryDto.class);
    }

    @Override
    public void update(SalaryDto salaryDto) {
        Salary salaryUpdate = salaryRepository.findSalaryByEmployeeIdAndDate(salaryDto.getEmployee().getEmployeeId(),salaryDto.getDate());
        if (salaryUpdate != null) {
            salaryUpdate.setSalaryAmount(salaryDto.getSalaryAmount());
            salaryRepository.save(salaryUpdate);
        }
    }

    @Override
    public void delete(SalaryDto salaryDto) {
        Salary salarydelete = salaryRepository.findSalaryByEmployeeIdAndDate(salaryDto.getEmployee().getEmployeeId(),salaryDto.getDate());
        salaryRepository.delete(salarydelete);
    }
}
