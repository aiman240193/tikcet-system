package it.aiman.ticketsystem.Command.User;

import it.aiman.ticketsystem.Model.User;
import it.aiman.ticketsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.MissingFormatArgumentException;

@Component
public class FindUserByUsernameCommand {
    String username;

    @Autowired
    UserService userService;

    public FindUserByUsernameCommand() {
        this.username = null;
    }

    private Boolean doExecute(){
        if(username == null)
            return false;
        return true;
    }

    public User execute(){
        if(doExecute()) {
            return userService.findAllByUsername(username).get();
        }
        else{
            throw new MissingFormatArgumentException("Missing id");
        }
    }


    public String getId() {
        return username;
    }

    public void setId(String id) {
        this.username = id;
    }
}
