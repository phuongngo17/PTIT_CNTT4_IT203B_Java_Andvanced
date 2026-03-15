package session06.baitap05;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int capacity) {

        this.roomName = roomName;

        for (int i = 1; i <= capacity; i++) {

            String id = roomName + "-" + String.format("%03d", i);

            boolean vip = (i <= 2);

            tickets.add(new Ticket(id, vip));
        }
    }

    public synchronized Ticket holdTicket(boolean vip) {

        for (Ticket t : tickets) {

            if (!t.isSold() && !t.isHeld()) {

                if (!vip || t.isVIP()) {

                    t.setHeld(true);
                    t.setHoldExpiryTime(System.currentTimeMillis() + 5000);

                    System.out.println(Thread.currentThread().getName()
                            + " giữ vé " + t.getTicketId());

                    return t;
                }
            }
        }

        return null;
    }

    public synchronized void sellHeldTicket(Ticket t) {

        if (t != null && t.isHeld()) {

            t.setHeld(false);
            t.setSold(true);

            System.out.println(Thread.currentThread().getName()
                    + " thanh toán vé " + t.getTicketId());
        }
    }

    public synchronized void releaseExpiredTickets() {

        long now = System.currentTimeMillis();

        for (Ticket t : tickets) {

            if (t.isHeld() && !t.isSold()
                    && t.getHoldExpiryTime() < now) {

                t.setHeld(false);

                System.out.println("TimeoutManager: Vé "
                        + t.getTicketId()
                        + " hết hạn giữ, trả lại kho");
            }
        }
    }
}