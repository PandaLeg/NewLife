<template>
    <div>
        <b-dropdown-item-button v-for="locale in supportedLocales" :key="locale" @click="switchLocale(locale)">
            <span class="locale-name">{{locale}}</span>
        </b-dropdown-item-button>
    </div>
</template>

<script>
    import { Trans } from 'plugins/Translation'
    export default {
        name: 'LocaleSwitcher',
        data() {
            return {
            }
        },
        computed:{
            supportedLocales(){
                return Trans.supportedLocales
            }
        },
        methods:{
            switchLocale(locale){
                if(Trans.currentLocale !== locale){
                    this.$i18n.locale = locale;
                }
            }
        }
    }
</script>

<style scoped>
</style>