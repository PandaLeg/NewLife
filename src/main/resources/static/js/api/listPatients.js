import Vue from 'vue'

const listPatients = Vue.resource('/patients-set');

export default {
    get: id => listPatients.get()
}