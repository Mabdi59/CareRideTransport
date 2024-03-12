DROP TABLE IF EXISTS operational_logs CASCADE;
DROP TABLE IF EXISTS user_accessibility_preferences CASCADE;
DROP TABLE IF EXISTS ride_assignments CASCADE;
DROP TABLE IF EXISTS employee_schedules CASCADE;
DROP TABLE IF EXISTS vehicles CASCADE;
DROP TABLE IF EXISTS notifications CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;
DROP TABLE IF EXISTS payments CASCADE;
DROP TABLE IF EXISTS bookings CASCADE;
DROP TABLE IF EXISTS pricing CASCADE;
DROP TABLE IF EXISTS services CASCADE;
DROP TABLE IF EXISTS employees CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Users Table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role VARCHAR(50) NOT NULL, -- 'customer', 'employee'
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Employees Table
CREATE TABLE employees (
    employee_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    employee_role VARCHAR(50), -- 'driver', 'admin'
    available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Services Table
CREATE TABLE services (
    service_id SERIAL PRIMARY KEY,
    service_name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Bookings Table
CREATE TABLE bookings (
    booking_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    service_id INT NOT NULL,
    pickup_location TEXT NOT NULL,
    dropoff_location TEXT NOT NULL,
    pickup_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status VARCHAR(50), -- 'scheduled', 'completed', 'cancelled'
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE RESTRICT
);

-- Pricing Table
CREATE TABLE pricing (
    pricing_id SERIAL PRIMARY KEY,
    service_id INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE RESTRICT
);

-- Payments Table
CREATE TABLE payments (
    payment_id SERIAL PRIMARY KEY,
    booking_id INT NOT NULL,
    amount_paid DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50), -- 'credit_card', 'paypal'
    payment_status VARCHAR(50), -- 'completed', 'pending', 'failed'
    paid_at TIMESTAMP WITHOUT TIME ZONE,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE
);

-- Reviews Table
CREATE TABLE reviews (
    review_id SERIAL PRIMARY KEY,
    booking_id INT NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE
);

-- Notifications Table
CREATE TABLE notifications (
    notification_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    notification_type VARCHAR(50), -- 'booking_confirmation', 'payment_receipt'
    status VARCHAR(50), -- 'sent', 'pending'
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Vehicles Table
CREATE TABLE vehicles (
    vehicle_id SERIAL PRIMARY KEY,
    registration_number VARCHAR(255) UNIQUE NOT NULL,
    model VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    available BOOLEAN DEFAULT TRUE,
    last_maintenance_date DATE
);

-- Employee Schedules Table
CREATE TABLE employee_schedules (
    schedule_id SERIAL PRIMARY KEY,
    employee_id INT NOT NULL,
    start_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE
);

-- Ride Assignments Table
CREATE TABLE ride_assignments (
    assignment_id SERIAL PRIMARY KEY,
    booking_id INT NOT NULL,
    employee_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    assigned_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE RESTRICT
);

-- User Accessibility Preferences Table
CREATE TABLE user_accessibility_preferences (
    preference_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    requires_wheelchair_access BOOLEAN DEFAULT FALSE,
    other_requirements TEXT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Operational Logs Table
CREATE TABLE operational_logs (
    log_id SERIAL PRIMARY KEY,
    log_type VARCHAR(255) NOT NULL,
    description TEXT,
    occurred_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

COMMIT;
