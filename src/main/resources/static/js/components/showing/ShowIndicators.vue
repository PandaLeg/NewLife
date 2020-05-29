<template>
    <div>
        <div class="container mt-5">
            <h1> Показатели </h1>
            <div class = "form-group row">
                <label class="col-sm-2 col-form-label"> Температура: </label>
                <div class="col-sm-6">
                    <label> {{ currentChild.temperature }}</label>
                </div>
            </div>
            <div class = "form-group row">
                <label class="col-sm-2 col-form-label"> Пульс: </label>
                <div class="col-sm-6">
                    <label> {{ currentChild.pulse }}</label>
                </div>
            </div>
            <div class = "form-group row">
                <label class="col-sm-2 col-form-label"> Давление: </label>
                <div class="col-sm-6">
                    <label> {{ currentChild.pressure }}</label>
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
                id: ''
            }
        },
        created(){
            this.id = this.childId;
            this.findChild();
        },
        methods:{
            findChild(){
                this.$resource('/child/{id}').get({id: this.id}).then(result =>
                    result.json().then(data =>{
                        this.currentChild = data;
                        console.log(data);
                    })
                )
            }
        }
    }
</script>

<style>

</style>