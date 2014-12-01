package com.testlab.matching.impl;

import java.util.List;

public class PatternMatching<T, V> {

    public V match(T value, List<Case<T, V>> cases) {
        for (Case<T, V> c : cases) {
            if (c.canApply(value)) {
                return c.doIt(value);
            }
        }
        return null;
    }
}
