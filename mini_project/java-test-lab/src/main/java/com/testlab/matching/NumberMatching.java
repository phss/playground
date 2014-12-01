package com.testlab.matching;

import com.testlab.matching.impl.Case;

import java.util.List;

import static java.util.Arrays.asList;

public class NumberMatching {

    public static String says(int number) {
        return match(number, asList(
                new Case((x) -> (int) x > 0, (x) -> "Yay!"),
                new Case((x) -> (int) x < 0, (x) -> "Nay?"),
                new Case((x) -> (int) x == 0, (x) -> "Boo...")
        ));
    }

    private static String match(int number, List<Case> cases) {
        for (Case c : cases) {
            if (c.canApply(number)) {
                return (String)c.doIt(number);
            }
        }
        return null;
    }

}
