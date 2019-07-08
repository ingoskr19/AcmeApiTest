package co.com.prueba.ibm.util;

import java.io.IOException;
import java.io.OutputStream;

public class FileCopier {

	public static void copyBytes( byte[] bytes, OutputStream out) throws IOException {
		int offset = 0;
		int length = bytes.length;
		do {
			int size = 1024;
			if( offset+size>length )
				size = length-offset;
			out.write( bytes, offset, size );
			out.flush();
			offset += size;
		} while( offset<length );
	}
}
