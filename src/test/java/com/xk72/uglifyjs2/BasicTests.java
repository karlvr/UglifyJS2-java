package com.xk72.uglifyjs2;

import javax.script.ScriptException;

import org.junit.Test;

public class BasicTests {

	@Test
	public void testHelloWorld() throws ScriptException {
		System.out.println(new UglifyJS2().compress("(function() { var karl = \"abc\"; return karl; })();"));
	}
	
	@Test
	public void testPerformance() throws ScriptException {
		UglifyJS2 u = new UglifyJS2();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			u.compress("(function() { var karl = \"abc\"; return karl; })();");
		}
		System.out.println(System.currentTimeMillis() - startTime);
	}
	
}
