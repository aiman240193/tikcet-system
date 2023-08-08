package it.aiman.ticketsystem.Command.User;

import it.aiman.ticketsystem.Dto.UserDto;
import it.aiman.ticketsystem.Model.User;
import it.aiman.ticketsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.MissingFormatArgumentException;

@Component
public class CreateUserCommand {
    UserDto userDto;
    @Autowired
    UserService userService;

    private Boolean doExecute(){
        if(userDto.getUsername() == null || userDto.getEmail() == null ) {
            return false;
        }
        else return true;
    }

    public Boolean execute(){
        if(doExecute()) {
            //userService.createUser(new User( userDto.getUsername(), userDto.getEmail(), userDto.));
            return true;
        }
        else{
            throw new MissingFormatArgumentException("Missing first name, last name or email");
        }
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
