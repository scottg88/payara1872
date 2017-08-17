package com.example.payara;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class QueueContextListener implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(QueueContextListener.class);
    @EJB
    private ResourceBean resources;
    private String listenerId = "";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        QueueListener listener = new QueueListener(resources.getQueue());
        listenerId = resources.getQueue().addItemListener(listener, false);
        if (listenerId != null && !listenerId.equals("")) {
            log.debug("Item listener added to queue 'auditQueue' successfully.");
        } else {
            log.debug("Item listener was not added successfully to queue 'auditQueue'.");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (listenerId != null && !listenerId.equals("")) {
            if (resources.getQueue().removeItemListener(listenerId)) {
                log.debug("Successfully removed listener from queue 'auditQueue'.");
            } else {
                log.debug("Failed to remove listener from queue 'auditQueue'.");
            }
        } else log.debug("Listener was not registered in the first place, no need to remove.");
    }
}
