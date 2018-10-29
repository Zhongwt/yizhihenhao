package com.yzhh.backstage.api.util;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yzhh.backstage.api.commons.BizException;

/**
 * @description:二维码
 * @projectName:backstage-api
 * @className:QrCide.java
 * @author:wentao
 * @createTime:2018年8月9日 下午7:17:10
 * @version 1.0.1
 */
public class QrCide {
	
	/**
	 * @Description:将连接保存为二维码
	 * @author:
	 * @createTime:2018年6月6日 上午11:40:06
	 */
	@SuppressWarnings("unchecked")
	public static void createQrCode(String name, String scenes) {
		int width = 300;//图片的宽度
		int height = 300;//图片的高度
		String format = "png";//图片的格式
		String contents = scenes; //图片的内容,即网址链接
		//定义二维码的参数
		@SuppressWarnings("rawtypes")
		HashMap map = new HashMap();
		map.put(EncodeHintType.CHARACTER_SET, "utf-8");//字符集
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//纠错等级LMQH,一般用M
		map.put(EncodeHintType.MARGIN, 2);//边距
		//生成二维码
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, map);
			String path="/home/yzhh/img/qr/"+name+".png";
			//String path = "D:/code/test/"+name+".png";
			Path file = new File(path).toPath();
			MatrixToImageWriter.writeToPath(bitMatrix, format, file);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("错误信息:"+e.getMessage());
			throw new BizException("二维码生成失败");
		}
	}
	
	public static void main(String[] args) {
		QrCide.createQrCode("222", "http://47.95.118.92:9080/api/job/position/info?id=18");
	}
}
