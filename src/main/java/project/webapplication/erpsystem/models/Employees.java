package project.webapplication.erpsystem.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employees {

    @Id
    @Column(name = "employee_id", length = 5)
    private String employeeId;

    @Column(name = "employee_name", length = 30)
    private String employeeName;

    @Column(name = "employee_age")
    private Integer employeeAge;

    @Column(name = "employee_numberPhone")
    private String employeeNumberPhone;
    @Column(name = "employee_address")
    private String employeeAddress;


    @ManyToMany
    @JoinTable(
            name = "employee_position",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private Collection<Position> positions;

    @ManyToMany
    @JoinTable(name = "employee_vehicle",joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private Collection<Vehicle> vehicles;
}
