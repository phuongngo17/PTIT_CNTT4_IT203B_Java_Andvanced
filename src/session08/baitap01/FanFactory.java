package session08.baitap01;

public class FanFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        System.out.println("Đã tạo quạt mới");
        return new Fan()    ;
    }
}
