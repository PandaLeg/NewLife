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
        <label>Name Clinic: </label>
        <input type="text" v-model="nameClinic" placeholder="Name Clinic"/>
        <br>
        <label>Address: </label>
        <input type="text" v-model="address" placeholder="Address"/>
        <br>
        <input type="button" value="Create" @click="saveClinic">
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
                nameClinic: '',
                address: ''
            }
        },
        methods: {
            saveClinic() {
                let clinic = {
                    username: this.username, password: this.password, email: this.email,
                    nameClinic: this.nameClinic, address: this.address
                };

                this.$resource('/registration-clinic').save({}, clinic).then(result =>
                    result.json().then(data => {
                        console.log(data);
                        this.username = "";
                        this.password = "";
                        this.email = "";
                        this.nameClinic = "";
                        this.address = "";
                    })
                )
            }

        }
    }
</script>

<style>

</style>