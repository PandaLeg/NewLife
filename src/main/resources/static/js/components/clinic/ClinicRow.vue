<template>
    <div>
        <router-link to="/clinic-profile/1"> {{ clinic.nameClinic }}</router-link>
    </div>
</template>

<script>
    export default {
        props: ['clinic'],
        created() {
            console.log(this.clinic.id);
        },
        methods: {
            showClinicProfile() {

            }
        }
    }
</script>

<style>

</style>
