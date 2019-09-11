package com.tsystems.board.ejb;

import com.itextpdf.text.DocumentException;
import com.tsystems.board.ejb.dto.Event;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * This service provides logic for generation of today's events list.
 *
 * @author Yurii Shevelev
 * @version 1.0.0
 */
public interface EventService {

    /**
     * Gets the list of events for today.
     *
     * @return {@code List} containing events for today or empty list, if no events are scheduled for today
     */
    List<Event> getEvents();

    /**
     * Generates pdf file.
     *
     * @param events the List<event
     * @param path   the path
     * @throws DocumentException     the document exception
     * @throws FileNotFoundException the file not found exception
     */
    void generatePDF(List<Event> events, String path)
            throws DocumentException, FileNotFoundException;

}
