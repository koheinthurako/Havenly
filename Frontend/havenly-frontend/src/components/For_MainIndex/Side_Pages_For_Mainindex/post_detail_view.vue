<template>
    <div class="post_detail_view">
        <div class="container">
            <div class="row mx-auto">

                <div class="col-12 col-md-8">
                    <div class="detail_upper">
                        <div class="header">
                            <h5>Header Section</h5>
                        </div>
                        <div class="image">
                            <swiper3 class="top-swiper" :style="{
                                '--swiper-navigation-color': 'red',
                            }" :loop="true" :autoplay="{
                                delay: 3000,
                                disableOnInteraction: false,
                            }" :thumbs="{ swiper: thumbsSwiper }" :navigation="true" :modules="modules">
                                <swiper-slide3 class="slide" v-for="(data, index) in images" :key="index">
                                    <v-img :src="data.img" @click="openModal(index)" />

                                </swiper-slide3>

                            </swiper3>
                            <swiper3 class="thumbs-swiper" @swiper="setThumbsSwiper" :loop="true" :slidesPerView="5"
                                :freeMode="true" :watchSlidesProgress="true" :modules="modules">

                                <swiper-slide3 class="slide" v-for="data in images" :key="data">
                                    <v-img :src="data.img" /></swiper-slide3>

                            </swiper3>

                            <!-- Modal for displaying full-size image -->
                            <v-dialog transition="dialog-top-transition" v-model="modal" @keydown.esc="closeModal">
                                <v-card class="image_dialog">
                                    <v-img :src="selectedImage.img"></v-img>
                                    <v-btn class="prev_btn" @click="prevImage" :disabled="selectedImageIndex <= 0">
                                        <v-icon>mdi-arrow-left-bold</v-icon> Previous
                                    </v-btn>
                                    <v-btn class="next_btn" @click="nextImage"
                                        :disabled="selectedImageIndex >= images.length - 1">
                                        Next <v-icon>mdi-arrow-right-bold</v-icon>
                                    </v-btn>
                                    <v-btn class="close_btn" @click="closeModal">Close</v-btn>
                                </v-card>
                            </v-dialog>


                        </div>
                    </div>
                    <div class="detail_lower">
                        <div class="status">
                            <div class="d-flex"><v-icon class="me-2">mdi-clock</v-icon><span>26 Apr 2024 3:06 pm</span>
                            </div>

                            <div class="d-flex"><v-icon class="me-2">mdi-eye</v-icon><span>441 views</span></div>
                            <div class="d-flex"><v-icon class="me-2">mdi-information</v-icon><span>Post id</span></div>
                        </div>
                        <v-divider inset class="mx-auto  py-0"></v-divider>
                        <div class="information">
                            <div class="row">
                                <div class="col-12 col-md-6">
                                    <div>
                                        <v-icon class="me-2">mdi-map-marker</v-icon><span>Insein | Yangon Region</span>
                                    </div>
                                    <div><v-icon class="me-2">mdi-office-building</v-icon><span>Apartment | Ground Floor
                                            +
                                            Attic</span></div>
                                    <div class="d-flex">
                                        <div class="d-flex me-2"><v-icon class="me-2"
                                                style="transform: rotate(45deg);">mdi-arrow-all</v-icon><span>18
                                                ft x 55 ft</span></div>
                                        <div class="d-flex"><v-icon class="me-2"
                                                style="transform: rotate(45deg);">mdi-arrow-all</v-icon><span>18 ft x
                                                55 ft</span></div>
                                    </div>
                                    <div class="d-flex">
                                        <div class="d-flex me-2"><v-icon class="me-2">mdi-shower</v-icon><span>1
                                                Bedroom</span></div>
                                        <div class="d-flex"><v-icon class="me-2">mdi-bed</v-icon><span>2
                                                Bathroom(s)</span></div>
                                    </div>

                                </div>
                                <v-divider inset class="mx-auto py-0 d-block d-md-none"></v-divider>
                                <div class="col-12 col-md-6">

                                    <div><v-icon class="me-2">mdi-tag-multiple</v-icon><span>Price</span></div>

                                    <div>
                                        <h2>2300 Lakh</h2>
                                    </div>

                                    <div class="d-flex justify-start ">
                                        <div class="owner-info me-2">Owner direct</div>
                                        <div class="bank-info"><span class="mdi mdi-checkbox-marked-circle"></span>Bank
                                            Installment
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <v-divider inset class="mx-auto py-0"></v-divider>
                        <div class="documentation">
                            <p style="text-indent: 40px;">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellat, accusantium impedit
                                alias cum dicta incidunt aut nesciunt libero recusandae, nemo, consequatur in vel rerum
                                praesentium neque saepe maxime eveniet minus. Lorem ipsum dolor sit amet consectetur
                                adipisicing elit. Ipsa ratione doloremque architecto itaque dolorum dolores distinctio
                                pariatur voluptatem eos saepe tenetur fugiat quidem qui, delectus incidunt ab, iste
                                ipsum
                                sit!
                            </p>
                        </div>
                        <v-divider inset class="mx-auto  py-0"></v-divider>
                        <div class="footer">
                            <div class="w-100">
                                <p>Extra feature</p>
                                <div><v-icon class="me-2">mdi-checkbox-marked-circle</v-icon><span>Complete</span>
                                </div>
                                <div><v-icon class="me-2">mdi-checkbox-marked-circle</v-icon><span>Fully
                                        Furnished</span></div>
                            </div>

                            <div class="row mt-4">
                                <div class="col-md-6 col-12">
                                    <v-btn class="advertister-phone mb-3"><v-icon>mdi-phone</v-icon>Contact Advertister
                                        by
                                        phone</v-btn>
                                </div>
                                <div class="col-md-6 col-12">
                                    <v-btn class="advertister-check"><v-icon>mdi-phone</v-icon>Check with
                                        Advertiser</v-btn>

                                </div>
                            </div>


                            <v-divider inset class="mx-auto py-0"></v-divider>
                            <div class="function_btn">
                                <div class=row>
                                    <div class="px-0 mb-3 col-md-6 col-12 d-flex justify-md-end justify-center">
                                        <v-btn class="compare-btn"><v-icon class="me-1">mdi-plus</v-icon>Compare</v-btn>
                                        <v-btn class="fav-btn "><v-icon class="me-1">mdi-heart-outline</v-icon>Add to
                                            favorite</v-btn>
                                    </div>
                                    <div class="px-0 col-md-6 col-12 d-flex justify-md-start justify-center">
                                        <v-btn class="share-btn "><v-icon class="me-1">mdi-share</v-icon>Share</v-btn>
                                        <v-btn class="report-btn "><v-icon class="me-1">mdi-flag</v-icon>Report</v-btn>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <!-- related Post section -->
                    <div class="related_section">
                        <div class="w-100">
                            <h3 class="header-text">Related Sell posts</h3>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-sm-12" v-for="data in Sells" :key="data.id">

                                <div class="post">
                                    <div class="img">
                                        <div class="type">
                                            <div>
                                                <div><v-icon>mdi-format-list-bulleted-type</v-icon>{{ data.post_type }}
                                                </div>
                                            </div>
                                            <div>

                                                <div @click="toggleSaved(data.id)">
                                                    <v-icon v-if="isSaved(data.id)">mdi-bookmark-check</v-icon>
                                                    <v-icon v-else>mdi-bookmark-outline</v-icon>
                                                    Save Post
                                                </div>

                                            </div>
                                        </div>
                                        <v-img :src="data.img" />
                                        <div class="info">
                                            <div class="d-flex"><v-icon>mdi-eye</v-icon><span>441</span></div>
                                            <div><v-icon>mdi-currency-usd</v-icon>
                                                <span class="money">2300 Lakh</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="data">
                                        <div class="header">
                                            <h5>{{ data.title }}</h5>
                                        </div>
                                        <v-divider inset class="mx-auto py-0"></v-divider>

                                        <div class="information">
                                            <div><v-icon>mdi-map-marker</v-icon> Insein Yangon</div>
                                            <div><v-icon>mdi-post</v-icon><span>For {{ data.type }}</span></div>
                                        </div>


                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="footer_section">
                            <v-btn class="footer-btn">See all sell
                                posts<v-icon>mdi-chevron-double-right</v-icon></v-btn>
                        </div>
                    </div>

                    <!-- related Post section -->
                    <div class="related_section">
                        <div class="w-100">
                            <h3 class="header-text">Related Rent posts</h3>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-sm-12" v-for="data in Rents" :key="data">

                                <div class="post">
                                    <div class="img">
                                        <div class="type">
                                            <div>
                                                <div><v-icon>mdi-format-list-bulleted-type</v-icon>{{ data.post_type }}
                                                </div>
                                            </div>
                                            <div @click="toggleSaved(data.id)">
                                                <v-icon v-if="isSaved(data.id)">mdi-bookmark-check</v-icon>
                                                <v-icon v-else>mdi-bookmark-outline</v-icon>
                                                Save Post
                                            </div>
                                        </div>
                                        <v-img :src="data.img" />
                                        <div class="info">
                                            <div class="d-flex"><v-icon>mdi-eye</v-icon><span>441</span></div>
                                            <div><v-icon>mdi-currency-usd</v-icon>
                                                <span class="money">2300 Lakh</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="data">
                                        <div class="header">
                                            <h5>{{ data.title }}</h5>
                                        </div>
                                        <v-divider inset class="mx-auto py-0"></v-divider>

                                        <div class="information">
                                            <div><v-icon>mdi-map-marker</v-icon> Insein Yangon</div>
                                            <div><v-icon>mdi-post</v-icon><span>For {{ data.type }}</span></div>
                                        </div>


                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="footer_section">
                            <v-btn class="footer-btn">See all sell
                                posts<v-icon>mdi-chevron-double-right</v-icon></v-btn>
                        </div>
                    </div>

                    <!-- Add some ads pages -->
                    <div class="related_section">
                        <div class="w-100 py-2 d-flex justify-end">
                            <v-btn class="ads-btn">See more ads<v-icon>mdi-chevron-double-right</v-icon></v-btn>
                        </div>
                        <small_ads />
                    </div>

                    <!-- Other related in this rea -->
                    <div class="related_section">
                        <div class="w-100">
                            <h3 class="header-text">Other related post in this area</h3>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-sm-12" v-for="data in Rents" :key="data">

                                <div class="post">
                                    <div class="img">
                                        <div class="type">
                                            <div>
                                                <div><v-icon>mdi-format-list-bulleted-type</v-icon>{{ data.post_type }}
                                                </div>
                                            </div>
                                            <div @click="toggleSaved(data.id)">
                                                <v-icon v-if="isSaved(data.id)">mdi-bookmark-check</v-icon>
                                                <v-icon v-else>mdi-bookmark-outline</v-icon>
                                                Save Post
                                            </div>
                                        </div>
                                        <v-img :src="data.img" />
                                        <div class="info">
                                            <div class="d-flex"><v-icon>mdi-eye</v-icon><span>441</span></div>
                                            <div><v-icon>mdi-currency-usd</v-icon>
                                                <span class="money">2300 Lakh</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="data">
                                        <div class="header">
                                            <h5>{{ data.title }}</h5>
                                        </div>
                                        <v-divider inset class="mx-auto py-0"></v-divider>

                                        <div class="information">
                                            <div><v-icon>mdi-map-marker</v-icon> Insein Yangon</div>
                                            <div><v-icon>mdi-post</v-icon><span>For {{ data.type }}</span></div>
                                        </div>


                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="footer_section">
                            <v-btn class="footer-btn">See all sell
                                posts<v-icon>mdi-chevron-double-right</v-icon></v-btn>
                        </div>
                    </div>


                </div>
                <div class="col-12 col-md-4 d-none d-sm-block">

                    <div class="side_show my-3">


                        <!-- button row -->
                        <div class="btn-group">
                            <v-btn class="left_btn d-flex align-items-center"><v-icon>mdi-arrow-left-bold</v-icon>Back
                                to
                                home</v-btn>
                            <v-btn class="right_btn">Next post<v-icon>mdi-arrow-right-bold</v-icon></v-btn>
                        </div>

                        <!-- event start -->
                        <div class="my-3">
                            <event_page />
                        </div>

                        <!-- Ads medium size -->
                        <div class="my-3">
                            <medium_ads />
                        </div>

                    </div>


                </div>
            </div>

        </div>
    </div>
</template>

<style scoped></style>

<script>
import { ref, onMounted } from 'vue';

import { Swiper as Swiper3, SwiperSlide as SwiperSlide3 } from 'swiper/vue';

import 'swiper/css';

import 'swiper/css/free-mode';
import 'swiper/css/navigation';
import 'swiper/css/thumbs';


import { Autoplay, FreeMode, Navigation, Thumbs } from 'swiper/modules';


import small_ads from '../../Temp_Collection/ads-Small.vue'
import medium_ads from '../../Temp_Collection/ads-Medium.vue'


import event_page from '../../Temp_Collection/eventVue.vue'

export default {

    data() {
        return {

            images: [
                { id: 1, img: require('@/assets/img/h1.jpg') },
                { id: 2, img: require('@/assets/img/h2.jpg') },
                { id: 3, img: require('@/assets/img/h3.jpg') },
                { id: 4, img: require('@/assets/img/h4.jpg') },
                { id: 5, img: require('@/assets/img/h5.jpg') },
                { id: 6, img: require('@/assets/img/c6.jpg') },
                { id: 7, img: require('@/assets/img/h7.jpg') },
                { id: 8, img: require('@/assets/img/h8.jpg') },
                { id: 9, img: require('@/assets/img/h9.jpg') },
                { id: 10, img: require('@/assets/img/h10.jpg') },
            ],

            Sells: [
                { id: 1, title: 'Hello world', img: require('@/assets/img/h1.jpg'), post_type: 'Condo', type: 'sell' },
                { id: 2, title: 'Hello Again', img: require('@/assets/img/h3.jpg'), post_type: 'Apartment', type: 'sell' },
                { id: 3, title: 'Hello Elephant', img: require('@/assets/img/h5.jpg'), post_type: 'Industrial', type: 'sell' },
                { id: 4, title: 'Hello Dolphin', img: require('@/assets/img/h7.jpg'), post_type: 'Home', type: 'sell' },

            ],

            Rents: [
                { id: 5, title: 'Hello world', img: require('@/assets/img/h2.jpg'), post_type: 'Mini Condo', type: 'rent' },
                { id: 6, title: 'Hello Again', img: require('@/assets/img/h4.jpg'), post_type: 'Restaurant', type: 'rent' },
                { id: 7, title: 'Hello Elephant', img: require('@/assets/img/h9.jpg'), post_type: 'Office', type: 'rent' },
                { id: 8, title: 'Hello Dolphin', img: require('@/assets/img/h8.jpg'), post_type: 'Hotel', type: 'rent' },

            ],
            savedPosts: [],


            modal: false,
            selectedImage: null
        }
    },


    components: {
        Swiper3,
        SwiperSlide3,

        small_ads,
        medium_ads,
        event_page,
    },

    methods: {
        toggleSaved(postId) {
            const index = this.savedPosts.indexOf(postId);
            if (index !== -1) {
                this.savedPosts.splice(index, 1);
            } else {
                this.savedPosts.push(postId);
            }
        },
        isSaved(postId) {
            return this.savedPosts.includes(postId);
        },
        openModal(index) {


            this.selectedImage = this.images[index];
            this.selectedImageIndex = index;
            this.modal = true;
        },
        closeModal() {
            this.modal = false;
        },
        nextImage() {
            if (this.selectedImageIndex < this.images.length - 1) {
                this.selectedImageIndex++;
                this.selectedImage = this.images[this.selectedImageIndex];
            }
        },
        prevImage() {
            if (this.selectedImageIndex > 0) {
                this.selectedImageIndex--;
                this.selectedImage = this.images[this.selectedImageIndex];
            }
        },

    },
    setup() {
        const thumbsSwiper = ref(null);

        const setThumbsSwiper = (swiper) => {
            thumbsSwiper.value = swiper;
        };

        onMounted(() => {
            thumbsSwiper.value.slideTo(0);
        });

        return {
            thumbsSwiper,
            setThumbsSwiper,
            modules: [Autoplay, FreeMode, Navigation, Thumbs],
        };
    },
};
</script>
