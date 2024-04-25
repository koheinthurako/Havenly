<template>
  <div class="third-carousel-page">

    <div class="">
      <div class="third-carousel" @mouseenter="stopAutoScroll" @mouseleave="startAutoScroll">
        <div class="inner" ref="inner" :style="innerStyles">
          <v-img v-for="(data, index) in cards" :key="index" :src="data.url" class=" card-img">

            <div class="split left-part">

              <div>
                <h3>Cafe Badilico</h3>

                <div>
                  <span class="me-1">Local Favorite</span>

                  <v-icon color="error" icon="mdi-fire-circle" size="small"></v-icon>
                </div>
              </div>


              <v-row align="center" class="mx-0">
                <v-rating :model-value="4.5" color="amber" density="compact" size="small" half-increments
                  readonly></v-rating>

                <div class="text-grey ms-4">
                  4.5 (413)
                </div>
              </v-row>
              <div class="my-3"></div>
              <div>Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.
              </div>

              <br>

              <v-btn elevation="20" color="#E97559" style="border-radius: 30px;">Check Details</v-btn>

            </div>
            <div class="split right-part">
              hello world
            </div>



          </v-img>
        </div>
      </div>
    </div>

    <v-btn @click="prev" class="next"><v-icon>mdi-chevron-left</v-icon></v-btn>
    <v-btn @click="next" class="prev"><v-icon>mdi-chevron-right</v-icon></v-btn>

  </div>
</template>

<script>
export default {
  data() {
    return {
      cards: [
        { url: require('@/assets/img/1.jpg') },
        { url: require('@/assets/img/2.jpg') },
        { url: require('@/assets/img/3.jpg') },
        { url: require('@/assets/img/4.jpg') },
        { url: require('@/assets/img/5.jpg') },
        { url: require('@/assets/img/6.jpg') },
        { url: require('@/assets/img/7.jpg') },
        { url: require('@/assets/img/8.jpg') },
        { url: require('@/assets/img/9.jpg') },
        { url: require('@/assets/img/10.jpg') },

      ],
      innerStyles: {},
      step: '',
      transitioning: false,
      autoScrollInterval: null,
      autoScrollSpeed: 3000, // Adjust as needed
    };
  },

  mounted() {
    this.setStep();
    this.resetTranslate();
    this.startAutoScroll();
  },

  methods: {
    setStep() {
      const innerWidth = this.$refs.inner.offsetWidth;
      const totalCards = this.cards.length;
      this.step = `${innerWidth / totalCards}px`;
    },

    next() {
      if (this.transitioning) return;
      this.transitioning = true;
      this.moveLeft();
      this.afterTransition(() => {
        const card = this.cards.shift();
        this.cards.push(card);
        this.resetTranslate();
        this.transitioning = false;
      });
    },

    prev() {
      if (this.transitioning) return;
      this.transitioning = true;
      this.moveRight();
      this.afterTransition(() => {
        const card = this.cards.pop();
        this.cards.unshift(card);
        this.resetTranslate();
        this.transitioning = false;
      });
    },

    moveLeft() {
      this.innerStyles = {
        transform: `translateX(-${this.step}) translateX(-${this.step})`
      };
    },

    moveRight() {
      this.innerStyles = {
        transform: `translateX(${this.step}) translateX(-${this.step})`
      };
    },

    afterTransition(callback) {
      const innerRef = this.$refs.inner;
      if (!innerRef) return; // Check if innerRef is null
      const listener = () => {
        callback();
        innerRef.removeEventListener('transitionend', listener);
      };
      innerRef.addEventListener('transitionend', listener);
    },

    resetTranslate() {
      this.innerStyles = {
        transition: 'none',
        transform: `translateX(-${this.step})`
      };
    },

    startAutoScroll() {
      this.autoScrollInterval = setInterval(() => {
        this.next();
      }, this.autoScrollSpeed);
    },

    stopAutoScroll() {
      clearInterval(this.autoScrollInterval);
    }
  }
};
</script>
