package phss.playground.wiz;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class HelloWizardApplication extends Application<HelloWizardConfiguration> {

    @Override
    public String getName() {
        return "hello-wizard";
    }

    @Override
    public void initialize(Bootstrap<HelloWizardConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
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
