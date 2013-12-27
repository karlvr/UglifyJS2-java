package com.xk72.uglifyjs2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import javax.script.ScriptException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.ScriptableObject;

import com.xk72.uglifyjs2.util.IOUtils;

/**
 * https://github.com/mishoo/UglifyJS2/issues/122
 * @author karlvr
 *
 */
public class UglifyJS2 {

	private static final String UGLIFYJS2_COMBINED_JS = "javascript/uglifyjs2.combined.js";

	private ScriptableObject scope;
	
	public UglifyJS2() throws ScriptException, IOException {
		super();
		init();
	}

	public String compress(String code, boolean mangle) throws ScriptException {
		final Context js = Context.enter();
		try {
			scope.defineProperty("code", code, ScriptableObject.READONLY);
			return (String) js.evaluateString(scope, "compress(code, " + mangle + ")", "", 1, null);
		} finally {
			Context.exit();
		}
	}
	
	public String compress(String code) throws ScriptException {
		return compress(code, false);
	}

	public String compress(Reader reader, boolean mangle) throws ScriptException, IOException {
		return compress(IOUtils.read(reader), mangle);
	}
	
	public String compress(Reader reader) throws ScriptException, IOException {
		return compress(reader, false);
	}

	public String compress(URL resource, boolean mangle) throws IOException, ScriptException {
		InputStream inputStream = resource.openStream();
		return compress(new InputStreamReader(inputStream), mangle);
	}
	
	public String compress(URL resource) throws IOException, ScriptException {
		return compress(resource, false);
	}
	
	private void init() throws ScriptException, IOException {
		Context js = Context.enter();
		try {
			js.setOptimizationLevel(9);
			
			ImporterTopLevel scope = new ImporterTopLevel(js);
			js.evaluateReader(scope, new InputStreamReader(UglifyJS2.class.getResourceAsStream(UGLIFYJS2_COMBINED_JS)), UGLIFYJS2_COMBINED_JS, 1, null);
			js.evaluateReader(scope, new InputStreamReader(UglifyJS2.class.getResourceAsStream("javascript/compress.js")), "compress.js", 1, null);
			
			this.scope = scope;
		} finally {
			Context.exit();
		}
	}
	
}
