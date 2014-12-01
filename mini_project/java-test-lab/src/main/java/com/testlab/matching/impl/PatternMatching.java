package com.testlab.matching.impl;


import java.util.ArrayList;
import java.util.List;

public class PatternMatching<T, V> {

    private List<Case<T, V>> cases = new ArrayList<Case<T, V>>();

    public V match(T value, Cases<T, V> casesCreation) {
        casesCreation.collectCases(this);

        for (Case<T, V> c : cases) {
            if (c.canApply(value)) {
                return c.doIt(value);
            }
        }
        return null;
    }

    public void when(When<T> when, Action<T, V> action) {
        cases.add(new Case<>(when, action));
    }
}
