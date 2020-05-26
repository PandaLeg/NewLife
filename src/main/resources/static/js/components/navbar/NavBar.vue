<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-brand href="#">New Life</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item href="/">Home</b-nav-item>
                    <b-nav-item v-if="profileClinic || profileDoctor || profilePatient" href="/clinics">
                        Клиники
                    </b-nav-item>
                    <b-nav-item v-if="profileClinic || profileDoctor || profilePatient" href="/requests">
                        Запросы
                    </b-nav-item>
                    <b-nav-item v-if="profileClinic" href="/list-doctors">
                        Список докторов
                    </b-nav-item>
                    <b-nav-item v-if="profileClinic || profileDoctor" href="/list-patients">
                        Список пациентов
                    </b-nav-item>
                    <b-nav-item v-if="profilePatient" href="/state">
                        Состояние детей
                    </b-nav-item>
                    <b-nav-item v-if="profilePatient" href="/add-child">
                        Добавить ребёнка
                    </b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto"  v-if="profileClinic || profileDoctor || profilePatient">

                    <b-nav-item-dropdown right v-if="profileClinic">
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>{{profileClinic.username}}</em>
                        </template>
                        <b-dropdown-item href="#">Profile</b-dropdown-item>
                        <b-dropdown-item href="/logout">Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>
                    <b-nav-item-dropdown right v-if="profileDoctor">
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>{{profileDoctor.username}}</em>
                        </template>
                        <b-dropdown-item> Profile </b-dropdown-item>
                        <b-dropdown-item href="/logout">Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>
                    <b-nav-item-dropdown right v-if="profilePatient">
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>{{profilePatient.username}}</em>
                        </template>
                        <b-dropdown-item>

                        </b-dropdown-item>
                        <b-dropdown-item href="/logout">Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
            <div class="navbar-text mr-3" v-if="!profileClinic && !profileDoctor && !profilePatient">
                Please, login!
            </div>
            <div v-if="!profileClinic && !profileDoctor && !profilePatient">
                <a href="/login">Войти</a>
            </div>
        </b-navbar>
    </div>
</template>

<script>
    export default {
        props: ['profileClinic', 'profileDoctor', 'profilePatient'],
        data() {
            return{
                currentProfPatient: '3',
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
            /*if(this.currentProfilePatient != null){
                this.currentProfPatient = this.currentProfilePatient.id;
            }*/
        },
        methods:{
            /*showPatientProfile(){
                this.$router.push({name: 'patient', params: { idPatient: '3' } })
            }*/
        }
    }
</script>

<style>

</style>