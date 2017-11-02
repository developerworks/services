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
import io.vertx.core.json.JsonObject;

/**
 * DatabaseConnection
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link com.totorotec.service.DatabaseConnection original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.reactivex.RxGen(com.totorotec.service.DatabaseConnection.class)
public class DatabaseConnection {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DatabaseConnection that = (DatabaseConnection) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<DatabaseConnection> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new DatabaseConnection((com.totorotec.service.DatabaseConnection) obj),
    DatabaseConnection::getDelegate
  );

  private final com.totorotec.service.DatabaseConnection delegate;
  
  public DatabaseConnection(com.totorotec.service.DatabaseConnection delegate) {
    this.delegate = delegate;
  }

  public com.totorotec.service.DatabaseConnection getDelegate() {
    return delegate;
  }

  public void insert(JsonObject data) { 
    delegate.insert(data);
  }

  public void close() { 
    delegate.close();
  }


  public static  DatabaseConnection newInstance(com.totorotec.service.DatabaseConnection arg) {
    return arg != null ? new DatabaseConnection(arg) : null;
  }
}
