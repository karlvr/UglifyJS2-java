package com.xk72.uglifyjs2;

import java.io.IOException;

import javax.script.ScriptException;

import org.junit.Assert;
import org.junit.Test;

public class BasicTests {

	@Test
	public void testBasic() throws ScriptException {
		String result = new UglifyJS2().compress("(function() { var karl = \"abc\"; return karl; })();");
		Assert.assertEquals("(function(){var karl=\"abc\";return karl})();", result);
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
	
	@Test
	public void testJQuery() throws ScriptException, IOException {
		UglifyJS2 u = new UglifyJS2();
		long startTime = System.currentTimeMillis();
		u.compress(getClass().getResource("javascript/jquery.js"));
		System.out.println(System.currentTimeMillis() - startTime);
	}
	
}
