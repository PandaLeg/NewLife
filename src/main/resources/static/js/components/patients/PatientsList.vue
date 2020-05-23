<template>
    <div>
        <patient-row v-for="patient in patients" :key="patient.id" :patient="patient"></patient-row>
    </div>
</template>

<script>
    import PatientRow from './PatientRow.vue'
    export default {
        components: {
            PatientRow
        },
        props: ['patients']
    }
</script>

<style>

</style>