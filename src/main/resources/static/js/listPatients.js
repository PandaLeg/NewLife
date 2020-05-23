import Vue from 'vue'
import VueResource from 'vue-resource'
import ListPatients from 'pages/ListPatients.vue'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.use(VueResource);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

new Vue({
    el: "#patientsClinic",
    render: a => a(ListPatients)
});