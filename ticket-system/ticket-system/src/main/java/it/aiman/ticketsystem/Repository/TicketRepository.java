package it.aiman.ticketsystem.Repository;

import it.aiman.ticketsystem.Model.Status;
import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
    @Query("{title: '?0'}")
    Ticket findTicketByTitle(String title);

    @Query(value="{status: '?0'}", fields = "{'title': 1, 'description':1}")
    List<Ticket> findAllWithStatus(Status status);

    public long count();

}
