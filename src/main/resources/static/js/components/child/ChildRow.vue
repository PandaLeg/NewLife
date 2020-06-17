<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <router-link :to="{name: 'childProfile', params: {idProfileChild: child.id} }">
            {{child.nameChild}}
        </router-link>
        <button class="btn btn-info" @click="addIndicatorsChild">{{ $t('state.addIndicators') }}</button>
        <!--<div class="row">
            <div class="col-md-6">
                <b-button variant="outline-primary" @click="showModal">
                    Подключить устройство
                </b-button>
                <b-modal id="modal-prevent-closing" ref="my-modal" hide-footer
                         title="Добавление смарт-девайса">
                    <b-form-group label="Mi Band M8"
                                  label-for="description-input" class="text-center">

                        <b-button class="mt-2" variant="outline-primary" block
                                  @click="connectToDevice">Подключить
                        </b-button>
                        <b-button class="mt-3" variant="outline-danger" block
                                  @click="hideModal">Закрыть
                        </b-button>

                    </b-form-group>
                </b-modal>
            </div>
        </div>-->
        <v-app id="inspire">
            <v-row>
                <v-dialog v-model="dialog" persistent max-width="400px">
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn
                                color="primary"
                                dark
                                v-bind="attrs"
                                v-on="on"
                                outlined
                        >
                            Подключить устройство
                        </v-btn>
                    </template>
                    <v-card>
                        <v-card-title>
                            <span class="headline">Smart-device</span>
                        </v-card-title>
                        <v-list flat>
                            <v-list-item-group color="primary">
                                <v-list-item>
                                    <v-list-item-icon>
                                        <v-icon>mdi-devices</v-icon>
                                    </v-list-item-icon>
                                    <v-list-item-content>
                                        <v-list-item-title @click="">
                                            Mi Smart Band 4
                                        </v-list-item-title>
                                    </v-list-item-content>
                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item>
                                    <v-list-item-icon>
                                        <v-icon>mdi-devices</v-icon>
                                    </v-list-item-icon>
                                    <v-list-item-content>
                                        <v-list-item-title @click="">
                                            Smart Watch Gelius Pro
                                        </v-list-item-title>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-list-item-group>
                        </v-list>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="dialog = false">
                                Close
                            </v-btn>
                            <v-btn color="blue darken-1" text @click="connectToDevice">
                                Connect
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
        </v-app>
    </div>
</template>

<script>
    import childApi from 'api/child'

    export default {
        props: ['child'],
        data() {
            return {
                topPressure: 0,
                lowerPressure: 0,
                dialog: false
            }
        },
        watch: {
            // эта функция запускается при любом изменении вопроса
            /*temperature : function () {
                setInterval(this.updateIndicators(), 15000);
            }*/
        },
        methods: {
            addIndicatorsChild() {
                this.$router.push({name: 'addIndicators', params: {childId: this.child.id}})
            },
            randomIndicators() {
                let min = Math.ceil(36.6);
                let max = Math.ceil(38);
                this.child.temperature = (Math.random() * (max - min) + min).toFixed(1);

                if (this.child.age >= 0 && this.child.age < 5) {
                    this.child.pulse = Math.floor(Math.random() * (150 - 110) + 110);
                } else if (this.child.age >= 5 && this.child.age <= 7) {
                    this.child.pulse = Math.floor(Math.random() * (110 - 80) + 80);
                }
                if (this.child.age >= 0 && this.child.age <= 1) {
                    // Верхнее артериальное давление (норма max = 112, min = 90)
                    this.topPressure = Math.floor(Math.random() * (120 - 80) + 80);
                    // Нижнее артериальное давление (норма max = 74, min = 50)
                    this.lowerPressure = Math.floor(Math.random() * (80 - 40) + 40);
                    this.child.pressure = this.topPressure + "x" + this.lowerPressure;
                }

                if (this.child.age >= 2 && this.child.age <= 3) {
                    // Верхнее артериальное давление (норма max = 112, min = 100)
                    this.topPressure = Math.floor(Math.random() * (120 - 90) + 90);
                    // Нижнее артериальное давление (норма max = 74, min = 60)
                    this.lowerPressure = Math.floor(Math.random() * (80 - 50) + 50);
                    this.child.pressure = this.topPressure + "x" + this.lowerPressure;
                }

                if (this.child.age >= 4 && this.child.age <= 5) {
                    // Верхнее артериальное давление (норма max = 116, min = 100)
                    this.topPressure = Math.floor(Math.random() * (125 - 90) + 90);
                    // Нижнее артериальное давление (норма max = 76, min = 60)
                    this.lowerPressure = Math.floor(Math.random() * (82 - 50) + 50);
                    this.child.pressure = this.topPressure + "x" + this.lowerPressure;
                }

                if (this.child.age >= 6 && this.child.age <= 7) {
                    // Верхнее артериальное давление (норма max = 122, min = 100)
                    this.topPressure = Math.floor(Math.random() * (130 - 90) + 90);
                    // Нижнее артериальное давление (норма max = 78, min = 60)
                    this.lowerPressure = Math.floor(Math.random() * (84 - 50) + 50);
                    this.child.pressure = this.topPressure + "x" + this.lowerPressure;
                }
            },
            async connectToDevice() {
                this.randomIndicators();
                let child = {
                    id: this.child.id, temperature: 36.6,
                    pulse: this.child.pulse, pressure: this.child.pressure
                };
                const result = await childApi.connectToDevice(child);
                this.dialog = false;
                console.log(result);
                this.updateIndicators();
            },
            async updateIndicators() {
                setInterval(async () => {
                    this.randomIndicators();
                    let child = {
                        id: this.child.id, temperature: this.child.temperature,
                        pulse: this.child.pulse, pressure: this.child.pressure
                    };
                    const result = await childApi.connectToDevice(child);
                    console.log(result);
                }, 20000);
            }
        }
    }
</script>

<style>

</style>