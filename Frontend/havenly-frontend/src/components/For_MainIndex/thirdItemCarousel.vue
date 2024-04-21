<!-- <template>
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

              <v-btn color="red">Check Details</v-btn>

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
      if (this.visibleStartIndex > 0) {
        this.visibleStartIndex--;
        this.offset += this.calculateButtonWidth();
        localStorage.setItem('visibleStartIndex', this.visibleStartIndex);
      }
    },
    openTab(index) {
      this.activeTab = index;
      localStorage.setItem('activeTab', index); // Save activeTab to local storage
    },
    calculateButtonWidth() {
      // Calculate and return the width of each button
      // You need to implement this according to your styling and button dimensions
    },
    startAutoScroll() {
      this.autoScrollInterval = setInterval(this.autoScroll, 3000); // Adjust the interval as needed
    },
    stopAutoScroll() {
      clearInterval(this.autoScrollInterval);
    },
    autoScroll() {
      // Auto-scroll to next set of buttons
      if (this.visibleStartIndex + this.visibleContent < this.buttons.length) {
        this.next();
      } else {
        // Reset to the beginning if reached the end
        this.visibleStartIndex = 0;
        this.offset = 0;
        localStorage.setItem('visibleStartIndex', this.visibleStartIndex);
      }
    },
    updateVisibleButtons() {
      if (window.innerWidth <= 600) {
        this.visibleContent = 1;
      } else {
        this.visibleContent = 4;
      }
    }
  }
};
</script> --> -->


  <!-- <template>
    <div class="third-carousel">
      <div>
        <div class="third-carousel-header mb-3">
          <h2>Top Commercial Ads.</h2>
        </div>
        <div @mouseenter="stopAutoScroll" @mouseleave="startAutoScroll">
          <div class="tab-carousel-content my-2 d-flex" :style="{ transform: `translateX(${offset}px)` }">
            <v-card @click="openTab(visibleStartIndex + index)"
              :class="{ 'tablinks': true, 'active': activeTab === visibleStartIndex + index }"
              v-for="(button, index) in buttons.slice(visibleStartIndex, visibleStartIndex + visibleContent)" :key="index"
              class="p-0 mx-2">
              <v-img class="align-end text-white" src="https://cdn.vuetifyjs.com/images/cards/docks.jpg" cover>
                <v-card-title>{{ button }}</v-card-title>
              </v-img>
    
              <v-card-subtitle class="pt-4">
                Number 10
              </v-card-subtitle>
    
              <v-card-text>
                <div>Whitehaven Beach</div>
                <div>Whitsunday Island, Whitsunday Islands</div>
              </v-card-text>
    
              <v-card-actions>
                <v-btn color="orange" text="Share"></v-btn>
                <v-btn color="orange" text="Explore"></v-btn>
              </v-card-actions>
            </v-card>
          </div>
    
          <div class="pointer-button d-none d-sm-block">
            <div class="d-flex">
              <div class="prev my-auto me-auto d-none" @click="prev" :disabled="isPrevButtonHidden">
                <v-icon>mdi-chevron-left</v-icon>
              </div>
              <div class="next my-auto ms-auto d-none" @click="next" :disabled="isNextButtonHidden">
                <v-icon>mdi-chevron-right</v-icon>
              </div>
            </div>
          </div>
    
        </div>
      </div>
    </div>
  </template>

  <script>
  export default {
    data() {
      return {
        buttons: ['Yangon', 'Mandaly', 'Nay pyi taw', 'Shan', 'Pyin Oo Lwin', 'Shan', 'Mon'],
        offset: 0,
        visibleStartIndex: 0,
        visibleContent: 4,
        activeTab: 0,
        autoScrollInterval: null
      };
    },
    computed: {
      isPrevButtonHidden() {
        return this.visibleStartIndex === 0;
      },
      isNextButtonHidden() {
        return this.visibleStartIndex + this.visibleContent >= this.buttons.length;
      }
    },
    mounted() {
      // Start auto-scrolling
      this.startAutoScroll();
    },
    beforeUnmount() {
      // Stop auto-scrolling
      this.stopAutoScroll();
    },
    methods: {
      next() {
        if (this.visibleStartIndex + this.visibleContent < this.buttons.length) {
          this.visibleStartIndex++;
          
        }
      },
      prev() {
        if (this.visibleStartIndex > 0) {
          this.visibleStartIndex--;
          
        }
      },
      openTab(index) {
        this.activeTab = index;
      },
    
      startAutoScroll() {
        this.autoScrollInterval = setInterval(this.autoScroll, 3000); // Adjust the interval as needed
      },
      stopAutoScroll() {
        clearInterval(this.autoScrollInterval);
      },
      autoScroll() {
        // Auto-scroll to next set of buttons
        if (this.visibleStartIndex + this.visibleContent < this.buttons.length) {
          this.next();
        } else {
          // Reset to the beginning if reached the end
          this.visibleStartIndex = 0;
          this.offset = 0;
        }
      },
      updateVisibleButtons() {
        if (window.innerWidth <= 600) {
          this.visibleContent = 1;
        } else {
          this.visibleContent = 4;
        }
      }
    }
  };
  </script> -->




  <template>
    <div class="third-item-carousel">
        <div class="container">
            <h2>I don't know what is that Exactly!</h2>
        </div>
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
                                <v-img :src="c1" height="100%"
                                    cover></v-img>
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
                                    <v-row align="center" class="mx-0">
                                        <v-rating :model-value="4.5" color="amber" density="compact" size="small"
                                            half-increments readonly></v-rating>

                                        <div class="text-grey ms-4">
                                            4.5 (413)
                                        </div>
                                    </v-row>

                                    <div class="my-4 text-subtitle-1">
                                        Bangkok Imue Noy
                                    </div>

                                    <div>FLO By Sansiri project, the first project in the form of a river curve view that is exclusive, the only one from Sansiri.</div>
                                </v-card-text>

                                <v-divider class="mx-4 mb-1"></v-divider>

                                <v-card-title>Tonight's availability</v-card-title>

                                <div class="px-4 mb-2">
                                    <v-chip-group v-model="selection" selected-class="bg-deep-purple-lighten-2">
                                        <v-chip>5:30PM</v-chip>

                                        <v-chip>7:30PM</v-chip>

                                        <v-chip>8:00PM</v-chip>

                                        <v-chip>9:00PM</v-chip>
                                    </v-chip-group>
                                </div>

                                <v-card-actions>
                                    <v-btn color="deep-purple-lighten-2" text="Reserve" block border
                                        @click="reserve"></v-btn>
                                </v-card-actions>
                            </div>
                        </div>

                    </v-card>

                </v-slide-group-item>
            </v-slide-group>
        </v-sheet>

    </div>
</template>



<script>
export default {
    // Your script goes here

    data: ()=> ({
        c1: require('@/assets/img/c7.jpg'),
    }),

}
</script>