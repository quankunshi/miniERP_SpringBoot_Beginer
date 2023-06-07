package project.webapplication.erpsystem.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
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
    private Date checkIn;

    @Column(name = "check_out")
    private Date checkOut;

    @Column(name = "hours_worked")
    private boolean hoursWorked;
    // Constructors, getters, and setters
}

