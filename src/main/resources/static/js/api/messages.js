import Vue from 'vue'

const fetchMessages = Vue.resource('/patient-messages-list');
const deleteMessage = Vue.resource('/patient-delete-message{/id}');


export default {
    fetchMessages: id => fetchMessages.get(),
    deleteMessage: message => deleteMessage.delete({id: message.id})
}