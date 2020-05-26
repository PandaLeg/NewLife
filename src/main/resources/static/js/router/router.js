import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'components/main/MessagesList.vue'
import Auth from 'pages/Auth.vue'
import ClinicsList from 'components/clinic/ClinicsList.vue'
import ClinicProfile from 'pages/ClinicProfile.vue'

Vue.use(VueRouter);

const routes = [
    {path: '/clinics', name: 'clinics', component: ClinicsList},
    {path: '/clinic-profile/:id', name: 'clinicProfile', component: ClinicProfile},
    {path: '/Auth', component: Auth},
    {path: '*', component: MessagesList}
];

export default new VueRouter({
    mode: 'history',
    routes // сокращённая запись для `routes: routes`
})