<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">

        </nav-bar>
        <div v-if="!profileClinic && !profileDoctor && !profilePatient">
            <h1>Приветствую вас!</h1>
        </div>
        <div v-else>
            <messages-list :messages="messages"></messages-list>
        </div>
    </div>
</template>

<script>
    import MessagesList from 'components/main/MessagesList.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            MessagesList,
            NavBar
        },
        data() {
            return {
                profileClinic: dataClinic.profileClinic,
                profileDoctor: dataClinic.profileDoctor,
                profilePatient: dataClinic.profilePatient,
                messages: [
                    {id: 1, text: 'Первый тост за localhost'},
                    {id: 2, text: 'Wow!So nice'}
                ],
            }
        }
    }
</script>

<style>

</style>