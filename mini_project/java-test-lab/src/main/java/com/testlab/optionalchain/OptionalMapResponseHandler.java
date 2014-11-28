package com.testlab.optionalchain;

import com.testlab.optionalchain.support.Account;
import com.testlab.optionalchain.support.ResponseToAccountConverter;
import com.testlab.optionalchain.support.ServiceResponse;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class OptionalMapResponseHandler implements ServiceResponseHandler {

    private final ResponseToAccountConverter converter;

    public OptionalMapResponseHandler(ResponseToAccountConverter converter) {
        this.converter = converter;
    }

    @Override
    public Account handle(Optional<ServiceResponse> serviceResponse) {
        return serviceResponse.map(converter::convert).orElse(Account.getFallback());
    }
}
