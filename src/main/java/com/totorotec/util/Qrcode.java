package com.totorotec.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

/**
 * Qrcode
 * http://crunchify.com/java-simple-qr-code-generator-example/
 */
public class Qrcode extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(Qrcode.class);

    @Override
    public void start() throws Exception {
        super.start();
        // 二维码内容
        String myCodeText = "https://gm.totorotec.cn";
        // 长宽
        int size = 380;
        // 输出类型: 支持文件, DataURL
        String outputType = "file";
        // 文件类型
        String imageType = "png";
        // 临时文件路径
        String filePath = String.format("/Users/hezhiqiang/totoro/_vertx-projects/services/_tmp/%s.png",
                UUID.randomUUID().toString());
        // 文件对象
        File myFile = new File(filePath);

        // TODO: 上传到云端
        // String bucketName = "promotion-images";

        try {

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
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
            int matrixWidth = byteMatrix.getWidth();
            // 图片缓冲区
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            // 创建
            image.createGraphics();
            // 获取
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            // 色彩
            graphics.setColor(Color.WHITE);
            // 填充矩形
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
            // 色彩
            graphics.setColor(Color.BLACK);
            // 循环填充
            for (int i = 0; i < matrixWidth; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            if (outputType == "dataurl") {
                // 创建输出流
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 写入流
                ImageIO.write(image, imageType, baos);
                // BASE64编码
                String base64 = DatatypeConverter.printBase64Binary(baos.toByteArray());
                @SuppressWarnings("unused")
                String dataUrl = String.format("data:image/%s;base64,%s", imageType, base64);
                // 日志
                String msg = String.format("Qrcode image file is encoded to base 64 data url, image type: %s",
                        imageType);
                logger.info(msg);
                // TODO: 通过 EventBus 发送
                // return dataUrl;
            } else if (outputType == "file") {
                ImageIO.write(image, imageType, myFile);
                String msg = String.format("Qrcode image file is written to %s", filePath);
                logger.info(msg);
                // return myFile.getPath();
            } else {
                // 不支持的输出类型
                logger.warn("Output type is not supported.");
            }
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myFile.delete();
            logger.info(String.format("Image file %s has been deleted.", myFile.getPath()));
        } catch (SecurityException e) {
            String msg = String.format("Can not delete file: %s", myFile.getPath());
            logger.error(msg, e);
        }
        logger.info("You have successfully created QR Code.");
    }
}
