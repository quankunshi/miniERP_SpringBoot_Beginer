package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.webapplication.erpsystem.models.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
}
