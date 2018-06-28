package com.yzhh.backstage.api.util;

import java.util.Collection;

public class CollectionUtils {

	public static boolean isNotEmpty(Collection<?> c) {
		return !org.springframework.util.CollectionUtils.isEmpty(c);
	}
	
	public static boolean isEmpty(Collection<?> c) {
		return org.springframework.util.CollectionUtils.isEmpty(c);
	}
}
