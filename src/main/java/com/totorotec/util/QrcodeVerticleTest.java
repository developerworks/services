package com.totorotec.util;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;

/**
 * Qrcode
 * http://crunchify.com/java-simple-qr-code-generator-example/
 */
public class QrcodeVerticleTest extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(Qrcode.class);

  @Override
  public void start() throws Exception {
    super.start();
    Qrcode qrcode = new Qrcode("https://gm.totorotec.cn", 380, "png", "/Users/hezhiqiang/totoro/_vertx-projects/services/logs/%s.%s");
    String s = qrcode.getQrcode();
    logger.info(s);
  }
}
