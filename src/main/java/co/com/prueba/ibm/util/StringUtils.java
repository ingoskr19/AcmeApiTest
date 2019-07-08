package co.com.prueba.ibm.util;

public final class StringUtils {

	public static boolean isNull( Object value ){
		if( value==null ) return true;
		if( value instanceof java.lang.String && value.equals("") ) return true;
		return false;
	}
}
