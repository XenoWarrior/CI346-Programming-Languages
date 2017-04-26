var path = require('path');

module.exports = {
    entry: './src/main/webapp/js/main.js',
    devtool: 'sourcemaps',
    output: {
        path: __dirname,
        filename: './src/main/resources/js/main.js'
    },
    module: {
        loaders: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                loader: 'babel',
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            }
        ]
    }
};