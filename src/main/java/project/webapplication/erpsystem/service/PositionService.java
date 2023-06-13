package project.webapplication.erpsystem.service;

import org.springframework.stereotype.Service;
import project.webapplication.erpsystem.dto.PositionDto;

import java.util.List;
public interface PositionService {

    List<PositionDto> findAll();
    boolean existsByPositionName(String name);

    void save(PositionDto positionDto);

    void delete(PositionDto positionDto);


}
