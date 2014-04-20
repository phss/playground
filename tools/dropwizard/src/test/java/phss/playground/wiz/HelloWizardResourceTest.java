package phss.playground.wiz;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HelloWizardResourceTest {

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new HelloWizardResource("Defaulty"))
            .build();

    @Test
    public void sayingWithDefaultWizard() {
        final Saying saying = resources.client().resource("/hello-wizard").get(Saying.class);

        assertThat(saying.getContent(), equalTo("Hello to you, Defaulty"));
    }

    @Test
    public void sayingWithSuppliedName() {
        final Saying saying = resources.client().resource("/hello-wizard").queryParam("name", "Bob").get(Saying.class);

        assertThat(saying.getContent(), equalTo("Hello to you, Bob"));
    }
}
