package phss.playground.wiz;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWizardApplication extends Application<HelloWizardConfiguration> {

    @Override
    public String getName() {
        return "hello-wizard";
    }

    @Override
    public void initialize(Bootstrap<HelloWizardConfiguration> bootstrap) {

    }

    @Override
    public void run(HelloWizardConfiguration configuration, Environment environment) throws Exception {
        final HelloWizardResource wizardResource = new HelloWizardResource(configuration.getWizard());
        environment.jersey().register(wizardResource);
        environment.healthChecks().register("wizard", new HelloWizardHealthcheck());
    }

    public static void main(String[] args) throws Exception {
        new HelloWizardApplication().run(args);
    }
}
