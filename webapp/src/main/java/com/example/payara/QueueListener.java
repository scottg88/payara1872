package com.example.payara;

import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueListener implements ItemListener<ExampleMessage> {
    private static final Logger log = LoggerFactory.getLogger(QueueListener.class);
    private IQueue<ExampleMessage> queue;

    public QueueListener(IQueue<ExampleMessage> queue) {
        this.queue = queue;
    }

    @Override
    public void itemAdded(ItemEvent<ExampleMessage> itemEvent) {
        log.debug(queue.getName() + ": Item added event fired.");
        processQueue();
    }

    @Override
    public void itemRemoved(ItemEvent<ExampleMessage> itemEvent) {
        log.debug(queue.getName() + ": Item removed event fired.");
    }

    private void processQueue() {
        ExampleMessage message = queue.poll();
        if (message == null) {
            log.debug("processQueue() fired but not message waiting, must have been picked up by another instance.");
            return;
        }
        log.debug("processQueue() fired, received message: " + message.getMessage());
    }
}
