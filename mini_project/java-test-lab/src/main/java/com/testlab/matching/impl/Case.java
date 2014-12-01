package com.testlab.matching.impl;

public class Case<T, V> {
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
