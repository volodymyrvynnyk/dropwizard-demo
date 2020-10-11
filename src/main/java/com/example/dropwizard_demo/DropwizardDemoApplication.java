package com.example.dropwizard_demo;

import com.example.dropwizard_demo.health.TemplateHealthCheck;
import com.example.dropwizard_demo.resources.DropwizardDemoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardDemoApplication extends Application<DropwizardDemoConfiguration> {

    public static void main(String[] args) throws Exception {
        new DropwizardDemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-demo";
    }

    @Override
    public void initialize(Bootstrap<DropwizardDemoConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DropwizardDemoConfiguration configuration, Environment environment) throws Exception {
        final DropwizardDemoResource resource = new DropwizardDemoResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
