import Vue from 'vue'

const fetchChild = Vue.resource('/child{/id}');
const addChild = Vue.resource('/child-add');
const listChild = Vue.resource('/child-patient-list');
const connectToDevice = Vue.resource('/update-child-indicators{/id}');

export default {
    fetchChild: id => fetchChild.get({id}),
    addChild: child => addChild.save({}, child),
    listChild: id => listChild.get(),
    connectToDevice: child => connectToDevice.update({id: child.id}, child)
}