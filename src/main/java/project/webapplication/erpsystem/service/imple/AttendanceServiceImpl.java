package project.webapplication.erpsystem.service.imple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.webapplication.erpsystem.dto.AttendanceDto;
import project.webapplication.erpsystem.models.Attendance;
import project.webapplication.erpsystem.repository.AttendanceRepository;
import project.webapplication.erpsystem.service.AttendanceService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public AttendanceServiceImpl() {
    }

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, ModelMapper modelMapper) {
        this.attendanceRepository = attendanceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AttendanceDto> findAll(){
        List<Attendance> attendanceList = attendanceRepository.findAll();
        return attendanceList.stream()
                .map(attendance -> modelMapper.map(attendance,AttendanceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Date> findDateList(){
        return attendanceRepository.findDistinctByCheckIn();
    }

}
