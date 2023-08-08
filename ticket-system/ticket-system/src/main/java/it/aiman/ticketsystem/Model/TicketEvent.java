package it.aiman.ticketsystem.Model;

public class TicketEvent {
    private String ticketId;
    private String event;

    public TicketEvent(String ticketId, String event) {
        this.ticketId = ticketId;
        this.event = event;
    }

    public TicketEvent() {
    }


    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
