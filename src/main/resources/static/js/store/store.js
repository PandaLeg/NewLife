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
                messages: [
                    {id: 1, text: 'Первый тост за localhost'},
                    {id: 2, text: 'Wow!So nice'}
                ]
            }),
            getters: {
                getAllMessages: state => {
                    return state.messages
                }
            }
        }
    }
})
