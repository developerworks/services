package com.totorotec.service;

import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.json.JsonObject;

/**
 * DatabaseConnection
 */

@ProxyGen
@VertxGen

public interface DatabaseConnection {
  void insert(JsonObject data);

  @ProxyClose
  void close();
}
