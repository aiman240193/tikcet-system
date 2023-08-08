package it.aiman.ticketsystem.Configuration.StateMachine;

import it.aiman.ticketsystem.Model.Event;
import it.aiman.ticketsystem.Model.Status;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

public class StateMachineConfig extends StateMachineConfigurerAdapter<Status, Event> {
    @Override
    public void configure(StateMachineStateConfigurer<Status, Event> states) throws Exception {
        states.withStates()
                .initial(Status.TO_BE_ASSIGNED)
                .fork(Status.ASSIGNED)
                .state(Status.ACCEPTED)
                .state(Status.DECLINED)
                .end(Status.CLOSED)
                .end(Status.DECLINED);
    }

    @Override
    public void configure (StateMachineTransitionConfigurer<Status, Event> transitions) throws Exception {
        transitions
                .withExternal()
                .source(Status.TO_BE_ASSIGNED).target(Status.ASSIGNED).event(Event.ASSIGNATION)
                .and()
                .withExternal()
                .source(Status.ASSIGNED).target(Status.ACCEPTED).event(Event.START)
                .and()
                .withExternal()
                .source(Status.ASSIGNED).target(Status.DECLINED).event(Event.REFUSAL)
                .and()
                .withExternal()
                .source(Status.ACCEPTED).target(Status.STARTED).event(Event.START)
                .and()
                .withExternal()
                .source(Status.STARTED).target(Status.BLOCKED).event(Event.PROBLEM)
                .and()
                .withExternal()
                .source(Status.BLOCKED).target(Status.STARTED).event(Event.FIX)
                .and()
                .withExternal()
                .source(Status.STARTED).target(Status.CLOSED).event(Event.CONCLUSION);
    }
}
