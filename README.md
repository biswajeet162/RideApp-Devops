Detailed Document for User, Driver, and Ride Service Scenarios
This document outlines the detailed scenarios for the User, Driver, and Ride services, including the flow of actions and integrations between different services, as discussed. These scenarios will provide clarity on how the components interact within the application and the responsibilities of each service.

1. User Registration (User Service)
Scenario: User Registers as a Driver
Frontend Flow:
The user fills out the registration form on the frontend, selecting the User role or Driver role during registration.
The User Service is responsible for validating the user's details (e.g., name, email, phone number, etc.).
Backend Flow (User Service):
The User Service receives the registration data and stores it in the User Database.
After successful user creation, the service sets the user type as "Driver" in the database.
On successful creation, the User Service routes the user to the Driver Service for further onboarding (e.g., driver-specific details like vehicle type, license number, etc.).
2. Driver Onboarding (Driver Service)
Scenario: Driver Onboarding and Location Update
Frontend Flow:

After user registration as a Driver, the frontend redirects the user to a Driver Onboarding Page.
The user is prompted to enter vehicle type, license details, driver address, and driver's availability.
Backend Flow (Driver Service):

The Driver Service receives the information entered by the driver and stores it in the Driver Database.
The service will call the Google Maps API to get the driver's location (latitude and longitude) using the driver's address.
The driver's status is initially set to "AVAILABLE" and this is stored in the database.
Periodic Location Updates:

The Driver Service will periodically update the driver's location in the database using the Google Maps API or another geolocation service.
The driver’s status is updated as needed (e.g., "ON_RIDE", "UNAVAILABLE").
3. Ride Request (Ride Service)
Scenario: User Requests a Ride
Frontend Flow:

After the user successfully registers and is redirected, the user is asked to enter pickup and drop location.
Upon entering the locations, the frontend calls the Ride Service API to submit the pickup and drop location.
Backend Flow (Ride Service):

The Ride Service receives the pickup and drop location from the frontend.
The Ride Service uses the Map Service (e.g., Google Maps API) to convert the addresses into latitude and longitude.
The Ride Service queries the Driver Service for a list of available drivers near the pickup location.
The Driver Service returns the available drivers based on proximity and status (e.g., "AVAILABLE").
The Ride Service sends the available driver details back to the frontend, which then displays a list of available drivers to the user.
4. Driver Notification and Ride Acceptance
Scenario: Notify Driver of New Ride Request
Backend Flow (Ride Service):

Once the available drivers are found, the Ride Service sends a ride request notification to the Driver Service for the selected drivers.
This notification includes pickup location, drop location, and user details (e.g., name, contact).
The Driver Service will notify the driver about the incoming ride request and ask the driver to accept or reject the ride.
Driver Response:

The Driver Service will allow the driver to accept or reject the ride via an API endpoint (/drivers/{driverId}/accept-ride).
If the driver accepts, the Driver Service updates the driver’s status to "ON_RIDE".
If the driver rejects, the Driver Service updates the driver’s status to "AVAILABLE" and the Ride Service proceeds to the next available driver.
Ride Service Updates:

Once the ride is accepted, the Ride Service updates the ride status to "IN_PROGRESS" and stores the ride details.
The Ride Service also updates the driver’s status to "ON_RIDE".
5. Ride Status Updates
Scenario: Track Ride Progress
Backend Flow (Ride Service):
The Ride Service tracks the progress of the ride. When the ride begins, it is marked as "IN_PROGRESS".
The Driver Service periodically updates the driver’s location and other ride-related information (e.g., estimated arrival time).
The Ride Service sends the current ride status to the frontend so that the user and driver can track progress in real time.
6. Ride Completion and Finalization
Scenario: Complete the Ride
Frontend Flow:

Once the user reaches the drop location, the ride is marked as "COMPLETED" on the frontend.
The user is then asked to rate the driver and pay the fare.
Backend Flow (Ride Service):

The Ride Service updates the ride status to "COMPLETED" and records the final details such as ride duration, distance, and fare.
The Driver Service updates the driver’s status to "AVAILABLE" and is ready to accept new ride requests.
The Ride Service calculates the fare and sends it to the frontend for display.
Final Actions:

The Driver Service is notified that the ride is complete and updates the driver’s status accordingly.
Both the User and Driver can rate the ride, providing valuable feedback for future interactions.
7. Periodic Location Updates and Driver Status
Scenario: Periodic Location Updates for Drivers
Backend Flow (Driver Service):

The Driver Service tracks the driver’s location using GPS or another geolocation service.
The driver’s location is updated periodically (e.g., every 5 minutes).
If the driver is not in an "ON_RIDE" status, their status is updated to "AVAILABLE".
Integration with Ride Service:

The Ride Service uses the updated driver location to find nearby available drivers for the next ride request.
If the driver’s location changes significantly or the status is "UNAVAILABLE", they are removed from the available list.
8. Google Maps Integration
Scenario: Use of Google Maps API for Location and Distance Calculation
Google Maps API is used for:
Converting Addresses: The Ride Service uses the Google Maps API to convert the pickup and drop location addresses into latitude and longitude coordinates.
Distance Calculation: The Ride Service calculates the distance between the pickup location and the driver’s location to determine the closest available drivers.
Driver Location Tracking: The Driver Service uses the Google Maps API to track the driver’s real-time location during the ride.
9. Database Structure and Integration
Scenario: Database Design for User, Driver, and Ride Services
User Service Database:

Tables:
users: Stores user details (ID, name, email, user_type, etc.).
user_roles: Links users to their roles (e.g., Driver, Passenger).
Driver Service Database:

Tables:
drivers: Stores driver details (ID, user_id, vehicle_type, license_number, current_status, current_location, etc.).
ride_history: Stores information about completed rides, including driver ID, user ID, ride status, fare, etc.
Ride Service Database:

Tables:
rides: Stores ride details (ID, user_id, driver_id, pickup_location, drop_location, status, fare, etc.).
ride_status: Tracks the current status of the ride (e.g., "IN_PROGRESS", "COMPLETED").
10. Key APIs for Communication
User Service APIs:

Register User (POST /users/register)
Get User Details (GET /users/{id})
Driver Service APIs:

Register Driver (POST /drivers/register)
Get Available Drivers (GET /drivers/available)
Update Driver Status (POST /drivers/{id}/update-status)
Get Driver Location (GET /drivers/{id}/location)
Ride Service APIs:

Create Ride Request (POST /rides/create)
Get Available Drivers (GET /rides/available-drivers)
Notify Driver (POST /drivers/{id}/notify-ride)
Update Ride Status (POST /rides/{rideId}/update-status)
Conclusion
This document outlines all key user flows, scenarios, and integrations between the User Service, Driver Service, and Ride Service. The Driver Service handles the driver's onboarding, location updates, and availability status, while the Ride Service facilitates the ride request and ride completion flow. All services communicate with each other to ensure smooth interactions between the driver and the user.


 +----------------+           +----------------+           +----------------+
 |    user        |           |    driver      |           |     ride       |
 +----------------+           +----------------+           +----------------+
 | id (PK)        |<--------->| id (PK)        |           | id (PK)        |
 | name           |           | user_id (FK)   |<--------->| user_id (FK)   |
 | email          |           | vehicle_type   |           | driver_id (FK) |
 | phone_number   |           | status         |           | pickup_location|
 | password       |           | location       |           | dropoff_location|
 | role           |           | rating         |           | ride_status    |
 | created_at     |           | created_at     |           | payment_status |
 | updated_at     |           | updated_at     |           | fare           |
 +----------------+           +----------------+           | created_at     |
                                                           | updated_at     |
                                                           +----------------+
