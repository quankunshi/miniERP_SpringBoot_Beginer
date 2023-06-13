package project.webapplication.erpsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private long positionId;

    @Column(name = "position_name", length = 30)
    private String positionName;

    @Column(name = "position_salarybase")
    private BigDecimal salaryBase;



}
