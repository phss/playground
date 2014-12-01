package com.testlab.matching;

import com.testlab.matching.impl.PatternMatching;

public class NumberMatching {

    public static String says(int number) {
        return new PatternMatching<Integer, String>().match(number, cases -> {
            cases.when((x) -> x > 0, (x) -> "Yay!");
            cases.when((x) -> x < 0, (x) -> "Nay?");
            cases.when((x) -> x == 0, (x) -> "Boo...");
        });
    }
}
