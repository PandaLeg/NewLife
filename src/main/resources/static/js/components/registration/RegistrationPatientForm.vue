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
                                :counter="3"
                                :rules="usernameRules"
                                :label="$t('registrationPatient.username')"
                                clearable
                                required
                        ></v-text-field>
                        <v-text-field
                                v-model="password"
                                :counter="6"
                                :rules="passwordRules"
                                :label="$t('registrationPatient.password')"
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
                                :label="$t('registrationPatient.name')"
                                clearable
                                required
                        >
                        </v-text-field>
                        <v-text-field
                                v-model="surname"
                                :rules="surnameRules"
                                :label="$t('registrationPatient.surname')"
                                clearable
                                required
                        >
                        </v-text-field>
                        <v-btn class="mr-4" @click="savePatient" outlined>Create</v-btn>
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
                    v => !!v || this.$i18n.t('registrationPatient.usernameEmpty'),
                    v => (v && v.length >= 3) || this.$i18n.t('registrationPatient.usernameLess')
                ],
                password: '',
                passwordRules: [
                    v => !!v || this.$i18n.t('registrationPatient.passwordEmpty'),
                    v => (v && v.length >= 6) || this.$i18n.t('registrationPatient.passwordLess'),
                ],
                email: '',
                emailRules: [
                    v => !!v || this.$i18n.t('registrationPatient.emailEmpty'),
                    v => /.+@.+\..+/.test(v) || this.$i18n.t('registrationClinic.emailLess'),
                ],
                firstName: '',
                firstNameRules: [
                    v => !!v || this.$i18n.t('registrationPatient.nameEmpty'),
                ],
                surname: '',
                surnameRules: [
                    v => !!v || this.$i18n.t('registrationPatient.surnameEmpty'),
                ],
                showPassword: false
            }
        },
        methods: {
            savePatient() {
                this.$refs.form.validate();
                let patient = {username: this.username, password: this.password, email: this.email,
                    firstName: this.firstName, surname: this.surname};

                this.$resource('/registration-patient').save({}, patient).then(result =>
                    result.json().then(data => {
                        console.log(data);
                        this.username = "";
                        this.password = "";
                        this.email = "";
                        this.firstName = "";
                        this.surname = "";
                        this.$router.push('/login');
                    }, result => {
                        console.log(result);
                    })
                )
            }
        }
    }
</script>

<style>
    body {

    }
    #container{
        margin-left: 550px;
    }
</style>