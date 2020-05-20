<template>
    <div>
        <div v-if="!profileClinic && !profileDoctor && !profilePatient">
            Пожалуйста авторизуйтесь <a href="/login">Login</a></div>
        <div v-else>
            <div v-if="profileClinic">{{ profileClinic.username }}&nbsp;<a href="/logout">Выйти</a></div>
            <div v-else-if="profileDoctor">{{ profileDoctor.username }}&nbsp;<a href="/logout">Выйти</a></div>
            <div v-else>{{ profilePatient.username }}&nbsp;<a href="/logout">Выйти</a></div>
            <messages-list :messages="messages"></messages-list>
            <br>
            <a href="/clinics">All Clinic, my friend!</a>
            <label style="margin-left: 10px; margin-right: 10px;"> | </label>
            <a href="/requests">All Request, my friend!</a> +
            <label style="margin-left: 10px; margin-right: 10px;"> | </label>
            <a href="/list-doctors">My List Doctors!</a>
        </div>
    </div>
</template>

<script>
    import MessagesList from 'components/main/MessagesList.vue'

    export default {
        components:{
            MessagesList
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