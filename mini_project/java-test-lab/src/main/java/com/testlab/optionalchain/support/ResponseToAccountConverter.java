package com.testlab.optionalchain.support;

import static com.testlab.optionalchain.support.Account.AccessType.valueOf;
import static java.lang.Integer.parseInt;

public class ResponseToAccountConverter {

    public Account convert(ServiceResponse response) {
        return new Account(parseInt(response.getAccount()), valueOf(response.getAccessType()));
    }
}
