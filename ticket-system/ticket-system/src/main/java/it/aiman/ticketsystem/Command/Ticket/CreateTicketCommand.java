package it.aiman.ticketsystem.Command.Ticket;

import it.aiman.ticketsystem.Dto.TicketDto;
import it.aiman.ticketsystem.Model.Status;
import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.MissingFormatArgumentException;

@Component
public class CreateTicketCommand {

    TicketDto ticketDto;
    @Autowired
    TicketService ticketService;

    private Boolean doExecute(){
        if(ticketDto.getTitle() == null) {
            return false;
        }
        if(ticketService.findAllWithTitle(ticketDto.getTitle()) != null)
            return false;
        else return true;
    }

    public Boolean execute(){
        if(doExecute()) {
            ticketService.createTicket(new Ticket(ticketDto.getTitle(), ticketDto.getDescription(), Status.TO_BE_ASSIGNED,ticketDto.getCreatedBy(), ticketDto.getAssignedTo() ));
            return true;
        }
        else{
            throw new MissingFormatArgumentException("Missing title or title already exists");
        }
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }

    public void setTicketDto(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }
}
