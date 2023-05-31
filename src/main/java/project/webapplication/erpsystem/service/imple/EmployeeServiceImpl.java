package project.webapplication.erpsystem.service.imple;

import jakarta.validation.constraints.Null;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.webapplication.erpsystem.dto.EmployeeDto;
import project.webapplication.erpsystem.dto.PositionDto;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.models.Position;
import project.webapplication.erpsystem.models.Salary;
import project.webapplication.erpsystem.repository.EmployeesRepository;
import project.webapplication.erpsystem.repository.SalaryRepository;
import project.webapplication.erpsystem.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private SalaryRepository salaryRepository;

    private ModelMapper modelMapper = new ModelMapper();


    public EmployeeServiceImpl() {
    }

    public EmployeeServiceImpl(EmployeesRepository employeesRepository, SalaryRepository salaryRepository, ModelMapper modelMapper) {
        this.employeesRepository = employeesRepository;
        this.salaryRepository = salaryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<EmployeeDto> findAll() {
        List<Employees> employeeList = employeesRepository.findAll();
        return employeeList.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))//iterate over the elements and map to EmployeeDto
                .collect(Collectors.toList());//gather the EmployeeDto and make a list
    }

    @Override
    public EmployeeDto findById(String id) {
        Employees employees = employeesRepository.findById(id).orElse(null);
        if (employees != null) {
            return modelMapper.map(employees, EmployeeDto.class);
        }
        return null;
    }


    @Override
    public void save(EmployeeDto employeeDto) {
        Employees employees = modelMapper.map(employeeDto, Employees.class);
        employeesRepository.save(employees);
    }

    @Override
    public boolean existsById(String id) {
        return employeesRepository.existsById(id);
    }

    @Override
    public void update(EmployeeDto employeeDto) {
        Condition<Object, Object> notNullCondition = new Condition<Object, Object>() {
            /**
             *Is used to check if a mapping condition should be applied to a particular mapping context.
             *@return a boolean value that determines whether the mapping condition should be applied.
             * */
            @Override
            public boolean applies(MappingContext<Object, Object> context) {
                //this is its condition, since the attributes are both integer(!null) and String(isEmpty)
                //if only numbers then !null or only String then isEmpty
                return context.getSource() != null && !context.getSource().toString().isEmpty();
            }
        };
        modelMapper.getConfiguration().setPropertyCondition(notNullCondition);

        Employees employeesUpdate = employeesRepository.findById(employeeDto.getEmployeeId()).orElse(null);

        if (employeesUpdate != null) {
            modelMapper.map(employeeDto, employeesUpdate);
            employeesRepository.save(employeesUpdate);
            /* this way is also ok
            if (!employeeDto.getEmployeeName().isEmpty()) {
                employeesUpdate.setEmployeeName(employeeDto.getEmployeeName());
            }
            if (employeeDto.getEmployeeAge() != null) {
                employeesUpdate.setEmployeeAge(employeeDto.getEmployeeAge());
            }
            if (!employeeDto.getEmployeeNumberPhone().isEmpty()) {
                employeesUpdate.setEmployeeNumberPhone(employeeDto.getEmployeeNumberPhone());
            }
            if (!employeeDto.getEmployeeAddress().isEmpty()) {
                employeesUpdate.setEmployeeAddress(employeeDto.getEmployeeAddress());
            }
            */

        }


    }

    @Override
    public void deleteById(String id) {
        Salary salary = salaryRepository.findByEmployee(employeesRepository.findById(id).get());
        if (salary != null) {
            salaryRepository.delete(salary);
        }
        // Xóa nhân viên
        employeesRepository.deleteById(id);
    }

    @Override
    public List<Object[]> findEmployeePositionCounts() {
        return employeesRepository.findEmployeePositionCounts();

    }


}
