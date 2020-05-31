<template>
    <div>
        <div class="container mt-5">
            <div v-if="currentChild" class="content">
                <h1> Показатели </h1>
                <h1> {{ currentChild.nameChild }} </h1>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label"> Температура: </label>
                    <div class="col-sm-6" v-if="temperature != ''">
                        <label> {{ currentChild.temperature }}</label>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label"> Пульс: </label>
                    <div class="col-sm-6" v-if="pulse != ''">
                        <label> {{ currentChild.pulse }}</label>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label"> Давление: </label>
                    <div class="col-sm-6" v-if="pressure != ''">
                        <label> {{ currentChild.pressure }}</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['childId'],
        data(){
            return{
                currentChild: null,
                temperature: '',
                pressure: '',
                pulse: ''
            }
        },
        created(){
            this.findChildOfPatient();
        },
        methods:{
            findChildOfPatient(){
                this.$resource('/child/{id}').get({id: this.childId}).then(result =>
                    result.json().then(data =>{
                        this.currentChild = data;
                        this.temperature = this.currentChild.temperature;
                        this.pressure = this.currentChild.pressure;
                        this.pulse = this.currentChild.pulse;
                    })
                )
            }
        }
    }
</script>

<style>

</style>