<template>
    <v-app>
        <v-main>
            <div>
                <clinics-list ></clinics-list>
            </div>
        </v-main>
    </v-app>
</template>

<script>
    import ClinicsList from 'components/clinic/ClinicsList.vue'

    export default {
        components:{
            ClinicsList,
        }
    }
</script>

<style>

</style>