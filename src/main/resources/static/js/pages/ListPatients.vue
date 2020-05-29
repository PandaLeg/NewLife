<template>
    <div>
        <patients-list :patients="patients"></patients-list>
    </div>
</template>

<script>
    import PatientsList from 'components/patients/PatientsList.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            PatientsList,
            NavBar
        },
        data(){
            return{
                patients: []
            }
        },
        created(){
            this.getAllPatients()
        },
        methods:{
            getAllPatients(){
                this.$resource('patients-list').get().then(result =>
                    result.json().then(data => {
                        this.patients = data;
                    })
                )
            }
        }
    }
</script>

<style>

</style>