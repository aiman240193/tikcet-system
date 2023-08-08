package it.aiman.ticketsystem.Command.User;

import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Model.User;
import it.aiman.ticketsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.MissingFormatArgumentException;

@Component
public class FindAllUserCommand {

    @Autowired
    UserService userService;

    private Boolean doExecute(){
        return true;
    }

    public List<User> execute(){
        if(doExecute()) {
            return userService.findAll();
        }
        else return null;
    }

}
