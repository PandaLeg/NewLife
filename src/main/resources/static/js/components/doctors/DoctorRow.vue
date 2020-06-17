<template>
    <div>
        <label class="col-form-label"> {{ doctor.firstName }} </label>
        <label class="col-form-label"> {{ doctor.surname }} </label>
        <div>
            <router-link :to="{name: 'doctorProfile', params: {idProfileDoctor: doctor.id} }">
                {{ doctor.username }}
            </router-link>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['doctor']
    }
</script>

<style>

</style>