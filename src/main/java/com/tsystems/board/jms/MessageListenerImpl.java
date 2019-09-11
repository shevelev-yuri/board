package com.tsystems.board.jms;

import com.tsystems.board.ejb.EventList;
import com.tsystems.board.ejb.EventService;

import javax.jms.Message;
import javax.jms.MessageListener;

public class MessageListenerImpl implements MessageListener {

    private EventService eventService;

    private EventList eventList;

    @Override
    public void onMessage(Message message) {
        eventList.setEvents(eventService.getEvents());
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public void setEventList(EventList eventList) {
        this.eventList = eventList;
    }
}
