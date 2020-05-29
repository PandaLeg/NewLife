<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-brand href="#">New Life</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item href="/">Home</b-nav-item>
                    <b-nav-item v-if="profileClinic || profileDoctor || profilePatient" @click="allClinic">
                        Клиники
                    </b-nav-item>
                    <b-nav-item v-if="profileClinic || profileDoctor" @click="allRequests">
                        Запросы
                    </b-nav-item>
                    <b-nav-item v-if="profileClinic" @click="allDoctors">
                        Список докторов
                    </b-nav-item>
                    <b-nav-item v-if="profileClinic || profileDoctor" @click="allPatients">
                        Список пациентов
                    </b-nav-item>
                    <b-nav-item v-if="profilePatient" @click="formAddChild">
                        Добавить ребёнка
                    </b-nav-item>
                    <b-nav-item v-if="profilePatient" @click="stateChild">
                        Состояние детей
                    </b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto"  v-if="profileClinic || profileDoctor || profilePatient">

                    <b-nav-item-dropdown right v-if="profileClinic">
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>{{profileClinic.username}}</em>
                        </template>
                        <b-dropdown-item @click="showClinicProfile">Profile</b-dropdown-item>
                        <b-dropdown-item @click="updateVisit" href="/logout">Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>

                    <b-nav-item-dropdown right v-if="profileDoctor">
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>{{profileDoctor.username}}</em>
                        </template>
                        <b-dropdown-item @click="showDoctorProfile"> Profile </b-dropdown-item>
                        <b-dropdown-item @click="updateVisit" href="/logout" >Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>

                    <b-nav-item-dropdown right v-if="profilePatient">
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>{{profilePatient.username}}</em>
                        </template>
                        <b-dropdown-item @click="showPatientProfile">Profile</b-dropdown-item>
                        <b-dropdown-item @click="updateVisit" href="/logout">Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
            <div class="navbar-text mr-3" v-if="!profileClinic && !profileDoctor && !profilePatient">
                Please, login!
            </div>
            <div v-if="!profileClinic && !profileDoctor && !profilePatient">
                <router-link to="/login">Войти</router-link>
            </div>
        </b-navbar>
    </div>
</template>

<script>
    export default {
        props: ['profileClinic', 'profileDoctor', 'profilePatient'],
        data() {
            return{
                idClinic: 0,
                idDoctor: 0,
                idPatient: 0
            }
        },
        created(){
            if(this.profileClinic != null) {
                this.idClinic = this.profileClinic.id;
            }
            if(this.profileDoctor != null) {
                this.idDoctor = this.profileDoctor.id;
            }
            if(this.profilePatient != null) {
                this.idPatient = this.profilePatient.id;
            }
        },
        methods:{
            allClinic() {
                this.$router.push("/clinics")
            },
            allRequests() {
                this.$router.push("/requests")
            },
            allDoctors() {
                this.$router.push("/doctors-list")
            },
            allPatients() {
                this.$router.push("/patients-list")
            },
            showClinicProfile() {
                this.$router.push({name: 'clinicProfile', params: { idProfileClinic: this.idClinic } })
            },
            showDoctorProfile() {
                this.$router.push({name: 'doctorProfile', params: { idProfileDoctor: this.idDoctor } })
            },
            showPatientProfile() {
                this.$router.push({name: 'patientProfile', params: { idProfilePatient: this.idPatient } })
            },
            updateVisit() {
                if(this.profileClinic != null) {
                    this.$resource('/update-visit-clinic/{id}').update({id: this.profileClinic.id}, {})
                        .then()
                }else if(this.profileDoctor != null){
                    this.$resource('/update-visit-doctor/{id}').update({id: this.profileDoctor.id}, {})
                        .then()
                }else{
                    this.$resource('/update-visit-patient/{id}').update({id: this.profilePatient.id}, {})
                        .then()
                }
            },
            formAddChild() {
                this.$router.push('/add-child')
            },
            stateChild(){
                this.$router.push('/state')
            }
        }
    }
</script>

<style>

</style>