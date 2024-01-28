package com.careridetransport.server.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String pickupLocation;
    private String dropoffLocation;
    private LocalDateTime scheduledTime;
    private String status; // e.g., requested, in_progress, completed, cancelled

    // Constructors
    public Ride(Long rideId, User user, String pickupLocation, String dropoffLocation, LocalDateTime scheduledTime, String status) {
        this.rideId = rideId;
        this.user = user;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.scheduledTime = scheduledTime;
        this.status = status;
    }

    // Getters and Setters
    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
