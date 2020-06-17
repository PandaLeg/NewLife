<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
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
                                            <label v-for="child in listChildren" :key="child.id">
                                                <router-link
                                                        :to="{name: 'childProfile', params: {idProfileChild: child.id}}">
                                                    {{ child.nameChild }}
                                                </router-link>
                                            </label>
                                        </p>
                                    </div>
                                </div>
                                <v-app id="inspire">
                                    <v-row>
                                        <div class="col-md-6" v-if="profileClinic && checkBindingPatientClinic">
                                            <v-dialog v-model="dialog" persistent max-width="600px">
                                                <template v-slot:activator="{ on, attrs }">
                                                    <v-btn
                                                            color="primary"
                                                            dark
                                                            v-bind="attrs"
                                                            v-on="on"
                                                            outlined
                                                    >
                                                        {{ $t('patientProfile.sendMessage') }}
                                                    </v-btn>
                                                </template>
                                                <v-container>
                                                    <v-form
                                                            ref="form"
                                                            lazy-validation
                                                    >
                                                        <v-card>
                                                            <v-card-title>
                                                            <span class="headline">{{ $t('patientProfile.sendMessage')}}
                                                            </span>
                                                            </v-card-title>

                                                            <v-card-text>
                                                                <v-row>
                                                                    <v-col cols="12">
                                                                        <v-text-field
                                                                                v-model="title"
                                                                                :rules="titleRules"
                                                                                :label="$t('patientProfile.title')"
                                                                                clearable
                                                                                required
                                                                        >
                                                                        </v-text-field>
                                                                    </v-col>
                                                                    <v-col cols="12">
                                                                        <v-textarea
                                                                                v-model="description"
                                                                                :counter="255"
                                                                                :rules="descriptionRules"
                                                                                :label="$t('patientProfile.description')"
                                                                                color="teal"
                                                                                clearable
                                                                                required
                                                                        >
                                                                        </v-textarea>
                                                                    </v-col>
                                                                </v-row>
                                                            </v-card-text>

                                                            <v-card-actions>
                                                                <v-spacer></v-spacer>
                                                                <v-btn color="blue darken-1" text
                                                                       @click="dialog = false">
                                                                    {{ $t('patientProfile.close') }}
                                                                </v-btn>
                                                                <v-btn color="blue darken-1" text @click="sendMessage">
                                                                    {{ $t('patientProfile.send') }}
                                                                </v-btn>
                                                            </v-card-actions>
                                                        </v-card>
                                                    </v-form>
                                                </v-container>
                                            </v-dialog>
                                        </div>
                                    </v-row>
                                </v-app>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapState, mapGetters} from 'vuex'
    import {mapActions} from 'vuex'
    import patientApi from 'api/patientProfile'

    export default {
        props: ['idProfilePatient'],
        data() {
            return {
                title: '',
                titleRules: [
                    v => !!v || this.$i18n.t('patientProfile.titleEmpty')
                ],
                description: '',
                descriptionRules: [
                    v => !!v || this.$i18n.t('patientProfile.descriptionEmpty')
                ],
                dialog: false
            }
        },
        created() {
            if (this.profileDoctor != null) {
                this.checkBindingPatient();
            }
            if (this.profileClinic != null) {
                this.checkBindingPatientToClinic();
            }
            this.fetchPatientData();
            this.fetchChildrenData();
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient', 'defaultPicture']),
            ...mapState('patientProfile', ['currentProfilePatient', 'patientPicture', 'checkBindingToPatient',
                'checkBindingPatientClinic', 'error']),
            ...mapGetters('patientProfile', ['listChildren'])
        },
        methods: {
            ...mapActions('patientProfile', ['fetchPatientDataAction', 'fetchChildrenDataAction',
                'checkBindingPatientAction', 'checkBindingPatientToClinicAction']),
            fetchPatientData() {
                this.fetchPatientDataAction(this.idProfilePatient);
            },
            fetchChildrenData() {
                this.fetchChildrenDataAction(this.idProfilePatient);
            },
            checkBindingPatient() {
                this.checkBindingPatientAction(this.idProfilePatient);
            },
            checkBindingPatientToClinic() {
                this.checkBindingPatientToClinicAction(this.idProfilePatient);
            },
            showModal() {
                this.title = '';
                this.description = '';

                this.$refs['my-modal'].show();
            },
            hideModal() {
                this.title = '';
                this.description = '';

                this.$refs['my-modal'].hide()
            },
            async sendMessage() {
                this.$refs.form.validate();
                let message = {
                    idProfilePatient: this.idProfilePatient, title: this.title,
                    description: this.description
                };
                const result = await patientApi.sendMessage(message);
                if(this.title !== '' && this.description !== ''){
                    this.dialog = false;
                    this.title = '';
                    this.description = '';
                }
                console.log(result);
            }
        }
    }
</script>
<style scoped>
    .emp-profile {
        padding: 3%;
        margin-top: 3%;
        margin-bottom: 3%;
        border-radius: 0.5rem;
        background: #fff;
    }

    .profile-img {
        text-align: center;
    }

    .profile-img img {
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

    .profile-head h5 {
        color: #333;
    }

    .profile-head h6 {
        color: #0062cc;
    }

    .profile-edit-btn {
        border: none;
        border-radius: 1.5rem;
        width: 80%;
        height: 20%;
        padding: 2%;
        font-weight: 600;
        color: #6c757d;
        cursor: pointer;
    }

    .profile-rating {
        font-size: 12px;
        color: #818182;
        margin-top: 5%;
    }

    .profile-rating span {
        color: #495057;
        font-size: 15px;
        font-weight: 600;
    }

    .profile-head .nav-tabs {
        margin-bottom: 5%;
    }

    .profile-head .nav-tabs .nav-link {
        font-weight: 600;
        border: none;
    }

    .profile-head .nav-tabs .nav-link.active {
        border: none;
        border-bottom: 2px solid #0062cc;
    }

    .profile-work {
        padding: 20%;
        margin-top: -15%;
    }

    .profile-check {
        padding: 13%;
        margin-top: -20%;
    }

    .btn-primary {
        border-radius: 12px;
    }

    .profile-work p {
        font-size: 12px;
        color: #818182;
        font-weight: 600;
        margin-top: 10%;
    }

    .profile-work a {
        text-decoration: none;
        color: #495057;
        font-weight: 600;
        font-size: 14px;
    }

    .profile-tab label {
        font-weight: 600;
    }

    .profile-tab p {
        font-weight: 600;
        color: #0062cc;
    }
</style>