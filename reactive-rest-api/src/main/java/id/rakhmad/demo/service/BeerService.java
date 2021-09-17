package id.rakhmad.demo.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import id.rakhmad.demo.model.Beer;

@Path("/v2")
@RegisterRestClient
public interface BeerService {

    @GET
    @Path("/beers")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 3, delay = 1000)
    @Fallback(PunkAPIFallBack.class)
    List<Beer> getBeers(@QueryParam("page") int page);

    public static class PunkAPIFallBack implements FallbackHandler<List<Beer>> {
        private static final Beer EMPTY_BEER = Beer.of("EMPTY", "Empty Beer", 0.0);
        private static final List<Beer> LIST_EMPTY_BEER = List.of(EMPTY_BEER);

        @Override
        public List<Beer> handle(ExecutionContext context) {
            return LIST_EMPTY_BEER;
        }

    }
}
