package com.testlab.matching;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ExampleNumberMatchingTest {

    @Test
    public void saysYayIfNumberIsPositive() {
        assertThat(NumberMatching.says(42), equalTo("Yay!"));
    }

    @Test
    public void saysNayIfNumberIsNegative() {
        assertThat(NumberMatching.says(-13), equalTo("Nay?"));
    }
    @Test
    public void saysBooIfNumberIsZero() {
        assertThat(NumberMatching.says(0), equalTo("Boo..."));
    }
}
