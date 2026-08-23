package corejava;

import java.time.LocalDateTime;
/*
1. Vehicle
   ├── Car
   ├── Bike
   └── Truck

2. Customer

3. Rental

4. PricingStrategy
   ├── CarPricingStrategy
   ├── BikePricingStrategy
   └── DynamicPricingStrategy

5. PaymentService
   ├── CreditCardPaymentService
   ├── UpiPaymentService
   └── WalletPaymentService

6. AvailabilityService

7. VehicleRepository

8. RentalService
*/
interface PricingStrategy {

    double calculatePrice(
            Vehicle vehicle,
            long rentalHours);
}

interface PaymentService {

    void pay(double amount);
}

interface VehicleRepository {

    Vehicle findByRegistrationNumber(String registrationNumber);

    void save(Vehicle vehicle);

    void delete(String registrationNumber);
}
interface AvailabilityService {

    boolean isAvailable(
            Vehicle vehicle,
            LocalDateTime start,
            LocalDateTime end);
}

class VehicleAvailabilityService
        implements AvailabilityService {

    @Override
    public boolean isAvailable(
            Vehicle vehicle,
            LocalDateTime start,
            LocalDateTime end) {

        // Check existing reservations

        return true;
    }
}

class DatabaseVehicleRepository
        implements VehicleRepository {

    @Override
    public Vehicle findByRegistrationNumber(
            String registrationNumber) {

        // Database lookup
        return null;
    }

    @Override
    public void save(Vehicle vehicle) {
        // INSERT
    }

    @Override
    public void delete(String registrationNumber) {
        // DELETE
    }
}

class CreditCardPaymentService
        implements PaymentService {

    @Override
    public void pay(double amount) {
        System.out.println(
                "Paid ₹" + amount + " using credit card");
    }
}

class UpiPaymentService
        implements PaymentService {

    @Override
    public void pay(double amount) {
        System.out.println(
                "Paid ₹" + amount + " using UPI");
    }
}
class WalletPaymentService
        implements PaymentService {
    // ...
      public void pay(double amount) {
        System.out.println(
                "Paid ₹" + amount + " using Wallet");
    }
}
class CarPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(
            Vehicle vehicle,
            long rentalHours) {

        return rentalHours * 500;
    }
}

class BikePricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(
            Vehicle vehicle,
            long rentalHours) {

        return rentalHours * 200;
    }
}

class DynamicPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(
            Vehicle vehicle,
            long rentalHours) {

        double basePrice = rentalHours * 500;

        // Example demand multiplier
        return basePrice * 1.25;
    }
}
public class Vehicle {

    private final String registrationNumber;
    private final String brand;
    private final String model;
    private final VehicleType type;

    public Vehicle(
            String registrationNumber,
            String brand,
            String model) {

        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.type = null;
    }

    public Vehicle(
            String registrationNumber,
            String model,
            VehicleType type) {

        this.registrationNumber = registrationNumber;
        this.brand = null;
        this.model = model;
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }
}

class Car extends Vehicle {

    private final int seats;

    public Car(
            String registrationNumber,
            String brand,
            String model,
            int seats) {

        super(registrationNumber, brand, model);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }
}
class Bike extends Vehicle {

    private final int engineCapacity;

    public Bike(
            String registrationNumber,
            String brand,
            String model,
            int engineCapacity) {

        super(registrationNumber, brand, model);
        this.engineCapacity = engineCapacity;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }
}
class Truck extends Vehicle {

    private final double loadCapacity;

    public Truck(
            String registrationNumber,
            String brand,
            String model,
            double loadCapacity) {

        super(registrationNumber, brand, model);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }
}
enum VehicleType {
    CAR,
    BIKE,
    TRUCK,
    SUV,
    LUXURY_CAR
}


class Rental {

    private final String rentalId;
    private final Customer customer;
    private final Vehicle vehicle;

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Rental(
            String rentalId,
            Customer customer,
            Vehicle vehicle,
            LocalDateTime startTime,
            LocalDateTime endTime) {

        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}

class Customer {

    private final String customerId;
    private final String name;
    private final String drivingLicenseNumber;

    public Customer(
            String customerId,
            String name,
            String drivingLicenseNumber) {

        this.customerId = customerId;
        this.name = name;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getCustomerId() {
        return customerId;
    }
}

class RentalService {

    private final AvailabilityService availabilityService;
    private final PricingStrategy pricingStrategy;
    private final PaymentService paymentService;

    public RentalService(
            AvailabilityService availabilityService,
            PricingStrategy pricingStrategy,
            PaymentService paymentService) {

        this.availabilityService = availabilityService;
        this.pricingStrategy = pricingStrategy;
        this.paymentService = paymentService;
    }

    public Rental rent(
            Customer customer,
            Vehicle vehicle,
            LocalDateTime start,
            LocalDateTime end) {

        if (!availabilityService.isAvailable(
                vehicle, start, end)) {

            throw new IllegalStateException(
                    "Vehicle is not available");
        }

        long hours =
                java.time.Duration.between(start, end)
                        .toHours();

        double price =
                pricingStrategy.calculatePrice(
                        vehicle, hours);

        paymentService.pay(price);

        return new Rental(
                java.util.UUID.randomUUID().toString(),
                customer,
                vehicle,
                start,
                end);
    }
}