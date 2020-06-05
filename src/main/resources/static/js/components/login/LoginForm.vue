<template>
    <div class="container mt-5">
        <form method="post" action="/login">
            <h1> {{ $t('login.pleaseSignIn') }} </h1>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input type="text" id="logUsername" class="form-control" name="username"
                           :placeholder="$t('login.username')"/>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input type="password" id="logPassword" class="form-control" name="password"
                           :placeholder="$t('login.password')"/>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input type="submit" id="logIn" class="btn btn-primary col-sm-12"
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