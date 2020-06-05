<template>
    <div class="container mt-5">
        <h1> {{ $t('formAddChild.addChild') }} </h1>
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="text" class="form-control" v-model="nameChild" name="nameChild"
                       :placeholder="$t('formAddChild.nameChild') "/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="text" class="form-control" v-model="age" name="age"
                       :placeholder="$t('formAddChild.age')"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-4">
                <input type="button" class="btn btn-info col-sm-12" :value="$t('formAddChild.add')" @click="saveChild"/>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                id: '',
                nameChild: '',
                age: ''
            }
        },
        methods: {
            saveChild()
            {
                let child = {nameChild: this.nameChild, age: this.age};
                this.$resource('/child-add').save({}, child).then(result =>
                    result.json().then(data => {
                        console.log(data);
                        this.nameChild = "";
                        this.age = "";
                        this.state = ""
                    })
                )
            }
        }
    }
</script>

<style>

</style>
