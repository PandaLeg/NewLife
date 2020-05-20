import Vue from 'vue'
import VueResource from 'vue-resource'
import Request from 'pages/Request.vue'

Vue.use(VueResource);

new Vue({
    el: "#request",
    render: a => a(Request)
});