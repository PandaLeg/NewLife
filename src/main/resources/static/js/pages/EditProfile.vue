<template>
    <div class="container-fluid m-5">
        <div class="row">
            <div class="col-sm-3">
                <h2>{{ $t('editingProfile.changeProfile') }}</h2>
            </div>
        </div>
        <form enctype="multipart/form-data" class="center-block">
            <div class="form-group row">
                <div class="col-sm-4 mt-3">
                    <div class="custom-file col-sm-7">
                        <input type="file" name="file" id="customFile" @change="fileChange">
                        <label class="custom-file-label" for="customFile">{{ $t('editingProfile.changeAvatar') }}</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input type="button" class="btn btn-primary" @click="updateProfileAvatar"
                           :value="$t('editingProfile.saveAvatar')"/>
                </div>
            </div>
        </form>
        <div class="form-group row">
            <div class="col-sm-4 mt-3">
                <input type="text" class="form-control" v-model="username"
                       name="username" :placeholder="$t('editingProfile.enterName')"/>
            </div>
        </div>

        <div v-if="profileClinic">
            <div class="form-group row">
                <div class="col-sm-4 mt-3">
                    <input type="text" class="form-control" v-model="city"
                           name="city" :placeholder="$t('editingProfile.enterCity')"/>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4 mt-3">
                    <input type="text" class="form-control" v-model="address"
                           name="address" :placeholder="$t('editingProfile.enterAddress')"/>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4 mt-3">
                    <input type="text" class="form-control" v-model="phone"
                           name="phone" :placeholder="$t('editingProfile.enterPhone')"/>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4 mt-3">
                    <input type="button" class="btn btn-primary" @click="updateProfileClinic"
                           :value="$t('editingProfile.saveInformation')"/>
                </div>
            </div>
        </div>

        <div v-if="profileDoctor">
            <div class="form-group row">
                <div class="col-sm-4 mt-3">
                    <input type="text" class="form-control" v-model="experience"
                           name="experience" :placeholder="$t('editingProfile.experience')"/>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-4 mt-3">
                    <input type="text" class="form-control" v-model="position"
                           name="experience" :placeholder="$t('editingProfile.position')"/>
                </div>
            </div>

            <div class="form-group row" v-if="profileDoctor">
                <div class="col-sm-4 mt-3">
                    <input type="button" class="btn btn-primary" @click="updateProfileDoctor"
                           :value="$t('editingProfile.saveInformation')"/>
                </div>
            </div>
        </div>

        <div v-if="profilePatient">
            <div class="form-group row" v-if="profilePatient">
                <div class="col-sm-4 mt-3">
                    <input type="button" class="btn btn-primary" @click="updateProfilePatient"
                           :value="$t('editingProfile.saveInformation')"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    import { mapActions } from 'vuex'
    import editApi from 'api/editProfile'
    export default {
        data(){
            return{
                username: '',
                city: '',
                address: '',
                phone: '',
                experience: '',
                position: '',
                clinicPicture: null,
                doctorPicture: null,
                patientPicture: null,
                checkClinicPicture: false,
                form: new FormData
            }
        },
        created(){
            if(this.profileClinic != null) {
                this.username = this.profileClinic.username;
                this.city = this.profileClinic.city;
                this.address = this.profileClinic.address;
                this.phone = this.profileClinic.phone;
                this.clinicPicture = this.profileClinic.clinicPicture;
            }
            if(this.profileDoctor != null){
                this.username = this.profileDoctor.username;
                this.experience = this.profileDoctor.experience;
                this.position = this.profileDoctor.position;
                this.doctorPicture = this.profileDoctor.doctorPicture;
            }
            if(this.profilePatient != null){
                this.username = this.profilePatient.username;
            }
        },
        computed:{
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient', 'defaultPicture'])
        },
        methods:{
            ...mapActions('editProfile', ['updateProfileClinicAction', 'updateProfileDoctorAction',
            'updateProfilePatientAction']),
            fileChange(e){
                let selectedFile = e.target.files[0];
                if(this.profileClinic != null) {
                    this.clinicPicture = selectedFile;
                }else if(this.profileDoctor != null){
                    this.doctorPicture = selectedFile;
                }else{
                    this.patientPicture = selectedFile;
                }
            },
            async updateProfileAvatar(){
                if (this.clinicPicture !== null) {
                    this.form.append('picture', this.clinicPicture);
                }
                if(this.doctorPicture !== null){
                    this.form.append('picture', this.doctorPicture);
                }
                if(this.patientPicture !== null){
                    this.form.append('picture', this.patientPicture);
                }
                const result = await editApi.updateAvatar(this.form);
                console.log(result);
            },
            updateProfileClinic(){
                let updateClinic = {username: this.username, phone: this.phone, city: this.city, address: this.address};
                this.updateProfileClinicAction(updateClinic);
            },
            updateProfileDoctor(){
                let updateDoctor = {username: this.username, experience: this.experience, position: this.position};
                this.updateProfileDoctorAction(updateDoctor);
            },
            updateProfilePatient(){
                let updatePatient = {username: this.username};
                this.updateProfilePatientAction(updatePatient);
            }
        }
    }
</script>

<style scoped>
    #form {
        max-width: 250px;
        padding-left: 15px;
        padding-right: 15px;
    }
    .profile-img{
        text-align: center;
    }
    .profile-img img{
        width: 150px;
        height: 150px;
        border-radius: 50%;
        background-size: auto 300px;
    }
    .profile-img .file input {
        position: absolute;
        opacity: 0;
        right: 0;
        top: 0;
    }
</style>