package com.yzhh.backstage.api.util;

/**
 * @description:验证吗生成器
 * @projectName:school-biz
 * @className:VerificationCodeProvider.java
 * @author:衷文涛
 * @createTime:2017年10月19日 上午11:17:57
 * @version 1.0
 */
public class VerificationCodeProvider
{

	public static final int LENGTH = 6;

	// 生成验证码
	public static String getVerificationCode(int length)
	{
		char[] ss = new char[length];
		int i = 0;
		while (i < length)
		{
			i = i % length;
			int f = (int) (Math.random() * 3 % 3);
			System.out.println(f );
			if (f == 0)
				ss[i] = (char) ('A' + Math.random() * 26);
			else if (f == 1)
				ss[i] = (char) ('a' + Math.random() * 26);
			else
				ss[i] = (char) ('0' + Math.random() * 10);
			i++;
		}
		return new String(ss);
	}

}
