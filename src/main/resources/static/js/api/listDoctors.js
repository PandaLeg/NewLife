import Vue from 'vue'

const listDoctors = Vue.resource('/doctors-list-clinic');

export default {
    get: id => listDoctors.get()
}