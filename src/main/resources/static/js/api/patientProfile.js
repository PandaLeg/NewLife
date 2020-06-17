import Vue from 'vue'

const patientProfile = Vue.resource('/patient{/id}');
const fetchChildren = Vue.resource('/children-patient{/id}');
const checkBindingDoctor = Vue.resource('/check-binding-patient{/id}');
const checkBindingClinic = Vue.resource('/check-binding-clinic-to-patient{/id}');
const sendMessage = Vue.resource('/send-message{/id}');

export default {
    idProfilePatient: idProfilePatient => idProfilePatient,
    fetchPatientProfile: id => patientProfile.get({id}),
    fetchChildren: id => fetchChildren.get({id}),
    checkBindingDoctor: id => checkBindingDoctor.get({id}),
    checkBindingClinic: id => checkBindingClinic.get({id}),
    sendMessage: message => sendMessage.save({id: message.idProfilePatient}, message)
}