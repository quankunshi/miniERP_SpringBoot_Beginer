package project.webapplication.erpsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private long attendanceId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employee;


    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "time_in")
    private LocalTime timeIn;
    @Column(name = "time_out")
    private LocalTime timeOut;

    // Constructors, getters, and setters
}

