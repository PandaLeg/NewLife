<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <child :patient="patient"></child>
    </div>
</template>

<script>
    import Child from 'components/child/Child.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            Child,
            NavBar
        },
        data(){
            return {
                patient: childData.patient,
                profileClinic: childData.profileClinic,
                profileDoctor: childData.profileDoctor,
                profilePatient: childData.profilePatient
            }
        }
    }
</script>

<style>

</style>