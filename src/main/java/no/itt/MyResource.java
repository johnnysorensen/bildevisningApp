package no.itt;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/")
public class MyResource {

    private static final List<String> bildeUrlListe = Arrays.asList(
            "images/Chrysanthemum.jpg",
            "images/Desert.jpg",
            "images/Hydrangeas.jpg",
            "images/Jellyfish.jpg",
            "images/Koala.jpg",
            "images/Lighthouse.jpg",
            "images/Penguins.jpg",
            "images/Tulips.jpg"
    );
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Path("/hi")
    @Produces("text/plain")
    public String getIt() {
        return "Hi there!";
    }

    @GET
    @Path("/sse")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput getServerSentEvents() {
        final EventOutput eventOutput = new EventOutput();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (EventOutput ignored = eventOutput) {
                    for (int i = 0; i < 100; i++) {
                        // ... code that waits 1 second
                        Thread.sleep(5000L);
                        final OutboundEvent.Builder klockBuilder = new OutboundEvent.Builder();
                        klockBuilder.name("klock");
                        klockBuilder.data(String.class, String.valueOf(new Date().getTime()));
                        final OutboundEvent event = klockBuilder.build();
                        eventOutput.write(event);

                        final OutboundEvent.Builder imageUrlBuilder = new OutboundEvent.Builder();
                        imageUrlBuilder.name("image");
                        imageUrlBuilder.data(bildeUrlListe.get(i % 7));
                        final OutboundEvent imageUrlEvent = imageUrlBuilder.build();

                        eventOutput.write(imageUrlEvent);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Error when writing the event.", e);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Interrupted!", e);
                } finally {
                    System.out.println("eventOutput.isClosed() = " + eventOutput.isClosed());
                }
            }
        }).start();
        System.out.println("When returned: eventOutput.isClosed() = " + eventOutput.isClosed());
        return eventOutput;
    }
}
