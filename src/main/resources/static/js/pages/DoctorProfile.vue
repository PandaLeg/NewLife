<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <profile-doctor :currentProfileDoctor="currentProfileDoctor" :check="isRole" :profileClinic="profileClinic"
                        :profilePatient="profilePatient">
        </profile-doctor>
    </div>
</template>

<script>
    import ProfileDoctor from 'components/doctors/ProfileDoctor.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            ProfileDoctor,
            NavBar
        },
        data() {
            return {
                currentProfileDoctor: profileData.currentProfileDoctor,
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