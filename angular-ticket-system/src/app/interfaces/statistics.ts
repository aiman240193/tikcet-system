export class Statistics{
    weeklyCompletions: {labels : string[], values: number[]};

    constructor(ticket_id: number, weeklyCompletions: {labels : string[], values: number[]}){
        this.weeklyCompletions = weeklyCompletions;
    }
}