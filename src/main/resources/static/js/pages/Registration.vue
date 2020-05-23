<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <div v-if="checkClinic">
            <registration-clinic-form></registration-clinic-form>
        </div>
        <div v-else-if="checkDoctor">
            <registration-doctor-form></registration-doctor-form>
        </div>
        <div v-else>
            <registration-patient-form></registration-patient-form>
        </div>
    </div>
</template>

<script>
    import RegistrationClinicForm from 'components/registration/RegistrationClinicForm.vue'
    import RegistrationDoctorForm from 'components/registration/RegistrationDoctorForm.vue'
    import RegistrationPatientForm from 'components/registration/RegistrationPatientForm.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            RegistrationClinicForm,
            RegistrationDoctorForm,
            RegistrationPatientForm,
            NavBar
        },
        data(){
            return {
                checkClinic: regData.checkClinic,
                checkDoctor: regData.checkDoctor,
                profileClinic: null,
                profileDoctor: null,
                profilePatient: null
            }
        }
    }
</script>

<style>

</style>
