<template>
    <div>
        <div v-if="currentProfileDoctor" class="content">
            <h4>{{ currentProfileDoctor.username }}</h4>
            <br>
            <label>{{ currentProfileDoctor.firstName }}</label>
            <br>
            <label>{{ currentProfileDoctor.surname }}</label>
            <br>
            <label>{{ currentProfileDoctor.position }}</label>
            <br>
            <!-- Проверяет привязаны ли пациент и доктор к одной клинике-->
            <div v-if="checkAffiliation && !profileClinic">
                <!--Показывает кнопку отправить запрос, если доктор не содержит такого пациента-->
                <div v-if="!checkBindingToDoctor">
                    <input type="button" class="btn btn-primary" @click="saveDoctorRequest" :disabled="checkButton"
                           value="Send Request!"/>
                </div>
                <!--Показывает кнопку отвязаться от доктора, если доктор содержит такого пациента-->
                <div v-else-if="checkBindingToDoctor">
                    <input type="button" class="btn btn-primary" @click="cancelBindingDoctor" value="Cancel Binding!"/>
                </div>
            </div>

            <!-- Выводит кнопку отправить запрос, если клиника не содержит такого доктора-->
            <div v-if="!profileDoctor && !profilePatient && !checkBindingToDoctor">
                <input type="button" class="btn btn-primary" @click="saveDoctorRequest" :disabled="checkButton"
                       value="Send Request!"/>
            </div>
            <!-- Выводит кнопку отвязаться от доктора, если клиника содержит такого доктора-->
            <div v-else-if="!profileDoctor && !profilePatient && checkBindingToDoctor">
                <input type="button" class="btn btn-primary" @click="cancelBindingDoctor"
                       value="Cancel Binding!"/>
            </div>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        name: 'doctorProfile',
        props: ['idProfileDoctor'],
        data() {
            return {
                currentProfileDoctor: null,
                error: null,
                id: '',
                idDoctor: 0,
                idClinic: 0,
                idPatient: 0,
                checkButton: false,
                checkAffiliation: null,
                checkBindingToDoctor: false
            }
        },
        created() {
            this.idDoctor = this.idProfileDoctor;
            if(this.profileClinic != null){
                this.idClinic = this.profileClinic.id;
            }
            if(this.profilePatient != null) {
                this.idPatient = this.profilePatient.id;
                this.affiliationToClinic();
            }

            this.fetchDoctorData();
            this.checkBindingDoctor();
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient'])
        },
        methods: {
            saveDoctorRequest() {
                let request = {idClinic: this.idClinic, idPatient: this.idPatient};

                this.$resource('/send-request-doctor/{id}').save({id: this.idDoctor}, request).then(result =>
                    result.json().then(data => {
                        this.checkButton = true;
                        console.log(data);
                    })
                )
            },
            fetchDoctorData(){
                this.error = null;
                this.$resource('/doctor/{id}').get({id: this.idProfileDoctor}).then(result =>
                    result.json().then(data => {
                        this.currentProfileDoctor = data;
                    })
                )
            },
            affiliationToClinic() {
                this.$resource('/affiliation-to-clinic/{id}').get({id: this.idProfileDoctor}).then(result => {
                        this.checkAffiliation = result.data;
                    }
                )
            },

            checkBindingDoctor(){
                this.$resource('/check-binding-doctor/{id}').get({id: this.idDoctor}).then(result => {
                    this.checkBindingToDoctor = result.data;
                    console.log(result);
                })
            },
            // Отмена привязки от доктора
            cancelBindingDoctor(){
                this.$resource('/cancel-binding-from-doctor/{id}').delete({id: this.idDoctor}).then(result => {
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