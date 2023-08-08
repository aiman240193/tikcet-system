package it.aiman.ticketsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.aiman.ticketsystem.Model.TicketEvent;
import it.aiman.ticketsystem.Model.WebSocketMessage;
import it.aiman.ticketsystem.Repository.TicketRepository;
import it.aiman.ticketsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableMongoRepositories
public class TicketSystemApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TicketSystemApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TicketSystemApplication.class, args);
	}


}
