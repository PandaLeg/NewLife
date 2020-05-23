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
            <div class="form-group row ml-sm-2">
                <a href="/registration-clinic">Add new Clinic</a>
                <label style="margin-left: 10px; margin-right: 10px;"> | </label>
                <a href="/registration-doctor">Add new Doctor</a>
                <label style="margin-left: 10px; margin-right: 10px;"> | </label>
                <a href="/registration-patient">Add new Patient</a>
            </div>
        </form>
    </div>

</template>

<script>

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
</style>