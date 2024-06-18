<template>
    <div class="temp-package">
        <div class="temp-package-data">
            <div class="temp-header-row">
                <br>
                <div>
                    <p>Join us, {{ user.name }}!</p>
                    <h3 class="my-2 mx-auto">Choose a package according to your needs!</h3>
                </div>
                <div v-if="message !== ''">{{ message }}</div>
            </div>
            <div class="temp-package-row">
                <swiper ref="swiper" :keyboard="true" :loop="false" effect="coverflow" :grabCursor="true"
                    :centeredSlides="true" slidesPerView="auto" :navigation="true" :mousewheel="true" :coverflowEffect="{
                        rotate: 10,
                        stretch: 10,
                        depth: 120,
                        modifier: 1.5,
                        slideShadows: true,
                    }" :modules="modules" @slideChange="handleSlideChange" class="mySwiper">
                    <swiper-slide v-for="(packageItem, index) in packages" :key="index">
                        <div class="temp-package">
                            <div v-if="packageItem">
                                <div style="line-height: 0px;">
                                    <h3 class="d-flex">
                                        <div>{{ packageItem.packName }}</div>&nbsp;&nbsp;package
                                    </h3>
                                    <h5>{{ packageItem.description }}</h5>
                                </div>
                                <div>
                                    <h1 class="mt-4 mb-5">$ {{ packageItem.price }}</h1>
                                </div>
                                <div class="w-100 px-4">
                                    <div class="d-flex">
                                        <v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;
                                        <p class="p1">Up to {{ packageItem.total_posts }} posts a month.</p>
                                    </div>
                                    <div class="d-flex">
                                        <v-icon class="icon-2">mdi-checkbox-marked-circle</v-icon>&nbsp;
                                        <p class="p2">Up to {{ packageItem.total_ads }} ads a month.</p>
                                    </div>
                                    <div class="d-flex">
                                        <v-icon class="icon-3">mdi-checkbox-marked-circle</v-icon>&nbsp;
                                        <p class="p3">Up to 5 images per post.</p>
                                    </div>
                                    <div class="d-flex">
                                        <v-icon class="icon-4">mdi-checkbox-marked-circle</v-icon>&nbsp;
                                        <p class="p4">1000+ words per post.</p>
                                    </div>
                                </div>
                                <div class="w-100 mt-5 d-flex justify-center temp-btn">
                                    <v-btn elevation="20" class="sub-btn" @click="parseAndGoNext(packageItem)">
                                        <div class="toggle-v-l">
                                            <span class="visible">Buy {{ packageItem.packName }}</span>
                                            <span class="later-visible">Buy now</span>
                                        </div>
                                    </v-btn>
                                </div>
                            </div>
                            <div v-else>No items found.</div>
                        </div>
                    </swiper-slide>
                </swiper>
            </div>
        </div>
        <br />
        <br />
    </div>
</template>

<script>
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/effect-coverflow';
import 'swiper/css/pagination';
import { Navigation, EffectCoverflow, Pagination, Mousewheel, Keyboard } from 'swiper/modules';
import axios from 'axios';
import router from '@/router';

export default {
    name: 'TempForPackage',
    components: {
        Swiper,
        SwiperSlide,
    },
    setup() {
        return {
            modules: [Navigation, EffectCoverflow, Pagination, Mousewheel, Keyboard],
        };
    },
    data() {
        return {
            user: { name: 'User' }, // Mock user data for demonstration
            message: '',
            items: [],
        };
    },
    mounted() {
        this.fetchData();
        this.fetchSubUserInfo();
        localStorage.removeItem('openTab');
    },
    computed: {
        packages() {
            return this.items || []; // Ensure items is an array or return an empty array
        },
    },
    methods: {
        fetchData() {
            axios.get('http://localhost:8083/package-types/getall')
                .then(response => {
                    this.items = response.data;
                    console.log("Package Types", this.items);
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        },
        parseAndGoNext(item) {
            sessionStorage.setItem('packageData', JSON.stringify(item));
            router.push('/payment');
        },
        fetchSubUserInfo() {
            const user = JSON.parse(sessionStorage.getItem('login_user'));
            if (user) {
                const registerId = user.register_id;
                axios.get('http://localhost:8083/subscribe/getSubUserInfo', { params: { registerId } })
                    .then(response => {
                        console.log(response.data);
                        this.availPosts = response.data.availPosts;
                        this.availAds = response.data.availAds;
                    })
                    .catch(error => {
                        console.error('Error fetching data:', error);
                    });
            } else {
                console.log("User is not subscribed.");
            }
        },
    },
};
</script>
