package com.careridetransport.server.repository;

import com.careridetransport.server.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
    // You can add custom methods if needed
}
