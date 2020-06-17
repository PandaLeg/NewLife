<template>
    <div>
        <div class="container mt-5">
            <h1> {{ $t('listPatients.listPatients') }} </h1>
            <label class="col-form-label"> {{ patient.firstName }} </label>
            <label class="col-form-label"> {{ patient.surname }} </label>
            <div>
                <router-link :to="{name: 'patientProfile', params: {idProfilePatient: patient.id} }">
                    {{ patient.username }}
                </router-link>
            </div>
            <div v-if="profileDoctor">
                <label> Дети: </label>
                <label v-for="child in children" :key="child.id">
                    <router-link :to="{name: 'childProfile', params: {idProfileChild: child.id}}">
                        {{ child.nameChild }}
                    </router-link>
                </label>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    import doctorApi from 'api/doctorProfile'

    export default {
        props: ['patient'],
        data() {
            return {
                children: []
            }
        },
        created() {
            if (this.profileDoctor != null) {
                this.showListChild();
            }
        },
        computed: {
            ...mapState('mainModule', ['profileDoctor'])
        },
        methods: {
            // Список детей, пациентов
            async showListChild() {
                const result = await doctorApi.fetchChildren(this.patient.id);
                const data = await result.json();
                if (result.ok) {
                    this.children = data;
                }
                console.log(result)

            }
        }
    }
</script>

<style>

</style>