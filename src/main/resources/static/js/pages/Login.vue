<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <login-form></login-form>
    </div>
</template>

<script>
    import LoginForm from 'components/login/LoginForm.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            LoginForm,
            NavBar
        },
        data(){
            return{
                profileClinic: null,
                profileDoctor: null,
                profilePatient: null
            }
        }
    }
</script>

<style>

</style>