<template>
    <div>
        <h5> {{child.nameChild}} </h5>
        <label> {{child.state}} </label>
        <button class="btn btn-info" @click="addIndicatorsChild">Добавить показатели</button>
        <button class="btn btn-info" @click="showIndicatorsChild">Просмотреть показатели</button>
    </div>
</template>

<script>
    export default {
        props: ['child'],
        methods:{
            addIndicatorsChild(){
                this.$router.push({name: 'addIndicators', params: { childId: this.child.id } })
            },
            showIndicatorsChild(){
                this.$router.push({name: 'showIndicators', params: { childId: this.child.id } })
            }

        }
    }
</script>

<style>

</style>