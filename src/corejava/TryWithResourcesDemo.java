package corejava;

public class TryWithResourcesDemo {

    public static void main(String[] args) {

        System.out.println("Program started");

        try (MyResource resource = new MyResource("Database Connection")) {

            resource.use();

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("Program finished");
    }
}


// Custom resource
class MyResource implements AutoCloseable {

    private final String name;

    public MyResource(String name) {
        this.name = name;
        System.out.println(name + " opened");
    }

    public void use() {
        System.out.println(name + " is being used");
    }

    @Override
    public void close() {
        System.out.println(name + " closed");
    }
}
