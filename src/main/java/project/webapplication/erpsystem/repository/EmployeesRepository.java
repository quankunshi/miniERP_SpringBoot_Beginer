package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "SELECT p.position_name, COUNT(e.position_id) " +
            "FROM Position p LEFT JOIN Employees e ON e.position_id = p.position_id " +
            "GROUP BY p.position_id, p.position_name",
            nativeQuery = true)
    List<Object[]> findEmployeePositionCounts();



}
