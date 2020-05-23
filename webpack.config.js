const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = {
    mode: 'development',
    devtool: 'source-map',
    entry: {
        main: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'main.js'),
        registration: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'registration.js'),
        login: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'login.js'),
        clinic: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'clinic.js'),
        clinicProfile: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'clinicProfile.js'),
        doctorProfile: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'doctorProfile.js'),
        request: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'request.js'),
        listDoctors: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'listDoctors.js'),
        listPatients: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'listPatients.js'),
        addChild: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'addChild.js'),
        state: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'state.js')
    },
    devServer: {
        contentBase: './dist',
        compress: true,
        port: 8000,
        allowedHosts: [
            'localhost:9090'
        ]
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.css$/,
                use: [
                    'vue-style-loader',
                    'css-loader'
                ]
            }
        ]
    },
    plugins: [
        new VueLoaderPlugin()
    ],
    resolve: {
        modules: [
            path.join(__dirname, 'src', 'main', 'resources', 'static', 'js'),
            path.join(__dirname, 'node_modules'),
        ],
    }
};