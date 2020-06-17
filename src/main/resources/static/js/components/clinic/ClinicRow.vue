<template>
    <v-card color="orange">
        <v-list two-line>
            <v-list-item @click="showProfileClinic">
                <v-list-item-avatar>
                    <img :src="defaultPicture">
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title>{{clinic.nameClinic}}</v-list-item-title>
                    <v-list-item-subtitle>{{clinic.city}}</v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
        </v-list>
    </v-card>
</template>

<script>
    import {mapState} from 'vuex'
    export default {
        props: ['clinic'],
        created() {
            console.log(this.clinic.id);
        },
        computed:{
            ...mapState('mainModule', ['defaultPicture']),
        },
        methods: {
            showProfileClinic() {
                this.$router.push({name: 'clinicProfile', params: {idProfileClinic: this.clinic.id}})
            }
        }
    }
</script>

<style>

</style>
