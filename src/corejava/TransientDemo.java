package corejava;

import java.io.*;

class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private transient String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

public class TransientDemo {

    public static void main(String[] args) throws Exception {

        User user = new User("gaurav", "secret123");

        // Serialize
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("user.ser"))) {

            out.writeObject(user);
        }

        // Deserialize
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("user.ser"))) {

            User restoredUser = (User) in.readObject();

            System.out.println(restoredUser);
        }
    }
}
