package project.webapplication.erpsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.webapplication.erpsystem.models.Employees;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private long attendanceId;


    private Employees employee;


    private Date checkIn;


    private Date checkOut;
}
