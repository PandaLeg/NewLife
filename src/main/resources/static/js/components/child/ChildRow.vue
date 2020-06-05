<template>
    <div>
        <router-link :to="{name: 'childProfile', params: {idProfileChild: child.id} }">
            {{child.nameChild}}
        </router-link>
        <button class="btn btn-info" @click="addIndicatorsChild">{{ $t('state.addIndicators') }}</button>
    </div>
</template>

<script>
    export default {
        props: ['child'],
        methods:{
            addIndicatorsChild(){
                this.$router.push({name: 'addIndicators', params: { childId: this.child.id } })
            }
        }
    }
</script>

<style>

</style>