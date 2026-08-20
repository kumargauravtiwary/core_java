package design_patterns;

//Separates abstraction from implementation.

interface Device {
    void turnOn();
    void turnOff();
}

class TV implements Device {

    public void turnOn() {
        System.out.println("TV ON");
    }

    public void turnOff() {
        System.out.println("TV OFF");
    }
}

class Remote {

    protected Device device;

    public Remote(Device device) {
        this.device = device;
    }

    public void on() {
        device.turnOn();
    }

    public void off() {
        device.turnOff();
    }
}

public class Bridge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Device tv = new TV();

		Remote remote = new Remote(tv);

		remote.on();
		remote.off();

	}

}
