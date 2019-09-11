package com.tsystems.board.ejb;

import com.itextpdf.text.DocumentException;
import com.tsystems.board.ejb.dto.Event;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Basic implementation of the <tt>EventService</tt> interface.
 *
 * @author Yurii Shevelev
 * @version 1.0.0
 */
@Singleton
public class EventServiceRestImpl implements EventService {

    /**
     * Log4j logger.
     */
    private static final Logger log = LogManager.getLogger(EventServiceRestImpl.class);

    /**
     * Entry point URL to the ecm webapp
     */
    private static final String REST_SERVICE_ENTRY_POINT = "http://localhost:8080/ecm/api/";

    /**
     * Client type reference
     */
    private Client client;

    @PostConstruct
    public void postConstruct() {
        log.debug("Client initialized");
        client = ClientBuilder.newClient();
    }

    @PreDestroy
    public void preDestroy() {
        log.debug("Client closed");
        client.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEvents() {
        try {
            WebTarget target = client.target(REST_SERVICE_ENTRY_POINT + "events");
            return target.request().get(List.class);

        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public void generatePDF(final List<Event> events, final String path)
            throws DocumentException, FileNotFoundException {
/*
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();
        Font chapterFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,
                20, Font.BOLD);
        Font subHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,
                14, Font.BOLDITALIC);
        Font paragraphFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,
                12, Font.NORMAL);
        Font cellHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,
                12, Font.BOLD);
        float defaultSpacing = 20f;

        //Header
        Paragraph chapterParagraph = new Paragraph("Shop report", chapterFont);
        chapterParagraph.setAlignment(Element.ALIGN_CENTER);
        Chapter chapter = new Chapter(chapterParagraph, 1);
        chapter.setNumberDepth(0);
        document.add(chapter);
        Paragraph periodParagraph;
        {
            periodParagraph = new Paragraph("Sales information for full period", subHeaderFont);
        }
        periodParagraph.setAlignment(Element.ALIGN_CENTER);
        periodParagraph.setSpacingAfter(defaultSpacing * 2);
        document.add(periodParagraph);

        document.add(new Paragraph("Total orders: "
                + eventList.getPeriodOrders(), paragraphFont));
        document.add(new Paragraph("Total Sales: "
                + eventList.getPeriodSales(), paragraphFont));

        //Orders by statuses
        PdfPTable ordersPerStatus = new PdfPTable(2);
        ordersPerStatus.setHeaderRows(1);
        ordersPerStatus.setSpacingBefore(defaultSpacing);
        ordersPerStatus.addCell(new Phrase("Status", cellHeaderFont));
        ordersPerStatus.addCell(new Phrase("Orders", cellHeaderFont));
        for (Map.Entry<String, Integer> status
                : eventList.getOrdersPerStatus().entrySet()) {
            ordersPerStatus.addCell(status.getKey());
            ordersPerStatus.addCell(status.getValue().toString());
        }
        document.add(ordersPerStatus);
        //Top users table
        Paragraph usersParagraph = new Paragraph("Top users", subHeaderFont);
        usersParagraph.setAlignment(Element.ALIGN_CENTER);
        usersParagraph.setSpacingBefore(defaultSpacing);
        usersParagraph.setSpacingAfter(defaultSpacing);
        document.add(usersParagraph);
        PdfPTable topUsers = new PdfPTable(5);
        topUsers.addCell(new Phrase("Name", cellHeaderFont));
        topUsers.addCell(new Phrase("Email", cellHeaderFont));
        topUsers.addCell(new Phrase("Orders count", cellHeaderFont));
        topUsers.addCell(new Phrase("Total bought", cellHeaderFont));
        topUsers.addCell(new Phrase("% of total", cellHeaderFont));
        topUsers.setWidthPercentage(100);
        topUsers.setHeaderRows(1);
        for (User user : eventList.getTopUsers()) {
            topUsers.addCell(user.getName() + " "
                    + user.getLastName());
            topUsers.addCell(user.getEmail());
            topUsers.addCell("" + user.getOrdersCount());
            topUsers.addCell("" + user.getOrderTotal());
            topUsers.addCell("" + eventList.getSalesPercent(
                    (double) user.getOrderTotal()));

        }
        document.add(topUsers);

        //Top products table
        Paragraph productsParagraph = new Paragraph("Top products",
                subHeaderFont);
        productsParagraph.setAlignment(Element.ALIGN_CENTER);
        productsParagraph.setSpacingBefore(defaultSpacing);
        productsParagraph.setSpacingAfter(defaultSpacing);
        document.add(productsParagraph);
        PdfPTable topProducts = new PdfPTable(5);
        topProducts.addCell(new Phrase("Name", cellHeaderFont));
        topProducts.addCell(new Phrase("Price", cellHeaderFont));
        topProducts.addCell(new Phrase("Stocks count", cellHeaderFont));
        topProducts.addCell(new Phrase("Total sales", cellHeaderFont));
        topProducts.addCell(new Phrase("% of total", cellHeaderFont));
        topProducts.setWidthPercentage(100);
        topProducts.setHeaderRows(1);
        for (Product product : eventList.getTopProducts()) {
            topProducts.addCell(product.getName());
            topProducts.addCell(product.getPrice() + "");
            topProducts.addCell(product.getStock() + "");
            topProducts.addCell((product.getTotalSales()
                    * product.getPrice()) + "");
            topProducts.addCell(eventList.getSalesPercent(
                    (double) product.getTotalSales()
                            * product.getPrice()) + "");

        }
        document.add(topProducts);


        document.close();*/
    }
}
