package id.rakhmad.demo.rest.json;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import id.rakhmad.demo.model.Beer;
import id.rakhmad.demo.service.BeerService;
import io.smallrye.mutiny.Multi;

@Path("/beer")
public class BeerResource {

    @RestClient
    BeerService bService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Beer> beers() {
        return Multi.createBy().repeating()
                .supplier(() -> new AtomicInteger(1), i -> bService.getBeers(i.getAndIncrement())).until(List::isEmpty)
                .onItem().<Beer>disjoint().select().where(b -> b.getAbv() > 15.0);
    }

}
