<!-- <template>
  <div style="margin-top: 200px;"></div>
  <swiper :loop="true" :slidesPerView="3" :autoplay="{
    delay: 2500,
    disableOnInteraction: false,
  }" :spaceBetween="30" :navigation="true" :pagination="{
    clickable: true,
  }" :modules="modules" class="mySwiper">
    <swiper-slide>Slide 1</swiper-slide>
    <swiper-slide>Slide 2</swiper-slide><swiper-slide>Slide 3</swiper-slide>
    <swiper-slide>Slide 4</swiper-slide><swiper-slide>Slide 5</swiper-slide>
    <swiper-slide>Slide 6</swiper-slide><swiper-slide>Slide 7</swiper-slide>
    <swiper-slide>Slide 8</swiper-slide><swiper-slide>Slide 9</swiper-slide>
  </swiper>
</template>
<script>
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from 'swiper/vue';

// Import Swiper styles
import 'swiper/css';

import 'swiper/css/pagination';

// import required modules
import { Autoplay, Pagination, Navigation } from 'swiper/modules';

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    return {
      modules: [Autoplay, Pagination, Navigation],
    };
  },
};
</script> -->


<!-- <template>
  <div class="custom-swiper-container" style="margin-top: 200px;">
    <swiper :loop="true" :slidesPerView="3" :autoplay="{
      delay: 2500,
      disableOnInteraction: false
    }" :spaceBetween="30" :navigation="true" :modules="modules" class="mySwiper">
      <swiper-slide v-for="(data, index) in getData" :key="index">
        <v-img :src="data.img" class="card-img" style="cursor:pointer;"></v-img>
      </swiper-slide>
    </swiper>
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

      getData: [
        { img: require('@/assets/img/1.jpg') },
        { img: require('@/assets/img/2.jpg') },
        { img: require('@/assets/img/3.jpg') },
        { img: require('@/assets/img/4.jpg') },
        { img: require('@/assets/img/5.jpg') },
        { img: require('@/assets/img/6.jpg') },
        { img: require('@/assets/img/7.jpg') },
        { img: require('@/assets/img/8.jpg') },
        { img: require('@/assets/img/9.jpg') },
        { img: require('@/assets/img/10.jpg') },
      ]
    };
  },
  mounted() {
    this.fetchPosts();
  },
  methods: {
    async fetchPosts() {
      this.loading = true;
      try {
        const response = await axios.get('http://localhost:8083/posts/allComplete');
        const data = response.data;

        const fetchedPosts = data.map(post => {
          const mainId = post.post_id;
          const postDetails = post.rentpost || post.sellpost;
          const description = postDetails.description.length > 60
            ? postDetails.description.substring(0, 60) + "..."
            : postDetails.description;
          const imageUrls = Array.isArray(postDetails.image) ? postDetails.image : [postDetails.image];

          return {
            province: postDetails.locations.province,
            region: postDetails.locations.region,
            country: postDetails.locations.countries.country_name,
            post_id: mainId,
            title: postDetails.title,
            description: description,
            property_type: postDetails.property_type,
            area: postDetails.area,
            price: postDetails.price,
            deposit: postDetails.deposit || null,
            least_contract: postDetails.least_contract || null,
            photo_url: imageUrls,
          };
        });

        this.posts = fetchedPosts;


      } catch (error) {
        console.error('Error fetching photos:', error);
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style lang="scss">
.custom-swiper-container {
  width: 100%;
  height: auto;
  background-color: #f5f5f5;
  padding: 20px;

  .swiper-slide {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    padding: 15px;

    .card-img {
      width: 100%;
      height: 200px;
      object-fit: cover;
      border-radius: 10px;
      margin-bottom: 10px;
    }

    .slide-content {
      text-align: center;

      h3 {
        font-size: 1.5em;
        margin-bottom: 10px;
      }

      p {
        font-size: 1em;
        color: #666;
      }
    }
  }
}
</style> -->


<!-- <template>
  <div class="custom-swiper-container" style="margin-top: 200px;">
    <swiper :loop="posts.length > 1" :breakpoints="{
      '640': {
        slidesPerView: 1,
        spaceBetween: 10,
      },
      '768': {
        slidesPerView: 4,
        spaceBetween: 40,
      },
      '1024': {
        slidesPerView: 4,
        spaceBetween: 30,
      },
    }" :autoplay="{ delay: 2500, disableOnInteraction: true }" :spaceBetween="30" :navigation="true" :modules="modules"
      class="mySwiper">
      <swiper-slide v-for="(post, index) in posts" :key="index" @click="handleSlideClick(post.title)">
        <v-img :src="post.photo_url" class="card-img" style="cursor:pointer;"></v-img>
      </swiper-slide>
    </swiper>
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
      }

      console.log(this.posts);
    },
    handleSlideClick(post) {
      alert(post);
      // Handle slide click action here
    },
  },
};
</script>
<style lang="scss">
.custom-swiper-container {
  width: 100%;
  height: auto;
  background-color: #f5f5f5;
  padding: 20px;

  .swiper-slide {
    background-color: transparent;
    border-radius: 10px;

    .card-img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 10px;
      margin-bottom: 10px;
    }
  }
}
</style> -->

<template>
  <div class="custom-swiper-container" style="margin-top: 200px;">
    <swiper :loop="posts.length > 1" :breakpoints="{
      '640': {
        slidesPerView: 1,
        spaceBetween: 10,
      },
      '768': {
        slidesPerView: 3,
        spaceBetween: 40,
      },
      '1024': {
        slidesPerView: 4,
        spaceBetween: 25,
      },
    }" :autoplay="{ delay: 2500, disableOnInteraction: true }" :spaceBetween="30" :navigation="true"
      :modules="modules" class="mySwiper" @swiper="onSwiperInit">
      <swiper-slide v-for="(post, index) in posts" :key="index" @click="handleSlideClick(post.title)">
        <v-img :src="post.photo_url" class="card-img" style="cursor:pointer;" @mouseenter="stopAutoScroll"
          @mouseleave="startAutoScroll"></v-img>
      </swiper-slide>
    </swiper>
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

<style lang="scss">
.custom-swiper-container {
  width: 100%;
  height: auto;
  background-color: #f5f5f5;
  padding: 20px;

  .swiper-slide {
    background-color: transparent;
    border-radius: 10px;

    .card-img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 10px;
      margin-bottom: 10px;
    }
  }
}
</style>
