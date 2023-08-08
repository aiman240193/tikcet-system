package it.aiman.ticketsystem.Command.Ticket;

import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllTicketCommand {

    @Autowired
    TicketService ticketService;

    private Boolean doExecute(){
        return true;
    }

    public List<Ticket> execute(){
        if(doExecute()) {
            return ticketService.findAll();
        }
        else return null;
    }

}
