package com.tsystems.board.jms;

import com.tsystems.board.ejb.EventList;
import com.tsystems.board.ejb.EventService;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSException;
import javax.jms.Topic;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import java.util.Iterator;
import java.util.Set;

@Singleton
@Startup
public class JmsConfig {

    private static final Logger log = LogManager.getLogger(JmsConfig.class);

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String BROKER_URL = "tcp://localhost:61616";

    private ActiveMQConnection connection;
    private TopicSession session;
    private TopicSubscriber receiver;
    private Topic topic;

    @EJB
    private EventService eventService;

    @EJB
    private EventList eventList;

    @PostConstruct
    private void startConnection() throws JMSException {
        log.debug("Starting connection to JMS broker");
        eventList.setEvents(eventService.getEvents());

        ActiveMQConnectionFactory amqCF = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        connection = (ActiveMQConnection) amqCF.createConnection();
        connection.start();
        DestinationSource destinationSource = connection.getDestinationSource();
        Set<ActiveMQTopic> topics = destinationSource.getTopics();
        Iterator<ActiveMQTopic> it = topics.iterator();
        if (it.hasNext()) topic = it.next();
        session = connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
        receiver = session.createSubscriber(topic);

        MessageListenerImpl listener = new MessageListenerImpl();
        listener.setEventService(eventService);
        listener.setEventList(eventList);
        receiver.setMessageListener(listener);
    }

    @PreDestroy
    private void closeConnection() {
        try {
            receiver.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}