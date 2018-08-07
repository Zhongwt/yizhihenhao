package com.yzhh.backstage.api.util;

import java.util.UUID;

public class UUIDUtils {

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-","");
	}
	
	public static void main(String[] args) {
		String str =UUIDUtils.getUUID();
		System.out.println(str+":"+str.length());
	}
	
}
