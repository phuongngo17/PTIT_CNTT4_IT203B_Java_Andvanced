package session08.baitap05;

public class AirConditioner implements Observer {
    private int temperature = 25;
    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    @Override
    public void update(int temp) {
    }

    public void showStatus() {
        System.out.println("Điều hòa đang ở: " + temperature);
    }
}