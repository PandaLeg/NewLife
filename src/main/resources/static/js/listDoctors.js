import Vue from 'vue'
import VueResource from 'vue-resource'
import ListDoctors from 'pages/ListDoctors.vue'

Vue.use(VueResource);

new Vue({
    el: "#doctors",
    render: a => a(ListDoctors)
});