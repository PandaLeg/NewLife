<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <patients-list :patients="patients"></patients-list>
    </div>
</template>

<script>
    import PatientsList from 'components/patients/PatientsList.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            PatientsList,
            NavBar
        },
        data(){
            return{
                patients: patientData.patients,
                profileClinic: patientData.profileClinic,
                profileDoctor: patientData.profileDoctor,
                profilePatient: patientData.profilePatient
            }
        }
    }
</script>

<style>

</style>