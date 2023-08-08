package it.aiman.ticketsystem.Model;

public class WebSocketMessage {
    String ticketTitle;
    String ticketEvent;

    public WebSocketMessage(String ticketTitle,  String ticketEvent) {
        this.ticketTitle = ticketTitle;
        this.ticketEvent = ticketEvent;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }


    public String getTicketEvent() {
        return ticketEvent;
    }

    public void setTicketEvent(String ticketEvent) {
        this.ticketEvent = ticketEvent;
    }
}
