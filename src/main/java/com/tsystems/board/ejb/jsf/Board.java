package com.tsystems.board.ejb.jsf;

import com.tsystems.board.ejb.EventList;
import com.tsystems.board.ejb.dto.Event;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * The Board controller.
 *
 * @author Yurii Shevelev
 * @version 1.0.0
 */
@Named
@ApplicationScoped
public class Board implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Log4j logger.
     */
    private static final Logger log = LogManager.getLogger(Board.class);

    /**
     * List of events, scheduled for today.
     */
    private List<Event> events;

    /**
     * EventList bean injection.
     */
    @EJB
    private EventList eventList;

    /**
     * Gets events list on startup
     */
    @PostConstruct
    public void init() {
        log.debug("Board constructed, init events list.");
        updateEvents();
    }

    /**
     * Updates events list.
     */
    public void updateEvents() {
        events = eventList.getEvents();
    }

    /**
     * Gets events list.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Sets events list
     *
     * @param events the events list
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

}