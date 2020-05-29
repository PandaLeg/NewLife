<template>
    <div class="container mt-5">
        <h1> Добавление показателей</h1>
        <div class="form-group row">
            <div class="col-sm-8">
                <input type="text" class="form-control" v-model="temperature" name="temperature"
                       placeholder="Температура"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-8">
                <input type="text" class="form-control" v-model="pulse" name="pulse"
                       placeholder="Пульс"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-8">
                <input type="text" class="form-control" v-model="pressure" name="pressure"
                       placeholder="Давление"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-8">
                <input type="button" class="btn btn-info col-sm-12" value="Добавить" @click="addIndicatorsToChild"/>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['childId'],
        data(){
            return{
                temperature: '',
                pulse: '',
                pressure: '',
                id: ''
            }
        },
        created(){
            this.id = this.childId;
        },
        methods:{
            addIndicatorsToChild(){
                let child = {temperature: this.temperature, pulse: this.pulse, pressure: this.pressure};
                this.$resource('/update-child-indicators/{id}').update({id: this.id}, child).then(result =>
                    result.json().then(data => {
                        this.temperature = "";
                        this.pulse = "";
                        this.pressure = "";
                        console.log(result);
                    })
                )
            }
        }
    }
</script>

<style>

</style>