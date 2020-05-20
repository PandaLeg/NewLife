<template>
    <div>
        <doctors-list :doctors="doctors"></doctors-list>
    </div>
</template>

<script>
    import DoctorsList from 'components/doctors/DoctorsList.vue'

    export default {
        components:{
            DoctorsList
        },
        data(){
            return {
                doctors: doctorData.doctors
            }
        },
        created(){
            console.log(this.doctors);
        }
    }
</script>

<style>

</style>