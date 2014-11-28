package com.testlab.optionalchain.support;

public class Account {

    private final int accountNumber;
    private final AccessType accessType;

    enum AccessType {
        FULL, PARTIAL, GUEST
    }

    public Account(int accountNumber, AccessType accessType) {
        this.accountNumber = accountNumber;
        this.accessType = accessType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public AccessType getAccessType() {
        return accessType;
    }
}
