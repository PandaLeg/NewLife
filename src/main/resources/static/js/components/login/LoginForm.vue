<template>
    <form method="post" action="/login">
        <div class="form-group row mt-sm-5">
            <!-- col-sm-2 - ширина 2 колонки -->
            <label class="col-sm-2 pl-sm-5 col-form-label">User Name:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="username" placeholder="Username"/>
            </div>
        </div>
        <div class="form-group row">
            <!-- col-sm-2 - ширина 2 колонки -->
            <label class="col-sm-2 pl-sm-5 col-form-label">Password:</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" name="password" placeholder="Password"/>
            </div>
        </div>
        <input type="submit" class="btn btn-primary" value="Sign in"/>
        <div class="form-group row ml-sm-2">
            <a href="/registration-clinic">Add new Clinic</a>
            <label style="margin-left: 10px; margin-right: 10px;"> | </label>
            <a href="/registration-doctor">Add new Doctor</a>
            <label style="margin-left: 10px; margin-right: 10px;"> | </label>
            <a href="/registration-patient">Add new Patient</a>
        </div>
    </form>
</template>

<script>

</script>

<style>

</style>