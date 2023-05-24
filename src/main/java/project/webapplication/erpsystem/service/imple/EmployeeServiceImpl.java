package project.webapplication.erpsystem.service.imple;

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

    public EmployeeServiceImpl(EmployeesRepository employeesRepository, SalaryRepository salaryRepository) {
        this.employeesRepository = employeesRepository;
        this.salaryRepository = salaryRepository;
    }

    @Override
    public List<Employees> findAll() {
        return employeesRepository.findAll();
    }

    @Override
    public Employees findById(String id) {
        return employeesRepository.findById(id).get();
    }

    @Override
    public Employees save(Employees employees) {
        Employees newEmployees = new Employees();
        newEmployees.setEmployeeId(employees.getEmployeeId());
        newEmployees.setEmployeeName(employees.getEmployeeName());
        newEmployees.setEmployeeAge(employees.getEmployeeAge());
        newEmployees.setEmployeeNumberPhone(employees.getEmployeeNumberPhone());
        newEmployees.setEmployeeAddress(employees.getEmployeeAddress());
        return employeesRepository.save(newEmployees);
    }

    @Override
    public boolean existsById(String id) {
       return employeesRepository.existsById(id);
    }

    @Override
    public Employees update(Employees employees) {
        Employees employeesUpdate = employeesRepository.findById(employees.getEmployeeId()).get();
        if (!employees.getEmployeeName().equals("")) {employeesUpdate.setEmployeeName(employees.getEmployeeName());}
        if (employees.getEmployeeAge() != null) { employeesUpdate.setEmployeeAge(employees.getEmployeeAge());}
        if (!employees.getEmployeeNumberPhone().equals("")) { employeesUpdate.setEmployeeNumberPhone(employees.getEmployeeNumberPhone());}
        if (!employees.getEmployeeAddress().equals("")) { employeesUpdate.setEmployeeAddress(employees.getEmployeeAddress());}
        return employeesRepository.save(employeesUpdate);
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
