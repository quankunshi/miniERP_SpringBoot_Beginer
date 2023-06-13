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
import project.webapplication.erpsystem.service.AttendanceService;
import project.webapplication.erpsystem.service.SalaryService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    SalaryRepository salaryRepository;
    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    AttendanceService attendanceService;

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
       Optional<Employees> employee = employeesRepository.findById(salaryDto.getEmployee().getEmployeeId());
       if (employee.isPresent()) {
           salary.setEmployee(employee.get());
           salaryRepository.save(salary);
       }
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

    @Override
    public void salary(List<SalaryDto> salaryDtoList) {
        if (salaryDtoList !=  null){
            for (SalaryDto salaryDto : salaryDtoList){
                //It's disgusting, I'm scared of what I do. This is a pile of garbage, I did, and you saw them =))
                if (salaryDto.getSalaryAmount()!= null) {
                    salaryDto.setDayWork(attendanceService.countDaysByMonthAndYearAndEmployeeId(6, 2023, salaryDto.getEmployee().getEmployeeId()));
                    salaryDto.setSalaryAmountFinal(BigDecimal.valueOf((float) salaryDto.getDayWork() / 30).multiply(salaryDto.getEmployee().getPositions().getSalaryBase().multiply(salaryDto.getSalaryAmount())).setScale(2, RoundingMode.HALF_UP));
                    salaryRepository.save(modelMapper.map(salaryDto, Salary.class));
                }

            }
        }
    }
}
