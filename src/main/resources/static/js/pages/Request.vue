<template>
    <div>
        <requests-list :requests="requests"
                       :profileClinic="profileClinic"
                       :profileDoctor="profileDoctor"
                       :profilePatient="profilePatient">
        </requests-list>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    import RequestsList from 'components/requests/RequestsList.vue'

    export default {
        components:{
            RequestsList
        },
        data() {
            return {
                requests: null
            }
        },
        created(){
            this.getAllRequests();
        },
        computed:{
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient'])
        },
        methods:{
            getAllRequests(){
                this.$resource('/list-requests').get().then(result =>
                    result.json().then(data => {
                        this.requests = data;
                    })
                )
            }
        }
    }
</script>

<style>

</style>