package session06.baitap03;

import session06.baitap01.Ticket;
import session06.baitap01.TicketPool;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private boolean reverseOrder;

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB, boolean reverseOrder) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
        this.reverseOrder = reverseOrder;
    }

    @Override
    public void run() {

        while (true) {
            sellComboDeadlock();
        }
    }

    public void sellComboDeadlock() {

        if (!reverseOrder) {

            synchronized (roomA) {

                System.out.println(counterName + " đã lấy khóa phòng A");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (roomB) {

                    System.out.println(counterName + " đã lấy khóa phòng B");

                    sell();
                }
            }

        } else {

            synchronized (roomB) {

                System.out.println(counterName + " đã lấy khóa phòng B");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (roomA) {

                    System.out.println(counterName + " đã lấy khóa phòng A");

                    sell();
                }
            }
        }
    }

    private void sell() {

        Ticket a = roomA.sellTicket();
        Ticket b = roomB.sellTicket();

        if (a != null && b != null) {

            System.out.println(counterName +
                    " bán combo thành công: "
                    + a.getTicketId() + " & " + b.getTicketId());

        } else {

            if (a != null) a.setSold(false);
            if (b != null) b.setSold(false);

            System.out.println(counterName + " bán combo thất bại");
        }
    }
}