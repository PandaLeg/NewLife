<template>
    <div v-if="profileClinic || profileDoctor || profilePatient">

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
        }
    }
</script>


<style>

</style>