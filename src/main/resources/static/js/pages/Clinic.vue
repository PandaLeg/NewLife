<template>
    <clinics-list :clinics="clinics"></clinics-list>
</template>

<script>
    import ClinicsList from 'components/clinic/ClinicsList.vue'
    export default {
        components:{
            ClinicsList
        },
        data(){
            return {
                clinics: clinicData
            }
        }
    }
</script>

<style>

</style>