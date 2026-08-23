package corejava;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyTest {

    String description() default "";
}

class Calculator {

    @MyTest(description = "Test addition")
    public void testAddition() {
        int result = 10 + 20;

        if (result != 30) {
            throw new AssertionError("Addition failed");
        }

        System.out.println("Addition test passed");
    }

    @MyTest(description = "Test subtraction")
    public void testSubtraction() {
        int result = 20 - 10;

        if (result != 10) {
            throw new AssertionError("Subtraction failed");
        }

        System.out.println("Subtraction test passed");
    }

    public void normalMethod() {
        System.out.println("This method is not a test");
    }
}



public class AnnotationProcessor {

    public static void main(String[] args) throws Exception {

        Calculator calculator = new Calculator();

        Class<?> clazz = calculator.getClass();

        for (Method method : clazz.getDeclaredMethods()) {

            if (method.isAnnotationPresent(MyTest.class)) {

                MyTest annotation =
                        method.getAnnotation(MyTest.class);

                System.out.println(
                        "Running: " + method.getName()
                );

                System.out.println(
                        "Description: " + annotation.description()
                );

                try {
                    method.invoke(calculator);

                    System.out.println("Status: PASSED\n");

                } catch (Exception e) {

                    System.out.println("Status: FAILED");
                    System.out.println(
                            "Reason: " + e.getCause()
                    );
                }
            }
        }
    }
}
