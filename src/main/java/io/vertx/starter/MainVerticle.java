package io.vertx.starter;

import com.totorotec.service.DatabaseConsumerVerticle;
import com.totorotec.service.DatabaseServiceVerticle;
import com.totorotec.util.QrcodeVerticleTest;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    DeploymentOptions options = new DeploymentOptions().setInstances(1);
    // vertx.deployVerticle("com.totorotec.util.QrcodeVerticleTest", options);
    vertx.deployVerticle(QrcodeVerticleTest.class.getName(), options);

    vertx.deployVerticle(DatabaseServiceVerticle.class.getName(), options);
    vertx.setTimer(1000, id -> {
      vertx.deployVerticle(DatabaseConsumerVerticle.class.getName(), options);
    });
  }

}
