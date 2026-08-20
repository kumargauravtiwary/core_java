package design_patterns;

//Controls access to another object.

interface Image {
    void display();
}

class RealImage implements Image {

    private final String file;

    public RealImage(String file) {
        this.file = file;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + file);
    }

    public void display() {
        System.out.println("Displaying " + file);
    }
}

class ImageProxy implements Image {

    private RealImage realImage;
    private final String file;

    public ImageProxy(String file) {
        this.file = file;
    }

    public void display() {

        if (realImage == null) {
            realImage = new RealImage(file);
        }

        realImage.display();
    }
}

public class Proxy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Image image = new ImageProxy("photo.jpg");

		image.display();
		image.display();

	}

}
