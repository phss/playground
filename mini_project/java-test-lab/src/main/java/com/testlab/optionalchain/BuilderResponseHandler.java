package com.testlab.optionalchain;

import com.testlab.optionalchain.support.Account;
import com.testlab.optionalchain.support.ResponseToAccountConverter;
import com.testlab.optionalchain.support.ServiceResponse;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class BuilderResponseHandler implements ServiceResponseHandler {

    private final ResponseToAccountConverter converter;

    public BuilderResponseHandler(ResponseToAccountConverter converter) {
        this.converter = converter;
    }

    private static class Process<T, V> {

        private final Optional<T> thing;
        private Function<T, V> processingFunction;

        public Process(Optional<T> thing) {
            this.thing = thing;
        }

        public Process<T, V> with(Function<T, V> processingFunction) {
            this.processingFunction = processingFunction;
            return this;
        }

        public V getOrFallbackTo(Supplier<V> fallbackFunction) {
            if (thing.isPresent()) {
                return processingFunction.apply(thing.get());
            } else {
                return fallbackFunction.get();
            }
        }
    }

    @Override
    public Account handle(Optional<ServiceResponse> serviceResponse) {
        return new Process<ServiceResponse, Account>(serviceResponse).with(converter::convert).getOrFallbackTo(Account::getFallback);
    }
}
