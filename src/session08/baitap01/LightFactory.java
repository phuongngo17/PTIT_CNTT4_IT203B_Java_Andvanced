package session08.baitap01;

public class LightFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        System.out.println("Đã tạo đèn mới");
        return new Light();
    }
}
