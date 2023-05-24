package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.webapplication.erpsystem.models.Position;

public interface PositionRepository  extends JpaRepository<Position, Long> {
}
