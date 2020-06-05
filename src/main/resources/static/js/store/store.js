import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        mainModule: {
            namespaced: true,
            state: () => ({
                profileClinic: allData.profileClinic,
                profileDoctor: allData.profileDoctor,
                profilePatient: allData.profilePatient,
                defaultPicture: '/img/DefaultAvatar.png'
            }),
            getters: {
            }
        }
    }
})
