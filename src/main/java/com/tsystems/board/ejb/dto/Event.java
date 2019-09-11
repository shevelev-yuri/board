package com.tsystems.board.ejb.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Event DTO class.
 */
@Getter
@Setter
public class Event implements Serializable {

    /**
     * Name of the patient to whom the event is intended.
     */
    private String patient;

    /**
     * Scheduled date and time of the event.
     */
    private String scheduledDatetime;

    /**
     * Event's status.
     */
    private String eventStatus;

    /**
     * Event's treatment name.
     */
    private String treatment;

    /**
     * Cancellation reason of the event (if {@code eventStatus} is CANCELLED).
     */
    private String cancelReason;

}
