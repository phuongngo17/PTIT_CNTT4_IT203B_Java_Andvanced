package session08.baitap05;

import java.util.*;

public class TemperatureSensor implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;
    public void attach(Observer o) {
        observers.add(o);
    }
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature);
        }
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Cảm biến: Nhiệt độ = " + temperature);
        notifyObservers();
    }
}
