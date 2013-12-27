package com.xk72.uglifyjs2;

import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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

	public String compress(String code) throws ScriptException {
		js.put("code", code);
		return (String) js.eval("compress(code)");
	}
	
	private ScriptEngine getScriptEngine() throws ScriptException {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine js = sem.getEngineByName("JavaScript");
		js.eval(new InputStreamReader(UglifyJS2.class.getResourceAsStream(UGLIFYJS2_COMBINED_JS)));
		js.eval(new InputStreamReader(UglifyJS2.class.getResourceAsStream("javascript/compress.js")));
		return js;
	}
	
}
