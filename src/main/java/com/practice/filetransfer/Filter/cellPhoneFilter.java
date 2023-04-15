package com.practice.filetransfer.Filter;


import org.springframework.stereotype.Component;

import java.util.Arrays;

public class cellPhoneFilter {

	public static boolean cellphoneValidateCheck(String phoneNumber) {

		// 检查是否为11位数字
		if (!phoneNumber.matches("\\d{11}")) {
			return true;
		}

		// 检查前缀是否正确
		String prefix = phoneNumber.substring(0, 3);
		if (! Arrays.asList("130", "131", "132", "133", "134", "135", "136", "137", "138", "139",
				"144", "147", "150", "151", "152", "153", "155", "156", "157", "158",
				"159", "166", "170", "171", "172", "173", "174", "175", "176", "177",
				"178", "180", "181", "182", "183", "184", "185", "186", "187", "188",
				"189", "190", "191", "192", "193", "194", "195", "196", "197", "198",
				"199").contains(prefix)) {
			return true;
		}

		// 检查后缀是否为纯数字
		String suffix = phoneNumber.substring(3);
		if (!suffix.matches("\\d{8}")) {
			return true;
		}

		return false;
	}
}
