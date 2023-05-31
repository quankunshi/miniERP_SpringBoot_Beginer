package project.webapplication.erpsystem.service.imple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.webapplication.erpsystem.dto.PositionDto;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.models.Position;
import project.webapplication.erpsystem.repository.EmployeesRepository;
import project.webapplication.erpsystem.repository.PositionRepository;
import project.webapplication.erpsystem.service.PositionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepository positionRepository;


    private ModelMapper modelMapper = new ModelMapper();

    public PositionServiceImpl() {
    }

    public PositionServiceImpl(PositionRepository positionRepository, ModelMapper modelMapper) {
        this.positionRepository = positionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PositionDto> findAll() {
        return positionRepository.findAll()
                .stream().map(position -> modelMapper.map(position,PositionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByPositionName(String name) {
        return positionRepository.existsByPositionName(name);
    }

    @Override
    public void save(PositionDto positionDto) {
        positionRepository.save(modelMapper.map(positionDto, Position.class));
    }

    @Override
    public void delete(PositionDto positionDto) {
        Position position = positionRepository.findByName(positionDto.getPositionName());
        positionRepository.deleteById(position.getPositionId());
    }
}
