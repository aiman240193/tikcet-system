package it.aiman.ticketsystem.Command.Ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.aiman.ticketsystem.Dto.TicketDto;
import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.MissingFormatArgumentException;

@Component
public class AssignTicketCommand {



    String ticketId;
    String userId;
    @Autowired
    TicketService ticketService;

    private Boolean doExecute(){
        return true;
    }

    public Boolean execute() throws JsonProcessingException {
        if(doExecute()) {
            ticketService.assignTicketTo(ticketId, userId);
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
