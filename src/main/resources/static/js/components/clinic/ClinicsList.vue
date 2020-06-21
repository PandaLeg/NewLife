<template>
    <v-layout>
        <v-row>
            <v-col cols="12" sm="12" offset-sm="12">
                <v-row>
                    <v-col cols="12" sm="12" offset-sm="12">
                        <clinic-row v-for="clinic in listClinics" :key="clinic.id" :clinic="clinic"></clinic-row>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
    </v-layout>
</template>

<script>
    import ClinicRow from './ClinicRow.vue'

    export default {
        data() {
            return {
                listClinics: null,
                error: null
            }
        },
        components: {
            ClinicRow
        },
        created() {
            this.fetchListClinics();
        },
        methods: {
            fetchListClinics() {
                this.$resource('/hospitals').get().then(result => {
                        this.listClinics = result.data;
                    }
                )
            }
        }
    }
</script>

<style>

</style>