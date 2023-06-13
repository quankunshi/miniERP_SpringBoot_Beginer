package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.models.Position;

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

    @Query(value = "SELECT p.position_name, COUNT(e.position_id), p.salary_base " +
            "FROM Position p LEFT JOIN Employees e ON e.position_id = p.position_id " +
            "GROUP BY p.position_id, p.position_name, p.salary_base",
            nativeQuery = true)
    List<Object[]> findEmployeePositionCounts();




}
