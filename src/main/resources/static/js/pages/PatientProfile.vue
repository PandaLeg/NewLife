<template>
    <div>
        <div v-if="error" class="error">
            {{ error }}
        </div>
        <div v-if="currentProfilePatient" class="content">
            <div class="container emp-profile">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img" v-if="patientPicture != '/img/null'">
                            <img :src="patientPicture">
                        </div>
                        <div class="profile-img" v-else>
                            <img :src="defaultPicture">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                            <h5>
                                {{ currentProfilePatient.username }}
                            </h5>
                            <p class="profile-rating">RANKINGS : <span>8/10</span></p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" role="tab"
                                       aria-controls="home" aria-selected="true">{{ $t('patientProfile.about') }}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2" v-if="profilePatient">
                        <input type="submit" class="profile-edit-btn" name="btnAddMore"
                               :value="$t('patientProfile.editProfile')"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                            <p>{{ $t('patientProfile.interests') }}</p>
                            <a href=""></a><br/>
                            <a href=""></a><br/>
                            <a href=""></a>
                        </div>
                    </div>

                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('patientProfile.name') }}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfilePatient.firstName }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('patientProfile.surname') }}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfilePatient.surname }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Email</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfilePatient.email }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6" v-if="profilePatient || checkBindingToPatient">
                                        <label>{{ $t('patientProfile.children') }}</label>
                                    </div>
                                    <div class="col-md-6" v-if="profilePatient || checkBindingToPatient">
                                        <p><!--Показывает, доктору или опекуну ссылку на профиль ребёнка-->
                                            <label v-for="child in children" :key="child.id">
                                                <router-link :to="{name: 'childProfile', params: {idProfileChild: child.id}}">
                                                    {{ child.nameChild }}
                                                </router-link>
                                            </label>
                                        </p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6" v-if="profileClinic && checkBindingPatientClinic">
                                        <b-button variant="outline-primary" @click="showModal">
                                            {{ $t('patientProfile.sendMessage') }}
                                        </b-button>

                                        <b-modal id="modal-prevent-closing" ref="my-modal" hide-footer
                                                 :title="$t('patientProfile.sendMessage')">
                                            <b-form-group :label="$t('patientProfile.title')" label-for="name-input">
                                                <b-form-input type="text" id="name-input" v-model="title">

                                                </b-form-input>
                                            </b-form-group>

                                            <b-form-group :label="$t('patientProfile.description')"
                                                          label-for="description-input">
                                                <b-form-input type="text" id="description-input" v-model="description"

                                                ></b-form-input>
                                                <b-button class="mt-2" variant="outline-primary" block
                                                          @click="sendMessage">{{ $t('patientProfile.send') }}
                                                </b-button>
                                                <b-button class="mt-3" variant="outline-danger" block
                                                          @click="hideModal">{{ $t('patientProfile.close') }}
                                                </b-button>
                                            </b-form-group>
                                        </b-modal>
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
    import {mapState} from 'vuex'
    export default {
        props: ['idProfilePatient'],
        data(){
            return{
                currentProfilePatient: null,
                error: null,
                children: [],
                patientPicture: '',
                checkBindingToPatient: false,
                checkBindingPatientClinic: false,
                title: '',
                description: ''
            }
        },
        created() {
            if(this.profileDoctor != null){
                this.checkBindingPatient();
            }
            if(this.profileClinic != null){
                this.checkBindingPatientToClinic();
            }
            this.fetchPatientData();
            this.fetchChildrenData();
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient', 'defaultPicture']),
        },
        methods:{
            fetchPatientData() {
                this.error = null;
                this.$resource('/patient/{id}').get({id: this.idProfilePatient}).then(result =>
                        result.json().then(data =>{
                            this.currentProfilePatient = data;
                            this.patientPicture = '/img/' + this.currentProfilePatient.patientPicture;
                        }), response => {
                        this.error = response.error;
                    }
                )
            },
            fetchChildrenData() {
                this.$resource('/children-patient/{id}').get({id: this.idProfilePatient}).then(result => {
                    this.children = result.data;
                    console.log(result);
                })
            },
            // Проверка принадлежности пациента к врачу
            checkBindingPatient() {
                this.$resource('/check-binding-patient/{id}').get({id: this.idProfilePatient}).then(result => {
                    this.checkBindingToPatient = result.data;
                    console.log(result);
                })
            },
            checkBindingPatientToClinic(){
                this.$resource('/check-binding-clinic-to-patient/{id}').get({id: this.idProfilePatient}).then(result => {
                    this.checkBindingPatientClinic = result.data;
                    console.log(result);
                })
            },
            showModal() {
                this.title =  '';
                this.description = '';
                this.$refs['my-modal'].show();
            },
            hideModal() {
                this.title =  '';
                this.description = '';
                this.$refs['my-modal'].hide()
            },
            sendMessage(){
                let mess = {title: this.title, description: this.description};

                this.$resource('/send-message/{id}').save({id: this.idProfilePatient}, mess).then(result =>
                    result.json().then(data => {
                        this.title =  '';
                        this.description = '';
                        console.log(result);
                    })
                );
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
        padding: 13%;
        margin-top: -20%;
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