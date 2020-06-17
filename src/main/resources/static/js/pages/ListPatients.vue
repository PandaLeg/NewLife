<template>
    <div>
        <patients-list :patients="listPatients"></patients-list>
    </div>
</template>

<script>
    import PatientsList from 'components/patients/PatientsList.vue'
    import {mapGetters} from 'vuex'
    import {mapActions} from 'vuex'

    export default {
        components:{
            PatientsList
        },
        created(){
            this.getAllPatients()
        },
        computed:{
            ...mapGetters('listPatients', ['listPatients'])
        },
        methods:{
            ...mapActions('listPatients', ['getAllPatientsAction']),
            getAllPatients(){
                this.getAllPatientsAction();
            }
        }
    }
</script>

<style>

</style>