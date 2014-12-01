package com.testlab.matching;

import com.testlab.matching.impl.Case;
import com.testlab.matching.impl.PatternMatching;

import static java.util.Arrays.asList;

public class NumberMatching {

    public static String says(int number) {
        return new PatternMatching<Integer, String>().match(number, asList(
                new Case<Integer, String>((x) -> x > 0, (x) -> "Yay!"),
                new Case<Integer, String>((x) -> x < 0, (x) -> "Nay?"),
                new Case<Integer, String>((x) -> x == 0, (x) -> "Boo...")
        ));
    }
}
