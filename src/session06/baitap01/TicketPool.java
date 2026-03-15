package session06.baitap01;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets;

    public TicketPool(String roomName, int numberOfSeats) {
        this.roomName = roomName;
        tickets = new ArrayList<>();

        for (int i = 1; i <= numberOfSeats; i++) {

            String id = roomName + "-" + String.format("%03d", i);

            tickets.add(new Ticket(id, roomName, false));
        }
    }

    public synchronized Ticket sellTicket() {

        for (Ticket ticket : tickets) {

            if (!ticket.isSold()) {

                ticket.setSold(true);

                return ticket;
            }
        }

        return null;
    }

    public synchronized void addTickets(int count) {

        int start = tickets.size() + 1;

        for (int i = 0; i < count; i++) {

            String id = roomName + "-" + String.format("%03d", start + i);

            tickets.add(new Ticket(id, roomName, false));
        }

        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
    }

    public int remainingTickets() {

        int count = 0;

        for (Ticket ticket : tickets) {

            if (!ticket.isSold()) {
                count++;
            }
        }

        return count;
    }
}