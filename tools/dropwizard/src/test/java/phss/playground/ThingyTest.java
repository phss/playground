package phss.playground;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ThingyTest {

    @Test
    public void helloWithStuff() {
        Thingy thingy = new Thingy("coisa");

        assertThat(thingy.saidHelloToTheStuff(), equalTo("Hello to you, coisa"));
    }
}
