module.exports = {
	entry: './src/main/webapp/main.js',
	output: {
		filename: 'bundle.js',
		path: './src/main/webapp/build'
	},
	module: {
		loaders: [
			{
				test: /\.js?$/,
				loader: 'babel-loader',
				exclude: /node_modules/,
				query: {
					cacheDirectory: true,
					presets: ['react', 'es2015']
				}
			}
		]
	}
};