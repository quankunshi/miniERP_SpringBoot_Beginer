package project.webapplication.erpsystem.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.webapplication.erpsystem.models.Position;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String employeeId;
    private String employeeName;
    private Integer employeeAge;
    private String employeeNumberPhone;
    private Date joiningDate;
    private String employeeAddress;
    private Position positions;


}
