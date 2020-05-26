<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <!--<profile-clinic :currentProfileClinic="currentProfileClinic" :check="isRole" :profileDoctor="profileDoctor"
        :profilePatient="profilePatient">
        </profile-clinic>-->
        <h4>{{ currentProfileClinic.username }}</h4>
        <br>
        <label>{{ currentProfileClinic.nameClinic }}</label>
        <br>
        <label>{{ currentProfileClinic.address }}</label>
        <br>
        <div v-if="isRole">
            <input type="button" class="btn btn-primary" @click="saveRequest" value="Send Request!"/>
        </div>
    </div>
</template>

<script>
    /*import ProfileClinic from 'components/clinic/ProfileClinic.vue'*/
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            /*ProfileClinic,*/
            NavBar
        },
        data() {
            return {
                profileClinic: profileData.profileClinic,
                profileDoctor: profileData.profileDoctor,
                profilePatient: profileData.profilePatient,
                currentProfileClinic: profileData.currentProfileClinic,
                isRole: profileData.checkRole,
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