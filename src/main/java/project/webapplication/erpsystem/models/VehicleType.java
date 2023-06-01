package project.webapplication.erpsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_type")
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_type_id")
    private long vehicleTypeId;

    @Column(name = "vehicle_type_name", length = 30)
    private String vehicleTypeName;

    // Constructors, getters, and setters
}
