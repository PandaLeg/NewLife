<template>
    <div>
        <div class="container mt-5">
            <h1> {{ $t('messagesList.listMessages') }} </h1>
            <label v-for="message in listMessages" :key="message.id">
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
    import {mapState, mapGetters} from 'vuex'
    import { mapActions } from 'vuex'
    export default {
        created(){
            this.findAllMessages();
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient']),
            ...mapGetters('messages', ['listMessages'])
        },
        methods:{
            ...mapActions('messages', ['findAllMessagesAction', 'deleteMessageAction']),
            findAllMessages(){
                this.findAllMessagesAction();
            },
            deleteMessage(message){
                this.deleteMessageAction(message);
            }
        }
    }
</script>

<style scoped>
    .btn-info{
        max-width:250px;
    }
</style>