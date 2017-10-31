package io.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {
        DeploymentOptions options = new DeploymentOptions().setInstances(1);
        vertx.deployVerticle("com.totorotec.util.Qrcode", options);
    }

}
