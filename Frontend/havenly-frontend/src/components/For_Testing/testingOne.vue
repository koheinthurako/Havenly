<template>
  <div class="testing-one-data">
    <div style="margin-top: 7%;"></div>
    <swiper :keyboard="true" :loop="true" :effect="'coverflow'" :grabCursor="true" :centeredSlides="true"
      :slidesPerView="'auto'" :navigation="true" :mousewheel="true" :coverflowEffect="{
        rotate: 30,
        stretch: 20,
        depth: 150,
        modifier: 1,
        slideShadows: true,
      }" :pagination="{
        clickable: true,
      }" :modules="modules" class="mySwiper">
      <swiper-slide>
        <img src="https://swiperjs.com/demos/images/nature-1.jpg" />
      </swiper-slide>
      <swiper-slide>
        <img src="https://swiperjs.com/demos/images/nature-2.jpg" />
      </swiper-slide>
      <swiper-slide>
        <img src="https://swiperjs.com/demos/images/nature-3.jpg" />
      </swiper-slide>
      <swiper-slide>
        <img src="https://swiperjs.com/demos/images/nature-4.jpg" />
      </swiper-slide>
      <swiper-slide>
        <img src="https://swiperjs.com/demos/images/nature-5.jpg" />
      </swiper-slide><swiper-slide>
        <img src="https://swiperjs.com/demos/images/nature-6.jpg" />
      </swiper-slide><swiper-slide>
        <img src="https://swiperjs.com/demos/images/nature-7.jpg" />
      </swiper-slide><swiper-slide>
        <img src="https://swiperjs.com/demos/images/nature-8.jpg" />
      </swiper-slide><swiper-slide>
        <img src="https://swiperjs.com/demos/images/nature-9.jpg" />
      </swiper-slide>
    </swiper>
  </div>
</template>
<script>
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from 'swiper/vue';

// Import Swiper styles
import 'swiper/css';

import 'swiper/css/effect-coverflow';
import 'swiper/css/pagination';


// import required modules
import { EffectCoverflow, Pagination, Mousewheel, Keyboard } from 'swiper/modules';

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    return {
      modules: [EffectCoverflow, Pagination, Mousewheel, Keyboard],
    };
  },
};
</script>


<style scope>
.testing-one-data {
  width: 100%;
  height: auto;

  .swiper {
    width: 100%;
    padding-top: 50px;
    padding-bottom: 50px;
  }

  .swiper-slide {
    background-position: center;
    background-size: cover;
    width: 300px;
    height: 500px;
  }

  .swiper-slide img {
    display: block;
    width: 100%;
    border-radius: 10px;
    width: 100%;
    height: 100%;
  }

}
</style>