package com.testlab.optionalchain;

import com.testlab.optionalchain.support.Account;
import com.testlab.optionalchain.support.ServiceResponse;

import java.util.Optional;

public interface ServicerThingy {
    Account handle(Optional<ServiceResponse> serviceResponse);
}
