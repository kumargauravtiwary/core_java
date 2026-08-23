package corejava;

//Parent initialization → Parent initializer block → Parent constructor → Child initialization → Child initializer block → Child constructor
public class InitializerVsConstructorDemo {

    public static void main(String[] args) {

        System.out.println("Creating Child object...\n");

        Child child = new Child();

        System.out.println("\nCreating another Child object...\n");

        Child child2 = new Child();
    }
}


// =====================================================
// Parent Class
// =====================================================

class Parent {

    // Instance variable initialization
    private int parentValue = initializeParentValue();

    // Instance initializer block
    {
        System.out.println("Parent: Instance initializer block");
    }

    // Constructor
    Parent() {
        System.out.println("Parent: Constructor");
    }

    private int initializeParentValue() {
        System.out.println("Parent: Instance variable initialization");
        return 10;
    }
}


// =====================================================
// Child Class
// =====================================================

class Child extends Parent {

    // Instance variable initialization
    private int childValue = initializeChildValue();

    // Instance initializer block
    {
        System.out.println("Child: Instance initializer block");
    }

    // Constructor
    Child() {
        System.out.println("Child: Constructor");
    }

    private int initializeChildValue() {
        System.out.println("Child: Instance variable initialization");
        return 20;
    }
}
