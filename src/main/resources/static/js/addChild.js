import Vue from 'vue'
import VueResource from 'vue-resource'
import AddChild from 'pages/AddChild.vue'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.use(VueResource);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

new Vue({
    el: "#addChild",
    render: a => a(AddChild)
});
