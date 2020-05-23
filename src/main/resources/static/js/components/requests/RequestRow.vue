<template>
    <div>
        <label> {{ request.message }}</label>
        <div v-if="profileClinic">
            <span>
                <input type="button" class="btn btn-info" value="Save" @click="ClinicToDoctorOrPatient">
            </span>
        </div>
        <div v-else-if="profileDoctor">
            <span>
                <span>
                <input type="button" class="btn btn-info" value="Save" @click="DoctorToPatient">
            </span>
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
            ClinicToDoctorOrPatient() {
                this.$resource('/accept-request-clinic{/id}').delete({id: this.id}).then(result => {
                        if(result.ok){
                            console.log(result);
                            this.requests.splice(this.requests.indexOf(this.request), 1);
                        }
                    }
                )
            },
            DoctorToPatient(){
                this.$resource('/accept-request-doctor{/id}').delete({id: this.id}).then(result =>{
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