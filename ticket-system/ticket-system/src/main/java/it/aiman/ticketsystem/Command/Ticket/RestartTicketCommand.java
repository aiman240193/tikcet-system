package it.aiman.ticketsystem.Command.Ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.aiman.ticketsystem.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.MissingFormatArgumentException;

@Component
public class RestartTicketCommand {
    String ticketId;
    @Autowired
    TicketService ticketService;

    private Boolean doExecute(){
        return true;
    }

    public Boolean execute() throws JsonProcessingException {
        if(doExecute()) {
            ticketService.restartTicket(ticketId);
            return true;
        }
        else{
            throw new MissingFormatArgumentException("Missing title or title already exists");
        }
    }
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

}
