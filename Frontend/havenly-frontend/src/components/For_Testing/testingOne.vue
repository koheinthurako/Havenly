<template>
  <div class="testing-edit">
    <swiper :style="{
      '--swiper-navigation-color': '#fff',
      '--swiper-pagination-color': '#fff',
    }" :spaceBetween="10" :autoplay="{
      delay: 2500,
      disableOnInteraction: false,
    }" :navigation="true" :loop="true" :thumbs="{ swiper: thumbsSwiper }" :modules="modules" class="mySwiper2">
      <swiper-slide v-for="(img, index) in images" :key="index">
        <v-img :src="img.img" />
      </swiper-slide>
    </swiper>
    <swiper @swiper="setThumbsSwiper" :spaceBetween="10" :slidesPerView="4" :freeMode="true" :watchSlidesProgress="true"
      :modules="modules" class="mySwiper">
      <swiper-slide v-for="(img, index) in images" :key="index">
        <v-img :src="img.img" />
      </swiper-slide>
    </swiper>
  </div>
</template>
<script>
import { ref } from 'vue';
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from 'swiper/vue';

// Import Swiper styles
import 'swiper/css';

import 'swiper/css/free-mode';
import 'swiper/css/navigation';
import 'swiper/css/thumbs';

// import required modules
import { Autoplay, FreeMode, Navigation, Thumbs } from 'swiper/modules';

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    const thumbsSwiper = ref(null);

    const setThumbsSwiper = (swiper) => {
      thumbsSwiper.value = swiper;
    };

    return {
      thumbsSwiper,
      setThumbsSwiper,
      modules: [Autoplay, FreeMode, Navigation, Thumbs],
    };
  },

  data: () => ({
    images: [
      { img: require('@/assets/img/1.jpg') },
      { img: require('@/assets/img/3.jpg') },
      { img: require('@/assets/img/5.jpg') },
      { img: require('@/assets/img/2.jpg') },
      { img: require('@/assets/img/4.jpg') },
      { img: require('@/assets/img/6.jpg') },
    ],
  })
};
</script>
