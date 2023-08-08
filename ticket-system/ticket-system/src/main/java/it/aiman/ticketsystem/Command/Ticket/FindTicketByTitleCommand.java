package it.aiman.ticketsystem.Command.Ticket;

import it.aiman.ticketsystem.Model.Status;
import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.MissingFormatArgumentException;

@Component
public class FindTicketByTitleCommand {
    String title;

    @Autowired
    TicketService ticketService;

    public FindTicketByTitleCommand() {
        this.title = null;
    }

    private Boolean doExecute(){
        if(title == null)
            return false;
        return true;
    }

    public Ticket execute(){
        if(doExecute()) {
            return ticketService.findAllWithTitle(title);
        }
        else{
            throw new MissingFormatArgumentException("Missing status");
        }
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
