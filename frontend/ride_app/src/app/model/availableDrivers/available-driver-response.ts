import { VehicleType } from "./vehicle-type";

export interface AvailableDriverResponse{
    userId: number;
    userPickupLocation: string | undefined
    userDropLocation: string | undefined
    userCoordinates: string | undefined
    driverLocationUpdatesAt: string | undefined
    drivers: VehicleType[]
}