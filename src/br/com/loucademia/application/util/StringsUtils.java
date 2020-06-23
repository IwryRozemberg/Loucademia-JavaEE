package br.com.loucademia.application.util;

public class StringsUtils {
	public static boolean isEmpty(String value) {
		if (value == null) {
			return true;
		}
		return value.trim().length() == 0;
	}
	
	public static String letfZeros(int value, int size) {
		return String.format("%0" + size + "d", value);
	}
}
