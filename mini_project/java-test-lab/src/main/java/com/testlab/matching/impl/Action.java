package com.testlab.matching.impl;

public interface Action<T, V> {
    V doIt(T value);
}
