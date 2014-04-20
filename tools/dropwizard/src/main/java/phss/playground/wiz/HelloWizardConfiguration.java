package phss.playground.wiz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class HelloWizardConfiguration extends Configuration {

    private String wizard = "Gandalf";

    @JsonCreator
    public HelloWizardConfiguration(@JsonProperty("wizard") String wizard) {
        this.wizard = wizard;
    }

    @JsonProperty("wizard")
    public String getWizard() {
        return wizard;
    }
}
