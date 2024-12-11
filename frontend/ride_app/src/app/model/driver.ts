export interface Driver {
    id: number;  // Equivalent to Long in Java
    user: User;  // This can be another interface or type for User
    vehicleType: string;
    status: 'AVAILABLE' | 'ON_RIDE' | 'UNAVAILABLE';  // Enums for predefined statuses
    location: string;
    rating: number;
    createdAt: string;  // LocalDateTime will be a string in ISO format
    updatedAt: string;  // LocalDateTime will be a string in ISO format
  }
  
  // User Interface (assuming structure based on the code in the Java class)
  export interface User {
    id: number;
    name: string;
    email: string;
    phoneNumber: string;
    // Add any other properties of the User class if necessary
  }
  