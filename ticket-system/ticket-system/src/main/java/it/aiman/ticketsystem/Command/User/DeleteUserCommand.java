package it.aiman.ticketsystem.Command.User;

import it.aiman.ticketsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.MissingFormatArgumentException;

@Component
public class DeleteUserCommand {
    String id;
    @Autowired
    UserService userService;

    public DeleteUserCommand() {
        this.id = null;
    }

    private Boolean doExecute(){
        if(id == null)
            return false;
        if(userService.getUser(id)==null)
            return false;
        return true;
    }

    public Boolean execute(){
        if(doExecute()) {
            userService.deleteUser(userService.getUser(id));
            return true;
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
