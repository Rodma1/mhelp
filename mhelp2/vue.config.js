module.exports={
    publicPath: "./",
    productionSourceMap: false,
    configureWebpack:{
        resolve:{
            alias:{
                'assets':'@/assets',
                'common':'@/common',
                'components':'@/components',
                'views':'@/views',
                'network':'@/network',
                'mixins':'@/mixins',
                'store':'@/store',
            }
        }
    }    
}
