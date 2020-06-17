<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div id="app">
        <nav-bar :profileClinic="profileClinic" :profileDoctor="profileDoctor" :profilePatient="profilePatient">

        </nav-bar>
        <v-app id="inspire">
            <div v-if="(profileDoctor || profileClinic || profilePatient) && $route.path === '/'">
                <v-flex xs12>
                <v-carousel
                        cycle
                        height="600"
                        style="width: 100%;"
                        show-arrows-on-hover
                >
                    <v-carousel-item
                            v-for="(item,i) in items"
                            :key="i"
                            :src="item.src"
                    >
                    </v-carousel-item>
                </v-carousel>
                </v-flex>
            </div>
            <v-main>
                <router-view></router-view>
            </v-main>
        </v-app>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    import MessagesList from 'components/main/MessagesList.vue'
    import NavBar from 'components/navbar/NavBar.vue'

    export default {
        name: 'Main',
        components: {
            MessagesList,
            NavBar
        },
        data() {
            return {
                items: [
                    {
                        src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
                    },
                    {
                        src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
                    },
                    {
                        src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
                    },
                    {
                        src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
                    },
                ]
            }
        },
        created() {
            console.log(this.profileClinic, this.profileDoctor, this.profilePatient);
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient'])
        }
    }
</script>

<style scoped>
</style>