package com.yzhh.backstage.api;

import org.springframework.util.StringUtils;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public static void main(String[] args) {
		
		System.out.println(AppTest.wildcardMatch("/api/123/123", "/api/123/23", "*"));
	}
	
	/**
     * 字符串匹配.
     * @param pattern   模板
     * @param str   要验证的字符串
     * @param wildcard 通配符
     * @return
     */
    public static boolean wildcardMatch(String pattern, String str, String wildcard) {
        if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(str)) {
            return false;
        }
        final boolean startWith = pattern.startsWith(wildcard);
        final boolean endWith = pattern.endsWith(wildcard);
        String[] array = StringUtils.split(pattern, wildcard);
        int currentIndex = -1;
        int lastIndex = -1 ;
        switch (array.length) {
            case 0:
                return true ;
            case 1:
                currentIndex = str.indexOf(array[0]);
                if (startWith && endWith) {
                    return currentIndex >= 0 ;
                }
                if (startWith) {
                    return currentIndex + array[0].length() == str.length();
                }
                if (endWith) {
                    return currentIndex == 0 ;
                }
                return str.equals(pattern) ;
            default:
                for (String part : array) {
                    currentIndex = str.indexOf(part);
                    if (currentIndex > lastIndex) {
                        lastIndex = currentIndex;
                        continue;
                    }
                    return false;
                }
                return true;
        }
    }
}
