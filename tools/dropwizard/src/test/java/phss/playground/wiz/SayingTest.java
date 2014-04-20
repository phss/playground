package phss.playground.wiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class SayingTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJson() throws Exception {
        final Saying saying = new Saying(42, "This is the answer");

        assertThat(MAPPER.writeValueAsString(saying), equalTo(fixture("fixtures/saying.json")));
    }

    @Test
    public void deserialisationFromJson() throws Exception {
        final Saying saying = MAPPER.readValue(fixture("fixtures/saying.json"), Saying.class);

        assertThat(saying.getId(), equalTo(42L));
        assertThat(saying.getContent(), equalTo("This is the answer"));
    }


}
