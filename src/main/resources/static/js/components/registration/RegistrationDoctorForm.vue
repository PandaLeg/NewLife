<template>
    <div>
        <v-container fluid>
            <v-form
                    ref="form"
                    lazy-validation
            >
                <v-row justify="center">
                    <v-col md="4">
                        <v-text-field
                                v-model="username"
                                :counter="20"
                                :rules="usernameRules"
                                :label="$t('registrationDoctor.username')"
                                clearable
                                required
                        ></v-text-field>
                        <v-text-field
                                v-model="password"
                                :counter="6"
                                :rules="passwordRules"
                                :label="$t('registrationDoctor.password')"
                                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                                :type="showPassword ? 'text' : 'password'"
                                clearable
                                required
                                @click:append="showPassword = !showPassword"
                        >
                        </v-text-field>
                        <v-text-field
                                v-model="email"
                                :rules="emailRules"
                                label="root@gmail.com"
                                clearable
                                required
                        >
                        </v-text-field>
                        <v-text-field
                                v-model="firstName"
                                :rules="firstNameRules"
                                :label="$t('registrationDoctor.name')"
                                clearable
                                required
                        >
                        </v-text-field>
                        <v-text-field
                                v-model="surname"
                                :rules="surnameRules"
                                :label="$t('registrationDoctor.surname')"
                                clearable
                                required
                        >
                        </v-text-field>
                        <v-text-field
                                v-model="position"
                                :rules="positionRules"
                                :label="$t('registrationDoctor.position')"
                                clearable
                                required
                        >
                        </v-text-field>
                        <v-btn class="mr-4" @click="saveDoctor" outlined>{{ $t('registrationClinic.create')}}</v-btn>
                    </v-col>
                </v-row>
            </v-form>
        </v-container>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                id: '',
                username: '',
                usernameRules: [
                    v => !!v || this.$i18n.t('registrationDoctor.usernameEmpty'),
                    v => (v && v.length >= 3 && v.length <= 20) || this.$i18n.t('registrationClinic.usernameLess'),
                ],
                password: '',
                passwordRules: [
                    v => !!v || this.$i18n.t('registrationDoctor.passwordEmpty'),
                    v => (v && v.length >= 6) || this.$i18n.t('registrationClinic.passwordLess'),
                ],
                email: '',
                emailRules: [
                    v => !!v || this.$i18n.t('registrationDoctor.emailEmpty'),
                    v => /.+@.+\..+/.test(v) || this.$i18n.t('registrationClinic.emailLess'),
                ],
                firstName: '',
                firstNameRules: [
                    v => !!v || this.$i18n.t('registrationDoctor.nameEmpty'),
                ],
                surname: '',
                surnameRules: [
                    v => !!v || this.$i18n.t('registrationDoctor.surnameEmpty'),
                ],
                position: '',
                positionRules: [
                    v => !!v || this.$i18n.t('registrationDoctor.positionEmpty'),
                ],
                showPassword: false
            }
        },
        methods: {
            saveDoctor() {
                this.$refs.form.validate();
                let doctor = {
                    username: this.username, password: this.password, email: this.email,
                    firstName: this.firstName, surname: this.surname, position: this.position
                };

                this.$resource('/registration-doctor').save({}, doctor).then(result =>
                    result.json().then(data => {
                        console.log(data);
                        this.username = "";
                        this.password = "";
                        this.email = "";
                        this.firstName = "";
                        this.surname = "";
                        this.position = "";
                        this.$router.push('/authenticate');
                    }, result => {
                        console.log(result);
                    })
                )
            },
            getValidationState({ dirty, validated, valid = null }) {
                return dirty || validated ? valid : null;
            },
            onSubmit() {

            }
        }
    }
</script>

<style>

</style>
