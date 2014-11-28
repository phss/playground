package com.testlab.optionalchain.support;

public class Account {

    private static final Account FALLBACK = new Account(-1, Account.AccessType.GUEST);
    private final int accountNumber;
    private final AccessType accessType;

    public enum AccessType {
        FULL, PARTIAL, GUEST
    }

    public Account(int accountNumber, AccessType accessType) {
        this.accountNumber = accountNumber;
        this.accessType = accessType;
    }

    public static Account getFallback() {
        return FALLBACK;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public AccessType getAccessType() {
        return accessType;
    }
}
