package id.rakhmad.demo;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class WineGenerator {

    private static final List<Wine> WINES = Arrays
            .asList("Fay", "Cask 23", "Jordan", "Caymus", "Don Melchor", "Barca Velha", "Pera Manca").stream()
            .map(Wine::of).collect(Collectors.toList());

    @Outgoing("wine")
    Multi<String> wines() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1)).onOverflow().drop()
                .map(tick -> WINES.get(ThreadLocalRandom.current().nextInt(0, WINES.size())))
                .map(JsonbBuilder.create()::toJson);
    }

}