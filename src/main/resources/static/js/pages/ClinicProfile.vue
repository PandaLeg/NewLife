<template>
    <div>
        <div v-if="error" class="error">
            {{ error }}
        </div>
        <div v-if="currentProfileClinic" class="content">
            <h4>{{ currentProfileClinic.username }}</h4>
            <br>
            <label>{{ currentProfileClinic.nameClinic }}</label>
            <br>
            <label>{{ currentProfileClinic.address }}</label>
            <br>
            <div v-if="!checkBindingToClinic">
                <div v-if="!profileClinic">
                    <input type="button" class="btn btn-primary" @click="saveClinicRequest" :disabled="checkButton"
                           value="Send Request!"/>
                </div>
            </div>
            <div v-if="checkBindingToClinic">
                <div v-if="!profileClinic">
                    <input type="button" class="btn btn-primary" @click="cancelBindingClinic"
                           value="Cancel Binding!"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        name: 'clinicProfile',
        props: ['idProfileClinic'],
        data() {
            return {
                currentProfileClinic: null,
                error: null,
                id: '',
                idDoctor: 0,
                idClinic: 0,
                idPatient: 0,
                checkButton: false,
                checkBindingToClinic: false
            }
        },
        created() {
            this.idClinic = this.idProfileClinic;
            if(this.profileDoctor != null) {
                this.idDoctor = this.profileDoctor.id;
            }
            if(this.profilePatient != null) {
                this.idPatient = this.profilePatient.id;
            }

            this.fetchClinicData();
            this.checkBindingClinic();
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient'])
        },
        methods:{
            saveClinicRequest() {
                let request = {idDoctor: this.idDoctor, idPatient: this.idPatient};

                this.$resource('/send-request-clinic/{id}').save({id: this.idClinic}, request).then(result =>
                    result.json().then(data => {
                        this.checkButton = true;
                        console.log(data);
                    })
                )
            },

            fetchClinicData(){
                this.error = null;
                this.$resource('/clinic/{id}').get({id: this.idProfileClinic}).then(result =>
                    result.json().then(data => {
                        this.currentProfileClinic = data;
                    }), response => {
                    this.error = response.error;
                });
            },

            checkBindingClinic(){
                this.$resource('/check-binding-clinic/{id}').get({id: this.idClinic}).then(result => {
                    this.checkBindingToClinic = result.data;
                    console.log(result)
                })
            },

            // Отмена привязки от клиники
            cancelBindingClinic(){
                this.$resource('/cancel-binding-clinic/{id}').delete({id: this.idClinic}).then(result =>{
                    if(result.ok){
                        console.log(result)
                    }
                })
            }
        }
    }
</script>
<style>

</style>