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

/** @module database-service-js/database_connection */
var utils = require('vertx-js/util/utils');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JDatabaseConnection = Java.type('com.totorotec.service.DatabaseConnection');

/**
 DatabaseConnection

 @class
*/
var DatabaseConnection = function(j_val) {

  var j_databaseConnection = j_val;
  var that = this;

  /**

   @public
   @param data {Object} 
   */
  this.insert = function(data) {
    var __args = arguments;
    if (__args.length === 1 && (typeof __args[0] === 'object' && __args[0] != null)) {
      j_databaseConnection["insert(io.vertx.core.json.JsonObject)"](utils.convParamJsonObject(data));
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   */
  this.close = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_databaseConnection["close()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_databaseConnection;
};

DatabaseConnection._jclass = utils.getJavaClass("com.totorotec.service.DatabaseConnection");
DatabaseConnection._jtype = {
  accept: function(obj) {
    return DatabaseConnection._jclass.isInstance(obj._jdel);
  },
  wrap: function(jdel) {
    var obj = Object.create(DatabaseConnection.prototype, {});
    DatabaseConnection.apply(obj, arguments);
    return obj;
  },
  unwrap: function(obj) {
    return obj._jdel;
  }
};
DatabaseConnection._create = function(jdel) {
  var obj = Object.create(DatabaseConnection.prototype, {});
  DatabaseConnection.apply(obj, arguments);
  return obj;
}
module.exports = DatabaseConnection;