package session08.baitap01;

public class AirConditionerFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        System.out.println("Đã tạo điều hòa mới");
        return new AirConditioner();
    }
}
