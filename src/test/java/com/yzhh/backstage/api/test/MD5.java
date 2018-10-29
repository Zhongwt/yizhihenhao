package com.yzhh.backstage.api.test;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by hy on 2017/8/29.
 */
public class MD5 {
    /**
     *  
     *  生成md5 
     *   
     *  @param message 
     *  @return 
     */
    public static String getMD5(String message) {
        String md5str = "";
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象  
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 2 将消息变成byte数组  
            byte[] input = message.getBytes();
            // 3 计算后获得字节数组,这就是那128位了  
            byte[] buff = md.digest(input);

            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串  
            md5str = bytesToHex(buff);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    /**
     * 生成 sha256hmac 摘要
     * @param message
     * @param secret
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String sha256HMAC(String message, String secret) throws NoSuchAlgorithmException, InvalidKeyException{
        String secretMessage;
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256Hmac.init(secretKey);
        secretMessage = bytesToHex(sha256Hmac.doFinal(message.getBytes()));
        return secretMessage;
    }

    /**
     *      * 二进制转十六进制 
     *      *  
     *      * @param bytes 
     *      * @return 
     *      
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串  
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }
    
    //解码
    private static String convertMD5s(String inStr){  

        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  

    }  
    
    //两次解码
    public static String convertMD5(String inStr){  
    	return convertMD5s(convertMD5s(inStr));
    }  
    
    
    public static void main(String[] args) {
		System.out.println(getMD5("123"));
		 System.out.println("解密的：" + convertMD5(convertMD5("202CB962AC59075B964B07152D234B70")));  
	}
}
