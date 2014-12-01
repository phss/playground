package com.testlab.matching.impl;

public interface When<T> {
    public static When ANY = (x) -> true;

    boolean canApply(T value);
}
