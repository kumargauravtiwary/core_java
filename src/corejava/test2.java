package corejava;

public class test2 {

	public static void main(String[] args) {
		
		String original = "Java";
        System.out.println("Original hash: " + System.identityHashCode(original));

        // Any "modification" creates a new object
        String modified = original.concat(" Programming");
        System.out.println("Modified: " + modified);
        System.out.println("Modified hash: " + System.identityHashCode(modified));

        // The original string remains unchanged
        System.out.println("Original after concat: " + original);
        System.out.println("Original hash still: " + System.identityHashCode(original));

        // Using toUpperCase() also returns a new instance
        String upper = original.toUpperCase();
        System.out.println("Upper: " + upper);
        System.out.println("Upper hash: " + System.identityHashCode(upper));

	}

}
