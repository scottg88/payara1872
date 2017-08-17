package com.example.payara;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;

@Stateless
public class ResourceBean {
    private static final Logger log = LoggerFactory.getLogger(QueueContextListener.class);
    @Resource(lookup = "payara/Hazelcast")
    private HazelcastInstance hazelcastInstance;
    private IQueue<ExampleMessage> queue;

    public ResourceBean() {
    }

    @PostConstruct
    private void initialise() {
        if (hazelcastInstance == null) throw new RuntimeException("Hazelcast not available.");
        queue = hazelcastInstance.getQueue("auditQueue");
    }

    public IQueue<ExampleMessage> getQueue() {
        return queue;
    }

    public void setQueue(IQueue<ExampleMessage> queue) {
        this.queue = queue;
    }
}
