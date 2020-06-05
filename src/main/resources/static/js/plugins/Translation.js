import i18n from '../i18n'

const Trans = {
    get defaultLocale(){
        return process.env.VUE_APP_I18N_LOCALE
    },

    get supportedLocales(){
        return ['en', 'ru', 'ua']
    },

    get currentLocale() {
        return i18n.locale
    },

    set currentLocale(locale) {
        i18n.locale = locale;
    },

    isLocaleSupported(locale){
        return Trans.supportedLocales.includes(locale)
    },

};


export { Trans }