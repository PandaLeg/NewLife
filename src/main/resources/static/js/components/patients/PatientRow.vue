<template>
    <div>
        <div class="container mt-5">
            <h1> Список пациентов </h1>
            <label class="col-form-label"> {{ patient.firstName }} </label>
            <label class="col-form-label"> {{ patient.surname }} </label>
            <!--<label v-for="child in childrenFromDoctor" :key="child.id">
                <router-link :to="{name: 'childProfile', params: {idProfileChild: child.id}}">
                    Child
                </router-link>
            </label>-->
        </div>
    </div>
</template>

<script>
    export default {
        props: ['patient'],
        data(){
            return{
                id: '',
                childrenFromDoctor: []
            }
        },
        created(){
            /*this.id = this.patient.id;
            this.showProfileChild();*/
        },
        methods:{
            /*showProfileChild(){
                this.$resource('/child-patient-list/{id}').get({id: this.id}).then(result =>{
                    this.childrenFromDoctor = result.data;
                    console.log(result)
                })
            }*/
        }
    }
</script>

<style>

</style>