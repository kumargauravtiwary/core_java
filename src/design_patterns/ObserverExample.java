package design_patterns;

import java.util.ArrayList;
import java.util.List;

//When one object changes, multiple objects are notified.

interface Observer {
    void update(String message);
}

class EmailSubscriber implements Observer {

    public void update(String message) {
        System.out.println("Email: " + message);
    }
}

class SMSSubscriber implements Observer {

    public void update(String message) {
        System.out.println("SMS: " + message);
    }
}

class NotificationService {

    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {

        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

public class ObserverExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NotificationService service =
		        new NotificationService();

		service.subscribe(new EmailSubscriber());
		service.subscribe(new SMSSubscriber());

		service.notifyObservers("Order shipped");

	}

}
