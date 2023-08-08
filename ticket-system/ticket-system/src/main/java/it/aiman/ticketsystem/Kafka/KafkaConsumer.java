package it.aiman.ticketsystem.Kafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final SimpMessagingTemplate template;

    public KafkaConsumer(SimpMessagingTemplate template) {
        this.template = template;
    }

    @KafkaListener(topics = "ticket-system", groupId = "ticket-system")
    public void listenTicketEvents(String message) throws Exception {
        System.out.println("Received Message in group foo: " + message);

        template.convertAndSend("/topic/ticketEvents", message);
    }
}
