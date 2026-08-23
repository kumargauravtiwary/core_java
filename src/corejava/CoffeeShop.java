package corejava;

/*
                  <<interface>>
                     Coffee
                  /          \
                 /            \
        Espresso              CoffeeDecorator
        Cappuccino                  |
        Latte                       |
                             +------+-------+
                             |      |       |
                           Milk   Sugar   Caramel
                                    |
                              WhippedCream
WhippedCream
     |
   Caramel
     |
    Milk
     |
   Latte
                              */
interface Coffee {

    String getDescription();

    double getCost();
}
class Espresso implements Coffee {

    @Override
    public String getDescription() {
        return "Espresso";
    }

    @Override
    public double getCost() {
        return 100.0;
    }
}
class Cappuccino implements Coffee {

    @Override
    public String getDescription() {
        return "Cappuccino";
    }

    @Override
    public double getCost() {
        return 150.0;
    }
}
class Latte implements Coffee {

    @Override
    public String getDescription() {
        return "Latte";
    }

    @Override
    public double getCost() {
        return 130.0;
    }
}
abstract class CoffeeDecorator implements Coffee {

    protected final Coffee coffee;

    protected CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}
class Milk extends CoffeeDecorator {

    public Milk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 30.0;
    }
}
class Sugar extends CoffeeDecorator {

    public Sugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10.0;
    }
}
class WhippedCream extends CoffeeDecorator {

    public WhippedCream(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Whipped Cream";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 40.0;
    }
}
class Caramel extends CoffeeDecorator {

    public Caramel(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Caramel";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 50.0;
    }
}
public class CoffeeShop {

    public static void main(String[] args) {

        // Basic coffee
        Coffee coffee = new Espresso();

        System.out.println(coffee.getDescription());
        System.out.println("Price: ₹" + coffee.getCost());

        System.out.println("-------------------");

        // Espresso + Milk
        Coffee coffeeWithMilk =
                new Milk(new Espresso());

        System.out.println(coffeeWithMilk.getDescription());
        System.out.println("Price: ₹" + coffeeWithMilk.getCost());

        System.out.println("-------------------");

        // Espresso + Milk + Sugar
        Coffee customizedCoffee =
                new Sugar(
                    new Milk(
                        new Espresso()
                    )
                );

        System.out.println(customizedCoffee.getDescription());
        System.out.println("Price: ₹" + customizedCoffee.getCost());

        System.out.println("-------------------");

        // Latte + Milk + Caramel + Whipped Cream
        Coffee premiumCoffee =
                new WhippedCream(
                    new Caramel(
                        new Milk(
                            new Latte()
                        )
                    )
                );

        System.out.println(premiumCoffee.getDescription());
        System.out.println("Price: ₹" + premiumCoffee.getCost());
    }
}

