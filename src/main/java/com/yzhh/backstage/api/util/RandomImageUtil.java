package com.yzhh.backstage.api.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

// 随机验证码图片生成
/**
 * Created by garychen on 18/1/16.
 */
public class RandomImageUtil {

    @SuppressWarnings("restriction")
	public String createRandomNumberImage(String number)
            throws Exception {

        BufferedImage image = createBufferedImage(number);

        //step2 压缩图片，然后输出
        /*
        response.setContentType("image/jpeg");
        OutputStream output = response.getOutputStream();
        javax.imageio.ImageIO.write(image, "jpeg", output);*/

        // bufferImage->base64
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
		BASE64Encoder encoder = new BASE64Encoder();
		String base64Img = encoder.encode(outputStream.toByteArray());

        return base64Img;
    }

    private BufferedImage createBufferedImage(String number) {
        //step1　创建一个图片,创建一个内存映像对象
        int width = 238, height = 66;
        BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        //得到画笔
        Graphics g = image.getGraphics();
        Random r = new Random();
        //设置画笔的颜色
        //g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        g.setColor(new Color(230,230,230));
        //设置整个画板的背景颜色
        //int w = 60, h = 20;
        g.fillRect(0,0, width, height);

        int size = 36;
        Font f = new Font("宋体",Font.BOLD ,size);
        g.setFont(f);

        g.setColor(new Color(0,0,0));
        int x = 71, y =50;
        g.drawString(number, x, y);
        return image;
    }

    //随机从"A~Z","0~9"当中取出５个字符
    public String getRandomNumber(int length) {
        Random r = new Random();
        char[] chars = {'A','B','C','D','E','F','G',
                'H','I','J','K','L','M','N','O','P','Q',
                'R','S','T','U','V','W',	'X','Y','Z','0',
                '1','2','3','4','5','6','7','8','9'};
        String number = "";
        for(int i=0;i<length;i++){
            number += chars[r.nextInt(chars.length)];
        }
        return number;
    }
    
}
