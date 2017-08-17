package com.example.payara;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.io.IOException;

public class ExampleMessage implements IdentifiedDataSerializable {
    private String message;

    public ExampleMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFactoryId() {
        return MessageDataSerializableFactory.FACTORY_ID;
    }

    public int getId() {
        return MessageDataSerializableFactory.MESSAGE_TYPE;
    }

    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(message);
    }

    public void readData(ObjectDataInput in) throws IOException {
        message = in.readUTF();
    }
}
