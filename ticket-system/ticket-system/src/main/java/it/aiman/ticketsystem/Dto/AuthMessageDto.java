package it.aiman.ticketsystem.Dto;

public class AuthMessageDto {
    private String message;

    public AuthMessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
