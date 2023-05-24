package project.webapplication.erpsystem.service;

import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.Query;
import project.webapplication.erpsystem.dto.EmployeeDto;
import project.webapplication.erpsystem.models.Employees;

import java.util.List;

public interface EmployeeService {
    List<Employees> findAll();
    Employees findById(String id);
    Employees save(Employees employees);

    boolean existsById(String id);

    Employees update(Employees employees);

    void deleteById(String id);


}
