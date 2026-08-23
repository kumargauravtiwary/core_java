package corejava;
import java.util.ArrayList;
import java.util.List;

/*

FileSystemItem
       |
       +----------------+
       |                |
      File          Directory
                       |
              +--------+--------+
              |        |        |
             File    File    Directory
                              |
                            File
*/
interface FileSystemItem {

    String getName();

    void showDetails();

    long getSize();
}
class File implements FileSystemItem {

    private final String name;
    private final long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name +
                " (" + size + " KB)");
    }
}


class Directory implements FileSystemItem {

    private final String name;

    private final List<FileSystemItem> children =
            new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystemItem item) {
        children.add(item);
    }

    public void remove(FileSystemItem item) {
        children.remove(item);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {

        long totalSize = 0;

        for (FileSystemItem item : children) {
            totalSize += item.getSize();
        }

        return totalSize;
    }

    @Override
    public void showDetails() {

        System.out.println("Directory: " + name);

        for (FileSystemItem item : children) {
            item.showDetails();
        }
    }
}
public class CompositeDemo {

    public static void main(String[] args) {

        // Root directory
        Directory root = new Directory("root");

        // Files inside root
        File readme = new File("README.txt", 10);
        File config = new File("config.properties", 20);

        root.add(readme);
        root.add(config);

        // src directory
        Directory src = new Directory("src");

        File mainJava = new File("Main.java", 50);
        File serviceJava = new File("OrderService.java", 80);

        src.add(mainJava);
        src.add(serviceJava);

        // controllers directory
        Directory controllers =
                new Directory("controllers");

        File orderController =
                new File("OrderController.java", 70);

        controllers.add(orderController);

        // Add controllers inside src
        src.add(controllers);

        // Add src to root
        root.add(src);

        // Display complete tree
        root.showDetails();

        System.out.println();
        System.out.println("Total size = "
                + root.getSize() + " KB");
    }
}

