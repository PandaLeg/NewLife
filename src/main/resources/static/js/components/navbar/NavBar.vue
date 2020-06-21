<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-brand href="#">New Life</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav v-if="(profileClinic && profileClinic.username != 'admin')
                 || (profileDoctor && profileDoctor.username != 'admin') ||
                 (profilePatient && profilePatient.username != 'admin')">
                    <b-nav-item href="/">{{ $t('navBar.home') }}</b-nav-item>
                    <b-nav-item v-if="profileClinic || profileDoctor || profilePatient" @click="allClinic">
                        {{ $t('navBar.clinic') }}
                    </b-nav-item>
                    <b-nav-item v-if="profileClinic || profileDoctor" @click="allRequests">
                        {{ $t('navBar.requests') }}
                    </b-nav-item>
                    <b-nav-item v-if="profileClinic" @click="allDoctors">
                        {{ $t('navBar.listDoctors') }}
                    </b-nav-item>
                    <b-nav-item v-if="profileClinic || profileDoctor" @click="allPatients">
                        {{ $t('navBar.listPatients') }}
                    </b-nav-item>
                    <b-nav-item v-if="profilePatient" @click="allMessages">
                        {{ $t('navBar.messages') }}
                    </b-nav-item>
                    <b-nav-item v-if="profilePatient" @click="formAddChild">
                        {{ $t('navBar.addChild') }}
                    </b-nav-item>
                    <b-nav-item v-if="profilePatient" @click="stateChild">
                        {{ $t('navBar.stateChild') }}
                    </b-nav-item>
                </b-navbar-nav>

                <b-navbar-nav v-if="(profileClinic && profileClinic.username === 'admin')">
                    <b-nav-item href="/">{{ $t('navBar.home') }}</b-nav-item>
                    <b-nav-item @click="allUsers">
                        Пользователи
                    </b-nav-item>
                </b-navbar-nav>
                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto" v-if="profileClinic || profileDoctor || profilePatient">

                    <b-nav-item-dropdown right v-if="profileClinic">
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>{{profileClinic.username}}</em>
                        </template>
                        <b-dropdown-item @click="showClinicProfile">{{ $t('navBar.profile') }}</b-dropdown-item>
                        <b-dropdown-divider></b-dropdown-divider>
                        <b-dropdown-item @click="editingProfile">{{ $t('navBar.editing') }}</b-dropdown-item>
                        <b-dropdown-divider></b-dropdown-divider>
                        <b-dropdown-item @click="updateVisit" href="/logout">{{ $t('navBar.signOut') }}
                        </b-dropdown-item>
                    </b-nav-item-dropdown>

                    <b-nav-item-dropdown right v-if="profileDoctor">
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>{{profileDoctor.username}}</em>
                        </template>
                        <b-dropdown-item @click="showDoctorProfile"> {{ $t('navBar.profile') }}</b-dropdown-item>
                        <b-dropdown-divider></b-dropdown-divider>
                        <b-dropdown-item @click="editingProfile">{{ $t('navBar.editing') }}</b-dropdown-item>
                        <b-dropdown-divider></b-dropdown-divider>
                        <b-dropdown-item @click="updateVisit" href="/logout"> {{ $t('navBar.signOut') }}
                        </b-dropdown-item>
                    </b-nav-item-dropdown>

                    <b-nav-item-dropdown right v-if="profilePatient">
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>{{profilePatient.username}}</em>
                        </template>
                        <b-dropdown-item @click="showPatientProfile"> {{ $t('navBar.profile') }}</b-dropdown-item>
                        <b-dropdown-divider></b-dropdown-divider>
                        <b-dropdown-item @click="editingProfile">{{ $t('navBar.editing') }}</b-dropdown-item>
                        <b-dropdown-divider></b-dropdown-divider>
                        <b-dropdown-item @click="updateVisit" href="/logout"> {{ $t('navBar.signOut') }}
                        </b-dropdown-item>
                    </b-nav-item-dropdown>
                    <b-navbar-nav class="ml-auto">
                        <b-nav-item-dropdown right>
                            <!-- Using 'button-content' slot -->
                            <template v-slot:button-content class="locale-switcher">
                                🌐 {{$i18n.locale}}
                            </template>
                            <LocaleSwitcher/>
                        </b-nav-item-dropdown>
                    </b-navbar-nav>
                </b-navbar-nav>
            </b-collapse>
            <div class="navbar-text mr-3" v-if="!profileClinic && !profileDoctor && !profilePatient">
                {{ $t('navBar.pleaseLogin') }}
            </div>
            <div v-if="!profileClinic && !profileDoctor && !profilePatient">
                <router-link :to="{name: 'login'}">{{ $t('navBar.signIn') }}</router-link>
            </div>
            <div v-if="!profileClinic && !profileDoctor && !profilePatient">
                <b-navbar-nav class="ml-auto">
                    <b-nav-item-dropdown right>
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content class="locale-switcher">
                            🌐 {{$i18n.locale}}
                        </template>
                        <LocaleSwitcher/>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </div>
        </b-navbar>
    </div>
</template>

<script>
    import LocaleSwitcher from 'switcher/LocaleSwitcher.vue'

    export default {
        props: ['profileClinic', 'profileDoctor', 'profilePatient'],
        components: {
            LocaleSwitcher
        },
        data() {
            return {
                idClinic: 0,
                idDoctor: 0,
                idPatient: 0
            }
        },
        created() {
            if (this.profileClinic != null) {
                this.idClinic = this.profileClinic.id;
            }
            if (this.profileDoctor != null) {
                this.idDoctor = this.profileDoctor.id;
            }
            if (this.profilePatient != null) {
                this.idPatient = this.profilePatient.id;
            }
        },
        methods: {
            checkRole() {
                return this.profileClinic.checkRole();
            },
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
            allMessages() {
                this.$router.push("/messages-list")
            },
            allUsers() {
                this.$router.push("/users")
            },
            showClinicProfile() {
                this.$router.push({name: 'clinicProfile', params: {idProfileClinic: this.idClinic}})
            },
            showDoctorProfile() {
                this.$router.push({name: 'doctorProfile', params: {idProfileDoctor: this.idDoctor}})
            },
            showPatientProfile() {
                this.$router.push({name: 'patientProfile', params: {idProfilePatient: this.idPatient}})
            },
            editingProfile() {
                this.$router.push("/edit-profile")
            },
            updateVisit() {
                if (this.profileClinic != null) {
                    this.$resource('/update-visit-clinic/{id}').update({id: this.profileClinic.id}, {})
                        .then()
                } else if (this.profileDoctor != null) {
                    this.$resource('/update-visit-doctor/{id}').update({id: this.profileDoctor.id}, {})
                        .then()
                } else {
                    this.$resource('/update-visit-patient/{id}').update({id: this.profilePatient.id}, {})
                        .then()
                }
            },
            formAddChild() {
                this.$router.push('/add-child')
            },
            stateChild() {
                this.$router.push('/state')
            }
        }
    }
</script>

<style scoped>
</style>