package org.example.bth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class AccountManagement {
    private Map<String,Double> person = new HashMap<>();

    //DI- Field
    @Autowired
    private NormalNotify normalNotify;

    public AccountManagement(Map<String, Double> person) {
        this.person = person;
    }
    public void checkYourComputer(String username,String area){
        //ktra username trống hoặc null
        if(username == null || username.isEmpty()){
            System.out.println("[ERROR] Lỗi rồi không được để trống");
            return;
        }
        //ktra username có tồn tại hay k
        if(!person.containsKey(username)){
            System.out.println("[ERROR] k tồn tại người dùng");
            return;
        }
        //ktra tiền
        //thực hiện lấy tiền
        Double balance = person.get(username);
        if (balance < 0){
            System.out.println("[ERROR] k đủ tiền để chơi game");
            return;
        }
        //ktra < 5000 thì thông báo
        if(balance <5000){
            if(area.equals("VIP")){
                //khu vực vip thì cần yên lặng
                vipNotify.sendNotify(username,"Tài khoản sắp hết tiền");
            }else{
                //khu vực thường thì cần thông báo âm thanh
                normalNotify.sendNotify(username,"Tài khoản sắp hết tiền");
            }
        }
    }
}
