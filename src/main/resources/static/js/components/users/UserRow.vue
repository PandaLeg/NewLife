<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-card color="orange" class="mt-sm-2">
        <v-list two-line v-if="clinic">
            <v-list-item @click="">
                <v-list-item-avatar>
                    <img :src="defaultPicture">
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title>{{clinic.nameClinic}}</v-list-item-title>
                    <v-list-item-subtitle>{{clinic.city}}</v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-action>
                    <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn
                                    color="primary"
                                    dark
                                    v-bind="attrs"
                                    v-on="on"
                                    icon
                                    @click="getRoles"
                            >
                                <v-icon color="grey lighten-1">mdi-information</v-icon>
                            </v-btn>
                        </template>
                        <v-card>
                            <v-toolbar dark color="primary">
                                <v-btn icon dark @click="dialog = false">
                                    <v-icon>mdi-close</v-icon>
                                </v-btn>
                                <v-toolbar-title>Settings</v-toolbar-title>
                                <v-spacer></v-spacer>
                                <v-toolbar-items>
                                    <v-btn dark text @click="setRoles">Save</v-btn>
                                </v-toolbar-items>
                            </v-toolbar>
                            <v-list three-line subheader>
                                <v-subheader>User Controls</v-subheader>
                                <v-list-item>
                                    <v-list-item-content>
                                        <v-list-item-title>Content filtering</v-list-item-title>
                                        <v-list-item-subtitle>
                                            Set the content filtering level to restrict apps
                                            that can be downloaded
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item>
                                    <v-list-item-content>
                                        <v-list-item-title>Password</v-list-item-title>
                                        <v-list-item-subtitle>
                                            Require password for purchase or use password to
                                            restrict purchase
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list>
                            <v-divider></v-divider>
                            <v-list three-line subheader>
                                <v-subheader>Roles</v-subheader>
                                <v-list-item>
                                    <v-list-item-action>
                                        <v-checkbox v-model="clinicRole"></v-checkbox>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                        <v-list-item-title>Clinic</v-list-item-title>
                                        <v-list-item-subtitle>Notify me about updates to apps or games that I downloaded
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item>
                                    <v-list-item-action>
                                        <v-checkbox v-model="adminRole"></v-checkbox>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                        <v-list-item-title>Admin</v-list-item-title>
                                        <v-list-item-subtitle>Automatically add home screen widgets
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list>
                        </v-card>
                    </v-dialog>
                </v-list-item-action>
            </v-list-item>
        </v-list>

        <v-list two-line v-if="doctor">
            <v-list-item @click="">
                <v-list-item-avatar>
                    <img :src="defaultPicture">
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title>{{doctor.firstName}}</v-list-item-title>
                    <v-list-item-subtitle>{{doctor.surname}}</v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-action>
                    <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn
                                    color="primary"
                                    dark
                                    v-bind="attrs"
                                    v-on="on"
                                    icon
                                    @click="getRoles"
                            >
                                <v-icon color="grey lighten-1">mdi-information</v-icon>
                            </v-btn>
                        </template>
                        <v-card>
                            <v-toolbar dark color="primary">
                                <v-btn icon dark @click="dialog = false">
                                    <v-icon>mdi-close</v-icon>
                                </v-btn>
                                <v-toolbar-title>Settings</v-toolbar-title>
                                <v-spacer></v-spacer>
                                <v-toolbar-items>
                                    <v-btn dark text @click="setRoles">Save</v-btn>
                                </v-toolbar-items>
                            </v-toolbar>
                            <v-list three-line subheader>
                                <v-subheader>User Controls</v-subheader>
                                <v-list-item>
                                    <v-list-item-content>
                                        <v-list-item-title>Content filtering</v-list-item-title>
                                        <v-list-item-subtitle>
                                            Set the content filtering level to restrict apps
                                            that can be downloaded
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item>
                                    <v-list-item-content>
                                        <v-list-item-title>Password</v-list-item-title>
                                        <v-list-item-subtitle>
                                            Require password for purchase or use password to
                                            restrict purchase
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list>
                            <v-divider></v-divider>
                            <v-list three-line subheader>
                                <v-subheader>Roles</v-subheader>
                                <v-list-item>
                                    <v-list-item-action>
                                        <v-checkbox v-model="doctorRole"></v-checkbox>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                        <v-list-item-title>Doctor</v-list-item-title>
                                        <v-list-item-subtitle>Auto-update apps at any time. Data charges may apply
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item>
                                    <v-list-item-action>
                                        <v-checkbox v-model="adminRole"></v-checkbox>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                        <v-list-item-title>Admin</v-list-item-title>
                                        <v-list-item-subtitle>Automatically add home screen widgets
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list>
                        </v-card>
                    </v-dialog>
                </v-list-item-action>
            </v-list-item>
        </v-list>

        <v-list two-line v-if="patient">
            <v-list-item @click="">
                <v-list-item-avatar>
                    <img :src="defaultPicture">
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title>{{patient.firstName}}</v-list-item-title>
                    <v-list-item-subtitle>{{patient.surname}}</v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-action>
                    <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn
                                    color="primary"
                                    dark
                                    v-bind="attrs"
                                    v-on="on"
                                    icon
                                    @click="getRoles"
                            >
                                <v-icon color="grey lighten-1">mdi-information</v-icon>
                            </v-btn>
                        </template>
                        <v-card>
                            <v-toolbar dark color="primary">
                                <v-btn icon dark @click="dialog = false">
                                    <v-icon>mdi-close</v-icon>
                                </v-btn>
                                <v-toolbar-title>Settings</v-toolbar-title>
                                <v-spacer></v-spacer>
                                <v-toolbar-items>
                                    <v-btn dark text @click="setRoles">Save</v-btn>
                                </v-toolbar-items>
                            </v-toolbar>
                            <v-list three-line subheader>
                                <v-subheader>User Controls</v-subheader>
                                <v-list-item>
                                    <v-list-item-content>
                                        <v-list-item-title>Content filtering</v-list-item-title>
                                        <v-list-item-subtitle>
                                            Set the content filtering level to restrict apps
                                            that can be downloaded
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item>
                                    <v-list-item-content>
                                        <v-list-item-title>Password</v-list-item-title>
                                        <v-list-item-subtitle>
                                            Require password for purchase or use password to
                                            restrict purchase
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list>
                            <v-divider></v-divider>
                            <v-list three-line subheader>
                                <v-subheader>Roles</v-subheader>
                                <v-list-item>
                                    <v-list-item-action>
                                        <v-checkbox v-model="patientRole"></v-checkbox>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                        <v-list-item-title>Patient</v-list-item-title>
                                        <v-list-item-subtitle>Auto-update apps at any time. Data charges may apply
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item>
                                    <v-list-item-action>
                                        <v-checkbox v-model="adminRole"></v-checkbox>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                        <v-list-item-title>Admin</v-list-item-title>
                                        <v-list-item-subtitle>Automatically add home screen widgets
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list>
                        </v-card>
                    </v-dialog>
                </v-list-item-action>
            </v-list-item>
        </v-list>
    </v-card>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        props: ['clinic', 'doctor', 'patient'],
        computed: {
            ...mapState('mainModule', ['defaultPicture']),
        },
        data() {
            return {
                clinicRole: false,
                doctorRole: false,
                patientRole: false,
                adminRole: false,
                dialog: false,
                rolesClinic: [],
                rolesDoctor: [],
                rolesPatient: []
            }
        },
        methods: {
            showProfileClinic() {
                this.$router.push({name: 'clinicProfile', params: {idProfileClinic: this.clinic.id}})
            },
            getRoles() {
                if(this.clinic) {
                    this.$resource('/clinic-roles/{id}').get({id: this.clinic.id}).then(result => {
                        this.rolesClinic = result.data;
                        this.role(this.rolesClinic);
                    })
                }
                if(this.doctor){
                    this.$resource('/doctor-roles/{id}').get({id: this.doctor.id}).then(result => {
                        this.rolesDoctor = result.data;
                        this.role(this.rolesDoctor);
                    })
                }
                if(this.patient){
                    this.$resource('/patient-roles/{id}').get({id: this.patient.id}).then(result => {
                        this.rolesPatient = result.data;
                        this.role(this.rolesPatient);
                    })
                }
            },
            setRoles() {
                if (this.clinic) {
                    this.$resource('/set-role-clinic/{id}').update({id: this.clinic.id}, this.roles()).then(result => {
                        console.log(result);
                    });
                }
                if (this.doctor) {
                    this.$resource('/set-role-doctor/{id}').update({id: this.doctor.id}, this.roles()).then(result => {
                        console.log(result);
                    });
                }
                if (this.patient) {
                    this.$resource('/set-role-patient/{id}').update({id: this.patient.id}, this.roles()).then(result => {
                        console.log(result);
                    });
                }
                this.dialog = false;
            },
            roles() {
                return {
                    clinicRole: this.clinicRole, doctorRole: this.doctorRole, patientRole: this.patientRole,
                    adminRole: this.adminRole
                };
            },
            role(roles) {
                for (let i = 0; i < roles.length; i++) {
                    if (roles[i] === ('CLINIC')) {
                        this.clinicRole = true;
                    }
                    if (roles[i] === ('DOCTOR')) {
                        this.doctorRole = true;
                    }
                    if (roles[i] === ('PATIENT')) {
                        this.patientRole = true;
                    }
                    if (roles[i] === ('ADMIN')) {
                        this.adminRole = true;
                    }
                }
            }
        }
    }
</script>

<style>

</style>