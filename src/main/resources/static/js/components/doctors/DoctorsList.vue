<template>
    <div class="container m-5">
        <h1> {{ $t('listDoctors.listDoctors') }}</h1>
        <doctor-row v-for="doctor in doctors" :key="doctor.id" :doctor="doctor"></doctor-row>
    </div>
</template>

<script>
    import DoctorRow from './DoctorRow.vue'

    export default {
        components:{
            DoctorRow
        },
        props: ['doctors']
    }
</script>

<style>

</style>