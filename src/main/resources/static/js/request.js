import Vue from 'vue'
import VueResource from 'vue-resource'
import Request from 'pages/Request.vue'
import router from 'router/router'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.use(VueResource);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

new Vue({
    el: "#request",
    router,
    render: a => a(Request)
});