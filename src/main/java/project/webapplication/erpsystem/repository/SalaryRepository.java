package project.webapplication.erpsystem.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import project.webapplication.erpsystem.dto.PositionDto;
import project.webapplication.erpsystem.dto.SalaryDto;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.models.Salary;

import java.util.Date;
import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
    Salary findByEmployee(Employees employees);

    @Override
    boolean existsById(Long id);

    List<Salary> findAll();

    @Query("SELECT s FROM Salary s WHERE s.employee.employeeId = :employeeId AND s.date = :date")
    Salary findSalaryByEmployeeIdAndDate(String employeeId, Date date);

}
