package com.yzhh.backstage.api.util;

import java.util.UUID;

public class UUIDUtils {

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-","");
	}
	
}
