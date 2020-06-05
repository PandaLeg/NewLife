<template>
    <div>
        <div class="container mt-5">
            <h1> {{ $t('listPatients.listPatients') }} </h1>
            <label class="col-form-label"> {{ patient.firstName }} </label>
            <label class="col-form-label"> {{ patient.surname }} </label>
            <div>
                <router-link :to="{name: 'patientProfile', params: {idProfilePatient: patient.id} }">
                    {{ patient.username }}
                </router-link>
            </div>
            <div v-if="profileDoctor">
                <label> Дети: </label>
                <label v-for="child in children" :key="child.id">
                    <router-link :to="{name: 'childProfile', params: {idProfileChild: child.id}}">
                        {{ child.nameChild }}
                    </router-link>
                </label>
            </div>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        props: ['patient'],
        data(){
            return{
                children: []
            }
        },
        created(){
            if(this.profileDoctor != null) {
                this.showProfileChild();
            }
        },
        computed: {
            ...mapState('mainModule', ['profileDoctor'])
        },
        methods:{
            showProfileChild(){
                this.$resource('/children-patient/{id}').get({id: this.patient.id}).then(result =>{
                    this.children = result.data;
                    console.log(result)
                })
            }
        }
    }
</script>

<style>

</style>