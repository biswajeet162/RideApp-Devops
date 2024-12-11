export interface DriverInfo{
      driverId: string | undefined;
      price: number | undefined; // Price for the ride
      driverLocation: string | undefined;
      distanceFromUser: number | undefined; // Calculated distance from the user
      vehicleType: string | undefined;
      rating: number | undefined;
}