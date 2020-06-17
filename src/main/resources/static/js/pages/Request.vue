<template>
    <div>
        <requests-list :requests="listRequests"
                       :profileClinic="profileClinic"
                       :profileDoctor="profileDoctor"
                       :profilePatient="profilePatient">
        </requests-list>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    import { mapGetters } from 'vuex'
    import { mapActions } from 'vuex'
    import RequestsList from 'components/requests/RequestsList.vue'
    export default {
        components:{
            RequestsList
        },
        created(){
            this.getAllRequests();
        },
        computed:{
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient']),
            ...mapGetters('request', ['listRequests']),
        },
        methods:{
            ...mapActions('request', ['getAllRequestsAction']),
            getAllRequests(){
                this.getAllRequestsAction();
            }
        }
    }
</script>

<style>

</style>