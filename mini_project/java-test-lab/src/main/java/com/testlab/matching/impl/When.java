package com.testlab.matching.impl;

public interface When<T> {
    boolean canApply(T value);
}
