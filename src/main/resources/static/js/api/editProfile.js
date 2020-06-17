import Vue from 'vue'

const updateAvatar = Vue.resource('/update-profile-avatar');
const updateProfileClinic = Vue.resource('/update-clinic-profile');
const updateProfileDoctor = Vue.resource('/update-doctor-profile');
const updateProfilePatient = Vue.resource('/update-patient-profile');


export default {
    updateAvatar: form => updateAvatar.save({}, form),
    updateProfileClinic: updateClinic => updateProfileClinic.update({}, updateClinic),
    updateProfileDoctor: updateDoctor => updateProfileDoctor.update({}, updateDoctor),
    updateProfilePatient: updatePatient => updateProfilePatient.update({}, updatePatient),
}