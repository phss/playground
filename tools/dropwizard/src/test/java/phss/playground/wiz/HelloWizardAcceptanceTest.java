package phss.playground.wiz;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HelloWizardAcceptanceTest {

    @ClassRule
    public static final DropwizardAppRule<HelloWizardConfiguration> RULE =
            new DropwizardAppRule<>(HelloWizardApplication.class, "config.yaml");

    @Test
    public void isOk() {
        Client client = new Client();

        final ClientResponse response = client.resource(format("http://localhost:%d/hello-wizard", RULE.getLocalPort())).get(ClientResponse.class);

        assertThat(response.getStatus(), is(200));
    }

}
