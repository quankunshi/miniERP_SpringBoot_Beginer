package project.webapplication.erpsystem.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    @Size(min = 6, max = 35, message = "Last name and first name should be between 6 and 35 characters long")
    private String fullName;
    @Size(min = 4, message = "user name should be least 4 characters long")
    private String username;
    @Size(min = 4, message = "user name should be least 4 characters long")
    private String password;


}
