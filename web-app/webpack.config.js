'use strict';

var path = require("path");
var webpack = require('webpack');

const CleanWebPackPlugin = require('clean-webpack-plugin');

module.exports = {

    entry: {
        index: path.resolve(__dirname,'./bundles/index.js'),
        app: path.resolve(__dirname,'./js/app.js')
    },

    output: {
        path: path.resolve(__dirname, 'target/assets'),
        publicPath: "/assets/",
        filename: '[name]-bundle.js'
    },

    devtool: "source-map",

    plugins: [
        new CleanWebPackPlugin('target/assets'),
        new webpack.NoEmitOnErrorsPlugin(),
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery'
        })
    ],

    module: {
        loaders: [
            {
                test: /\.css$/,
                use: [
                    {
                        loader: 'style-loader'
                    },
                    {
                        loader: 'css-loader'
                    }
                ]
            },

            {
                test: /\.woff2?$|\.ttf$|\.eot$|\.svg$/,
                use: [{
                    loader: "file-loader"
                }]
            },

            {
                test: /\.(png|jpg|svg)$/,
                use: [
                    {
                        loader: 'url-loader',
                        options: {
                            query: {
                                limit: '8192'
                            }
                        }
                    },
                    {
                        loader: 'image-webpack-loader',
                        options: {
                            query: {
                                mozjpeg: {
                                    progressive: true
                                },
                                gifsicle: {
                                    interlaced: true
                                },
                                optipng: {
                                    optimizationLevel: 7
                                }
                            }
                        }
                    }
                ]
            }
        ]
    }
};