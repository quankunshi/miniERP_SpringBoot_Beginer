package project.webapplication.erpsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
    private long salary_id;
    private String employee_id;
    private String fullName;
    private String namePosition;
    private BigDecimal amount;
}
