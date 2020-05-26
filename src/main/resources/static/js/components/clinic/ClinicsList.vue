<template>
    <div>
        <clinic-row v-for="clinic in clinics" :key="clinic.id" :clinic="clinic"></clinic-row>
    </div>
</template>

<script>
    import ClinicRow from './ClinicRow.vue'
    export default {
        name: 'clinics',
        components:{
            ClinicRow
        },
        data(){
            return{
                clinics: clinicData.allClinics,
            }
        }
    }
</script>

<style>

</style>