package project.webapplication.erpsystem.service;

import project.webapplication.erpsystem.dto.AttendanceDto;
import project.webapplication.erpsystem.models.Attendance;

import java.util.Date;
import java.util.List;

public interface AttendanceService {

    List<AttendanceDto> findAll();

    void save(AttendanceDto attendanceDto);

    List<AttendanceDto> findByEmployeeId(String id);

    int countDaysByMonthAndYearAndEmployeeId(int month, int year, String employeeId);

}
