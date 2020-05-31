<template>
    <div>
        <div v-if="currentProfileChild" class="content">
            <h4>{{ currentProfileChild.nameChild }}</h4>
            <h4>{{ currentProfileChild.state }}</h4>
            <br>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['idProfileChild'],
        data(){
            return{
                currentProfileChild: null,
            }
        },
        created(){
            this.fetchChildData();
        },
        methods:{
            fetchChildData(){
                this.$resource('/child/{id}').get({id: this.idProfileChild}).then(result =>
                    result.json().then(data => {
                        this.currentProfileChild = data;
                        console.log(result);
                    })
                )
            }
        }
    }
</script>

<style>

</style>