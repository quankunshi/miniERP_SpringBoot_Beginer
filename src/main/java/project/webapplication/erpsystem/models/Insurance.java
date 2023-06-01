package project.webapplication.erpsystem.models;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private long insuranceId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employee;

    @Column(name = "insurance_name", length = 30)
    private String insuranceName;

    @Column(name = "insurance_price")
    private BigDecimal insurancePrice;

    @Column(name = "date")
    private Date date;

    // Constructors, getters, and setters
}
