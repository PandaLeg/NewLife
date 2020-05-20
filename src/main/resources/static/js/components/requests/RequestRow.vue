<template>
    <div>
        <label> {{ request.message }}</label>
        <div v-if="profileClinic">
            <span>
                <input type="button" value="Save" @click="ClinicToDoctor">
            </span>
        </div>
        <div v-else-if="profileDoctor">
            <span>
            </span>
        </div>
        <br>
    </div>
</template>

<script>
    export default {
        props: ['request', 'profileClinic', 'profileDoctor', 'requests'],
        data(){
            return{
                id: null
            }
        },
        created(){
            this.id = this.request.id;
        },
        methods:{
            ClinicToDoctor() {
                this.$resource('/request-accept{/id}').delete({id: this.id}).then(result => {
                        if(result.ok){
                            console.log(result);
                            this.requests.splice(this.requests.indexOf(this.request), 1);
                        }
                    }
                )
            }
        }
    }
</script>

<style>

</style>