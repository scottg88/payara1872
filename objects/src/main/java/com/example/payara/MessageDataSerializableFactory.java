package com.example.payara;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

public class MessageDataSerializableFactory implements DataSerializableFactory {
    public static final int FACTORY_ID = 111;
    public static final int MESSAGE_TYPE = 1;

    public IdentifiedDataSerializable create(int i) {
        if (i == MESSAGE_TYPE) return new ExampleMessage();
        else return null;
    }

    public MessageDataSerializableFactory() {
    }
}
