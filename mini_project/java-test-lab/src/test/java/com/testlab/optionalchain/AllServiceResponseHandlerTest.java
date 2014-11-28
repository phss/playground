package com.testlab.optionalchain;

import com.testlab.optionalchain.support.Account;
import com.testlab.optionalchain.support.ResponseToAccountConverter;
import com.testlab.optionalchain.support.ServiceResponse;
import org.junit.Test;

import java.util.Optional;

import static com.testlab.optionalchain.support.Account.AccessType.GUEST;
import static com.testlab.optionalchain.support.Account.AccessType.PARTIAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AllServiceResponseHandlerTest {

    private final IffyServiceResponseHandler handler = new IffyServiceResponseHandler(new ResponseToAccountConverter());

    @Test
    public void convertsResponseToAccountWhenAvailable() {
        Optional<ServiceResponse> response = Optional.of(new ServiceResponse(12, "1234", "PARTIAL"));

        Account account = handler.handle(response);

        assertThat(account.getAccountNumber(), equalTo(1234));
        assertThat(account.getAccessType(), equalTo(PARTIAL));
    }

    @Test
    public void fallbackWhenUnavailable() {
        Optional<ServiceResponse> response = Optional.empty();

        Account account = handler.handle(response);

        assertThat(account.getAccountNumber(), equalTo(-1));
        assertThat(account.getAccessType(), equalTo(GUEST));
    }
}
