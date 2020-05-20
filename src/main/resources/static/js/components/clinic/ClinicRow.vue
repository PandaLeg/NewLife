<template>
    <div>
    <a href="">{{clinic.nameClinic}}</a>
    </div>
</template>

<script>
    export default {
        props: ['clinic'],
        created() {
            console.log(this.clinic.id);
        }
    }
</script>

<style>

</style>
