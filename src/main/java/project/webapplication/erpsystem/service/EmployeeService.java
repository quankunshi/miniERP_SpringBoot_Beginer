package project.webapplication.erpsystem.service;

import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.Query;
import project.webapplication.erpsystem.dto.EmployeeDto;
import project.webapplication.erpsystem.models.Employees;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> findAll();
    EmployeeDto findById(String id);
    void save(EmployeeDto employeeDto);

    boolean existsById(String id);

    void update(EmployeeDto employeeDto);

    void deleteById(String id);

    List<Object[]> findEmployeePositionCounts();



}
