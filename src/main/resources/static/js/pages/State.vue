<template>
    <div>
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">
        </nav-bar>
        <child-list :children="children"></child-list>
    </div>
</template>

<script>
    import ChildList from 'components/child/ChildList.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        components:{
            ChildList,
            NavBar
        },
        data() {
            return {
                children: stateData.children,
                profileClinic: stateData.profileClinic,
                profileDoctor: stateData.profileDoctor,
                profilePatient: stateData.profilePatient
            }
        }
    }
</script>

<style>

</style>