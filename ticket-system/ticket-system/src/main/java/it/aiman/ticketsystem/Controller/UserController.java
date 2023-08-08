package it.aiman.ticketsystem.Controller;

import it.aiman.ticketsystem.Command.User.*;
import it.aiman.ticketsystem.Dto.UserDto;
import it.aiman.ticketsystem.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    FindUserByIdCommand findUserByIdCommand;
    @Autowired
    CreateUserCommand createUserCommand;
    @Autowired
    DeleteUserCommand deleteUserCommand;
    @Autowired
    FindAllUserCommand findAllUserCommand;
    @Autowired
    FindUserByUsernameCommand findUserByUsernameCommand;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        findUserByIdCommand.setId(userId);
        return new ResponseEntity<>(findUserByIdCommand.execute(), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(findAllUserCommand.execute(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> postUser(@RequestBody UserDto userDto){
        createUserCommand.setUserDto(userDto);
        return new ResponseEntity<>(createUserCommand.execute(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteUser(@PathVariable String userId){
        deleteUserCommand.setId(userId);
        return new ResponseEntity<>(deleteUserCommand.execute(), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByUsername(@RequestParam String username){
        findUserByUsernameCommand.setId(username);
        return new ResponseEntity<>(findUserByUsernameCommand.execute(), HttpStatus.OK);
    }


    //TRASFORMARE RICERCA CON SPECIFICATIONS
}
