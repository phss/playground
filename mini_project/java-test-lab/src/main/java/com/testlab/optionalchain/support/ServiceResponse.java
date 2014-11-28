package com.testlab.optionalchain.support;

public class ServiceResponse {

    private final int responseId;
    private final String account;
    private final String accessType;

    public ServiceResponse(int responseId, String account, String accessType) {
        this.responseId = responseId;
        this.account = account;
        this.accessType = accessType;
    }

    public int getResponseId() {
        return responseId;
    }

    public String getAccount() {
        return account;
    }

    public String getAccessType() {
        return accessType;
    }
}
