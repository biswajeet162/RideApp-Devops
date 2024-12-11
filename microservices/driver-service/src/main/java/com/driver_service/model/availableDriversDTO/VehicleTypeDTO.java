package com.driver_service.model.availableDriversDTO;
import lombok.Data;

import java.util.List;

@Data
public class VehicleTypeDTO {
    private String vehicleType;
    private List<DriverInfoDTO> drivers;

    // Constructors, Getters, and Setters
    public VehicleTypeDTO(String vehicleType, List<DriverInfoDTO> drivers) {
        this.vehicleType = vehicleType;
        this.drivers = drivers;
    }

    // Default constructor
    public VehicleTypeDTO() {
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<DriverInfoDTO> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverInfoDTO> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "VehicleTypeDTO{" +
                "vehicleType='" + vehicleType + '\'' +
                ", drivers=" + drivers +
                '}';
    }
}
