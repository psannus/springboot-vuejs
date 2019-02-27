import Vue from 'vue';
import VueRouter from 'vue-router';
import App from './App';
import VueLogger from 'vuejs-logger';
// Our SPA routes
import Routes from './routes.js';

Vue.config.productionTip = false;

const options = {
    isEnabled: true,
    logLevel: 'debug',
    stringifyArguments: false,
    showLogLevel: true,
    showMethodName: false,
    separator: '|',
    showConsoleColors: true
};

Vue.use(VueLogger, options);
Vue.use(VueRouter);

const router = new VueRouter({
    routes: Routes,
});

/* eslint-disable no-new */
new Vue({
    el: '#app',
    template: '<App/>',
    components: {App},
    router,
});