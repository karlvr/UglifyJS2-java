/* http://lisperator.net/uglifyjs/compress */
function compress(code) {
	var ast = UglifyJS.parse(code);
	ast.figure_out_scope();
	var compressor = UglifyJS.Compressor({});
	ast = ast.transform(compressor);
	
	return ast.print_to_string();
}
