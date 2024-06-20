<template>
  <div class="third-carousel-page">
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
      <swiper v-else :loop="posts.length > 1" :breakpoints="{
        '640': {
          slidesPerView: 2,
          spaceBetween: 20,
        },
        '768': {
          slidesPerView: 3,
          spaceBetween: 30,
        },
        '1024': {
          slidesPerView: 4,
          spaceBetween: 30,
        },
      }" :autoplay="{ delay: 2500, disableOnInteraction: true }" :navigation="true" :modules="modules" class="mySwiper"
        @swiper="onSwiperInit">
        <swiper-slide v-for="(post, index) in posts" :key="index" @click="handleSlideClick(post.title)">
          <v-img :src="post.photo_url" class="card-img" style="cursor:pointer;" @mouseenter="stopAutoScroll"
            @mouseleave="startAutoScroll"></v-img>
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
      posts: [],
      modules: [Autoplay, Pagination, Navigation],
      swiperInstance: null, // Store Swiper instance reference
      loading: true, // Loading indicator
    };
  },
  mounted() {
    this.fetchPosts();
  },
  methods: {
    async fetchPosts() {
      try {
        const response = await axios.get('http://localhost:8083/posts/allComplete');
        const data = response.data;

        const fetchedPosts = data.map(post => {
          const postDetails = post.rentpost || post.sellpost;
          const description = postDetails.description.length > 60
            ? postDetails.description.substring(0, 60) + "..."
            : postDetails.description;
          const imageUrls = Array.isArray(postDetails.image) ? postDetails.image : [postDetails.image];

          return {
            title: postDetails.title,
            description: description,
            photo_url: imageUrls[0], // Only take the first image
          };
        });

        this.posts = fetchedPosts;
      } catch (error) {
        console.error('Error fetching posts:', error);
      } finally {
        this.loading = false; // Disable loading indicator
      }
    },
    handleSlideClick(post) {
      alert(post);
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