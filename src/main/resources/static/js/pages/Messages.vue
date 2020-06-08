<template>
    <div>
        <div class="container mt-5">
            <h1> {{ $t('messagesList.listMessages') }} </h1>
            <label v-for="message in messages" :key="message.id">
                <label class="col-form-label"> {{ message.title }} </label>
                <p class="col-form-label"> {{ message.description }} </p>
                <div class="form-group row">
                    <div class="col-sm-4">
                        <input type="button" class="btn btn-info" :value="$t('messagesList.btnDelete') "
                               @click="deleteMessage(message)"/>
                    </div>
                </div>
            </label>
        </div>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    export default {
        data(){
            return{
                messages: []
            }
        },
        created(){
            this.findAllMessages();
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient']),
        },
        methods:{
            findAllMessages(){
                this.$resource('/patient-messages-list').get().then(result => {
                    this.messages = result.data;
                    console.log(result);
                })
            },
            deleteMessage(message){
                this.$resource('/patient-delete-message/{id}').delete({id: message.id}).then(result => {
                    this.messages.splice(this.messages.indexOf(this.message), 1);
                    console.log(result)
                })
            }
        }
    }
</script>

<style scoped>
    .btn-info{
        max-width:250px;
    }
</style>