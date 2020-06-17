import Vue from 'vue'
import Vuex from 'vuex'
import clinicApi from 'api/clinicProfile'
import doctorApi from 'api/doctorProfile'
import patientApi from 'api/patientProfile'
import childApi from 'api/child'
import editApi from 'api/editProfile'
import requestsApi from 'api/request'
import listDoctorsApi from 'api/listDoctors'
import listPatientsApi from 'api/listPatients'
import messagesApi from 'api/messages'

Vue.use(Vuex);

function deleteRequest(state, request) {
    const deletionIndex = state.requests.findIndex(item => item.id === request.id);
    state.requests = [
        ...state.requests.slice(0, deletionIndex),
        ...state.requests.slice(deletionIndex + 1)
    ];
}
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
            getters: {}
        },
        clinicProfile: {
            namespaced: true,
            state: () => ({
                currentProfileClinic: null,
                checkBindingToClinic: false,
                clinicPicture: '',
                doctors: [],
                patients: [],
                error: null
            }),
            getters: {
                listDoctors: state => {
                    return state.doctors;
                },
                listPatients: state => {
                    return state.patients;
                }
            },
            mutations: {
                getDoctorsListMutation(state, data) {
                    state.doctors = data;
                },
                getPatientsListMutation(state, data) {
                    state.patients = data;
                },
                fetchClinicDataMutation(state, data) {
                    if (data != null) {
                        state.currentProfileClinic = data;
                        state.clinicPicture = '/img/' + state.currentProfileClinic.clinicPicture;
                    } else {
                        state.error = data;
                    }
                },
                checkBindingClinicMutation(state, data) {
                    state.checkBindingToClinic = data;
                },
                cancelBindingClinicMutation(state) {
                    state.checkBindingToClinic = false;
                }
            },
            actions: {
                async getDoctorsListAction({commit}, idProfileClinic) {
                    const result = await clinicApi.getDoctors(idProfileClinic);
                    const data = await result.json();
                    if (result.ok) {
                        commit('getDoctorsListMutation', data)
                    }
                    console.log(result);
                },
                async getPatientsListAction({commit}, idProfileClinic) {
                    const result = await clinicApi.getPatients(idProfileClinic);
                    const data = await result.json();
                    if (result.ok) {
                        commit('getPatientsListMutation', data);
                    }
                },
                // Отправить запрос
                async saveClinicRequestAction({commit}, request) {
                    const result = await clinicApi.send(request);
                    const data = await result.json();
                    console.log(data);
                },
                // Вернуть всю информацию в профиль о клинике
                async fetchClinicDataAction({commit}, idProfileClinic) {
                    const result = await clinicApi.fetchClinicProfile(idProfileClinic);
                    const data = await result.json();
                    if (result.ok) {
                        commit('fetchClinicDataMutation', data);
                    } else {
                        commit('fetchClinicDataMutation', null);
                    }
                },
                // Проверка на привязку к клинике
                async checkBindingClinicAction({commit}, idClinic) {
                    const result = await clinicApi.checkBinding(idClinic);
                    const data = await result.json();
                    if (result.ok) {
                        commit('checkBindingClinicMutation', data);
                    }
                    console.log(result)
                },
                // Отмена привязки со стороны доктора или пациента
                async cancelBindingClinicAction({commit}, idClinic) {
                    const result = await clinicApi.cancelBinding(idClinic);
                    if (result.ok) {
                        commit('cancelBindingClinicMutation');
                    }
                    console.log(result);
                }
            }
        },
        doctorProfile: {
            namespaced: true,
            state: () => ({
                currentProfileDoctor: null,
                doctorPicture: '',
                checkAffiliation: null,
                checkBindingToDoctor: false,
                error: null
            }),
            getters: {},
            mutations: {
                fetchDoctorDataMutation(state, data) {
                    if (data != null) {
                        state.currentProfileDoctor = data;
                        state.doctorPicture = '/img/' + state.currentProfileDoctor.doctorPicture;
                    } else {
                        state.error = data;
                    }
                },
                affiliationToClinicMutation(state, data) {
                    state.checkAffiliation = data;
                },
                checkBindingDoctorMutation(state, data) {
                    state.checkBindingToDoctor = data;
                },
                cancelBindingDoctorMutation(state) {
                    state.checkBindingToDoctor = false;
                }
            },
            actions: {
                async saveDoctorRequestAction({commit}, request) {
                    const result = await doctorApi.send(request);
                    const data = await result.json();
                    console.log(data);
                },
                // Вернуть всю информацию в профиль о докторе
                async fetchDoctorDataAction({commit}, idProfileDoctor) {
                    const result = await doctorApi.fetchDoctorProfile(idProfileDoctor);
                    const data = await result.json();
                    if (result.ok) {
                        commit('fetchDoctorDataMutation', data);
                    } else {
                        commit('fetchDoctorDataMutation', null);
                    }
                },
                async affiliationToClinicAction({commit}, idProfileDoctor) {
                    const result = await doctorApi.affiliationToClinic(idProfileDoctor);
                    const data = await result.json();
                    if (result.ok) {
                        commit('affiliationToClinicMutation', data);
                    }
                },
                async checkBindingDoctorAction({commit}, idProfileDoctor) {
                    const result = await doctorApi.checkBinding(idProfileDoctor);
                    const data = await result.json();
                    if (result.ok) {
                        commit('checkBindingDoctorMutation', data);
                    }
                    console.log(result);
                },
                // Отмена привязки от доктора
                async cancelBindingDoctorAction({commit}, idDoctor) {
                    const result = await doctorApi.cancelBinding(idDoctor);
                    if (result.ok) {
                        commit('cancelBindingDoctorMutation')
                    }
                    console.log(result)
                }
            }
        },
        patientProfile: {
            namespaced: true,
            state: () => ({
                currentProfilePatient: null,
                patientPicture: '',
                children: [],
                checkBindingToPatient: false,
                checkBindingPatientClinic: false,
                error: null
            }),
            getters: {
                listChildren: state => {
                    return state.children;
                }
            },
            mutations: {
                fetchPatientDataMutation(state, data) {
                    if (data != null) {
                        state.currentProfilePatient = data;
                        state.patientPicture = '/img/' + state.currentProfilePatient.patientPicture;
                    } else {
                        state.error = data;
                    }
                },
                fetchChildrenDataMutation(state, data) {
                    state.children = data;
                },
                checkBindingPatientMutation(state, data) {
                    state.checkBindingToPatient = data;
                },
                checkBindingPatientToClinicMutation(state, data) {
                    state.checkBindingPatientClinic = data;
                }
            },
            actions: {
                async fetchPatientDataAction({commit}, idProfilePatient) {
                    const result = await patientApi.fetchPatientProfile(idProfilePatient);
                    const data = await result.json();
                    if (result.ok) {
                        commit('fetchPatientDataMutation', data)
                    } else {
                        commit('fetchPatientDataMutation', null)
                    }
                },
                async fetchChildrenDataAction({commit}, idProfilePatient) {
                    const result = await patientApi.fetchChildren(idProfilePatient);
                    const data = await result.json();
                    if (result.ok) {
                        commit('fetchChildrenDataMutation', data)
                    }
                    console.log(result);
                },
                // Проверка принадлежности пациента к врачу
                async checkBindingPatientAction({commit}, idProfilePatient) {
                    const result = await patientApi.checkBindingDoctor(idProfilePatient);
                    const data = await result.json();
                    if (result.ok) {
                        commit('checkBindingPatientMutation', data);
                    }
                    console.log(result);
                },
                // Проверка принадлежности пациента к клинике для отправки сообщения
                async checkBindingPatientToClinicAction({commit}, idProfilePatient) {
                    const result = await patientApi.checkBindingClinic(idProfilePatient);
                    const data = await result.json();
                    if (result.ok) {
                        commit('checkBindingPatientToClinicMutation', data)
                    }
                    console.log(result);
                }
            }
        },
        editProfile: {
            namespaced: true,
            state: () => ({}),
            getters: {},
            mutations: {},
            actions: {
                async updateProfileClinicAction({commit}, updateClinic) {
                    const result = await editApi.updateProfileClinic(updateClinic);
                    const data = await result.json();
                    console.log(result);
                },
                async updateProfileDoctorAction({commit}, updateDoctor) {
                    const result = await editApi.updateProfileDoctor(updateDoctor);
                    const data = await result.json();
                    console.log(result);
                },
                async updateProfilePatientAction({commit}, updatePatient) {
                    const result = await editApi.updateProfilePatient(updatePatient);
                    const data = await result.json();
                    console.log(result);
                }
            }
        },
        child: {
            namespaced: true,
            state: () => ({
                currentProfileChild: null,
                childPicture: ''
            }),
            getters: {},
            mutations: {
                fetchChildDataMutation(state, data) {
                    state.currentProfileChild = data;
                }
            },
            actions: {
                async fetchChildDataAction({commit}, idProfileChild) {
                    const result = await childApi.fetchChild(idProfileChild);
                    const data = await result.json();
                    if (result.ok) {
                        commit('fetchChildDataMutation', data)
                    }
                    console.log(result);
                }
            }
        },
        request: {
            namespaced: true,
            state: () => ({
                requests: null
            }),
            getters: {
                listRequests: state => {
                    return state.requests
                }
            },
            mutations: {
                getAllRequestsMutation(state, data) {
                    state.requests = data;
                },
                addByClinicToDoctorOrPatientMutation(state, request) {
                    deleteRequest(state, request);
                },
                cancelByClinicToDoctorOrPatientMutation(state, request) {
                    deleteRequest(state, request);
                },
                addByDoctorToPatientMutation(state, request) {
                    deleteRequest(state, request)
                },
                cancelByDoctorToPatientMutation(state, request) {
                    deleteRequest(state, request);
                }
            },
            actions: {
                async getAllRequestsAction(context) {
                    const result = await requestsApi.get();
                    const data = await result.json();
                    if (result.ok) {
                        context.commit('getAllRequestsMutation', data)
                    }
                },
                async addByClinicToDoctorOrPatientAction({commit}, request) {
                    const result = await requestsApi.acceptRequestFromClinic(request.id);
                    if (result.ok) {
                        commit('addByClinicToDoctorOrPatientMutation', request);
                    }
                    console.log(result);
                },
                async cancelByClinicToDoctorOrPatientAction({commit}, request) {
                    const result = await requestsApi.cancelRequestFromClinic(request.id);
                    if (result.ok) {
                        commit('cancelByClinicToDoctorOrPatientMutation', request);
                    }
                    console.log(result);
                },
                async addByDoctorToPatientAction({commit}, request) {
                    const result = await requestsApi.acceptRequestFromDoctor(request.id);
                    if (result.ok) {
                        commit('addByDoctorToPatientMutation', request)
                    }
                    console.log(result);
                },
                async cancelByDoctorToPatientAction({commit}, request) {
                    const result = await requestsApi.cancelRequestFromDoctor(request.id);
                    if (result.ok) {
                        commit('cancelByDoctorToPatientMutation', request)
                    }
                    console.log(result);
                }
            }
        },
        listDoctors: {
            namespaced: true,
            state: () => ({
                listDoctors: []
            }),
            getters: {
                listDoctors: state => {
                    return state.listDoctors;
                }
            },
            mutations: {
                getAllDoctorsMutation(state, data) {
                    state.listDoctors = data;
                }
            },
            actions: {
                async getAllDoctorsAction(context) {
                    const result = await listDoctorsApi.get();
                    const data = await result.json();
                    if (result.ok) {
                        context.commit('getAllDoctorsMutation', data);
                    }
                    console.log(data);
                }
            }
        },
        listPatients: {
            namespaced: true,
            state: () => ({
                listPatients: []
            }),
            getters: {
                listPatients: state => {
                    return state.listPatients;
                }
            },
            mutations: {
                getAllPatientsMutation(state, data) {
                    state.listPatients = data;
                }
            },
            actions: {
                async getAllPatientsAction(context) {
                    const result = await listPatientsApi.get();
                    const data = await result.json();
                    if (result.ok) {
                        context.commit('getAllPatientsMutation', data);
                    }
                    this.patients = data;
                }
            }
        },
        messages: {
            namespaced: true,
            state: () => ({
                messages: []
            }),
            getters: {
                listMessages: state => {
                    return state.messages;
                }
            },
            mutations: {
                findAllMessagesMutation(state, data) {
                    state.messages = data;
                },
                deleteMessageMutation(state, message) {
                    const deletionIndex = state.messages.findIndex(item => item.id === message.id);
                    state.messages = [
                        ...state.messages.slice(0, deletionIndex),
                        ...state.messages.slice(deletionIndex + 1)
                    ]
                }
            },
            actions: {
                async findAllMessagesAction(context) {
                    const result = await messagesApi.fetchMessages();
                    const data = await result.json();
                    if (result.ok) {
                        context.commit('findAllMessagesMutation', data)
                    }
                    console.log(result);
                },
                async deleteMessageAction({commit}, message) {
                    const result = await messagesApi.deleteMessage(message);
                    if (result.ok) {
                        commit('deleteMessageMutation', message)
                    }
                    console.log(result)
                }
            }
        }
    }
})
