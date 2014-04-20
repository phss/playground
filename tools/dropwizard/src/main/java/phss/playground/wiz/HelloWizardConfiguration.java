package phss.playground.wiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class HelloWizardConfiguration extends Configuration {

    private String wizard = "Gandalf";

    @JsonProperty
    public String getWizard() {
        return wizard;
    }

    @JsonProperty
    public void setWizard(String wizard) {
        this.wizard = wizard;
    }
}
