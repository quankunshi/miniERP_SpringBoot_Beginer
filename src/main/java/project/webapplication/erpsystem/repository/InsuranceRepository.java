package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.webapplication.erpsystem.dto.InsuranceDto;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.models.Insurance;

import java.util.List;
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
//    @Query("SELECT i.employee form Insurance i")
//    List<Employees> findEmployeeInsurance();

}
