<template>
    <div>
        <div v-if="profileClinic">
            <hr class="mt-2 mb-3"/>
            <b-card-group >
                <div class="card-check my-3">
                    <b-card
                            img-alt="Image"
                            img-top
                            tag="article"
                            style="max-width: 300px; width: 250px;"
                            class="text-center mr-4"
                            border-variant="info" header="">
                        <b-card-text>
                            <div v-if="request.doctor != null">
                                <router-link :to="{name: 'doctorProfile', params: {idProfileDoctor: request.doctor.id} }">
                                    {{ request.doctor.username }}
                                </router-link>
                            </div>
                            <div v-if="request.patient != null">
                                <router-link :to="{name: 'patientProfile', params: {idProfilePatient: request.patient.id} }">
                                    {{ request.patient.username }}
                                </router-link>
                            </div>
                        </b-card-text>
                        <b-button class="btn btn-info" @click="addByClinicToDoctorOrPatient">
                            Save
                        </b-button>
                        <b-button class="btn btn-info" @click="cancelByClinicToDoctorOrPatient">
                            Cancel
                        </b-button>
                    </b-card>
                </div>
            </b-card-group>
        </div>
        <div v-else-if="profileDoctor">
            <b-card-group >
                <div class="card-check my-3">
                    <b-card
                            img-alt="Image"
                            img-top
                            tag="article"
                            style="max-width: 300px; width: 250px;"
                            class="text-center mr-4"
                            border-variant="info" header="">
                        <b-card-text>
                            <div v-if="request.clinic != null">
                                <router-link
                                        :to="{name: 'clinicProfile', params: {idProfileClinic: request.clinic.id} }">
                                    {{ request.clinic.username }}
                                </router-link>
                            </div>
                            <div v-if="request.patient != null">
                                <router-link :to="{name: 'patientProfile', params: {idProfilePatient: request.patient.id} }">
                                    {{ request.patient.username }}
                                </router-link>
                            </div>
                        </b-card-text>
                        <b-button class="btn btn-info" @click="addByDoctorToPatient">
                            Save
                        </b-button>
                        <b-button class="btn btn-info" @click="cancelByDoctorToPatient">
                            Cancel
                        </b-button>
                    </b-card>
                </div>
            </b-card-group>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    import { mapActions } from 'vuex'
    export default {
        props: ['request', 'profileClinic', 'profileDoctor', 'profilePatient', 'requests'],
        data(){
            return{
                id: null
            }
        },
        created(){

        },
        computed:{
        },
        methods:{
            ...mapActions('request', ['addByClinicToDoctorOrPatientAction', 'cancelByClinicToDoctorOrPatientAction',
            'addByDoctorToPatientAction', 'cancelByDoctorToPatientAction']),
            addByClinicToDoctorOrPatient() {
                this.addByClinicToDoctorOrPatientAction(this.request);
            },
            cancelByClinicToDoctorOrPatient() {
                this.cancelByClinicToDoctorOrPatientAction(this.request);
            },
            addByDoctorToPatient(){
                this.addByDoctorToPatientAction(this.request);
            },
            cancelByDoctorToPatient() {
                this.cancelByDoctorToPatientAction(this.request);
            }
        }
    }
</script>

<style>

</style>