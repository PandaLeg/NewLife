<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor"
                 :profilePatient="profilePatient">
        </nav-bar>
        <requests-list :requests="requests"
                       :profileClinic="profileClinic"
                       :profileDoctor="profileDoctor"
                       :profilePatient="profilePatient">
        </requests-list>
    </div>
</template>

<script>
    import RequestsList from 'components/requests/RequestsList.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            RequestsList,
            NavBar
        },
        data() {
            return {
                requests: requestData.requests,
                profileClinic: requestData.profileClinic,
                profileDoctor: requestData.profileDoctor,
                profilePatient: requestData.profilePatient
            }
        }
    }
</script>

<style>

</style>