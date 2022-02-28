
// const path = require("path");
// function resolve(dir) {
//   return path.join(__dirname, dir);
// }

module.exports={
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

// module.exports = {
//   chainWebpack: config => {
//     config.resolve.alias
//       .set("@", resolve("src"))
//       .set("assets", resolve("src/assets"))
//       .set("components", resolve("src/components"))
//       .set("views", resolve("src/views"))
//       .set("base", resolve("baseConfig"))
//       .set("public", resolve("public"));
//   },
// }