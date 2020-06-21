<template>
    <v-app>
        <v-main>
            <div>
                <users-list></users-list>
            </div>
        </v-main>
    </v-app>
</template>

<script>
    import UsersList from 'components/users/UsersList.vue'
    import {mapState} from 'vuex'

    export default {
        components: {
            UsersList
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient'])
        },
        methods: {
            checkRoles(roles) {
                for (let i = 0; i < roles.length; i++) {
                    if (roles[i] === ('ADMIN')) {
                        return true;
                    }
                }
                return false;
            }
        },
        beforeMount() {
            if (this.profileClinic) {
                if (!this.checkRoles(this.profileClinic.roles)) {
                    this.$router.push('/')
                }
            }
            if (this.profileDoctor) {
                if (!this.checkRoles(this.profileDoctor.roles)) {
                    this.$router.push('/')
                }
            }
            if (this.profilePatient) {
                if (!this.checkRoles(this.profilePatient.roles)) {
                    this.$router.push('/')
                }
            }
        }
    }
</script>

<style scoped>

</style>