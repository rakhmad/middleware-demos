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
public class BookGenerator {

    private static final List<Book> BOOKS = Arrays
            .asList("A Life on Our Planet", "Letter from a Stoic", "The Design of Everyday Things", "Extreme Ownership",
                    "The Lean Startup", "The Infinite Game", "Thinking in Systems", "The Innovators",
                    "Camel in Action 2nd Edition", "Factfulness", "Nudge", "Longitude", "Predictably Irrational",
                    "Loonshots", "Educated", "Sapiens", "Homo Deus", "The Great Gatsby", "Nonviolent Communication",
                    "Console Wars", "Leaders Eat Last", "Hidden Figures", "Animal Farm", "1984")
            .stream().map(Book::of).collect(Collectors.toList());

    @Outgoing("book")
    Multi<String> books() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1)).onOverflow().drop()
                .map(tick -> BOOKS.get(ThreadLocalRandom.current().nextInt(0, BOOKS.size())))
                .map(JsonbBuilder.create()::toJson);
    }
}
