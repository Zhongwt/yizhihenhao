package com.yzhh.backstage.api.util.pdf;

public class Test {

	public static void main(String[] args) {
		String str = "2018-9-12";
		System.out.println(str.substring(0,str.indexOf("-"))+"年"+str.substring(str.indexOf("-")+1,str.lastIndexOf("-"))+"月" + str.substring(str.lastIndexOf("-")+1,str.length())+"日");
	}
	
}
