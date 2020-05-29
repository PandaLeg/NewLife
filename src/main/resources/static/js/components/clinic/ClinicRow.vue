<template>
    <div>
        <button @click="showProfileClinic">{{ clinic.nameClinic }}</button>
    </div>
</template>

<script>
    export default {
        props: ['clinic'],
        created() {
            console.log(this.clinic.id);
        },
        methods: {
            showProfileClinic(){
                this.$router.push({name: 'clinicProfile', params: { idProfileClinic: this.clinic.id } })
            }
        }
    }
</script>

<style>

</style>
