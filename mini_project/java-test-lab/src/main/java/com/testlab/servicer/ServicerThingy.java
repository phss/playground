package com.testlab.servicer;

import com.testlab.models.Account;
import com.testlab.models.ServiceResponse;

import java.util.Optional;

public interface ServicerThingy {
    Account handle(Optional<ServiceResponse> serviceResponse);
}
