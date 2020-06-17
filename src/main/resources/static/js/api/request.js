import Vue from 'vue'

const listRequests = Vue.resource('/list-requests');

const acceptRequestClinic = Vue.resource('/accept-request-clinic{/id}');
const cancelRequestClinic = Vue.resource('/cancel-request-clinic{/id}');

const acceptRequestDoctor = Vue.resource('/accept-request-doctor{/id}');
const cancelRequestDoctor = Vue.resource('/cancel-request-doctor{/id}');

export default {
    get: id => listRequests.get(),
    acceptRequestFromClinic: id => acceptRequestClinic.delete({id}),
    cancelRequestFromClinic: id => cancelRequestClinic.delete({id}),
    acceptRequestFromDoctor: id => acceptRequestDoctor.delete({id}),
    cancelRequestFromDoctor: id => cancelRequestDoctor.delete({id})
}