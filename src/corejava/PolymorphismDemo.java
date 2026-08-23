package corejava;

public class PolymorphismDemo {

    public static void main(String[] args) {

        // =====================================================
        // 1. STATIC POLYMORPHISM
        //    Method Overloading
        // =====================================================

        Calculator calculator = new Calculator();

        System.out.println("Static Polymorphism:");

        System.out.println(calculator.add(10, 20));
        System.out.println(calculator.add(10, 20, 30));
        System.out.println(calculator.add(10.5, 20.5));


        // =====================================================
        // 2. DYNAMIC POLYMORPHISM
        //    Method Overriding
        // =====================================================

        System.out.println("\nDynamic Polymorphism:");

        Animal animal;

        animal = new Dog();
        animal.sound();

        animal = new Cat();
        animal.sound();

        animal = new Cow();
        animal.sound();
    }
}


// =============================================================
// STATIC POLYMORPHISM
// Method Overloading
// =============================================================

class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }
}


// =============================================================
// DYNAMIC POLYMORPHISM
// Method Overriding
// =============================================================

class Animal {

    void sound() {
        System.out.println("Animal makes a sound");
    }
}


class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}


class Cat extends Animal {

    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}


class Cow extends Animal {

    @Override
    void sound() {
        System.out.println("Cow moos");
    }
}
