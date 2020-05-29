<template>
    <div>
        <div v-if="name_reg == 'clinic'">
            <registration-clinic-form></registration-clinic-form>
        </div>
        <div v-else-if="name_reg == 'doctor'">
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
        props: ['name_reg'],
        components:{
            RegistrationClinicForm,
            RegistrationDoctorForm,
            RegistrationPatientForm
        }
    }
</script>

<style>

</style>
