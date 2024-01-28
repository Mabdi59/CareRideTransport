package com.careridetransport.server.service;

import com.careridetransport.server.model.Ride;
import com.careridetransport.server.repository.RideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.careridetransport.server.exception.CustomRideNotFoundException;
import com.careridetransport.server.exception.CustomInvalidOperationException;


import java.util.List;

@Service
public class RideService {

    private static final Logger logger = LoggerFactory.getLogger(RideService.class);

    @Autowired
    private RideRepository rideRepository;

    @Transactional
    public Ride bookRide(Ride ride) {
        // Validate and apply business logic if necessary
        logger.info("Booking a new ride");
        return rideRepository.save(ride);
    }

    @Transactional
    public Ride updateRideStatus(Long rideId, String status) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new CustomRideNotFoundException("Ride not found with ID: " + rideId));

        // Additional validation logic

        ride.setStatus(status);
        logger.info("Updating status of ride {}", rideId);
        return rideRepository.save(ride);
    }

    @Transactional
    public Ride cancelRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new CustomRideNotFoundException("Ride not found with ID: " + rideId));

        if ("completed".equals(ride.getStatus())) {
            throw new CustomInvalidOperationException("Cannot cancel a completed ride");
        }

        ride.setStatus("cancelled");
        logger.info("Cancelling ride {}", rideId);
        return rideRepository.save(ride);
    }

    public List<Ride> getAllRides() {
        logger.info("Fetching all rides");
        return rideRepository.findAll();
    }

    // Define CustomRideNotFoundException, CustomInvalidOperationException etc., as needed

    // Other ride-related methods can be added as needed
}
