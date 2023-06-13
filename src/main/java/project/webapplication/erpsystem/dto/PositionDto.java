package project.webapplication.erpsystem.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.webapplication.erpsystem.models.Employees;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {
    private long positionId;

    private String positionName;

    private BigDecimal salaryBase;
}
