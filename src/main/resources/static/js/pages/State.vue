<template>
    <div>
        <child-list :children="children"></child-list>
    </div>
</template>

<script>
    import ChildList from 'components/child/ChildList.vue'

    export default {
        components:{
            ChildList
        },
        data() {
            return {
                children: []
            }
        },
        created() {
            this.getAllChildren();
        },
        methods: {
            getAllChildren() {
                this.$resource('/child-patient-list').get().then(result =>
                    result.json().then(data => {
                        this.children = data;
                        console.log(result);
                    })
                )
            }
        }
    }
</script>

<style>

</style>