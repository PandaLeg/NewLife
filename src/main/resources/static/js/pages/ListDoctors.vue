<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <doctors-list :doctors="doctors"></doctors-list>
    </div>
</template>

<script>
    import DoctorsList from 'components/doctors/DoctorsList.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            DoctorsList,
            NavBar
        },
        data(){
            return {
                doctors: doctorData.doctors,
                profileClinic: doctorData.profileClinic,
                profileDoctor: doctorData.profileDoctor,
                profilePatient: doctorData.profilePatient
            }
        },
        created(){
            console.log(this.doctors);
        }
    }
</script>

<style>

</style>