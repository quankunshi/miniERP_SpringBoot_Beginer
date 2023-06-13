package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.webapplication.erpsystem.dto.PositionDto;
import project.webapplication.erpsystem.models.Position;

import java.util.Date;
import java.util.List;

public interface PositionRepository  extends JpaRepository<Position, Long> {

    List<Position> findAll();

    boolean existsByPositionName(String name);

    @Query("Select p From Position p Where p.positionName = :name")
    Position findByName(String name);

    @Override
    void deleteById(Long aLong);
}
