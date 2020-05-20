import Vue from 'vue'
import VueResource from 'vue-resource'
import ClinicProfile from 'pages/ClinicProfile.vue'

Vue.use(VueResource);

new Vue({
    el: "#clinicProfile",
    render: a => a(ClinicProfile)
});

