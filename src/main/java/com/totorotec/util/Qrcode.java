package com.totorotec.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
// import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
// import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

class Qrcode {
  private static final Logger logger = LoggerFactory.getLogger(Qrcode.class);

  // 二维码生成参数
  private String text;
  private String outputType = "";
  private String imageType = "png";
  private int size = 128;
  // 如果使用dataURL, 该成员为空字符串
  private String filePath = "/Users/hezhiqiang/totoro/_vertx-projects/services/_tmp/%s.%s";
  private File imageFile = null;

  /**
   * 输出为dataUrl格式的base64字符串
   *
   * @param text 二维码内容
   * @param size 长宽
   * @param filePath 文件输出路径, 格式为 /tmp/%s.%s, 第一个 %s 为文件名称, 第二个 %s, 为文件类型
   */
  public Qrcode(String text, int size, String imageType) {
    this.text = text;
    // this.outputType = outputType;
    this.size = size;
    this.imageType = imageType;
    this.outputType = "dataurl";
  }

  /**
   * 把图片输出到临时目录准备进一步处理(比如上传到云存储)
   *
   * @param text 二维码内容
   * @param size 长宽
   * @param imageType 图像格式,png,jpg 等
   * @param filePath 文件输出路径, 格式为 /tmp/%s.%s, 第一个 %s 为文件名称, 第二个 %s, 为文件类型
   */
  public Qrcode(String text, int size, String imageType, String filePath) {
    this.text = text;
    // this.outputType = outputType;
    this.size = size;
    this.imageType = imageType;
    // this.filePath = filePath;
    this.filePath = String.format(filePath, UUID.randomUUID().toString(), imageType);
    this.outputType = "file";
    this.imageFile = new File(this.filePath);
  }

  public String getQrcode() throws Exception {

    Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
    // 字符集
    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
    // 设置白边大小
    hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
    // 纠错等级
    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    // 获取字节矩阵, 指定格式,大小,选项
    // BitMatrix 是通过 qrCodeWriter 编码得到的字节矩阵, 包含二维码黑白色块信息
    BitMatrix byteMatrix = qrCodeWriter.encode(this.text, BarcodeFormat.QR_CODE, size, size, hintMap);
    int matrixWidth = byteMatrix.getWidth();
    // 图片缓冲区
    BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
    // 创建
    image.createGraphics();
    // 获取
    Graphics2D graphics = (Graphics2D) image.getGraphics();
    // 用全白色块填充整个矩阵
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, matrixWidth, matrixWidth);
    // 用编码的黑色色块覆盖
    graphics.setColor(Color.BLACK);
    for (int i = 0; i < matrixWidth; i++) {
      for (int j = 0; j < matrixWidth; j++) {
        if (byteMatrix.get(i, j)) {
          graphics.fillRect(i, j, 1, 1);
        }
      }
    }
    // 输出类型
    // 目前仅支持 file, dataurl 两种方式
    if (outputType == "dataurl") {
      // 创建输出流
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      // 写入流
      ImageIO.write(image, imageType, baos);
      // BASE64编码
      String base64 = DatatypeConverter.printBase64Binary(baos.toByteArray());
      String dataUrl = String.format("data:image/%s;base64,%s", imageType, base64);
      // logger.info(dataUrl);
      // 日志
      String msg = String.format("Qrcode image file is encoded to base 64 data url, image type: %s", imageType);
      logger.info(msg);
      return dataUrl;
    } else if (outputType == "file") {
      try {
        ImageIO.write(image, imageType, imageFile);
        // String msg = String.format("Qrcode image file is written to %s", filePath);
        logger.info(String.format("Qrcode image file is written to %s", filePath));
        imageFile.delete();
        logger.info(String.format("Image file %s has been deleted.", imageFile.getPath()));
      } catch (SecurityException e) {
        String msg = String.format("Can not delete file: %s", imageFile.getPath());
        logger.error(msg, e);
      }
      return imageFile.getPath();
    } else {
      // 不支持的输出类型
      logger.warn("Output type is not supported.");
      return "";
    }

  }

  /**
   * @return the outputType
   */
  public String getOutputType() {
    return outputType;
  }

  /**
   * @param outputType the outputType to set
   */
  public void setOutputType(String outputType) {
    this.outputType = outputType;
  }

  /**
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * @param text the text to set
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * @return the imageType
   */
  public String getImageType() {
    return imageType;
  }

  /**
   * @param imageType the imageType to set
   */
  public void setImageType(String imageType) {
    this.imageType = imageType;
  }

  /**
   * @return the imageFile
   */
  public File getImageFile() {
    return imageFile;
  }

  /**
   * @param imageFile the imageFile to set
   */
  public void setImageFile(File imageFile) {
    this.imageFile = imageFile;
  }

}
