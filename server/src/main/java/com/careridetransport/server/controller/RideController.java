package com.careridetransport.server.controller;

import com.careridetransport.server.model.Ride;
import com.careridetransport.server.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/book")
    public ResponseEntity<Ride> bookRide(@RequestBody Ride ride) {
        Ride bookedRide = rideService.bookRide(ride);
        return new ResponseEntity<>(bookedRide, HttpStatus.CREATED);
    }

    @PutMapping("/{rideId}/update")
    public ResponseEntity<Ride> updateRideStatus(@PathVariable Long rideId, @RequestBody String status) {
        Ride updatedRide = rideService.updateRideStatus(rideId, status);
        if (updatedRide != null) {
            return ResponseEntity.ok(updatedRide);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{rideId}/cancel")
    public ResponseEntity<Ride> cancelRide(@PathVariable Long rideId) {
        Ride canceledRide = rideService.cancelRide(rideId);
        if (canceledRide != null) {
            return ResponseEntity.ok(canceledRide);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Ride>> getAllRides() {
        List<Ride> rides = rideService.getAllRides();
        return ResponseEntity.ok(rides);
    }
}
