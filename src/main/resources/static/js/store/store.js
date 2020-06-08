import Vue from 'vue'
import Vuex from 'vuex'
import requestsApi from 'api/clinicProfile'

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
        },
        clinicProfile: {
            namespaced: true,
            actions:{
                async saveClinicRequestAction({commit}, request){
                    const result = await requestsApi.add(request);
                    const data = await result.json();
                    console.log(data);
                }
            }
        }
    }
})
