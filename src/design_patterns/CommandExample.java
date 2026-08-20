package design_patterns;

interface Command {
    void execute();
}

class Light {

    public void on() {
        System.out.println("Light ON");
    }

    public void off() {
        System.out.println("Light OFF");
    }
}
class LightOnCommand implements Command {

    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }
}
class RemoteControl {

    public void submit(Command command) {
        command.execute();
    }
}

public class CommandExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Light light = new Light();

		Command command =
		        new LightOnCommand(light);

		RemoteControl remote = new RemoteControl();

		remote.submit(command);

	}

}
