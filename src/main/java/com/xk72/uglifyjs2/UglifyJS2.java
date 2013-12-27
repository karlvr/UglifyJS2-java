package com.xk72.uglifyjs2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.xk72.uglifyjs2.util.IOUtils;

/**
 * https://github.com/mishoo/UglifyJS2/issues/122
 * @author karlvr
 *
 */
public class UglifyJS2 {

	private static final String UGLIFYJS2_COMBINED_JS = "javascript/uglifyjs2.combined.js";

	private ScriptEngine js;
	
	public UglifyJS2() throws ScriptException {
		super();
		js = getScriptEngine();
	}

	public String compress(String code, boolean mangle) throws ScriptException {
		js.put("code", code);
		return (String) js.eval("compress(code, " + mangle + ")");
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
	
	private ScriptEngine getScriptEngine() throws ScriptException {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine js = sem.getEngineByName("JavaScript");
		js.eval(new InputStreamReader(UglifyJS2.class.getResourceAsStream(UGLIFYJS2_COMBINED_JS)));
		js.eval(new InputStreamReader(UglifyJS2.class.getResourceAsStream("javascript/compress.js")));
		return js;
	}
	
}
