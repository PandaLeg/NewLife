import Vue from 'vue'
import Login from 'pages/Login.vue'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

new Vue({
    el: "#login",
    render: a => a(Login)
});

