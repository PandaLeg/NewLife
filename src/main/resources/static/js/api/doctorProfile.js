import Vue from 'vue'

const requests = Vue.resource('/send-request-doctor{/id}');
const doctorProfile = Vue.resource('/doctor{/id}');
const affiliationToClinic = Vue.resource('/affiliation-to-clinic{/id}');
const checkBinding = Vue.resource('/check-binding-doctor{/id}');
const cancelBinding = Vue.resource('/cancel-binding-from-doctor{/id}');
const listChildrenPatients = Vue.resource('/children-patient{/id}');

export default {
    send: request => requests.save({id: request.idDoctor}, request),
    fetchDoctorProfile: id => doctorProfile.get({id}),
    affiliationToClinic: id => affiliationToClinic.get({id}),
    checkBinding: id => checkBinding.get({id}),
    cancelBinding: id => cancelBinding.delete({id}),
    fetchChildren: id => listChildrenPatients.get({id})
}