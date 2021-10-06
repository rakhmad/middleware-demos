package id.rakhmad.demo;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class BookDeserializer extends ObjectMapperDeserializer<Book> {
    public BookDeserializer() {
        super(Book.class);
    }
}
