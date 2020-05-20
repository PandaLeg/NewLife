<template>
    <requests-list :requests="requests"
                   :profileClinic="profileClinic"
                   :profileDoctor="profileDoctor">
    </requests-list>
</template>

<script>
    import RequestsList from 'components/requests/RequestsList.vue'

    export default {
        components:{
            RequestsList
        },
        data() {
            return {
                requests: requestData.requests,
                profileClinic: requestData.profileClinic,
                profileDoctor: requestData.profileDoctor
            }
        }
    }
</script>

<style>

</style>