package com.tsystems.board.controller;

import com.itextpdf.text.DocumentException;
import com.tsystems.board.ejb.EventService;
import com.tsystems.board.ejb.dto.EventsList;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * The Board controller.
 *
 * @author Yurii Shevelev
 * @version 1.0.0
 */
@ManagedBean
@SessionScoped
public class BoardController implements Serializable {

    /**
     * Log4j logger.
     */
    private static final Logger log = LogManager.getLogger(BoardController.class);

    /**
     * EventsList with list of events, scheduled for today.
     */
    private EventsList eventsList;

    /**
     * The Error message.
     */
    private String errorMessage;

    /**
     * Event service bean injection.
     */
    @EJB
    private EventService eventService;

    /**
     * Show eventsList board.
     *
     * @return string for events board view
     */
    public String showEvents() {
        eventsList = eventService.getEvents();

        return "events";
    }

    /**
     * Gets pdf.
     *
     * @return the pdf
     */
    public StreamedContent getPdf() {

        String path = "resources/report.pdf";
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance()
                .getExternalContext().getContext();
        try {
            eventService.generatePDF(eventsList, context.getRealPath("/" + path));
        } catch (DocumentException | FileNotFoundException e) {
            log.error("PDF generation exception", e);
        }

        InputStream stream = context.getResourceAsStream(path);

        return new DefaultStreamedContent(stream, "application/pdf",
                "eventslist.pdf");
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
