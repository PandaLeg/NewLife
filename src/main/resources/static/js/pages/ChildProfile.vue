<template>
    <div>
        <div v-if="currentProfileChild" class="content">
            <div class="container emp-profile">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img" v-if="childPicture != '/img/null'">
                            <img :src="defaultPicture">
                        </div>
                        <div class="profile-img" v-else>
                            <img :src="defaultPicture">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                            <h5>
                                {{ currentProfileChild.nameChild }}
                            </h5>
                            <p class="profile-rating">RANKINGS : <span>8/10</span></p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" role="tab"
                                       aria-controls="home" aria-selected="true">{{ $t('childProfile.about') }}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2" v-if="currentProfileChild">
                        <input type="submit" class="profile-edit-btn" name="btnAddMore"
                               :value="$t('childProfile.editProfile')"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                            <p>{{ $t('childProfile.interests') }}</p>
                            <a href=""></a><br/>
                            <a href=""></a><br/>
                            <a href=""></a>
                        </div>
                    </div>

                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('childProfile.name') }}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfileChild.nameChild }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('childProfile.age') }}</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p>{{ currentProfileChild.age }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('childProfile.height') }}</label>
                                    </div>
                                    <div class="col-md-6" v-if="currentProfileChild.height != null">
                                        <p>{{ currentProfileChild.height }}</p>
                                    </div>
                                    <div class="col-md-6" v-else>
                                        <p>{{ $t('childProfile.specified') }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('childProfile.weight') }}</label>
                                    </div>
                                    <div class="col-md-6" v-if="currentProfileChild.weight != null">
                                        <p>{{ currentProfileChild.weight }}</p>
                                    </div>
                                    <div class="col-md-6" v-else>
                                        <p>{{ $t('childProfile.specified') }}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>{{ $t('childProfile.state') }}</label>
                                    </div>
                                    <div class="col-md-6" v-if="currentProfileChild.state != null">
                                        <p>{{ currentProfileChild.state }}</p>
                                    </div>
                                    <div class="col-md-6" v-else>
                                        <p>{{ $t('childProfile.specified') }}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <h5>{{ $t('childProfile.indicators') }}</h5>
                <hr class="mt-2 mb-3"/>
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">{{ $t('childProfile.temperature') }}</th>
                        <th scope="col">{{ $t('childProfile.pulse') }}</th>
                        <th scope="col">{{ $t('childProfile.pressure') }}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>{{ currentProfileChild.temperature }}</td>
                        <td>{{ currentProfileChild.pulse }}</td>
                        <td>{{ currentProfileChild.pressure }}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        props: ['idProfileChild'],
        data(){
            return{
                currentProfileChild: null,
                childPicture: '',
            }
        },
        created(){
            this.fetchChildData();
        },
        computed: {
            ...mapState('mainModule', ['profileClinic', 'profileDoctor', 'profilePatient', 'defaultPicture'])
        },
        methods:{
            fetchChildData(){
                this.$resource('/child/{id}').get({id: this.idProfileChild}).then(result =>
                    result.json().then(data => {
                        this.currentProfileChild = data;
                        console.log(result);
                    })
                )
            }
        }
    }
</script>


<style scoped>
    .emp-profile{
        padding: 3%;
        margin-top: 3%;
        margin-bottom: 3%;
        border-radius: 50%;
        background: #fff;
    }
    .profile-img{
        text-align: center;
    }
    .profile-img img{
        width: 200px;
        height: 200px;
        border-radius: 50%;
        background-size: auto 300px;
    }
    .profile-img .file input {
        position: absolute;
        opacity: 0;
        right: 0;
        top: 0;
    }

    .profile-head h5{
        color: #333;
    }
    .profile-head h6{
        color: #0062cc;
    }

    .profile-edit-btn{
        border: none;
        border-radius: 1.5rem;
        width: 80%;
        height: 20%;
        padding: 2%;
        font-weight: 600;
        color: #6c757d;
        cursor: pointer;
    }
    .profile-rating{
        font-size: 12px;
        color: #818182;
        margin-top: 5%;
    }
    .profile-rating span{
        color: #495057;
        font-size: 15px;
        font-weight: 600;
    }

    .profile-head .nav-tabs{
        margin-bottom:5%;
    }
    .profile-head .nav-tabs .nav-link{
        font-weight:600;
        border: none;
    }

    .profile-head .nav-tabs .nav-link.active{
        border: none;
        border-bottom:2px solid #0062cc;
    }
    .profile-work{
        padding: 20%;
        margin-top: -15%;
    }

    .profile-check{
        padding: 20%;
        margin-top: -30%;
    }

    .profile-work p{
        font-size: 12px;
        color: #818182;
        font-weight: 600;
        margin-top: 10%;
    }
    .profile-work a{
        text-decoration: none;
        color: #495057;
        font-weight: 600;
        font-size: 14px;
    }

    .profile-tab label{
        font-weight: 600;
    }
    .profile-tab p{
        font-weight: 600;
        color: #0062cc;
    }

    .card-check img{
        max-height: 150px;
        max-width: 250px;
    }
    @media (min-width: 576px) and (max-width: 768px) {
        .slide-box img {
            -ms-flex: 0 0 50%;
            flex: 0 0 50%;
            max-width: 50%;
        }
    }

    @media (min-width: 768px) and (max-width: 992px) {
        .slide-box img {
            -ms-flex: 0 0 33.3333%;
            flex: 0 0 33.3333%;
            max-width: 33.3333%;
        }
    }

    @media (min-width: 992px)
    {
        .slide-box img {
            -ms-flex: 0 0 25%;
            flex: 0 0 25%;
            max-width: 25%;
        }
    }
</style>