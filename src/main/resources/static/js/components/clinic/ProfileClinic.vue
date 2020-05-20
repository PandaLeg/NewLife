<template>
    <div>
        <h4>{{ profileClinic.username }}</h4>
        <br>
        <label>{{ profileClinic.nameClinic }}</label>
        <br>
        <label>{{ profileClinic.address }}</label>
        <br>
        <div v-if="check">
            <input type="button" @click="saveRequest" value="Send Request!"/>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['profileClinic', 'check', 'profileDoctor'],
        data(){
            return{
                id: '',
                idDoctor: 0,
                idClinic: 0,
                message: ''
            }
        },
        created() {
            this.idClinic = this.profileClinic.id;
            if(this.profileDoctor != null) {
                this.idDoctor = this.profileDoctor.id;
            }
            console.log(this.idClinic, this.idDoctor)
        },
        methods:{
            saveRequest() {
                let request = {idDoctor: this.idDoctor};

                this.$resource('/send-request/{id}').save({id: this.idClinic}, request).then(result =>
                    result.json().then(data => {
                        console.log(data);
                    })
                )
            }
        }
    }
</script>

<style>

</style>