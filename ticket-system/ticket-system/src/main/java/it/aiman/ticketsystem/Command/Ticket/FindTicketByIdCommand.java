package it.aiman.ticketsystem.Command.Ticket;

import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.MissingFormatArgumentException;

@Component
public class FindTicketByIdCommand {
    String id;

    @Autowired
    TicketService ticketService;

    public FindTicketByIdCommand() {
        this.id = null;
    }

    private Boolean doExecute(){
        if(id == null)
            return false;
        return true;
    }

    public Ticket execute(){
        if(doExecute()) {
            return ticketService.getTicket(id);
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
