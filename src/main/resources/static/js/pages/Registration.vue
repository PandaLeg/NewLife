<template>
    <div>
        <div v-if="checkClinic">
            <h1>Registration clinic!</h1>
            <registration-clinic-form></registration-clinic-form>
        </div>
        <div v-else-if="checkDoctor">
            <h1>Registration doctor!</h1>
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

    export default {
        components:{
            RegistrationClinicForm,
            RegistrationDoctorForm,
            RegistrationPatientForm
        },
        data(){
            return {
                checkClinic: regData.checkClinic,
                checkDoctor: regData.checkDoctor
            }
        }
    }
</script>

<style>

</style>
