<template>
    <div id="container" class="container mt-4">
        <validation-observer ref="observer" v-slot="{ handleSubmit }">
            <b-form @submit.stop.prevent="handleSubmit(onSubmit)">
                <h1 id="h-header">Registration patient!</h1>

                <validation-provider
                        name="username"
                        :rules="{ required: true, min: 3 }"
                        v-slot="validationContext"
                >
                    <b-form-group id="input-group-1">
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <b-form-input
                                        type="text"
                                        id="username-input-1"
                                        name="username-input-1"
                                        placeholder="Username"
                                        class="form-control"
                                        v-model="username"
                                        :state="getValidationState(validationContext)"
                                        aria-describedby="input-1-live-feedback"
                                        required
                                ></b-form-input>

                                <b-form-invalid-feedback id="input-1-live-feedback"
                                                         v-if="username === ''">
                                    Имя пользователя не может быть пустым!
                                </b-form-invalid-feedback>
                                <b-form-invalid-feedback id="input-1-live-feedback" v-else>
                                    Имя пользователя должно быть не меньше трёх символов!
                                </b-form-invalid-feedback>
                            </div>
                        </div>
                    </b-form-group>
                </validation-provider>

                <validation-provider
                        name="password"
                        :rules="{ required: true, min: 1 }"
                        v-slot="validationContext"
                >
                    <b-form-group id="input-group-2">
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <b-form-input
                                        type="password"
                                        id="password-input-2"
                                        name="password-input-2"
                                        placeholder="Password"
                                        class="form-control"
                                        v-model="password"
                                        :state="getValidationState(validationContext)"
                                        aria-describedby="input-2-live-feedback"
                                        required
                                ></b-form-input>

                                <b-form-invalid-feedback id="input-2-live-feedback" v-if="password == ''">
                                    Пароль не может быть пустым!
                                </b-form-invalid-feedback>
                                <!--<b-form-invalid-feedback id="input-2-live-feedback" v-else>
                                    Пароль должно быть не меньше трёх символов
                                </b-form-invalid-feedback>-->
                            </div>
                        </div>
                    </b-form-group>
                </validation-provider>

                <validation-provider
                        name="email"
                        :rules="{ required: true, min: 6}"
                        v-slot="validationContext"
                >
                    <b-form-group id="input-group-3">
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <b-form-input
                                        type="email"
                                        id="email-input-3"
                                        name="email-input-3"
                                        placeholder="root@gmail.com"
                                        class="form-control"
                                        v-model="email"
                                        :state="getValidationState(validationContext)"
                                        aria-describedby="input-3-live-feedback"
                                        required
                                ></b-form-input>

                                <b-form-invalid-feedback id="input-3-live-feedback" v-if="email == ''">
                                    Email не может быть пустым!
                                </b-form-invalid-feedback>
                                <b-form-invalid-feedback id="input-3-live-feedback" v-else>
                                    Email не может быть меньше 6 символов(включая @)
                                </b-form-invalid-feedback>
                            </div>
                        </div>
                    </b-form-group>
                </validation-provider>

                <validation-provider
                        name="firstName"
                        :rules="{ required: true, min: 2}"
                        v-slot="validationContext"
                >
                    <b-form-group id="input-group-4">
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <b-form-input
                                        type="text"
                                        id="name-input-4"
                                        name="name-input-4"
                                        placeholder="Name"
                                        class="form-control"
                                        v-model="firstName"
                                        :state="getValidationState(validationContext)"
                                        aria-describedby="input-4-live-feedback"
                                        required
                                ></b-form-input>

                                <b-form-invalid-feedback id="input-4-live-feedback" v-if="firstName == ''">Пожалуйста,
                                    введите Имя
                                </b-form-invalid-feedback>
                            </div>
                        </div>
                    </b-form-group>
                </validation-provider>

                <validation-provider
                        name="surname"
                        :rules="{ required: true, min: 3}"
                        v-slot="validationContext"
                >
                    <b-form-group id="input-group-5">
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <b-form-input
                                        type="text"
                                        id="surname-input-5"
                                        name="surname-input-5"
                                        placeholder="Surname"
                                        class="form-control"
                                        v-model="surname"
                                        :state="getValidationState(validationContext)"
                                        aria-describedby="input-5-live-feedback"
                                        required
                                ></b-form-input>

                                <b-form-invalid-feedback id="input-5-live-feedback" v-if="firstName == 'surname'">Пожалуйста,
                                введите Фамилию
                                </b-form-invalid-feedback>
                            </div>
                        </div>
                    </b-form-group>
                </validation-provider>

                <div class="form-group row">
                    <div class="col-sm-4">
                        <input type="submit" class="btn btn-primary" value="Create" @click="savePatient">
                    </div>
                </div>
            </b-form>
        </validation-observer>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                id: '',
                username: '',
                password: '',
                email: '',
                firstName: '',
                surname: ''
            }
        },
        methods: {
            savePatient() {
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
    body {

    }
    #container{
        margin-left: 550px;
    }
</style>