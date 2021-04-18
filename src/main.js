import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
Vue.config.productionTip = false

import axios from 'axios'

axios.defaults.baseURL="http://localhost:8088/"
Vue.prototype.$http=axios

// module.exports={
//   dev: {
//     // Paths
//     assetsSubDirectory: 'static',
//     assetsPublicPath: '/',
//     proxyTable: {
//       '/': {
//         target: 'http://localhost:8088',
//         changeOrigin: true,
//         pathRewrite: {
//           '^/': ''
//         }
//       },
//       '/ws/*': {
//         target: 'ws://127.0.0.1:8080',
//         ws: true
//       }
//     },
//
//     // Various Dev Server settings
//     host: 'localhost', // can be overwritten by process.env.HOST
//     port: 8080, // can be overwritten by process.env.PORT, if port is in use, a free one will be determined
//     autoOpenBrowser: false,
//     errorOverlay: true,
//     notifyOnErrors: true,
//     poll: false, // https://webpack.js.org/configuration/dev-server/#devserver-watchoptions-
//
//     // Use Eslint Loader?
//     // If true, your code will be linted during bundling and
//     // linting errors and warnings will be shown in the console.
//     useEslint: true,
//     // If true, eslint errors and warnings will also be shown in the error overlay
//     // in the browser.
//     showEslintErrorsInOverlay: false,
//
//     /**
//      * Source Maps
//      */
//
//     // https://webpack.js.org/configuration/devtool/#development
//     devtool: 'cheap-module-eval-source-map',
//
//     // If you have problems debugging vue-files in devtools,
//     // set this to false - it *may* help
//     // https://vue-loader.vuejs.org/en/options.html#cachebusting
//     cacheBusting: true,
//
//     cssSourceMap: true
//   },
// }

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
