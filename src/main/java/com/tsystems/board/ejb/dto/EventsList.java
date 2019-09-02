package com.tsystems.board.ejb.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Events list class.
 */
@Getter
@Setter
public class EventsList implements Serializable {

    /**
     * List of events, scheduled for today.
     */
    List<Event> events;

}
