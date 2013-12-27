package com.xk72.uglifyjs2.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOUtils {
	
	private static final int BUFSIZ = 65536;

	public static String read(Reader in) throws IOException {
		StringWriter out = new StringWriter();
		copy(in, out, BUFSIZ);
		return out.toString();
	}
	
	public static void copy(Reader in, Writer out, int bufsiz) throws IOException {
		char[] buf = new char[bufsiz];

		int read = in.read(buf, 0, bufsiz);
		while (read != -1) {
			out.write(buf, 0, read);
			read = in.read(buf, 0, bufsiz);
		}
	}
	
}
