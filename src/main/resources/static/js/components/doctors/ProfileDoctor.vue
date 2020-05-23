<template>
    <div>
        <h4>{{ currentProfileDoctor.username }}</h4>
        <br>
        <label>{{ currentProfileDoctor.fio }}</label>
        <br>
        <label>{{ currentProfileDoctor.position }}</label>
        <br>
        <div v-if="check">
            <input type="button" class="btn btn-primary" @click="saveRequest" value="Send Request!"/>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['currentProfileDoctor', 'check', 'profileClinic', 'profilePatient'],
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
            this.idDoctor = this.currentProfileDoctor.id;
            if(this.profilePatient != null) {
                this.idPatient = this.profilePatient.id;
            }
            if(this.profileClinic != null){
                this.idClinic = this.profileClinic.id;
            }
            console.log(this.idClinic, this.idDoctor, this.idPatient)
        },
        methods:{
            saveRequest() {
                let request = {idClinic: this.idClinic, idPatient: this.idPatient};

                this.$resource('/send-request-doctor/{id}').save({id: this.idDoctor}, request).then(result =>
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