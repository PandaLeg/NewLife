<template>
    <div>
        <div v-if="currentProfileDoctor" class="content">
            <div class="container emp-profile">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img" v-if="doctorPicture != '/img/null'">
                            <img :src="doctorPicture">
                        </div>
                        <div class="profile-img" v-else>
                            <img :src="defaultPicture">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                            <h5>
                                {{ currentProfileDoctor.username }}
                            </h5>
                            <p class="profile-rating">RANKINGS : <span>8/10</span></p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" role="tab"
                                       aria-controls="home" aria-selected="true">{{ $t('doctorProfile.about') }}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2" v-if="profileDoctor">
                        <input type="submit" class="profile-edit-btn" name="btnAddMore"
                               :value="$t('doctorProfile.editProfile')"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                            <p>{{ $t('doctorProfile.achievements') }}</p>
                            <a href=""></a><br/>
                            <a href=""></a><br/>
                            <a href=""></a>
                        </div>
                        <!-- Проверяет привязаны ли пациент и доктор к одной клинике-->
                        <div class="profile-check" v-if="checkAffiliation && !profileClinic">
                            <!--Показывает кнопку отправить запрос, если доктор не содержит такого пациента-->
                            <div v-if="!checkBindingToDoctor">
                                <input type="button" class="btn btn-primary" @click="saveDoctorRequest"
                                       :disabled="checkButton"
                                       value="Send Request!"/>
                            </div>
                            <!--Показывает кнопку отвязаться от доктора, если доктор содержит такого пациента-->
                            <div v-else-if="checkBindingToDoctor">
                                <input type="button" class="btn btn-primary" @click="cancelBindingDoctor"
                                       value="Cancel Binding!"/>
                            </div>
                        </div>

                        <!-- Выводит кнопку отправить запрос, если клиника не содержит такого доктора-->
                        <div class="profile-check" v-if="!profileDoctor && !profilePatient && !checkBindingToDoctor">
                            <input type="button" class="btn btn-primary" @click="saveDoctorRequest"
                                   :disabled="checkButton" value="Send Request!"/>
                        </div>
                        <!-- Выводит кнопку отвязаться от доктора, если клиника содержит такого доктора-->
                        <div class="profile-check" v-else-if="!profilePatient && checkBindingToDoctor && !profileDoctor">
                            <input type="button" class="btn btn-primary" @click="cancelBindingDoctor"
                                   value="Cancel Binding!"/>
                        </div>
                    </div>

                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('doctorProfile.name') }}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfileDoctor.firstName }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('doctorProfile.surname') }}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfileDoctor.surname }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Email</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfileDoctor.email }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('doctorProfile.specialization') }}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfileDoctor.position }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('doctorProfile.experience') }}</label>
                                    </div>
                                    <div class="col-md-6" v-if="currentProfileDoctor.experience != null">
                                        <p>{{ currentProfileDoctor.experience }}</p>
                                    </div>
                                    <div class="col-md-6" v-else>
                                        <p>{{ $t('doctorProfile.specified') }}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import { mapState, mapGetters } from 'vuex'
    import {mapActions} from 'vuex'
    export default {
        name: 'doctorProfile',
        props: ['idProfileDoctor'],
        data() {
            return {
                id: '',
                idDoctor: 0,
                idClinic: 0,
                idPatient: 0,
                checkButton: false,
            }
        },
        created() {
            this.idDoctor = this.idProfileDoctor;
            if(this.profileClinic != null){
                this.idClinic = this.profileClinic.id;
                this.checkBindingDoctor();
            }
            if(this.profilePatient != null) {
                this.idPatient = this.profilePatient.id;
                this.affiliationToClinic();
                this.checkBindingDoctor();
            }
            this.fetchDoctorData();
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient', 'defaultPicture']),
            ...mapState('doctorProfile', ['currentProfileDoctor', 'doctorPicture', 'checkAffiliation',
            'checkBindingToDoctor', 'error'])
        },
        methods: {
            ...mapActions('doctorProfile', ['saveDoctorRequestAction', 'fetchDoctorDataAction',
                'affiliationToClinicAction', 'checkBindingDoctorAction', 'cancelBindingDoctorAction']),
            saveDoctorRequest() {
                let request = {idDoctor: this.idDoctor, idClinic: this.idClinic, idPatient: this.idPatient};
                this.saveDoctorRequestAction(request);
                this.checkButton = true;
            },
            fetchDoctorData(){
                this.fetchDoctorDataAction(this.idProfileDoctor);
            },
            affiliationToClinic() {
                this.affiliationToClinicAction(this.idProfileDoctor);
            },

            checkBindingDoctor(){
                this.checkBindingDoctorAction(this.idProfileDoctor);
            },
            // Отмена привязки от доктора
            cancelBindingDoctor(){
                this.cancelBindingDoctorAction(this.idDoctor);
            }
        }
    }
</script>

<style scoped>
    .emp-profile{
        padding: 3%;
        margin-top: 3%;
        margin-bottom: 3%;
        border-radius: 0.5rem;
        background: #fff;
    }
    .profile-img{
        text-align: center;
    }
    .profile-img img{
        width: 200px;
        height: 200px;
        border-radius: 50%;
        background-size: auto 300px;
    }
    .profile-img .file {
        position: relative;
        overflow: hidden;
        margin-top: -20%;
        width: 70%;
        border: none;
        border-radius: 0;
        font-size: 15px;
        background: #212529b8;
    }
    .profile-img .file input {
        position: absolute;
        opacity: 0;
        right: 0;
        top: 0;
    }

    .profile-head h5{
        color: #333;
    }
    .profile-head h6{
        color: #0062cc;
    }

    .profile-edit-btn{
        border: none;
        border-radius: 1.5rem;
        width: 80%;
        height: 20%;
        padding: 2%;
        font-weight: 600;
        color: #6c757d;
        cursor: pointer;
    }
    .profile-rating{
        font-size: 12px;
        color: #818182;
        margin-top: 5%;
    }
    .profile-rating span{
        color: #495057;
        font-size: 15px;
        font-weight: 600;
    }

    .profile-head .nav-tabs{
        margin-bottom:5%;
    }
    .profile-head .nav-tabs .nav-link{
        font-weight:600;
        border: none;
    }

    .profile-head .nav-tabs .nav-link.active{
        border: none;
        border-bottom:2px solid #0062cc;
    }
    .profile-work{
        padding: 20%;
        margin-top: -15%;
    }

    .profile-check{
        padding: 20%;
        margin-top: -30%;
    }

    .btn-primary{
        border-radius: 12px;
    }
    .profile-work p{
        font-size: 12px;
        color: #818182;
        font-weight: 600;
        margin-top: 10%;
    }
    .profile-work a{
        text-decoration: none;
        color: #495057;
        font-weight: 600;
        font-size: 14px;
    }

    .profile-tab label{
        font-weight: 600;
    }
    .profile-tab p{
        font-weight: 600;
        color: #0062cc;
    }
</style>