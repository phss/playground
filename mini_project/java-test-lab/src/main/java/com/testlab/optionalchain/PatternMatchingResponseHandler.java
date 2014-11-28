package com.testlab.optionalchain;

import com.testlab.optionalchain.support.Account;
import com.testlab.optionalchain.support.ResponseToAccountConverter;
import com.testlab.optionalchain.support.ServiceResponse;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class PatternMatchingResponseHandler implements ServiceResponseHandler {

    private final ResponseToAccountConverter converter;

    public PatternMatchingResponseHandler(ResponseToAccountConverter converter) {
        this.converter = converter;
    }

    private static class PatternMatching<T> {

        public <V> V match(Optional<T> t, Function<T, V> ifFunction, Supplier<V> elseFunction) {
            if (t.isPresent()) {
                return ifFunction.apply(t.get());
            } else {
                return elseFunction.get();
            }
        }
    }

    @Override
    public Account handle(Optional<ServiceResponse> serviceResponse) {
        return new PatternMatching<ServiceResponse>().match(serviceResponse, converter::convert, Account::getFallback);
    }
}
