package project.webapplication.erpsystem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.webapplication.erpsystem.models.Employees;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
    private long salaryId;


    private Employees employee;


    private BigDecimal salaryAmount;


    private BigDecimal salaryAmountFinal;

    private int dayWork;
    private Date date;
}
