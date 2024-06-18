<template>
  <div class="third-carousel-page">
    <!-- <h3 class="ms-4 mb-3" style="color:#e86f52;">Ads slider</h3> -->
    <div class="custom-swiper-container">
      <div v-if="loading">
        <v-row class="g-3">
          <v-col cols="12" md="3">
            <v-skeleton-loader class="mx-auto border" type="image"></v-skeleton-loader>
          </v-col>
          <v-col cols="12" md="3" class="d-none d-sm-block">
            <v-skeleton-loader class="mx-auto border" type="image"></v-skeleton-loader>
          </v-col>
          <v-col cols="12" md="3" class="d-none d-sm-block">
            <v-skeleton-loader class="mx-auto border" type="image"></v-skeleton-loader>
          </v-col>
          <v-col cols="12" md="3" class="d-none d-sm-block">
            <v-skeleton-loader class="mx-auto border" type="image"></v-skeleton-loader>
          </v-col>
        </v-row>
      </div>
      <swiper v-else :loop="ads.length > 1" :breakpoints="{
        '640': {
          slidesPerView: 1,
          spaceBetween: 20,
        },
        '768': {
          slidesPerView: 4,
          spaceBetween: 30,
        },
        '1024': {
          slidesPerView: 4,
          spaceBetween: 30,
        },
      }" :autoplay="{ delay: 2500, disableOnInteraction: true }" :navigation="true" :modules="modules" class="mySwiper"
        @swiper="onSwiperInit">
        <swiper-slide v-for="(ad, index) in ads" :key="index" @click="handleSlideClick(ad.title)" >
          <v-img :src="ad.photo_url" class="mt-3 mb-3 card-img" style="cursor:pointer; border-radius: 8px;" @mouseenter="stopAutoScroll"
            @mouseleave="startAutoScroll" ></v-img>
        </swiper-slide>
      </swiper>
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/pagination';
import axios from 'axios';
import { Autoplay, Pagination, Navigation } from 'swiper/modules';

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      ads: [],
      modules: [Autoplay, Pagination, Navigation],
      swiperInstance: null, // Store Swiper instance reference
      loading: true, // Loading indicator
    };
  },
  mounted() {
    this.fetchAds();
  },
  methods: {
    async fetchAds() {
      try {
        const response = await axios.get('http://localhost:8083/ads/all/complete');
        const data = response.data;

        const fetchedAds = data.map(ad => ({
          title: ad.title,
          photo_url: ad.imageUrl
          // const adtitle = ad.title;
          // const imageUrl = ad.imageUrl;
          // return {
          //   title: adtitle,
          //   photo_url: imageUrl
          // };
        })).reverse();

        this.ads = fetchedAds;
      } catch (error) {
        console.error('Error fetching ads:', error);
      } finally {
        this.loading = false; // Disable loading indicator
      }
    },
    handleSlideClick(ad) {
      console.log(ad);
      // Handle slide click action here
    },
    stopAutoScroll() {
      if (this.swiperInstance && this.swiperInstance.autoplay) {
        this.swiperInstance.autoplay.stop();
      }
    },
    startAutoScroll() {
      if (this.swiperInstance && this.swiperInstance.autoplay) {
        this.swiperInstance.autoplay.start();
      }
    },
    onSwiperInit(swiper) {
      // Store swiper instance when initialized
      this.swiperInstance = swiper;
    },
  },
};
</script>