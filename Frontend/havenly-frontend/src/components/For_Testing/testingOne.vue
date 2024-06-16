<template>

  <h1>Hello world</h1>

</template>

<script>
import { ref, onMounted } from 'vue';
import Swiper from 'swiper/bundle';
import 'swiper/css/bundle';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';

export default {
  name: 'SwiperDemo',

  setup() {
    const isCross = ref(false);
    const isMenuActive = ref(false);
    const swiperInstance = ref(null);

    const openMenu = () => {
      if (swiperInstance.value) {
        swiperInstance.value.slidePrev();
        isCross.value = true;
        isMenuActive.value = true;
        // Optionally disable scrolling on body when menu is open
        document.body.style.overflowY = 'hidden';
      }
    };

    const closeMenu = () => {
      if (swiperInstance.value) {
        swiperInstance.value.slideNext();
        isCross.value = false;
        isMenuActive.value = false;
        // Enable scrolling on body when menu is closed
        document.body.style.overflowY = 'auto';
      }
    };

    const toggleMenu = () => {
      if (swiperInstance.value) {
        if (swiperInstance.value.activeIndex === 1) {
          openMenu();
        } else {
          closeMenu();
        }
      }
    };

    const onClickOutside = () => {
      closeMenu();
    };

    const Home = (data) => {
      console.log(data);
    };

    onMounted(() => {
      swiperInstance.value = new Swiper('.custom-swiper', {
        slidesPerView: 'auto',
        initialSlide: 1,
        resistanceRatio: 0,
        slideToClickedSlide: false,
        on: {
          slideChangeTransitionStart() {
            if (swiperInstance.value && swiperInstance.value.activeIndex === 0) {
              isCross.value = true;
              isMenuActive.value = true;
              document.body.style.overflowY = 'hidden'; // Optionally disable scrolling on body when menu is open
            } else {
              isCross.value = false;
              isMenuActive.value = false;
              document.body.style.overflowY = 'auto'; // Enable scrolling on body when menu is closed
            }
          }
        }
      });
    });

    return {
      isCross,
      isMenuActive,
      toggleMenu,
      onClickOutside,
      Home
    };
  },
};
</script>
