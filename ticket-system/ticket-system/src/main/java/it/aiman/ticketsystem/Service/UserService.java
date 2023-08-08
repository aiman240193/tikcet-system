package it.aiman.ticketsystem.Service;

import it.aiman.ticketsystem.Model.Ticket;
import it.aiman.ticketsystem.Model.User;
import it.aiman.ticketsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User getUser(String id){
        return userRepository.findById(id).get();
    }

    public Optional<User> findAllByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
