package com.totorotec.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * DatabaseConsumerVerticle
 */
public class DatabaseConsumerVerticle extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(DatabaseProxyVerticle.class);

  @Override
  public void start() throws Exception {
    super.start();

    // 获取代理
    DatabaseService databaseServiceProxy = DatabaseService.createProxy(vertx, DatabaseServiceVerticle.SERVICE_ADDRESS);

    // 通过代理调用服务接口
    databaseServiceProxy.getUserById(1, r -> {
      if (r.succeeded()) {
        logger.info(r.result().encodePrettily());
      } else {
        logger.error(r.cause());
      }
    });
  }

}
