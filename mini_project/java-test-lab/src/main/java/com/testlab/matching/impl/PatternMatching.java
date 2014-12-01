package com.testlab.matching.impl;

import java.util.ArrayList;
import java.util.List;

public class PatternMatching<T, V> {

    private List<Case<T, V>> cases = new ArrayList<Case<T, V>>();

    public V match(T value, CasesBlock<T, V> casesBlock) {
        casesBlock.collectCases(this);

        return cases.stream().filter((c) -> c.canApply(value)).findFirst().map((c) -> c.doIt(value)).orElseGet(null);
    }

    public void when(When<T> when, Action<T, V> action) {
        cases.add(new Case<>(when, action));
    }
}
