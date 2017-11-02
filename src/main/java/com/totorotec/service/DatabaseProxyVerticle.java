package com.totorotec.service;

import io.vertx.core.AbstractVerticle;
// import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.serviceproxy.ServiceProxyBuilder;

/**
 * DatabaseProxyVerticle
 */
public class DatabaseProxyVerticle extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseProxyVerticle.class);

  @Override
  public void start() throws Exception {
    super.start();

    // 创建服务代理构造器
    ServiceProxyBuilder builder = new ServiceProxyBuilder(vertx).setAddress(DatabaseServiceVerticle.SERVICE_ADDRESS);
    // 构造服务
    DatabaseService service1 = builder.build(DatabaseService.class);

    // DeliveryOptions options = new DeliveryOptions();
    // options.addHeader("action", "getUserById");
    // DatabaseService service2 = builder.setOptions(options).build(DatabaseService.class);

    service1.getUserById(1, r -> {
      if(r.succeeded()) {

      }
    });

    // 上面从代理构建器创建服务实例的方法也可以通过 DatabaseService.createProxy 来创建
  }

}
