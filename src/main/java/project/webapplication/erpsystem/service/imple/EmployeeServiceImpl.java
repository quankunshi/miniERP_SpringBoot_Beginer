package project.webapplication.erpsystem.service.imple;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.webapplication.erpsystem.dto.EmployeeDto;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.models.Salary;
import project.webapplication.erpsystem.repository.EmployeesRepository;
import project.webapplication.erpsystem.repository.SalaryRepository;
import project.webapplication.erpsystem.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private  SalaryRepository salaryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public EmployeeServiceImpl() {
    }

    public EmployeeServiceImpl(EmployeesRepository employeesRepository, SalaryRepository salaryRepository, ModelMapper modelMapper) {
        this.employeesRepository = employeesRepository;
        this.salaryRepository = salaryRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeServiceImpl(EmployeesRepository employeesRepository, SalaryRepository salaryRepository) {
        this.employeesRepository = employeesRepository;
        this.salaryRepository = salaryRepository;
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employees> employeeList = employeesRepository.findAll();
        TypeToken<List<EmployeeDto>> typeToken = new TypeToken<List<EmployeeDto>>(){};
        return modelMapper.map(employeeList, typeToken.getType());
    }

    @Override
    public EmployeeDto findById(String id) {
        Employees employees = employeesRepository.findById(id).orElse(null);
        if (employees!= null){
            return modelMapper.map(employees,EmployeeDto.class);
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
        Employees employeesUpdate = employeesRepository.findById(employeeDto.getEmployeeId()).orElse(null);
        if (employeesUpdate!=null) {
            if (!employeeDto.getEmployeeName().isEmpty()) {
                employeesUpdate.setEmployeeName(employeeDto.getEmployeeName());
            }
            if (employeeDto.getEmployeeAge()!=null) {
                employeesUpdate.setEmployeeAge(employeeDto.getEmployeeAge());
            }
            if (!employeeDto.getEmployeeNumberPhone().isEmpty()) {
                employeesUpdate.setEmployeeNumberPhone(employeeDto.getEmployeeNumberPhone());
            }
            if (!employeeDto.getEmployeeAddress().isEmpty()) {
                employeesUpdate.setEmployeeAddress(employeeDto.getEmployeeAddress());
            }
            employeesRepository.save(employeesUpdate);
        }
    }

    @Override
    public void deleteById(String  id){
        Salary salary = salaryRepository.findByEmployee(employeesRepository.findById(id).get());
        if (salary!=null) {
            salaryRepository.delete(salary);
        }
        // Xóa nhân viên
        employeesRepository.deleteById(id);
    }


}
