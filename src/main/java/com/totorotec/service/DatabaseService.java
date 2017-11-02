package com.totorotec.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import com.totorotec.service.impl.DatabaseServiceImpl;
@ProxyGen
@VertxGen
public interface DatabaseService {

  // 一些用于创建服务实例和服务代理实例的工厂方法
  static DatabaseService create(Vertx vertx) {
    return new DatabaseServiceImpl(vertx);
  }

  static DatabaseService createProxy(Vertx vertx, String address) {
    return new DatabaseServiceVertxEBProxy(vertx, address);
    // return ProxyHelper.createProxy(DatabaseService.class, vertx, address);
  }

  // 实际的服务方法
  // void save(String collection, JsonObject document, Handler<AsyncResult<Void>> resultHandler);

  public void getUserById(int id, Handler<AsyncResult<JsonObject>> resultHandler);
}
