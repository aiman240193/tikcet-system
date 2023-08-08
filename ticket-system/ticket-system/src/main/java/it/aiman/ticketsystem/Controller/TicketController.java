package it.aiman.ticketsystem.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.aiman.ticketsystem.Command.Ticket.*;
import it.aiman.ticketsystem.Dto.TicketDto;
import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Model.User;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    FindTicketByTitleCommand findTicketByTitleCommand;
    @Autowired
    FindTicketByIdCommand findTicketByIdCommand;
    @Autowired
    FindAllTicketCommand findAllTicketCommand;
    @Autowired
    CreateTicketCommand createTicketCommand;
    @Autowired
    DeleteTicketCommand deleteTicketCommand;
    @Autowired
    AssignTicketCommand assignTicketCommand;
    @Autowired
    AcceptTicketCommand acceptTicketCommand;
    @Autowired
    DeclineTicketCommand declineTicketCommand;
    @Autowired
    StartTicketCommand startTicketCommand;
    @Autowired
    RestartTicketCommand restartTicketCommand;
    @Autowired
    BlockTicketCommand blockTicketCommand;
    @Autowired
    CloseTicketCommand closeTicketCommand;
    @GetMapping(value = "/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> getTicket(@PathVariable String ticketId) {
        findTicketByIdCommand.setId(ticketId);
        return new ResponseEntity<>(findTicketByIdCommand.execute(), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> getTickets() {
        return new ResponseEntity<>(findAllTicketCommand.execute(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> postTicket(@RequestBody TicketDto ticketDto){
        createTicketCommand.setTicketDto(ticketDto);
        return new ResponseEntity<>(createTicketCommand.execute(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteTicket(@PathVariable String ticketId){
        deleteTicketCommand.setId(ticketId);
        return new ResponseEntity<>(deleteTicketCommand.execute(), HttpStatus.OK);
    }
    @GetMapping(value = "/{ticketId}/assignTo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> assignTicket(@PathVariable String ticketId, @PathParam("userId") String userId) throws JsonProcessingException {
        assignTicketCommand.setTicketId(ticketId);
        assignTicketCommand.setUserId(userId);
        return new ResponseEntity<>(assignTicketCommand.execute(), HttpStatus.OK);
    }
    @GetMapping(value = "/{ticketId}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> acceptTicket(@PathVariable String ticketId) throws JsonProcessingException {
        acceptTicketCommand.setTicketId(ticketId);
        return new ResponseEntity<>(acceptTicketCommand.execute(), HttpStatus.OK);
    }
    @GetMapping(value = "/{ticketId}/decline", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> declineTicket(@PathVariable String ticketId) throws JsonProcessingException {
        declineTicketCommand.setTicketId(ticketId);
        return new ResponseEntity<>(declineTicketCommand.execute(), HttpStatus.OK);
    }
    @GetMapping(value = "/{ticketId}/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> startTicket(@PathVariable String ticketId) throws JsonProcessingException {
        startTicketCommand.setTicketId(ticketId);
        return new ResponseEntity<>(startTicketCommand.execute(), HttpStatus.OK);
    }
    @GetMapping(value = "/{ticketId}/restart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> restartTicket(@PathVariable String ticketId) throws JsonProcessingException {
        restartTicketCommand.setTicketId(ticketId);
        return new ResponseEntity<>(restartTicketCommand.execute(), HttpStatus.OK);
    }
    @GetMapping(value = "/{ticketId}/block", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> blockTicket(@PathVariable String ticketId) throws JsonProcessingException {
        blockTicketCommand.setTicketId(ticketId);
        return new ResponseEntity<>(blockTicketCommand.execute(), HttpStatus.OK);
    }
    @GetMapping(value = "/{ticketId}/close", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> closeTicket(@PathVariable String ticketId) throws JsonProcessingException {
        closeTicketCommand.setTicketId(ticketId);
        return new ResponseEntity<>(closeTicketCommand.execute(), HttpStatus.OK);
    }
    //TRASFORMARE RICERCA CON SPECIFICATIONS
}
