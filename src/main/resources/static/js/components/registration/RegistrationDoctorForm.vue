<template>
    <div>
        <label>Username: </label>
        <input type="text" v-model="username" placeholder="Username"/>
        <br>
        <label>Password: </label>
        <input type="password" v-model="password" placeholder="Password"/>
        <br>
        <label>Email: </label>
        <input type="email" v-model="email" placeholder="Email"/>
        <br>
        <label>FIO: </label>
        <input type="text" v-model="fio" placeholder="FIO"/>
        <br>
        <label>Position: </label>
        <input type="text" v-model="position" placeholder="Position"/>
        <br>
        <input type="button" value="Create" @click="saveDoctor">
    </div>
</template>

<script>
    export default {
        data() {
            return {
                id: '',
                username: '',
                password: '',
                email: '',
                fio: '',
                position: ''
            }
        },
        methods: {
            saveDoctor() {
                let doctor = {
                    username: this.username, password: this.password, email: this.email,
                    fio: this.fio, position: this.position
                };

                this.$resource('/registration-doctor').save({}, doctor).then(result =>
                    result.json().then(data => {
                        console.log(data);
                        this.username = "";
                        this.password = "";
                        this.email = "";
                        this.fio = "";
                        this.position = "";
                    })
                )
            }
        }
    }
</script>

<style>

</style>
