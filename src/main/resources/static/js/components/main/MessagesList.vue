<template>
    <div v-if="profileClinic || profileDoctor || profilePatient">
        <message-row v-for="message in getAllMessages" :key="message.id" :message="message"> </message-row>
    </div>
</template>

<script>
    import { mapGetters } from 'vuex'
    import { mapState } from 'vuex'
    import MessageRow from './MessageRow.vue'
    export default {
        components:{
            MessageRow
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient']),
            ...mapGetters('mainModule', ['getAllMessages'])
        }
    }
</script>


<style>

</style>