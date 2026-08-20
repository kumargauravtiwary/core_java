package design_patterns;

abstract class Handler {

    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handle(int amount);
}
class Manager1 extends Handler {

    public void handle(int amount) {

        if (amount <= 10000) {
            System.out.println("Manager approved");
        } else if (next != null) {
            next.handle(amount);
        }
    }
}

class Director extends Handler {

    public void handle(int amount) {

        if (amount <= 100000) {
            System.out.println("Director approved");
        } else if (next != null) {
            next.handle(amount);
        }
    }
}

public class ChainofResponsibility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager1 manager = new Manager1();
		Director director = new Director();

		manager.setNext(director);

		manager.handle(50000);

	}
}