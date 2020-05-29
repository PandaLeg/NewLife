<template>
    <div>
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
                doctors: []
            }
        },
        created(){
            this.getAllDoctors();
        },
        methods:{
            getAllDoctors(){
                this.$resource('/doctors-list-clinic').get().then(result =>
                    result.json().then(data => {
                        this.doctors = data
                    })
                )
            }
        }
    }
</script>

<style>

</style>