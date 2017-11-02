package com.totorotec.service;

import com.totorotec.service.impl.DatabaseServiceImpl;

import io.vertx.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;

/**
 * DatabaseServiceVerticle
 */
public class DatabaseServiceVerticle extends AbstractVerticle {

  /**
   * 定义服务所在的事件总线地址
   */
  public static final String SERVICE_ADDRESS = "service.database";

  @Override
  public void start() throws Exception {
    super.start();

    DatabaseService databaseService = new DatabaseServiceImpl(vertx);

    new ServiceBinder(vertx)
        // 设置地址
        .setAddress(SERVICE_ADDRESS)
        // 注册服务
        .register(DatabaseService.class, databaseService);
  }
}
