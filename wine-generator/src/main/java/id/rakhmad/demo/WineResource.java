package id.rakhmad.demo;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;

import io.smallrye.mutiny.Multi;

@Path("/wine")
public class WineResource {

    @Channel("priced-wine")
    Multi<String> pricedWines;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Multi<PricedWine> wines() {
        return pricedWines.map(s -> JsonbBuilder.create().fromJson(s, PricedWine.class));
    }

}