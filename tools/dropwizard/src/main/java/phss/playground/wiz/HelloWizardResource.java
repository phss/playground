package phss.playground.wiz;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import phss.playground.Thingy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.concurrent.atomic.AtomicLong;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/hello-wizard")
@Produces(APPLICATION_JSON)
public class HelloWizardResource {

    private final String wizardDefaultName;
    private final AtomicLong counter;

    public HelloWizardResource(String wizardDefaultName) {
        this.wizardDefaultName = wizardDefaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        Thingy thingy = new Thingy(name.or(wizardDefaultName));
        return new Saying(counter.incrementAndGet(), thingy.saidHelloToTheStuff());
    }
}
