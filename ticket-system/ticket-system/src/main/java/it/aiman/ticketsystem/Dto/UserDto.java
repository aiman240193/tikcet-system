package it.aiman.ticketsystem.Dto;

import it.aiman.ticketsystem.Model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDto {
    private String id;
    private String username;
    private String email;
    private List<String> roles;

    public UserDto(String id, String username, String email, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
