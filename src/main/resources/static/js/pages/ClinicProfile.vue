<template>
    <div>
        <div v-if="error" class="error">
            {{ error }}
        </div>
        <div v-if="currentProfileClinic" class="content">
            <div class="container emp-profile">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img" v-if="clinicPicture != '/img/null'">
                            <img :src="clinicPicture">
                        </div>
                        <div class="profile-img" v-else>
                            <img :src="defaultPicture">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                            <h5>
                                {{ currentProfileClinic.username }}
                            </h5>
                            <p class="profile-rating">RANKINGS : <span>8/10</span></p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" role="tab"
                                       aria-controls="home" aria-selected="true">{{ $t('clinicProfile.about') }}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2" v-if="profileClinic">
                        <input type="submit" class="profile-edit-btn" name="btnAddMore"
                               :value="$t('clinicProfile.editProfile')"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                            <p>{{ $t('clinicProfile.achievements') }}</p>
                            <a href=""></a><br/>
                            <a href=""></a><br/>
                            <a href=""></a>
                        </div>
                        <div class="profile-check" v-if="!checkBindingToClinic">
                            <div v-if="!profileClinic">
                                <input type="button" class="btn btn-primary" @click="saveClinicRequest"
                                       :disabled="checkButton" value="Send Request!"/>
                            </div>
                        </div>
                        <div class="profile-check" v-if="checkBindingToClinic">
                            <div v-if="!profileClinic">
                                <input type="button" class="btn btn-primary" @click="cancelBindingClinic"
                                       value="Cancel Binding!"/>
                            </div>
                        </div>

                        <div></div>
                    </div>

                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('clinicProfile.nameClinic') }}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfileClinic.nameClinic }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Email</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfileClinic.email }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('clinicProfile.phone') }}</label>
                                    </div>
                                    <div class="col-md-6" v-if="currentProfileClinic.phone != null">
                                        <p>{{ currentProfileClinic.phone }}</p>
                                    </div>
                                    <div class="col-md-6" v-else>
                                        <p>{{ $t('clinicProfile.specified') }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('clinicProfile.address') }}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfileClinic.address }}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <h5>{{ $t('clinicProfile.listDoctors') }}</h5>
                <hr class="mt-2 mb-3"/>
                <b-card-group >
                    <div class="card-check my-3" v-for="doctor in doctors" :key="doctor.id">
                        <b-card
                                img-alt="Image"
                                img-top
                                tag="article"
                                style="max-width: 300px; width: 250px;"
                                class="text-center mr-4"
                                border-variant="info" :header="doctor.position">
                            <b-card-text>
                                {{ doctor.firstName }}
                                {{ doctor.surname }}
                            </b-card-text>
                        </b-card>
                    </div>
                </b-card-group>

                <h5>{{ $t('clinicProfile.listPatients') }}</h5>
                <hr class="mt-2 mb-3"/>
                <b-card-group>
                    <div class="card-check my-3" v-for="patient in patients" :key="patient.id">
                        <b-card
                                img-alt="Image"
                                img-top
                                tag="article"
                                style="max-width: 300px; width: 250px;"
                                class="text-center mr-4"
                                border-variant="info">
                            <b-card-text>
                                {{ patient.firstName }}
                                {{ patient.surname }}
                            </b-card-text>
                        </b-card>
                    </div>
                </b-card-group>
            </div>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    import { mapActions } from 'vuex'
    import requestsApi from 'api/clinicProfile'
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
                clinicPicture: '',
                checkButton: false,
                checkBindingToClinic: false,
                doctors: [],
                patients: [],
                counter: 0,
                slide: 0,
                sliding: null
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
            this.getDoctorsList();
            this.getPatientsList();
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient', 'defaultPicture'])
        },
        methods:{
            ...mapActions('clinicProfile', ['saveClinicRequestAction']),
            saveClinicRequest() {
                let request = {idClinic: this.idClinic, idDoctor: this.idDoctor, idPatient: this.idPatient};
                this.saveClinicRequestAction(request);
                this.checkButton = true;
                /*requestsApi.add(request).then(result =>
                    result.json().then(data => {
                        this.checkButton = true;
                        console.log(data);
                    })
                )*/
            },

            fetchClinicData(){
                this.error = null;
                this.$resource('/clinic/{id}').get({id: this.idProfileClinic}).then(result =>
                    result.json().then(data => {
                        this.currentProfileClinic = data;
                        this.clinicPicture = '/img/' + this.currentProfileClinic.clinicPicture;
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
            },
            getDoctorsList(){
                this.$resource('/doctors-list-clinic/{id}').get({id: this.idProfileClinic}).then(result => {
                    result.json().then(data =>{
                        this.doctors = data;
                    })
                })
            },
            getPatientsList(){
                this.$resource('/patients-list-clinic/{id}').get({id: this.idProfileClinic}).then(result => {
                    result.json().then(data => {
                        this.patients = data;
                    })
                })
            }
        }
    }
</script>

<style scoped>
    .emp-profile{
        padding: 3%;
        margin-top: 3%;
        margin-bottom: 3%;
        border-radius: 50%;
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

    .card-check img{
        max-height: 150px;
        max-width: 250px;
    }
    @media (min-width: 576px) and (max-width: 768px) {
        .slide-box img {
            -ms-flex: 0 0 50%;
            flex: 0 0 50%;
            max-width: 50%;
        }
    }

    @media (min-width: 768px) and (max-width: 992px) {
        .slide-box img {
            -ms-flex: 0 0 33.3333%;
            flex: 0 0 33.3333%;
            max-width: 33.3333%;
        }
    }

    @media (min-width: 992px)
    {
        .slide-box img {
            -ms-flex: 0 0 25%;
            flex: 0 0 25%;
            max-width: 25%;
        }
    }
</style>