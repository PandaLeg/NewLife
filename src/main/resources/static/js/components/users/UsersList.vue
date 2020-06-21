<template>
    <v-layout>
        <v-row>
            <v-col cols="4" sm="4">
                <user-row v-for="clinic in clinics" :key="clinic.id" :clinic="clinic"></user-row>
            </v-col>
            <v-col cols="4" sm="4">
                <user-row v-for="doctor in doctors" :key="doctor.id" :doctor="doctor"></user-row>
            </v-col>
            <v-col cols="4" sm="4">
                <user-row v-for="patient in patients" :key="patient.id" :patient="patient"></user-row>
            </v-col>
        </v-row>
    </v-layout>
</template>

<script>
    import UserRow from './UserRow.vue'

    export default {
        components: {
            UserRow
        },
        data() {
            return {
                clinics: null,
                doctors: null,
                patients: null
            }
        },
        created() {
            this.fetchListClinics();
            this.fetchListDoctors();
            this.fetchListPatients();
        },
        methods: {
            fetchListClinics() {
                this.$resource('/hospitals').get().then(result => {
                    this.clinics = result.data;
                })
            },
            fetchListDoctors() {
                this.$resource('/doctors').get().then(result => {
                    this.doctors = result.data;
                })
            },
            fetchListPatients() {
                this.$resource('/patients').get().then(result => {
                    this.patients = result.data;
                })
            }
        }
    }
</script>

<style>

</style>