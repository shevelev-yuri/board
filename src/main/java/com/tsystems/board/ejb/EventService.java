package com.tsystems.board.ejb;

import com.itextpdf.text.DocumentException;
import com.tsystems.board.ejb.dto.EventsList;

import java.io.FileNotFoundException;

/**
 * This service provides logic for generation of today's events list.
 *
 * @author Yurii Shevelev
 * @version 1.0.0
 */
public interface EventService {

    /**
     * Gets a EventsList instance containing list of events for today.
     *
     * @return {@code EventsList} with a list containing events for today or empty list, if no events are scheduled for today
     */
    EventsList getEvents();

    /**
     * Generates pdf file.
     *
     * @param eventList the EventsList
     * @param path      the path
     * @throws DocumentException     the document exception
     * @throws FileNotFoundException the file not found exception
     */
    void generatePDF(EventsList eventList, String path)
            throws DocumentException, FileNotFoundException;
}
