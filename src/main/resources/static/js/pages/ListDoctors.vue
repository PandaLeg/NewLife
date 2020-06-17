<template>
    <div>
        <doctors-list :doctors="listDoctors"></doctors-list>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import {mapActions} from 'vuex'
    import DoctorsList from 'components/doctors/DoctorsList.vue'

    export default {
        components:{
            DoctorsList
        },
        created(){
            this.getAllDoctors();
        },
        computed:{
            ...mapGetters('listDoctors', ['listDoctors'])
        },
        methods:{
            ...mapActions('listDoctors', ['getAllDoctorsAction']),
            getAllDoctors(){
                this.getAllDoctorsAction();
            }
        }
    }
</script>

<style>

</style>