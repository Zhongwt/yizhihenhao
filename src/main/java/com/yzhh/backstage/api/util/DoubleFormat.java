package com.yzhh.backstage.api.util;

import java.text.DecimalFormat;

public class DoubleFormat {
	
	public static String m2(double f) {
		if(f == 0) {
			return "0.00";
		}
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(f);
    }
	
	public static void main(String[] args) {
		System.out.println(DoubleFormat.m2(0D));
	}
}
