package design_patterns;

//Defines the skeleton of an algorithm while allowing subclasses to customize steps.

abstract class DataProcessor {

    public final void process() {
        readData();
        processData();
        saveData();
    }

    protected abstract void readData();

    protected abstract void processData();

    protected void saveData() {
        System.out.println("Saving data");
    }
}

class CSVProcessor extends DataProcessor {

    protected void readData() {
        System.out.println("Reading CSV");
    }

    protected void processData() {
        System.out.println("Processing CSV");
    }
}

public class Template {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataProcessor processor = new CSVProcessor();

		processor.process();

	}

}
