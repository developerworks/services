/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.totorotec.service.reactivex;

import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.vertx.reactivex.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;


@io.vertx.lang.reactivex.RxGen(com.totorotec.service.DatabaseService.class)
public class DatabaseService {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DatabaseService that = (DatabaseService) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<DatabaseService> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new DatabaseService((com.totorotec.service.DatabaseService) obj),
    DatabaseService::getDelegate
  );

  private final com.totorotec.service.DatabaseService delegate;
  
  public DatabaseService(com.totorotec.service.DatabaseService delegate) {
    this.delegate = delegate;
  }

  public com.totorotec.service.DatabaseService getDelegate() {
    return delegate;
  }

  public static DatabaseService create(Vertx vertx) { 
    DatabaseService ret = DatabaseService.newInstance(com.totorotec.service.DatabaseService.create(vertx.getDelegate()));
    return ret;
  }

  public static DatabaseService createProxy(Vertx vertx, String address) { 
    DatabaseService ret = DatabaseService.newInstance(com.totorotec.service.DatabaseService.createProxy(vertx.getDelegate(), address));
    return ret;
  }

  public void getUserById(int id, Handler<AsyncResult<JsonObject>> resultHandler) { 
    delegate.getUserById(id, resultHandler);
  }

  public Single<JsonObject> rxGetUserById(int id) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<JsonObject>(handler -> {
      getUserById(id, handler);
    });
  }


  public static  DatabaseService newInstance(com.totorotec.service.DatabaseService arg) {
    return arg != null ? new DatabaseService(arg) : null;
  }
}
