import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'components/main/MessagesList.vue'
import Auth from 'pages/Auth.vue'
import Login from 'pages/Login.vue'
import Registration from 'pages/Registration.vue'
import Clinic from 'pages/Clinic.vue'
import Requests from 'pages/Request.vue'
import ListDoctors from 'pages/ListDoctors.vue'
import ListPatients from 'pages/ListPatients.vue'
import Messages from 'pages/Messages.vue'
import Users from 'pages/Users.vue'
import ClinicProfile from 'pages/ClinicProfile.vue'
import DoctorProfile from 'pages/DoctorProfile.vue'
import PatientProfile from 'pages/PatientProfile.vue'
import EditProfile from 'pages/EditProfile.vue'
import AddChild from 'pages/AddChild.vue'
import State from 'pages/State.vue'
import ChildProfile from 'pages/ChildProfile.vue'
import AddIndicators from 'components/showing/AddIndicators.vue'
import ShowIndicators from 'components/showing/ShowIndicators.vue'

Vue.use(VueRouter);

const routes = [
    {path: '/', component: MessagesList},
    {path: '/authenticate', component: Login, name: 'login'},
    {path: '/registration-clinic/:name_reg',component: Registration, name: 'registrationClinic', props: true},
    {path: '/registration-doctor/:name_reg',component: Registration, name: 'registrationDoctor', props: true},
    {path: '/registration-patient/:name_reg',component: Registration, name: 'registrationPatient', props: true},
    {path: '/clinics', component: Clinic},
    {path: '/requests', component: Requests},
    {path: '/doctors-list', component: ListDoctors},
    {path: '/patients-list', component: ListPatients},
    {path: '/messages-list', component: Messages},
    {path: '/users', component: Users},
    {path: '/clinic-profile/:idProfileClinic', component: ClinicProfile, name: 'clinicProfile', props: true},
    {path: '/doctor-profile/:idProfileDoctor', component: DoctorProfile, name: 'doctorProfile', props: true},
    {path: '/patient-profile/:idProfilePatient', component: PatientProfile, name: 'patientProfile', props: true},
    {path: '/edit-profile', component: EditProfile},
    {path: '/add-child', component: AddChild},
    {path: '/state', component: State},
    {path: '/child-profile/:idProfileChild', component: ChildProfile, name: 'childProfile', props: true},
    {path: '/add-indicators/:childId', component: AddIndicators, name: 'addIndicators', props: true},
    {path: '/show-indicators/:childId', component: ShowIndicators, name: 'showIndicators', props: true},
    {path: '/Auth', component: Auth},
    {path: '*', component: MessagesList}
];

export default new VueRouter({
    mode: 'history',
    routes // сокращённая запись для `routes: routes`
})