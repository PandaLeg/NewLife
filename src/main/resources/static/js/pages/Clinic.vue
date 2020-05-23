<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <clinics-list :clinics="clinics"></clinics-list>
    </div>
</template>

<script>
    import ClinicsList from 'components/clinic/ClinicsList.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            ClinicsList,
            NavBar
        },
        data(){
            return {
                clinics: clinicData.allClinics,
                profileClinic: clinicData.profileClinic,
                profileDoctor: clinicData.profileDoctor,
                profilePatient: clinicData.profilePatient
            }
        }
    }
</script>

<style>

</style>