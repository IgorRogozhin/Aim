package ru.aim.utilities;

/**
 * Utility class
 * 
 */
public class AimManagerUtils {

	/**
	 * Checks given String on empty and <b>null</b> values
	 * @param x
	 * @return boolean
	 */
	public static boolean stringNullOrEmpty(String x)
	{
		if (x == null) return true;
		if (x.length() == 0) return true;
		return false;
	}
	
	/**
	 * Checks given String[] on zero-length and <b>null</b> value
	 * @param x
	 * @return boolean
	 */
	public static boolean arrayNullOrEmpty(String[] x)
	{
		if (x == null) return true;
		if (x.length == 0) return true;
		return false;
	}
	
}
