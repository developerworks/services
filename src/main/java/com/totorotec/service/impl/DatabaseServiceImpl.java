package com.totorotec.service.impl;

import com.totorotec.service.DatabaseService;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * DatabaseServiceImpl
 */
public class DatabaseServiceImpl implements DatabaseService {

  public DatabaseServiceImpl(Vertx vertx) {
    // TODO:
    // 获取数据连接
	}

/**
   * 从一个Feture对象获取结果, 而不是立即返回
   */
  @Override
  public void getUserById(int id, Handler<AsyncResult<JsonObject>> resultHandler) {
    JsonObject result = new JsonObject().put("name", "hezhiqiang").put("email", "developerworks@163.com");
    // return result;
    resultHandler.handle(Future.succeededFuture(result));
  }

  // @Override
  // public void save(String collection, JsonObject document, Handler<AsyncResult<Void>> resultHandler) {

  // }

}
