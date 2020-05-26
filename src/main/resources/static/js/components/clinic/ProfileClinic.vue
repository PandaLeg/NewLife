<template>
    <div>
        <h4>{{ currentProfileClinic.username }}</h4>
        <br>
        <label>{{ currentProfileClinic.nameClinic }}</label>
        <br>
        <label>{{ currentProfileClinic.address }}</label>
        <br>
        <div v-if="check">
            <input type="button" class="btn btn-primary" @click="saveRequest" value="Send Request!"/>
        </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                id: '',
                idDoctor: 0,
                idClinic: 0,
                idPatient: 0,
                message: ''
            }
        },
        created() {
            this.idClinic = this.currentProfileClinic.id;
            if(this.profileDoctor != null) {
                this.idDoctor = this.profileDoctor.id;
            }
            if(this.profilePatient != null) {
                this.idPatient = this.profilePatient.id;
            }
            console.log(this.idClinic, this.idDoctor, this.idPatient)
        },
        methods:{
            saveRequest() {
                let request = {idDoctor: this.idDoctor, idPatient: this.idPatient};

                this.$resource('/send-request-clinic/{id}').save({id: this.idClinic}, request).then(result =>
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