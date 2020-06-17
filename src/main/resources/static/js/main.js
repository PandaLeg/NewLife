import Vue from 'vue'
import VueResource from 'vue-resource'
import 'api/resource'
import Main from 'pages/Main.vue'
import '@babel/polyfill'
import store from 'store/store'
import router from 'router/router'
import i18n  from './i18n'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'

import {
    ValidationObserver,
    ValidationProvider,
    extend,
    localize
} from 'vee-validate'
import en from 'vee-validate/dist/locale/en.json';
import * as rules from 'vee-validate/dist/rules';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

// ICONS

// FOR i18N
Vue.config.productionTip = false;

// VALIDATE
// Install VeeValidate rules and localization
Object.keys(rules).forEach(rule => {
    extend(rule, rules[rule])
});

localize("en", en);

// Install VeeValidate components globally
Vue.component("ValidationObserver", ValidationObserver);
Vue.component("ValidationProvider", ValidationProvider);

//VUETIFY
Vue.use(Vuetify);
// BOOTSTRAP
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
// API
Vue.use(VueResource);

new Vue({
    el: "#main",
    vuetify : new Vuetify(),
    store,
    router,
    i18n,
    render: a => a(Main)
});
