package project.webapplication.erpsystem.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.webapplication.erpsystem.models.Employees;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private long attendanceId;

    private Employees employee;
    private String checkIn;
    private String timeIn;
    private String timeOut;
    private String id;



}
