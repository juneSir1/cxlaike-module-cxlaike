package net.hzxzkj.cxlaike.module.cxlaike.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author jianan.han
 * @date 2023/9/22 下午1:59
 * @description
 */
public class GraphUtil {


    /**
     * 生成海报
     *
     * @param invitationCode 邀请码
     * @param url            路径
     * @param input          海报
     * @return java.io.InputStream
     * @author hjn
     * @date 2023/9/22 下午2:17
     */
    public static byte[] generatePlacard(String invitationCode, String url, byte[] input) {
        try {
            // 生成二维码
            String qrCodeData = String.format(url, invitationCode); // 包含邀请码的URL或文本信息
            int qrCodeSize = 300;

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize);

            BufferedImage qrCodeImage = new BufferedImage(qrCodeSize, qrCodeSize, BufferedImage.TYPE_INT_RGB);
            qrCodeImage.createGraphics();

            Graphics2D graphics = (Graphics2D) qrCodeImage.getGraphics();
            graphics.setColor(java.awt.Color.WHITE);
            graphics.fillRect(0, 0, qrCodeSize, qrCodeSize);
            graphics.setColor(java.awt.Color.BLACK);

            for (int i = 0; i < qrCodeSize; i++) {
                for (int j = 0; j < qrCodeSize; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            InputStream inputStream = new ByteArrayInputStream(input);
            // 加载另一张图片
            BufferedImage backgroundImage = ImageIO.read(inputStream);

            // 合并二维码和另一张图片
            int mergedImageWidth = backgroundImage.getWidth();
            int mergedImageHeight = backgroundImage.getHeight();
            BufferedImage mergedImage = new BufferedImage(mergedImageWidth, mergedImageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D mergedGraphics = mergedImage.createGraphics();

            mergedGraphics.drawImage(backgroundImage, 0, 0, null);
            int qrCodeX = 470; // 二维码在合并后图片中的X坐标
            int qrCodeY = 1600; // 二维码在合并后图片中的Y坐标
            mergedGraphics.drawImage(qrCodeImage, qrCodeX, qrCodeY, null);
            // 将合并后的图片转换为流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(mergedImage, "jpg", outputStream);
            return outputStream.toByteArray();
//            // 保存合并后的图片

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成海报
     * @param input1 海报
     * @param input2 二维码
     * @return java.io.InputStream
     * @author hjn
     * @date 2023/9/22 下午2:17
     */
    public static byte[] generatePlacard(byte[] input1, byte[] input2,int x,int y) {
        try {

            InputStream inputStream1 = new ByteArrayInputStream(input1);
            // 加载另一张图片
            BufferedImage image1 = ImageIO.read(inputStream1);


            InputStream inputStream2 = new ByteArrayInputStream(input2);
            // 加载另一张图片
            BufferedImage image2 = ImageIO.read(inputStream2);
            // 创建合成后的图像
            int width = Math.max(image1.getWidth(), image2.getWidth());
            int height = Math.max(image1.getHeight(), image2.getHeight());
            BufferedImage mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            // 获取合成图像的绘图上下文
            Graphics2D g2d = mergedImage.createGraphics();

            // 绘制第一张图像
            g2d.drawImage(image1, 0, 0, null);

            // 绘制第二张图像
            g2d.drawImage(image2, x, y, null);

            // 将合并后的图片转换为流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(mergedImage, "PNG", outputStream);
            // 保存合成后的图像
            //ImageIO.write(mergedImage, "PNG", new File("/Users/hjn/Desktop/创享来客/img.png"));
            // 释放绘图上下文资源
            return outputStream.toByteArray();



        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成任务海报
     * @param input 海报
     * @return java.io.InputStream
     * @author hjn
     * @date 2023/9/22 下午2:17
     */
    @SneakyThrows
    public static byte[] generateInvitation(byte[] input,String taskDay,String taskYear,String taskTime
            ,String taskTitle,String taskTypeStr,String taskAmountStr,String taskProfession) {
        InputStream inputStream = new ByteArrayInputStream(input);
        //String outputImageFile = "/Users/hjn/Desktop/创享来客/output.jpg"; // 输出图像文件路径
        // 加载另一张图片
        BufferedImage backgroundImage = ImageIO.read(inputStream);
        BufferedImage outputImage = addTextToImage(backgroundImage,taskDay,taskYear,taskTime,taskTitle,taskTypeStr,taskAmountStr,taskProfession);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        saveImage(outputImage, outputImageFile);
        ImageIO.write(outputImage, "PNG", outputStream);
        return outputStream.toByteArray();
    }



    @SneakyThrows
    private static BufferedImage addTextToImage(BufferedImage image, String taskDay, String taskYear, String taskTime
            , String taskTitle, String taskTypeStr, String taskAmountStr, String taskProfession) {

        Font font = new Font("Antiqua", Font.BOLD, 70);
//        String taskDay = "9/10--9/11";
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.setColor(Color.red);
        graphics.setFont(font);
        FontMetrics fontMetrics = graphics.getFontMetrics();
//        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getHeight();
        graphics.drawString(taskDay, 110, 580 + textHeight);

//        String taskYear = "2023";
        font = new Font("Antiqua", Font.BOLD, 130);
        graphics.setFont(font);
        graphics.drawString(taskYear, 110, 800);

//        String taskTime = "7:30--18:00";
        font = new Font("Antiqua", Font.BOLD, 50);
        graphics.setFont(font);
        graphics.drawString(taskTime, 110, 900);

//        String taskTitle = "实探真人-3";
        font = new Font("Antiqua", Font.BOLD, 60);
        graphics.setFont(font);
        graphics.drawString(taskTitle, 110, 1340);

//        String taskTypeStr = "云探";
        font = new Font("Antiqua", Font.BOLD, 70);
        graphics.setFont(font);
        graphics.setColor(Color.black);
        graphics.drawString(taskTypeStr, 450, 1440);

//        String taskAmountStr = "¥ 10.00";
        font = new Font("Antiqua", Font.BOLD, 60);
        graphics.setFont(font);
        graphics.setColor(Color.black);
        graphics.drawString(taskAmountStr, 450, 1550);

//        String taskProfession = "美食.火锅";
        font = new Font("Antiqua", Font.BOLD, 70);
        graphics.setFont(font);
        graphics.setColor(Color.black);
        graphics.drawString(taskProfession, 450, 1660);
        graphics.dispose();

        return newImage;
    }

}
