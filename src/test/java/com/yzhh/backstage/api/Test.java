package com.yzhh.backstage.api;

public class Test {

	public static void main(String[] args) {
		int COUNT_BITS = Integer.SIZE - 3;
		int CAPACITY   = (1 << COUNT_BITS);
		int SHUTDOWN   =  0 << COUNT_BITS;
		System.out.println(COUNT_BITS);
		System.out.println(Integer.toBinaryString(CAPACITY));
		System.out.println(Integer.toBinaryString(CAPACITY).length());
		System.out.println(Integer.toBinaryString(SHUTDOWN));
		
	}
}
