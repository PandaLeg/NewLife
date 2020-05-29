<template>
    <div>
        <h5> {{child.nameChild}} </h5>
        <label> {{child.state}} </label>
        <button class="btn btn-info" @click="addIndicators">Добавить показатели</button>
        <button class="btn btn-info" @click="showIndicators">Просмотреть показатели</button>
    </div>
</template>

<script>
    export default {
        props: ['child'],
        methods:{
            addIndicators(){
                this.$router.push({name: 'addIndicators', params: { childId: this.child.id } })
            },
            showIndicators(){
                this.$router.push({name: 'showIndicators', params: { childId: this.child.id } })
            }
        }
    }
</script>

<style>

</style>