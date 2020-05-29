<template>
    <div>
        <div v-if="error" class="error">
            {{ error }}
        </div>
        <div v-if="currentProfilePatient" class="content">
            <h4>{{ currentProfilePatient.username }}</h4>
            <br>
            <label>{{ currentProfilePatient.firstName }}</label>
            <br>
            <label>{{ currentProfilePatient.surname }}</label>
            <br>
            <!--Показывает, доктору или опекуну ссылку на профиль ребёнка-->
            <div v-if="profilePatient || checkBindingToPatient">
                <label v-for="child in children" :key="child.id">
                    <router-link :to="{name: 'childProfile', params: {idProfileChild: child.id}}"> My child </router-link>
                </label>
            </div>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        props: ['idProfilePatient'],
        data(){
            return{
                currentProfilePatient: null,
                error: null,
                children: [],
                checkBindingToPatient: false
            }
        },
        created() {
            if(this.profileDoctor != null){
                this.checkBindingPatient();
            }
            this.fetchPatientData();
            this.fetchChildrenData();
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient'])
        },
        methods:{
            fetchPatientData() {
                this.error = null;
                this.$resource('/patient/{id}').get({id: this.idProfilePatient}).then(result =>
                        result.json().then(data =>{
                            this.currentProfilePatient = data;
                        }), response => {
                        this.error = response.error;
                    }
                )
            },
            fetchChildrenData() {
                this.$resource('/children-patient/{id}').get({id: this.idProfilePatient}).then(result => {
                    this.children = result.data;
                    console.log(result);
                })
            },
            // Проверка принадлежности пациента к врачу
            checkBindingPatient() {
                this.$resource('/check-binding-patient/{id}').get({id: this.idProfilePatient}).then(result => {
                    this.checkBindingToPatient = result.data;
                    console.log(result);
                })
            }
        }
    }
</script>
<style>

</style>