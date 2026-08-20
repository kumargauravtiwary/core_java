package design_patterns;

//Adds functionality dynamically without modifying the original class.

interface Coffee {
    double cost();
}

class SimpleCoffee implements Coffee {

    public double cost() {
        return 100;
    }
}

class MilkDecorator implements Coffee {

    private final Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double cost() {
        return coffee.cost() + 30;
    }
}

class SugarDecorator implements Coffee {

    private final Coffee coffee;

    public SugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double cost() {
        return coffee.cost() + 10;
    }
}

public class decorator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coffee coffee = new SimpleCoffee();

		coffee = new MilkDecorator(coffee);
		coffee = new SugarDecorator(coffee);

		System.out.println(coffee.cost());

	}

}
