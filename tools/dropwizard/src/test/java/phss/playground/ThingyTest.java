package phss.playground;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThingyTest {

    @Test
    public void helloWithStuff() {
        Thingy thingy = new Thingy("coisa");

        assertEquals(thingy.saidHelloToTheStuff(), "Hello to you, coisa");
    }
}
