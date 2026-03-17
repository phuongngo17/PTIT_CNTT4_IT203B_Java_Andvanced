package session08.baitap01;

public class Fan implements Device {
    @Override
    public void turnOn() {
        System.out.println("Quạt quay");
    }

    @Override
    public void turnOff() {
        System.out.println("quạt tắt");
    }
}
