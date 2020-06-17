<template>
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
                            :label="$t('registrationClinic.username')"
                            clearable
                            required
                    ></v-text-field>
                    <v-text-field
                            v-model="password"
                            :counter="6"
                            :rules="passwordRules"
                            :label="$t('registrationClinic.password')"
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
                            v-model="nameClinic"
                            :rules="nameClinicRules"
                            :label="$t('registrationClinic.nameClinic')"
                            clearable
                            required
                    >
                    </v-text-field>
                    <v-text-field
                            v-model="address"
                            :rules="addressRules"
                            :label="$t('registrationClinic.address')"
                            clearable
                            required
                    >
                    </v-text-field>
                    <v-text-field
                            v-model="city"
                            :rules="cityRules"
                            :label="$t('registrationClinic.city')"
                            clearable
                            required
                    >
                    </v-text-field>
                    <v-btn class="mr-4" @click="saveClinic" outlined>{{ $t('registrationClinic.create')}}</v-btn>
                </v-col>
            </v-row>
        </v-form>
    </v-container>
</template>

<script>
    export default {
        data() {
            return {
                id: '',
                username: '',
                usernameRules: [
                    v => !!v || this.$i18n.t('registrationClinic.usernameEmpty'),
                    v => (v && v.length >= 3) || this.$i18n.t('registrationClinic.usernameLess'),
                ],
                password: '',
                passwordRules: [
                  v => !!v || this.$i18n.t('registrationClinic.passwordEmpty'),
                  v => (v && v.length >= 6) || this.$i18n.t('registrationClinic.passwordLess'),
                ],
                email: '',
                emailRules: [
                    v => !!v || this.$i18n.t('registrationClinic.emailEmpty'),
                    v => /.+@.+\..+/.test(v) || this.$i18n.t('registrationClinic.emailLess'),
                ],
                nameClinic: '',
                nameClinicRules: [
                    v => !!v || this.$i18n.t('registrationClinic.nameClinicEmpty'),
                ],
                address: '',
                addressRules: [
                    v => !!v || this.$i18n.t('registrationClinic.addressEmpty'),
                ],
                city: '',
                cityRules: [
                    v => !!v || this.$i18n.t('registrationClinic.cityEmpty'),
                    v => (v && v.length >= 6) || this.$i18n.t('registrationClinic.passwordLess'),
                ],
                showPassword: false,
            }
        },
        methods: {
            saveClinic() {
                this.$refs.form.validate();

                let clinic = {
                    username: this.username, password: this.password, email: this.email,
                    nameClinic: this.nameClinic, address: this.address, city: this.city,
                };

                this.$resource('/registration-clinic').save({}, clinic).then(result =>
                    result.json().then(data => {
                        console.log(data);
                        this.username = "";
                        this.password = "";
                        this.email = "";
                        this.nameClinic = "";
                        this.address = "";
                        this.city = "";
                        this.$router.push('/login');
                    }, result => {
                        console.log(result);
                    })
                )
            }
        }
    }
</script>

<style scoped>
    /deep/ .v-text-field {
        width: 400px;
    }
</style>