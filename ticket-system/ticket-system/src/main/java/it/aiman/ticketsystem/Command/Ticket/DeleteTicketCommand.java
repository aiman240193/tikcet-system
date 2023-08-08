package it.aiman.ticketsystem.Command.Ticket;

import it.aiman.ticketsystem.Dto.TicketDto;
import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.MissingFormatArgumentException;

@Component
public class DeleteTicketCommand {
    String id;
    @Autowired
    TicketService ticketService;

    public DeleteTicketCommand() {
        this.id = null;
    }

    private Boolean doExecute(){
        if(id == null)
            return false;
        if(ticketService.getTicket(id)==null)
            return false;
        return true;
    }

    public Boolean execute(){
        if(doExecute()) {
            ticketService.deleteTicket(ticketService.getTicket(id));
            return true;
        }
        else{
            throw new MissingFormatArgumentException("Missing id");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
