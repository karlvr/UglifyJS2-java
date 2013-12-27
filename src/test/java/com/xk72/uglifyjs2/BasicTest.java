package com.xk72.uglifyjs2;

import java.io.IOException;

import javax.script.ScriptException;

import org.junit.Assert;
import org.junit.Test;

public class BasicTest {

	@Test
	public void testBasicCompress() throws ScriptException, IOException {
		String result = new UglifyJS2().compress("(function() { var karl = \"abc\"; return karl; })();");
		Assert.assertEquals("(function(){var karl=\"abc\";return karl})();", result);
	}

	@Test
	public void testBasicMangle() throws ScriptException, IOException {
		String result = new UglifyJS2().compress("(function() { var karl = \"abc\"; return karl; })();", true);
		Assert.assertEquals("(function(){var n=\"abc\";return n})();", result);
	}
	
}
