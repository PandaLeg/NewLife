<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <profile-patient :currentProfilePatient="currentProfilePatient" :profileClinic="profileClinic"
                        :profileDoctor="profileDoctor">
        </profile-patient>
    </div>
</template>

<script>
    import ProfilePatient from 'components/patients/ProfilePatient.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            ProfilePatient,
            NavBar
        },
        data() {
            return {
                currentProfilePatient: profileData.currentProfilePatient,
                profileClinic: profileData.profileClinic,
                profileDoctor: profileData.profileDoctor,
                profilePatient: profileData.profilePatient
            }
        }
    }
</script>
<style>

</style>