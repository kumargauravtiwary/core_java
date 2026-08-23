package corejava;

/*
                 ┌──────────────┐
                 │  Startable   │
                 └──────┬───────┘
                        │
          ┌─────────────┼─────────────┐
          │             │             │
      PetrolCar    ElectricCar    ElectricBike
          │             │             │
          │             │             │
    ┌─────┴─────┐ ┌─────┴─────┐      │
    │           │ │           │      │
Refuelable  CargoCarrier  Chargeable │
    │           │         │          │
    │           │         │          │
    └───────────┴─────────┴──────────┘

             MusicSystem
                  │
           ┌──────┴──────┐
        PetrolCar   ElectricCar

*/        

interface MusicSystem {

    void playMusic();

    void stopMusic();
}

interface CargoCarrier {

    void loadCargo(double weight);
}

interface Startable {

    void start();

    void stop();
}

interface Refuelable {

    void refuel(double liters);
}

interface Chargeable {

    void chargeBattery();
}

class FuelStation {

    public void refuel(Refuelable vehicle) {

        vehicle.refuel(20);
    }
}
class ChargingStation {

    public void charge(Chargeable vehicle) {

        System.out.println("Starting charging...");

        vehicle.chargeBattery();

        System.out.println("Charging completed");
    }
}

class PetrolCar
        implements Startable,
                   Refuelable,
                   CargoCarrier,
                   MusicSystem {

    @Override
    public void start() {
        System.out.println("Petrol car started");
    }

    @Override
    public void stop() {
        System.out.println("Petrol car stopped");
    }

    @Override
    public void refuel(double liters) {
        System.out.println(
                "Refueled with " + liters + " liters");
    }

    @Override
    public void loadCargo(double weight) {
        System.out.println(
                "Loaded " + weight + " kg cargo");
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music");
    }

    @Override
    public void stopMusic() {
        System.out.println("Music stopped");
    }
}

class ElectricCar
        implements Startable,
                   Chargeable,
                   CargoCarrier,
                   MusicSystem {

    @Override
    public void start() {
        System.out.println("Electric car started");
    }

    @Override
    public void stop() {
        System.out.println("Electric car stopped");
    }

    @Override
    public void chargeBattery() {
        System.out.println("Battery charging");
    }

    @Override
    public void loadCargo(double weight) {
        System.out.println(
                "Loaded " + weight + " kg cargo");
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music");
    }

    @Override
    public void stopMusic() {
        System.out.println("Music stopped");
    }
}

class ElectricBike
        implements Startable,
                   Chargeable {

    @Override
    public void start() {
        System.out.println("Electric bike started");
    }

    @Override
    public void stop() {
        System.out.println("Electric bike stopped");
    }

    @Override
    public void chargeBattery() {
        System.out.println("Bike battery charging");
    }
}

class Truck
        implements Startable,
                   Refuelable,
                   CargoCarrier {

    @Override
    public void start() {
        System.out.println("Truck started");
    }

    @Override
    public void stop() {
        System.out.println("Truck stopped");
    }

    @Override
    public void refuel(double liters) {
        System.out.println(
                "Truck refueled: " + liters + " liters");
    }

    @Override
    public void loadCargo(double weight) {
        System.out.println(
                "Cargo loaded: " + weight + " kg");
    }
}
public class Main {

    public static void main(String[] args) {

        PetrolCar petrolCar = new PetrolCar();

        ElectricCar electricCar = new ElectricCar();

        ElectricBike electricBike =
                new ElectricBike();

        Truck truck = new Truck();

        // Start vehicles
        petrolCar.start();
        electricCar.start();
        electricBike.start();
        truck.start();

        // Fuel station
        FuelStation fuelStation =
                new FuelStation();

        fuelStation.refuel(petrolCar);
        fuelStation.refuel(truck);

        // Charging station
        ChargingStation chargingStation =
                new ChargingStation();

        chargingStation.charge(electricCar);
        chargingStation.charge(electricBike);
    }
}
