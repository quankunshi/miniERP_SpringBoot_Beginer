package project.webapplication.erpsystem.service;

import project.webapplication.erpsystem.dto.AttendanceDto;
import project.webapplication.erpsystem.models.Attendance;

import java.util.Date;
import java.util.List;

public interface AttendanceService {

    List<AttendanceDto> findAll();


}
