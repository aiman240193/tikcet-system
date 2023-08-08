package it.aiman.ticketsystem.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.aiman.ticketsystem.Model.Event;
import it.aiman.ticketsystem.Model.Status;
import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Model.TicketEvent;
import it.aiman.ticketsystem.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;


    private final String topic = "ticket-system";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Ticket getTicket(String id){
        return ticketRepository.findById(id).get();
    }
    public void createTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public  List<Ticket> findAll(){
       return ticketRepository.findAll();
    }

    public void deleteTicket(Ticket ticket){
        ticketRepository.delete(ticket);
    }

    public List<Ticket> findAllWithStatus(Status status){
        return ticketRepository.findAllWithStatus(status);
    }

    public Ticket findAllWithTitle(String title){
        return ticketRepository.findTicketByTitle(title);
    }

    public boolean assignTicketTo(String ticketId, String userId) throws JsonProcessingException {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setAssignedTo(userId);
        ticket.setStatus(Status.ASSIGNED);
        ticketRepository.save(ticket);
        TicketEvent message = new TicketEvent(ticketId, Event.ASSIGNATION.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(message));
        /*TODO: LAUNCH STATE MACHINE EVENT*/
        return true;
    }

    public boolean acceptTicket(String ticketId) throws JsonProcessingException {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setStatus(Status.ACCEPTED);
        ticketRepository.save(ticket);
        TicketEvent message = new TicketEvent(ticketId, Event.ACCEPTANCE.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(message));
        /*TODO: LAUNCH STATE MACHINE EVENT*/
        return true;
    }

    public boolean declineTicket(String ticketId) throws JsonProcessingException {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setStatus(Status.DECLINED);
        ticket.setAssignedTo(null);
        ticketRepository.save(ticket);
        TicketEvent message = new TicketEvent(ticketId, Event.REFUSAL.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(message));
        /*TODO: LAUNCH STATE MACHINE EVENT*/
        return true;
    }

    public boolean startTicket(String ticketId) throws JsonProcessingException {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setStatus(Status.STARTED);
        ticketRepository.save(ticket);
        TicketEvent message = new TicketEvent(ticketId, Event.START.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(message));
        /*TODO: LAUNCH STATE MACHINE EVENT*/
        return true;
    }

    public boolean restartTicket(String ticketId) throws JsonProcessingException {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setStatus(Status.STARTED);
        ticketRepository.save(ticket);
        TicketEvent message = new TicketEvent(ticketId, Event.FIX.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(message));
        /*TODO: LAUNCH STATE MACHINE EVENT*/
        return true;
    }

    public boolean blockTicket(String ticketId) throws JsonProcessingException {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setStatus(Status.BLOCKED);
        ticketRepository.save(ticket);
        TicketEvent message = new TicketEvent(ticketId, Event.PROBLEM.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(message));
        /*TODO: LAUNCH STATE MACHINE EVENT*/
        return true;
    }

    public boolean closeTicket(String ticketId) throws JsonProcessingException {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setStatus(Status.CLOSED);
        ticketRepository.save(ticket);
        TicketEvent message = new TicketEvent(ticketId, Event.CONCLUSION.toString()   );
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(message));
        /*TODO: LAUNCH STATE MACHINE EVENT*/
        return true;
    }
}
