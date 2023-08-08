import { Status } from "./status";

export class Ticket {
    id: string;
    title: string;
    description: string;
    status: Status;
    createdAt: Date;
    assignedTo: string;
    createdBy: string;

    constructor(id: string, title: string, description: string, status: Status, createdAt: Date, assignedTo: string, createdBy: string) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.assignedTo = assignedTo;
        this.createdBy = createdBy;
    }
}