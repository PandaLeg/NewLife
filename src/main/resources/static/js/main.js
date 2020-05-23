import Vue from 'vue'
import Main from 'pages/Main.vue'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

new Vue({
    el: "#main",
    render: a => a(Main)
});
