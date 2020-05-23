<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <profile-clinic :currentProfileClinic="currentProfileClinic" :check="isRole" :profileDoctor="profileDoctor"
        :profilePatient="profilePatient">
        </profile-clinic>
    </div>
</template>

<script>
    import ProfileClinic from 'components/clinic/ProfileClinic.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            ProfileClinic,
            NavBar
        },
        data() {
            return {
                currentProfileClinic: profileData.currentProfileClinic,
                profileClinic: profileData.profileClinic,
                profileDoctor: profileData.profileDoctor,
                profilePatient: profileData.profilePatient,
                isRole: profileData.checkRole
            }
        }
    }
</script>
<style>

</style>