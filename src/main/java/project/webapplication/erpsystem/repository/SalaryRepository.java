package project.webapplication.erpsystem.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.models.Salary;

import java.util.List;
import java.util.function.Function;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
    Salary findByEmployee(Employees employees);

    @Override
    boolean existsById(Long id);

    List<Salary> findAll();

}
