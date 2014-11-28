package com.testlab.optionalchain.support;

import org.junit.Test;

import static com.testlab.optionalchain.support.Account.AccessType.FULL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ResponseToAccountConverterTest {

    @Test
    public void convertsServiceResponseToAccount() {
        ServiceResponse response = new ServiceResponse(42, "45612", "FULL");

        Account account = new ResponseToAccountConverter().convert(response);

        assertThat(account.getAccountNumber(), equalTo(45612));
        assertThat(account.getAccessType(), equalTo(FULL));
    }

}
