package project.webapplication.erpsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.webapplication.erpsystem.models.VehicleType;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private long vehicleId;


    private VehicleType vehicleType;


    private String licensePlate;
}
