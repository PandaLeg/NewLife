<template>
    <div>
        <a href="/">Главная</a>
        <request-row v-for="request in requests"
                     :key="request.id"
                     :request="request"
                     :profileClinic="profileClinic"
                     :profileDoctor="profileDoctor"
                     :requests="requests">
        </request-row>
    </div>
</template>

<script>
    import RequestRow from './RequestRow.vue'

    export default {
        components:{
            RequestRow
        },
        props: ['requests', 'profileClinic', 'profileDoctor']
    }
</script>

<style>

</style>