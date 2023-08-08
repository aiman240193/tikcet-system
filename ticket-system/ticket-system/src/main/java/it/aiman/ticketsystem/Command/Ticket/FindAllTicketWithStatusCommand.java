package it.aiman.ticketsystem.Command.Ticket;

import it.aiman.ticketsystem.Model.Status;
import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.MissingFormatArgumentException;

@Component
public class FindAllTicketWithStatusCommand {
    private Status status;
    @Autowired
    TicketService ticketService;

    public FindAllTicketWithStatusCommand() {
        this.status = null;
    }

    private Boolean doExecute(){
        if(status == null)
            return false;
        return true;
    }

    public List<Ticket> execute(){
        if(doExecute()) {
            return ticketService.findAllWithStatus(status);
        }
        else{
            throw new MissingFormatArgumentException("Missing status");
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
