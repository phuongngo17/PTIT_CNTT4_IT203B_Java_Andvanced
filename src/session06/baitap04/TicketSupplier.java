package session06.baitap04;

public class TicketSupplier implements Runnable {

    private TicketPool roomA;
    private TicketPool roomB;
    private int supplyCount;
    private int interval;

    public TicketSupplier(TicketPool roomA,
                          TicketPool roomB,
                          int supplyCount,
                          int interval) {

        this.roomA = roomA;
        this.roomB = roomB;
        this.supplyCount = supplyCount;
        this.interval = interval;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(interval);
            } catch (Exception e) {
                e.printStackTrace();
            }

            roomA.addTickets(supplyCount);
            roomB.addTickets(supplyCount);
        }
    }
}