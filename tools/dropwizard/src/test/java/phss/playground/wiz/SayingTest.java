package phss.playground.wiz;

import org.junit.Test;

import static com.yammer.dropwizard.testing.JsonHelpers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class SayingTest {

    @Test
    public void serializesToJson() throws Exception {
        final Saying saying = new Saying(42, "This is the answer");

        assertThat(asJson(saying), equalTo(jsonFixture("fixtures/saying.json")));
    }

    @Test
    public void deserialisationFromJson() throws Exception {
        final Saying saying = fromJson(jsonFixture("fixtures/saying.json"), Saying.class);

        assertThat(saying.getId(), equalTo(42L));
        assertThat(saying.getContent(), equalTo("This is the answer"));
    }


}
