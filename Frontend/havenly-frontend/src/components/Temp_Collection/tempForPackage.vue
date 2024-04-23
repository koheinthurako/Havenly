<template>
    <div class="temp-package">

        <div class="temp-package-data">
            <div class="temp-header-row ">
                <div>
                    <p>pricing</p>
                    <h3 class="my-2 mx-auto">We offer three different kinds of package. Enjoy.</h3>
                </div>

            </div>
            <div class="temp-package-row">

                <swiper :keyboard="true" :effect="'coverflow'" :grabCursor="true" :centeredSlides="true"
                    :slidesPerView="'auto'" :navigation="true" :mousewheel="true" :coverflowEffect="{
                        rotate: 10,
                        stretch: 20,
                        depth: 100,
                        modifier: 1.5,
                        slideShadows: true,
                    }" :pagination="{
                        clickable: true,
                    }" :modules="modules" class="mySwiper">
                    <swiper-slide>
                        <!-- package one start -->
                        <div class="temp-package">
                            <div style="line-height: 0px;">
                                <h3>Free Trial</h3>
                                <p class="text-grey">For new user</p>
                            </div>
                            <div>
                                <h1 class="mt-4 mb-5">$0</h1>
                            </div>

                            <div class="w-100 px-4">

                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;Over all 3 posts.</p>
                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;Over all 3 ads.</p>
                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;3 images per post.</p>
                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;1000 words per post.</p>

                            </div>
                            <div class="w-100 mt-5 d-flex justify-center">
                                <v-btn elevation="20" class="sub-btn">
                                    Subscribe
                                </v-btn>
                            </div>
                        </div>
                        <!-- package one end -->
                    </swiper-slide>
                    <swiper-slide>
                        <!-- package two start -->
                        <div class="temp-package" style="background-color: #202842; color: #fff">
                            <div style="line-height: 0px;">
                                <h3 style="color: #fff;">Normal Package</h3>
                                <p class="text-white">For normal user</p>
                            </div>
                            <div>
                                <h1 class="mt-4 mb-5" style="color: #e86f52;">$399</h1>
                            </div>

                            <div class="w-100 px-4">

                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;Over all 3 posts.</p>
                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;Over all 3 ads.</p>
                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;3 images per post.</p>
                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;1000 words per post.</p>

                            </div>
                            <div class="w-100 mt-5 d-flex justify-center">
                                <v-btn elevation="20" class="sub-btn">
                                    Subscribe
                                </v-btn>
                            </div>
                        </div>
                        <!-- package two end -->
                    </swiper-slide>
                    <swiper-slide>
                        <!-- package three start -->
                        <div class="temp-package">
                            <div style="line-height: 0px;">
                                <h3>Premium Package</h3>
                                <p class="text-grey">For genius user</p>
                            </div>
                            <div>
                                <h1 class="mt-4 mb-5">$799</h1>
                            </div>

                            <div class="w-100 px-4">

                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;Over all 3 posts.</p>
                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;Over all 3 ads.</p>
                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;3 images per post.</p>
                                <p><v-icon>mdi-checkbox-marked-circle</v-icon>&nbsp;1000 words per post.</p>

                            </div>
                            <div class="w-100 mt-5 d-flex justify-center">
                                <v-btn elevation="20" class="sub-btn">
                                    Subscribe
                                </v-btn>
                            </div>
                        </div>
                        <!-- package three end -->
                    </swiper-slide>

                </swiper>

            </div>
        </div>

        <div class="sponsor-ship">
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
        </div>

    </div>

</template>


<script>

import { Swiper, SwiperSlide } from 'swiper/vue';

import 'swiper/css';

import 'swiper/css/effect-coverflow';
import 'swiper/css/pagination';

import { EffectCoverflow, Pagination, Mousewheel, Keyboard } from 'swiper/modules';
export default {
    name: 'tempForPackage',

    components: {
        Swiper,
        SwiperSlide,
    },
    setup() {
        return {
            modules: [EffectCoverflow, Pagination, Mousewheel, Keyboard],
        };
    },

    data: () => ({
        img1: require('@/assets/img/logo-apple.svg'),
        img2: require('@/assets/img/logo-facebook.svg'),
        img3: require('@/assets/img/logo-nasa.svg'),
        img4: require('@/assets/img/logo-vodafone.svg'),
        img5: require('@/assets/img/logo-digitalocean.svg'),
    }),
}

</script>
