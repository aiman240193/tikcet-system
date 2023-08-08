package it.aiman.ticketsystem.Dto;

import it.aiman.ticketsystem.Model.Status;

import java.util.Date;

public class TicketDto {

    private String id;
    private String title;
    private String description;
    private String createdBy;
    private String assignedTo;

    public TicketDto(){

    }

    public TicketDto(String id, String title, String description, String createdBy, String assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdBy= createdBy;
        this.assignedTo = assignedTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

}
