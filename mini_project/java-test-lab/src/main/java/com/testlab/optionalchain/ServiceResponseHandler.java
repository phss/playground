package com.testlab.optionalchain;

import com.testlab.optionalchain.support.Account;
import com.testlab.optionalchain.support.ServiceResponse;

import java.util.Optional;

public interface ServiceResponseHandler {
    Account handle(Optional<ServiceResponse> serviceResponse);
}
