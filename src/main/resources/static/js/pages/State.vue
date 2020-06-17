<template>
    <div>
        <child-list :children="children"></child-list>
    </div>
</template>

<script>
    import ChildList from 'components/child/ChildList.vue'
    import childApi from 'api/child'

    export default {
        components: {
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
            async getAllChildren() {
                const result = await childApi.listChild();
                const data = await result.json();
                if (result.ok) {
                    this.children = data;
                }
                console.log(result);
            }
        }
    }
</script>

<style>

</style>