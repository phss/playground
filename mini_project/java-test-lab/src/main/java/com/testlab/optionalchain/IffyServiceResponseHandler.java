package com.testlab.optionalchain;

import com.testlab.optionalchain.support.Account;
import com.testlab.optionalchain.support.ResponseToAccountConverter;
import com.testlab.optionalchain.support.ServiceResponse;

import java.util.Optional;

public class IffyServiceResponseHandler implements ServiceResponseHandler {

    private final ResponseToAccountConverter converter;

    public IffyServiceResponseHandler(ResponseToAccountConverter converter) {
        this.converter = converter;
    }

    @Override
    public Account handle(Optional<ServiceResponse> serviceResponse) {
        if (serviceResponse.isPresent()) {
            return converter.convert(serviceResponse.get());
        } else {
            return new Account(-1, Account.AccessType.GUEST);
        }
    }
}
