package project.webapplication.erpsystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.webapplication.erpsystem.models.Employees;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeesRepository extends CrudRepository<Employees,String> {
    @Override
    List<Employees> findAll();
    @Override
    Optional<Employees> findById(String id);

    @Override
    void deleteById(String id);

    @Override
    boolean existsById(String s);
}
