package ride_service.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ride_service.model.Ride;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findByUserId(Long userId);

    List<Ride> findByStatus(String status);

    List<Ride> findByDriverId(Long driverId);

}
