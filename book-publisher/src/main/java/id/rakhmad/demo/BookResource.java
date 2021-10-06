package id.rakhmad.demo;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;

import io.smallrye.mutiny.Multi;

@Path("/book")
public class BookResource {

    @Channel("priced-book")
    Multi<String> pricedBooks;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Multi<PricedBook> books() {
        return pricedBooks.map(s -> JsonbBuilder.create().fromJson(s, PricedBook.class));
    }
}