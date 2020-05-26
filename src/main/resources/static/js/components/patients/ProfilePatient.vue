<template>
    <div>
        <h4>{{ currentProfilePatient.username }}</h4>
        <br>
        <label>{{ currentProfilePatient.firstName }}</label>
        <br>
        <label>{{ currentProfilePatient.surname }}</label>
    </div>
</template>

<script>
    export default {
        props: ['currentProfilePatient', 'profileClinic', 'profileDoctor'],
        data(){
            return{
                id: '',
                idDoctor: 0,
                idClinic: 0,
                idPatient: 0,
                message: ''
            }
        },
        methods:{

        }
    }
</script>

<style>

</style>