# Example for Payara Issue 1872

# Steps

## Build shared objects

In `objects/`:

```
mvn install
```

## Build and run Payara Micro

In `webapp`:

```
mvn install
java -jar target/webapp-microbundle.jar --hzconfigfile hazelcast.xml
```

## Build and run client

In `client`:

```
mvn package
java -jar target/client.jar
```
