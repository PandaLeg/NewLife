<template>
    <div class="container mt-5">
        <h1> Добавление ребёнка </h1>
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="text" class="form-control" v-model="nameChild" name="nameChild" placeholder="Name child"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="text" class="form-control" v-model="state" name="state"
                       placeholder="Введите состояние(Радуется, Плачет)"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="button" class="btn btn-info col-sm-12" value="Add" @click="saveChild"/>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                id: '',
                nameChild: '',
                state: ''
            }
        },
        methods:{
            saveChild(){
                let child = {nameChild: this.nameChild, state: this.state};
                this.$resource('/child-add').save({}, child).then(result =>
                    result.json().then(data => {
                        console.log(data);
                        this.nameChild = "";
                        this.state = ""
                    })
                )
            }
        }
    }
</script>

<style>

</style>
