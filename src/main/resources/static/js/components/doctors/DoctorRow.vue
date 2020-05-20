<template>
    <div>
        <h5> {{doctor.username}} </h5>
        <label> {{doctor.fio}} </label>
        <br>
    </div>
</template>

<script>
    export default {
        props: ['doctor']
    }
</script>

<style>

</style>