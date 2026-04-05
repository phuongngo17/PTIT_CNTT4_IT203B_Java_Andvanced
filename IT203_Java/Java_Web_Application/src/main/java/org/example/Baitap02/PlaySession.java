package org.example.Baitap02;

import org.springframework.stereotype.Component;

@Component
public class PlaySession {

    private double playTime = 0;

    public void addTime(double time) {
        this.playTime += time;
    }
}
//Spring tạo duy nhất 1 instance PlaySession (Singleton)
//Tất cả máy trạm cùng dùng chung instance này
//Biến playTime bị chia sẻ → dẫn đến cộng dồn sai