import Vue from 'vue'
import VueResource from 'vue-resource'
import Clinic from 'pages/Clinic.vue'

Vue.use(VueResource);

new Vue({
   el: "#clinic",
    render: a => a(Clinic)
});
