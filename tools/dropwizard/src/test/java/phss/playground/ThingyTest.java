package phss.playground;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class ThingyTest {

    @Test
    public void helloWithStuff() {
        Thingy thingy = new Thingy("coisa");

        assertThat(thingy.saidHelloToTheStuff(), equalTo("Hello to you, coisa"));
    }

    @Test
    public void methodWithLambdaSortingThings() {
        Thingy thingy = new Thingy("coisa");
        String[] expectedArray = { "cc", "aaa", "bbbb" };

        String[] sortedThings = thingy.sortAnArray();

        assertArrayEquals(sortedThings, expectedArray);
    }
}
