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
    import childApi from 'api/child'

    export default {
        data() {
            return {
                id: '',
                nameChild: '',
                age: ''
            }
        },
        methods: {
            async saveChild() {
                let child = {nameChild: this.nameChild, age: this.age};
                const result = await childApi.addChild(child);
                this.nameChild = "";
                this.age = "";
                console.log(result);
            }
        }
    }
</script>

<style>

</style>
