package session08.baitap05;

public class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        System.out.println("Tắt đèn");
        light.off();
    }
}