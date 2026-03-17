package session08.baitap05;

public interface Subject {
    void attach(Observer o);
    void notifyObservers();
}
