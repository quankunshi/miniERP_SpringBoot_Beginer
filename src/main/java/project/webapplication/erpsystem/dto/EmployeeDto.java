package project.webapplication.erpsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private String id;
    private String fullName;
    private int age;
    private int numberPhone;
    private String address;


}
