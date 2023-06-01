package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.webapplication.erpsystem.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
