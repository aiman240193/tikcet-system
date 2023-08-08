package it.aiman.ticketsystem.Dto;

public class SignOutDto {
    private String refreshToken;

    public SignOutDto() {
    }

    public SignOutDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
