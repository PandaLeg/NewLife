<template>
    <profile-clinic :profileClinic="profileClinic" :check="isRole" :profileDoctor="profileDoctor"></profile-clinic>
</template>

<script>
    import ProfileClinic from 'components/clinic/ProfileClinic.vue'

    export default {
        components:{
            ProfileClinic
        },
        data() {
            return {
                profileClinic: profileData.clinicProfile,
                profileDoctor: profileData.doctorProfile,
                isRole: profileData.checkRole
            }
        }
    }
</script>
<style>

</style>