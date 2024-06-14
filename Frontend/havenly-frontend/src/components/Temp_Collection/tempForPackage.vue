<template>
    <div class="temp-package">

        <div class="temp-package-data">
            <div class="temp-header-row">
                <div>
                    <!-- <p>join us</p> -->
                    <h3 class="my-2 mx-auto">Choose a package according to your needs!</h3>
                </div>

            </div>
            <div class="temp-package-row">

                <swiper ref="swiper" :keyboard="true" :loop="false" :effect="'coverflow'" :grabCursor="true"
                    :centeredSlides="true" :slidesPerView="'auto'" :navigation="true" :mousewheel="true"
                    :coverflowEffect="{
                        rotate: 10,
                        stretch: 10,
                        depth: 120,
                        modifier: 1.5,
                        slideShadows: true,
                    }" :modules="modules" @slideChange="handleSlideChange" class="mySwiper">
                    <swiper-slide>
                        <!-- package three start -->
                        <div class="temp-package">
                            <div style="line-height: 0px;">
                                <h3 class="d-flex">
                                    <div>{{ items2.name }}</div>&nbsp;&nbsp;package
                                </h3>
                                <h5>For real-estate agents</h5>
                            </div>
                            <div>
                                <h1 class="mt-4 mb-5">
                                    $ {{ items2.price }}
                                </h1>
                            </div>

                            <div class="w-100 px-4">

                                <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;
                                    <p class="p1">Up to 30
                                        posts a month.</p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-2">mdi-checkbox-marked-circle</v-icon>&nbsp;
                                    <p class="p2">Up to 20 ads a month.
                                    </p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-3">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p3">Up to 5
                                        images per
                                        post.</p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-4">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p4">1000+
                                        words per
                                        post.</p>
                                </div>

                            </div>
                            <div class="w-100 mt-5 d-flex justify-center temp-btn">
                                <v-btn elevation="20" class="sub-btn" @click="parseAndGoNext(items2)">
                                    <div class="toggle-v-l">
                                        <span class="visible">Buy Premium</span>
                                        <span class="later-visible">Buy now</span>
                                    </div>
                                </v-btn>
                            </div>
                        </div>
                        <!-- package three end -->
                    </swiper-slide>
                    <swiper-slide>
                        <!-- package two start -->
                        <div class="temp-package">
                            <div style="line-height: 0px;">
                                <h3 class="d-flex">
                                    <div>{{ items1.name }}</div>&nbsp;&nbsp;package
                                </h3>
                                <h5>For average users</h5>
                            </div>
                            <div>
                                <h1 class="mt-4 mb-5">
                                    $ {{ items1.price }}
                                </h1>
                            </div>

                            <div class="w-100 px-4">

                                <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p1">Up to 20
                                        posts a month.</p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-2">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p2">Up to 10
                                        ads a month.
                                    </p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-3">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p3">Up to 3
                                        images per
                                        post.</p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-4">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p4">500+
                                        words per
                                        post.</p>
                                </div>

                            </div>
                            <div class="w-100 mt-5 d-flex justify-center temp-btn">
                                <v-btn elevation="20" class="sub-btn" @click="parseAndGoNext(items1)">
                                    <div class="toggle-v-l">
                                        <span class="visible">Buy Normal</span>
                                        <span class="later-visible">Buy now</span>
                                    </div>
                                </v-btn>
                            </div>
                        </div>
                        <!-- package two end -->
                    </swiper-slide>


                </swiper>

            </div>
        </div>

        <!-- <div class="sponsor-ship">
            <div class="row d-flex justify-center">
                <div class="col-md-2 col-sm-6">
                    <v-img :src="img1"></v-img>
                </div>
                <div class="col-md-2 col-sm-6">
                    <v-img :src="img2"></v-img>
                </div>
                <div class="col-md-2 col-sm-6">
                    <v-img :src="img3"></v-img>
                </div>
                <div class="col-md-2 col-sm-6 ">
                    <v-img :src="img4"></v-img>
                </div>
                <div class="col-md-2 col-sm-12">
                    <v-img :src="img5"></v-img>
                </div>
            </div>
        </div> -->

    </div>

</template>


<script>
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
// import Swal from 'sweetalert2';

import 'swiper/css/effect-coverflow';
import 'swiper/css/pagination';

import { Navigation, EffectCoverflow, Pagination, Mousewheel, Keyboard } from 'swiper/modules';

import router from '@/router';
// import axios from 'axios';

export default {
    name: 'tempForPackage',

    components: {
        Swiper,
        SwiperSlide,
    },
    setup() {

        return {
            modules: [Navigation, EffectCoverflow, Pagination, Mousewheel, Keyboard],
            items1: { name: 'Normal', price: '25' },
            items2: { name: 'Premium', price: '50' },
        };
    },

    data: () => ({
        img1: require('@/assets/img/logo-apple.svg'),
        img2: require('@/assets/img/logo-facebook.svg'),
        img3: require('@/assets/img/logo-nasa.svg'),
        img4: require('@/assets/img/logo-vodafone.svg'),
        img5: require('@/assets/img/logo-digitalocean.svg'),

    }),

    mounted() {
        localStorage.removeItem('openTab');
    },

    methods: {
        startCountAnimation(price) {
            this.hovering = true;
            this.animatedPrice = 0;
            clearInterval(this.interval);
            this.interval = setInterval(() => {
                this.animatedPrice += Math.ceil(price / 60);
                if (this.animatedPrice >= price) {
                    this.animatedPrice = price;
                    clearInterval(this.interval);
                }
            }, 20);
        },
        parseAndGoNext(item) {
            // Parse data and store in session storage
            sessionStorage.setItem('packageName', JSON.stringify(item));

            // Go to the next page
            router.push('/packages/payment'); // Assuming you're using Vue Router
        }

    },

    // setup() {

    //     return {
    //         modules: [EffectCoverflow, Pagination, Mousewheel, Keyboard],

    //     };

    // },


    computed: {
        user_info() {
            return this.$store.getters.Take_Userinfo
        }
    },

}

</script>