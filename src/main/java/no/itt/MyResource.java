
package no.itt;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/")
public class MyResource {
    
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
        new Thread(() -> {
            try (EventOutput ignored = eventOutput) {
                for (int i = 0; i < 10; i++) {
                    // ... code that waits 1 second
                    Thread.sleep(1000L);
                    final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                    eventBuilder.name("message-to-client");
                    eventBuilder.data(String.class, "Hello world " + i + "!");
                    final OutboundEvent event = eventBuilder.build();
                    eventOutput.write(event);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error when writing the event.", e);
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted!", e);
            } finally {
                System.out.println("eventOutput.isClosed() = " + eventOutput.isClosed());
            }
        }).start();
        System.out.println("When returned: eventOutput.isClosed() = " + eventOutput.isClosed());
        return eventOutput;
    }
}
