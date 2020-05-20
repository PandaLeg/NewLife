<template>
    <div>
        <clinic-row v-for="clinic in clinics" :key="clinic.id" :clinic="clinic"></clinic-row>
    </div>
</template>

<script>
    import ClinicRow from './ClinicRow.vue'
    export default {
        components:{
            ClinicRow
        },
        props: ['clinics']
    }
</script>

<style>

</style>