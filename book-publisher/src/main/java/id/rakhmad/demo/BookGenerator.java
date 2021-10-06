package id.rakhmad.demo;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import io.quarkus.scheduler.Scheduled;
import io.smallrye.reactive.messaging.kafka.Record;

@ApplicationScoped
public class BookGenerator {

        private static final Logger LOG = Logger.getLogger(BookGenerator.class);

        private static final List<Book> BOOKS = Arrays.asList("A Life on Our Planet", "Letter from a Stoic",
                        "The Design of Everyday Things", "Extreme Ownership", "The Lean Startup", "The Infinite Game",
                        "Thinking in Systems", "The Innovators", "Camel in Action 2nd Edition", "Factfulness", "Nudge",
                        "Longitude", "Predictably Irrational", "Loonshots", "Educated", "Sapiens", "Homo Deus",
                        "The Great Gatsby", "Nonviolent Communication", "Console Wars", "Leaders Eat Last",
                        "Hidden Figures", "Animal Farm", "1984").stream().map(Book::of).collect(Collectors.toList());

        @Inject
        @Channel("book")
        private Emitter<Record<String, Book>> emitter;

        public void sendBookToKafka(Book book) {
                emitter.send(Record.of(UUID.randomUUID().toString(), book));
        }

        @Scheduled(every = "5s")
        public void sendRandomBookToKafka() {
                int randomNum = ThreadLocalRandom.current().nextInt(0, BOOKS.size());
                Record<String, Book> record = Record.of(UUID.randomUUID().toString(), BOOKS.get(randomNum));
                emitter.send(record);
                LOG.infof("##### Published Message to Kafka: %s", record);

        }
}
