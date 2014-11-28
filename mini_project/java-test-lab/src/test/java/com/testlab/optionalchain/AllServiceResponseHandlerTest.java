package com.testlab.optionalchain;

import com.testlab.optionalchain.support.Account;
import com.testlab.optionalchain.support.ResponseToAccountConverter;
import com.testlab.optionalchain.support.ServiceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static com.testlab.optionalchain.support.Account.AccessType.GUEST;
import static com.testlab.optionalchain.support.Account.AccessType.PARTIAL;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class AllServiceResponseHandlerTest {

    @Parameters
    public static Collection<Object[]> data() {
        ResponseToAccountConverter converter = new ResponseToAccountConverter();
        return asList(new Object[][] {
                { new IffyServiceResponseHandler(converter) },
                { new PatternMatchingResponseHandler(converter) }
        });
    }

    public ServiceResponseHandler handler;

    public AllServiceResponseHandlerTest(ServiceResponseHandler handler) {
        this.handler = handler;
    }

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
        assertThat(account, is(Account.getFallback()));
    }
}
