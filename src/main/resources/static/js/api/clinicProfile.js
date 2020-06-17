import Vue from 'vue'

const requests = Vue.resource('/send-request-clinic{/id}');
const requestsDoctors = Vue.resource('/doctors-list-clinic{/id}');
const requestsPatients = Vue.resource('/patients-list-clinic{/id}');
const clinicProfile = Vue.resource('/clinic{/id}');
const checkBinding = Vue.resource('/check-binding-clinic{/id}');
const cancelBinding = Vue.resource('/cancel-binding-clinic{/id}');

export default {
    getDoctors: id => requestsDoctors.get({id}),
    getPatients: id => requestsPatients.get({id}),
    send: request => requests.save({id: request.idClinic}, request),
    fetchClinicProfile: id => clinicProfile.get({id}),
    checkBinding: id => checkBinding.get({id}),
    cancelBinding: id => cancelBinding.delete({id})
}