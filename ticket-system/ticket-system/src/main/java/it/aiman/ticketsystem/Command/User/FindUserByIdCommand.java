package it.aiman.ticketsystem.Command.User;

import it.aiman.ticketsystem.Model.User;
import it.aiman.ticketsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.MissingFormatArgumentException;

@Component
public class FindUserByIdCommand {
    String id;

    @Autowired
    UserService userService;

    public FindUserByIdCommand() {
        this.id = null;
    }

    private Boolean doExecute(){
        if(id == null)
            return false;
        return true;
    }

    public User execute(){
        if(doExecute()) {
            return userService.getUser(id);
        }
        else{
            throw new MissingFormatArgumentException("Missing id");
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
