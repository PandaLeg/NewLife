import Vue from 'vue'
import VueResource from 'vue-resource'
import ListDoctors from 'pages/ListDoctors.vue'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.use(VueResource);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

new Vue({
    el: "#doctors",
    render: a => a(ListDoctors)
});