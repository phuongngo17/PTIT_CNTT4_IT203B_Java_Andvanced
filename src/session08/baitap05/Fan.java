package session08.baitap05;

public class Fan implements Observer {

    private String speed = "OFF";

    public void setLow() {
        speed = "LOW";
        System.out.println("Quạt đang chạy tốc độ thấp");
    }

    public void setHigh() {
        speed = "HIGH";
        System.out.println("Quạt đang chạy tốc độ mạnh");
    }

    @Override
    public void update(int temp) {
        if (temp > 30) {
            setHigh();
        }
    }

    public void showStatus() {
        System.out.println("Quạt đang ở: " + speed);
    }
}
