<template>
    <div class="third-carousel-page">
  
      <div class="">
        <div class="third-carousel" @mouseenter="stopAutoScroll" @mouseleave="startAutoScroll">
          <div class="inner" ref="inner" :style="innerStyles">

         
            <v-img v-for="(data, index) in cards" :key="index" :src="data.imageUrl || ''" class=" card-img" height="250px" width="400px" contain>
              
            </v-img>
           
           <!-- <v-img v-for="(data, index) in cards" :key="index" :src="data.url" class=" card-img"> -->
              
              <!-- <div class="split left-part">
                <div>
                  <h2>Explore homes on Havenly.</h2>
                <div>
                    <span class="me-1">Local Favorite</span>
                    <v-icon color="error" icon="mdi-fire-circle" size="small"></v-icon>
              </div>

                </div>
                <v-row :align="center" class="mx-0">
                  <v-rating :model-value="4.5" color="amber" density="compact" size="small" half-increments
                    readonly></v-rating>
                  <div class="text-grey ms-4">
                    4.5 (413)
                  </div>
                </v-row>
                <div class="my-3"></div>
                <br>
              </div> -->
              <!-- <div class="split right-part">
                hello world
              </div>-->
            <!-- </v-img> -->
          </div>
        </div>

<!--         
        <v-sheet class="mx-auto" elevation="6">
            <v-slide-group v-model="model" selected-class="bg-success" show-arrows>
                <v-slide-group-item v-for="n in 15" :key="n" v-slot="{ isSelected, toggle }">
                <v-card @click="toggle" elevation="20" rounded :color="isSelected ? 'primary' : undefined"
                        :disabled="loading" :loading="loading" class="mx-3 mt-3">
                        <template v-slot:loader="{ isActive }">
                            <v-progress-linear :active="isActive" color="deep-purple" height="4"
                                indeterminate></v-progress-linear>
                        </template>

<div class="row">
  <div class="col-6">
    <v-img :src="c1" height="100%" cover></v-img>
  </div>
  <div class="col-6">
    <v-card-item>
      <v-card-title>Lat Phrao Condo</v-card-title>

      <v-card-subtitle>
        <span class="me-1">Local Favorite</span>

        <v-icon color="error" icon="mdi-fire-circle" size="small"></v-icon>
      </v-card-subtitle>
    </v-card-item>

                                <v-card-text>
                                    <v-row :align="center" class="mx-0">
                                        <v-rating :model-value="4.5" color="amber" density="compact" size="small"
                                            half-increments readonly></v-rating>

        <div class="text-grey ms-4">
          4.5 (413)
        </div>
      </v-row>
    </v-card-text>
    <div class="my-4 text-subtitle-1">
      Bangkok Imue Noy
    </div>

    <br>

    <v-btn elevation="20" color="#E97559" style="border-radius: 30px;">Check Details</v-btn>

  </div>
  <div class="split right-part">
    hello world
  </div>

                                <v-card-actions>
                                    <v-btn color="deep-purple-lighten-2" text="Reserve" block :border="true"
                                        @click="reserve"></v-btn>
                                </v-card-actions>
                            </div>
                       
                    </v-card>
                </v-slide-group-item>
            </v-slide-group>
        </v-sheet> -->
    </div></div>
  </template>
  
  <script>
  import axios from 'axios';

  export default {
    data() {
      return {
        cards: [
          // { url: require('@/assets/img/1.jpg') },
          // { url: require('@/assets/img/2.jpg') },
          // { url: require('@/assets/img/3.jpg') },
          // { url: require('@/assets/img/4.jpg') },
          // { url: require('@/assets/img/5.jpg') },
          // { url: require('@/assets/img/6.jpg') },
          // { url: require('@/assets/img/7.jpg') },
          // { url: require('@/assets/img/8.jpg') },
          // { url: require('@/assets/img/9.jpg') },
          // { url: require('@/assets/img/10.jpg') },
  
        ],
        innerStyles: {},
        step: '',
        transitioning: false,
        autoScrollInterval: null,
        autoScrollSpeed: 3000, // Adjust as needed
      };
    },
  
    mounted() {
      this.fetchAllAds();
      this.setStep();
      this.resetTranslate();
      this.startAutoScroll();
    },
  
    methods: {
      fetchAllAds(){
      axios.get('http://localhost:8083/ads/all/complete')
        .then(response => {
          if (response.data !== null) {
            this.cards = response.data.reverse();            
            console.log("Ads", response.data);
          }
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
        },

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