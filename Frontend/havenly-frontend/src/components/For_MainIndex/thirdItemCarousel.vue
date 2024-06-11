<template>
  <div class="third-carousel-page">

    <div class="">

      <h2 class="p-0 ms-3 mt-4" style="color: #e86f52; margin-bottom: -20px;">Hello world</h2>

      <div class="third-carousel">
        <div class="inner" ref="inner" :style="innerStyles">
          <v-img @mouseenter="stopAutoScroll" @mouseleave="startAutoScroll" v-for="(data, index) in posts" :key="index"
            :src="data.photo_url[0]" class="card-img" style="cursor:pointer;">


            <!-- <div class="split left-part">

              <div>
                <h2>Explore homes on Havenly.</h2>
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

            </div> -->
            <!-- <div class="split right-part">
              hello world
            </div> -->



          </v-img>

          <!-- <v-btn icon @click="prev" @mouseenter="stopAutoScroll" @mouseleave="startAutoScroll"
            style="position: absolute; top: 50%; left: 0; transform: translateY(-50%); z-index: 1000;">
            <v-icon>mdi-chevron-left</v-icon>
          </v-btn>
          <v-btn icon @click="next" @mouseenter="stopAutoScroll" @mouseleave="startAutoScroll"
            style="position: absolute; top: 50%; right: 0; transform: translateY(-50%); z-index: 1000;">
            <v-icon>mdi-chevron-right</v-icon>
          </v-btn> -->

        </div>
      </div>
      <!-- <v-sheet class="mx-auto" elevation="6">
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
      <v-row align="center" class="mx-0">
        <v-rating :model-value="4.5" color="amber" density="compact" size="small" half-increments readonly></v-rating>

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
    <v-btn color="deep-purple-lighten-2" text="Reserve" block border @click="reserve"></v-btn>
  </v-card-actions>
</div>

</v-card>
</v-slide-group-item>
</v-slide-group>
</v-sheet> -->
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      posts: [],
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
    // this.setStep();
    // this.resetTranslate();
    // this.startAutoScroll();

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
        this.$nextTick(() => {
          this.setStep();  // Reinitialize step after fetching posts
          this.resetTranslate();  // Reset translation after fetching posts
          this.startAutoScroll(); // Start auto-scroll after initialization
        });

      } catch (error) {
        console.error('Error fetching photos:', error);
      } finally {
        this.loading = false;
      }
    },

    setStep() {
      this.$nextTick(() => {
        const innerWidth = this.$refs.inner ? this.$refs.inner.offsetWidth : 0;
        const totalCards = this.posts.length;
        this.step = totalCards > 0 ? `${innerWidth / totalCards}px` : '0px';
      });
    },

    // setStep() {
    //   const innerWidth = this.$refs.inner.offsetWidth;
    //   const totalCards = this.cards.length;
    //   this.step = `${innerWidth / totalCards}px`;
    // },

    next() {
      if (this.transitioning) return;
      this.transitioning = true;
      this.moveLeft();
      this.afterTransition(() => {
        const card = this.posts.shift();
        this.posts.push(card);
        this.resetTranslate();
        this.transitioning = false;
      });
    },

    prev() {
      if (this.transitioning) return;
      this.transitioning = true;
      this.moveRight();
      this.afterTransition(() => {
        const card = this.posts.pop();
        this.posts.unshift(card);
        this.resetTranslate();
        this.transitioning = false;
      });
    },

    moveLeft() {
      this.innerStyles = {
        transition: 'transform 0.5s ease',
        transform: `translateX(-${this.step})`
      };
    },

    moveRight() {
      this.innerStyles = {
        transition: 'transform 0.5s ease',
        transform: `translateX(${this.step})`
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
        transform: `translateX(0px)`
      };
    },
    startAutoScroll() {
      this.autoScrollInterval = setInterval(() => {
        this.next();
      }, this.autoScrollSpeed);
    },


    stopAutoScroll() {
      if (this.autoScrollInterval) {
        clearInterval(this.autoScrollInterval);
        this.autoScrollInterval = null;
      }
    }
  }
};
</script>