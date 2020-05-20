import Vue from 'vue'
import VueResource from 'vue-resource'
import Registration from 'pages/Registration.vue'
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

// Install VeeValidate rules and localization
Object.keys(rules).forEach(rule => {
   extend(rule, rules[rule])
});

localize("en", en);

// Install VeeValidate components globally
Vue.component("ValidationObserver", ValidationObserver);
Vue.component("ValidationProvider", ValidationProvider);

Vue.use(VueResource);

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

new Vue({
   el: "#registration",
   render: a => a(Registration)
});
