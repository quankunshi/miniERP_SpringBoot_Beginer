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
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDto {
    private long insuranceId;


    private Employees employee;


    private String insuranceName;


    private BigDecimal insurancePrice;


    private Date date;
}
