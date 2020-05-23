<template>
    <div>
        <child-row v-for="child in children" :key="child.id" :child="child"></child-row>
    </div>
</template>

<script>
    import ChildRow from './ChildRow.vue'

    export default {
        components:{
            ChildRow
        },
        props: ['children']
    }
</script>

<style>

</style>