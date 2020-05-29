<template>
    <div>
        <label> {{ request.message }}</label>
        <div v-if="profileClinic">
            <span>
                <input type="button" class="btn btn-info" value="Save" @click="addByClinicToDoctorOrPatient">
                <input type="button" class="btn btn-info" value="Cancel" @click="cancelByClinicToDoctorOrPatient">
            </span>
        </div>
        <div v-else-if="profileDoctor">
            <span>
                <input type="button" class="btn btn-info" value="Save" @click="addByDoctorToPatient">
                <input type="button" class="btn btn-info" value="Cancel" @click="cancelByDoctorToPatient">
            </span>
        </div>
        <br>
    </div>
</template>

<script>
    export default {
        props: ['request', 'profileClinic', 'profileDoctor', 'profilePatient', 'requests'],
        data(){
            return{
                id: null
            }
        },
        created(){
            this.id = this.request.id;
        },
        methods:{
            addByClinicToDoctorOrPatient() {
                this.$resource('/accept-request-clinic{/id}').delete({id: this.id}).then(result => {
                        if(result.ok){
                            console.log(result);
                            this.requests.splice(this.requests.indexOf(this.request), 1);
                        }
                    }
                )
            },
            cancelByClinicToDoctorOrPatient() {
                this.$resource('/cancel-request-clinic/{id}').delete({id: this.id}).then(result => {
                    if(result.ok){
                        console.log(result);
                        this.requests.splice(this.requests.indexOf(this.request), 1);
                    }
                })
            },
            addByDoctorToPatient(){
                this.$resource('/accept-request-doctor{/id}').delete({id: this.id}).then(result =>{
                    if(result.ok){
                        console.log(result);
                        this.requests.splice(this.requests.indexOf(this.request),1);
                    }
                })
            },
            cancelByDoctorToPatient() {
                this.$resource('/cancel-request-doctor/{id}').delete({id: this.id}).then(result => {
                    if(result.ok){
                        console.log(result);
                        this.requests.splice(this.requests.indexOf(this.request),1);
                    }
                })
            }
        }
    }
</script>

<style>

</style>