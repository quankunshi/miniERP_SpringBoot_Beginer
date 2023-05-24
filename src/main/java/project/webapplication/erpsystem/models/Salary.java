package project.webapplication.erpsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id")
    private long salaryId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employee;

    @Column(name = "salary_amount")
    private BigDecimal salaryAmount;

    @Column(name = "date")
    private Date date;
}
