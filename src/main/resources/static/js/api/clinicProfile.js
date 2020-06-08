import Vue from 'vue'

const requests = Vue.resource('/send-request-clinic{/id}');

export default {
    add: request => requests.save({id: request.idClinic}, request)
}