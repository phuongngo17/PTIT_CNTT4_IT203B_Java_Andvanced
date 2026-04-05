package org.example.bth;

import org.springframework.stereotype.Component;

@Component
public class NormalNotify implements Notification {
    @Override
    public void sendNotify(String username, String message) {
        System.out.println("[ Volume 🔊 ] " + username + " | " + message);
    }

}
