package session08.baitap01;

public class AirConditioner implements Device{
    @Override
    public void turnOn() {
        System.out.println("Điều hòa làm lạnh");
    }

    @Override
    public void turnOff() {
        System.out.println("Điều hòa tắt");
    }
}
