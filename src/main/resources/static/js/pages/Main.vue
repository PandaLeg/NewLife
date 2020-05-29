<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">

        </nav-bar>
        <div v-if="!profileClinic && !profileDoctor && !profilePatient">

        </div>
        <div v-else>
            <!--<messages-list></messages-list>-->
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    import MessagesList from 'components/main/MessagesList.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            MessagesList,
            NavBar
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient'])
        }
    }
</script>

<style>

</style>