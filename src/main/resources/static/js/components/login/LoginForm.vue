<template>
    <div class="container mt-5">
        <form name="form" @submit.prevent="handleLogin">
            <h1> {{ $t('login.pleaseSignIn') }} </h1>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input type="text" id="logUsername" class="form-control" v-model="username" name="username"
                           :placeholder="$t('login.username')"/>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input type="password" id="logPassword" class="form-control" v-model="password" name="password"
                           :placeholder="$t('login.password')"/>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input type="submit" id="logIn" :disabled="loading" class="btn btn-primary col-sm-12"
                           :value="$t('login.signIn')"/>
                </div>
            </div>
        </form>
        <div class="col-sm-11">
            <h6>{{ $t('login.account') }}</h6>
        </div>
        <div class="col-sm-11">
            <router-link :to="{name: 'registrationClinic', params: {name_reg: 'clinic'}}">
                {{ $t('login.clinic') }}
            </router-link>
        </div>
        <div class="col-sm-11">
            <router-link :to="{name: 'registrationDoctor', params: {name_reg: 'doctor'}}">
                {{ $t('login.doctor') }}
            </router-link>
        </div>
        <div class="col-sm-11">
            <router-link :to="{name: 'registrationPatient', params: {name_reg: 'patient'}}">
                {{ $t('login.patient') }}
            </router-link>
        </div>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                username: '',
                password: '',
                loading: false
            };
        },
        computed: {
            loggedIn() {
                return this.$store.state.auth.status.loggedIn;
            }
        },
        created() {
            if (this.loggedIn) {
                this.$router.push('/');
            }
        },
        methods:{
            handleLogin() {
                this.loading = true;
                let user = {username: this.username, password: this.password};
                if (this.username && this.password) {
                    this.$store.dispatch('auth/login', user).then(
                        () => {
                            this.$router.push('/')
                        },
                        error => {
                            console.log(error);
                            this.loading = false;
                        }
                    )
                }
            }
        }
    }
</script>

<style>
    #logIn{
        border-radius: 14px;
    }
    #logUsername {
        border-radius: 14px;
    }
    #logPassword {
        border-radius: 14px;
    }
    span {
        display: block;
    }
</style>