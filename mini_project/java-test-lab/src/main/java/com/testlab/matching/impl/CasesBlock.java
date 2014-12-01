package com.testlab.matching.impl;

public interface CasesBlock<T, V> {
    void collectCases(PatternMatching<T, V> cases);
}
