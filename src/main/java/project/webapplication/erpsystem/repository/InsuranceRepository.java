package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.webapplication.erpsystem.models.Insurance;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
    List<Insurance> findAll();

}
