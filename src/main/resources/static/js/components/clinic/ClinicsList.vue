<template>
    <div>
        <div v-if="error" class="error">
            {{ error }}
        </div>
        <clinic-row v-for="clinic in allClinics" :key="clinic.id" :clinic="clinic"></clinic-row>
    </div>
</template>

<script>
    import ClinicRow from './ClinicRow.vue'
    export default {
        data() {
            return{
                allClinics: null,
                error: null
            }
        },
        components:{
            ClinicRow
        },
        created() {
            this.fetchListClinics()
        },
        methods:{
            fetchListClinics(){
                this.$resource('/hospitals').get().then(result =>
                    result.json().then(data => {
                        this.allClinics = data;
                    })
                , response => {
                    this.error = response.error
                })
            }
        }
    }
</script>

<style>

</style>