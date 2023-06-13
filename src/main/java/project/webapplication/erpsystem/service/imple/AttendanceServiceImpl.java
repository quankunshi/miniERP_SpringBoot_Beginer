package project.webapplication.erpsystem.service.imple;

import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.webapplication.erpsystem.dto.AttendanceDto;
import project.webapplication.erpsystem.dto.EmployeeDto;
import project.webapplication.erpsystem.models.Attendance;
import project.webapplication.erpsystem.models.Employees;
import project.webapplication.erpsystem.repository.AttendanceRepository;
import project.webapplication.erpsystem.repository.EmployeesRepository;
import project.webapplication.erpsystem.service.AttendanceService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private EmployeesRepository employeesRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public AttendanceServiceImpl() {
    }

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, EmployeesRepository employeesRepository, ModelMapper modelMapper) {
        this.attendanceRepository = attendanceRepository;
        this.modelMapper = modelMapper;
        this.employeesRepository =employeesRepository;
    }

    @Override
    public List<AttendanceDto> findAll(){
        List<Attendance> attendanceList = attendanceRepository.findAll();
        return attendanceList.stream()
                .map(attendance -> modelMapper.map(attendance,AttendanceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(AttendanceDto attendanceDto) {
        Optional<Employees> employee = employeesRepository.findById(attendanceDto.getId());
        if(employee.isPresent()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            // CHỈNH SỬA PHẦN IF ELSE CASE KHI KHÔNG CÓ TEXT
            LocalDate checkIn = LocalDate.parse(attendanceDto.getCheckIn(),formatter);
           Optional<Attendance> attendance = attendanceRepository.findByEmployeeIdAndCheckIn(attendanceDto.getId(),checkIn);
            if (attendance.isPresent()) {
                /* Config ModelMapper Later will be deployed centrally at package config
                modelMapper.createTypeMap(AttendanceDto.class, Attendance.class)
                        .addMappings(mapping -> {
                            mapping.skip(Attendance::setAttendanceId);
                            // Add additional mappings if needed
/                      }); // Because ModelMapper has not been used proficiently in complex cases, so it has not been implemented in this case

                 */
                /*Field*/
                Attendance attendanceUpdate = attendance.get();
                if (!attendanceDto.getTimeIn().isEmpty()){
                    LocalTime timeIn = LocalTime.parse(attendanceDto.getTimeIn(),timeFormatter);
                    attendanceUpdate.setTimeIn(timeIn);
                }
                if (!attendanceDto.getTimeOut().isEmpty()){
                    LocalTime timeOut = LocalTime.parse(attendanceDto.getTimeOut(),timeFormatter);
                    attendanceUpdate.setTimeOut(timeOut);
                }
                attendanceRepository.save(attendanceUpdate);
            }else {
                attendanceDto.setEmployee(employee.get());
                Attendance attendanceNew = modelMapper.map(attendanceDto, Attendance.class);
                attendanceNew.setCheckIn(checkIn);
                if(!attendanceDto.getTimeIn().isEmpty()) {
                    attendanceNew.setTimeIn(LocalTime.parse(attendanceDto.getTimeIn(), timeFormatter));
                }
                if(!attendanceDto.getTimeOut().isEmpty()) {
                    attendanceNew.setTimeOut(LocalTime.parse(attendanceDto.getTimeOut(), timeFormatter));
                }
                attendanceRepository.save(attendanceNew);
            }
        }

    }

    @Override
    public List<AttendanceDto> findByEmployeeId(String id) {
        List<Attendance> attendanceList = attendanceRepository.findByEmployeeId(id);
        return attendanceList.stream()
                .map(attendance -> modelMapper.map(attendance,AttendanceDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public int countDaysByMonthAndYearAndEmployeeId(int month, int year, String employeeId) {
        return attendanceRepository.countDaysByMonthAndYearAndEmployeeId(month,year,employeeId);
    }
}
