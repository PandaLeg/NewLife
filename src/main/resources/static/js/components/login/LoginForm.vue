<template>
    <div class="container mt-5">
        <form method="post" action="/login">
            <h1> Please Sign in</h1>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input type="text" id="logUsername" class="form-control" name="username" placeholder="Username"/>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input type="password" id="logPassword" class="form-control" name="password" placeholder="Password"/>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input type="submit" id="logIn" class="btn btn-primary col-sm-12" value="Sign in"/>
                </div>
            </div>
            <div class="col-sm-11">
                <router-link :to="{name: 'registrationClinic', params: {name_reg: 'clinic'}}"> Хотите
                    зарегистрироваться, как клиника?
                </router-link>
            </div>
            <div class="col-sm-11">
                <router-link :to="{name: 'registrationDoctor', params: {name_reg: 'doctor'}}"> Хотите
                    зарегистрироваться, как доктор?
                </router-link>
            </div>
            <div class="col-sm-11">
                <router-link :to="{name: 'registrationPatient', params: {name_reg: 'patient'}}"> Хотите
                    зарегистрироваться, как пациент?
                </router-link>
            </div>

        </form>
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