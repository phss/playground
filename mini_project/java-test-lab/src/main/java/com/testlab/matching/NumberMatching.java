package com.testlab.matching;

import com.testlab.matching.impl.PatternMatching;
import com.testlab.matching.impl.When;

public class NumberMatching {

    public static String says(int number) {
        return new PatternMatching<Integer, String>().match(number, cases -> {
            cases.when(isPositive(), (x) -> "Yay!");
            cases.when(isNegative(), (x) -> "Nay?");
            cases.when(isZero(), (x) -> "Boo...");
        });
    }

    private static When<Integer> isPositive() {
        return (x) -> x > 0;
    }

    private static When<Integer> isNegative() {
        return (x) -> x < 0;
    }

    private static When<Integer> isZero() {
        return (x) -> x == 0;
    }
}
