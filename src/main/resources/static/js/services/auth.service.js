import Vue from 'vue'
import VueResource from 'vue-resource'
import authHeader from "./auth-header";
Vue.use(VueResource);

const API_URL = '/authenticate';

class AuthService {
    login(user) {
        let usr = {userName: user.username, password: user.password};
        return Vue.resource('/authenticate').save({}, usr).then(response => {
            if(response.data){
                localStorage.setItem('user', JSON.stringify(response.data))
            }
            console.log(response.bodyText);
            return response.data;
        })
    }

    logout(){
        localStorage.removeItem('user')
    }
}



export default new AuthService();