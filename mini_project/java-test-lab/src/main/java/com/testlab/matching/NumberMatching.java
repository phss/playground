package com.testlab.matching;

import javafx.util.Pair;

import java.util.Arrays;
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

    private static class Case<T, V> {
        private final When<T> when;
        private final Action<T, V> action;

        public Case(When<T> when, Action<T, V> action) {
            this.when = when;
            this.action = action;
        }

        public boolean canApply(T value) {
            return when.canApply(value);
        }

        public V doIt(T value) {
            return action.doIt(value);
        }
    }

    private static interface When<T> {
        boolean canApply(T value);
    }

    private static interface Action<T, V> {
        V doIt(T value);
    }
}
