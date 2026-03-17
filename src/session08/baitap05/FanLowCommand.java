package session08.baitap05;

public class FanLowCommand implements Command {
    private Fan fan;

    public FanLowCommand(Fan fan) {
        this.fan = fan;
    }

    public void execute() {
        System.out.println("Quạt tốc độ thấp");
        fan.setLow();
    }
}