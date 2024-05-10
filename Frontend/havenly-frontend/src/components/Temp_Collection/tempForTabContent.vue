<template>
    <div class="tempOf-tabContent">
        <div class="container">

            <div class="content-data d-flex mt-1 mb-3">

                <!-- for Desktop view -->

                <v-btn size="large" class="d-none d-md-block content-btn ms-auto mb-3"
                    style="text-transform:capitalize;">See all post of
                    <span class="ms-1 red">condo</span> <v-icon style="margin-left: 8px;font-size: 24px;"
                        class="custom-icon">mdi-chevron-double-right</v-icon>
                </v-btn>

                <v-btn size="small" class="d-block d-sm-none content-btn ms-auto me-5 mb-1"
                    style="text-transform:capitalize;">See all
                    <span class="ms-1 red">{{ get_title }}</span> <v-icon style=" margin-left: 8px;font-size: 24px;"
                        class="custom-icon">mdi-chevron-double-right</v-icon>
                </v-btn>

            </div>

            <!-- Render real data from database-->

            <div class="row mb-5 g-3">
                <div v-for="post in posts" :key="post.post_id" class="col-md-3">
                    <div class="card-container">
                        <!-- TZH card styles -->
                        <div class="card" style="height: 600px;">
                            <!-- <div v-for="url in post.photo_urls" :key="url" class="cardImgBox mb-2">
                                <img :src="url" class="w-100 h-100" alt="Card image cap">
                            </div> -->
                            <div class="cardImgBox mb-2">
                                <img :src="post.photo_url" class="w-100 h-100" alt="Card image cap">
                            </div>
                            <div class="card-body p-3 d-flex flex-column">
                                <h5 class="card-title mb-3">{{ post.title }}</h5>
                                <p class="card-text small opacity-75">{{ post.description }}</p>
                                <p class="card-text text-danger small mb-auto opacity-75 mb-auto ">
                                    <v-icon >mdi-map-marker-radius</v-icon>
                                    {{ post.region }} , {{ post.province }} , {{ post.country }}
                                </p>
                                <div class="d-flex align-items-center justify-content-between mb-2">
                                    <v-rating :model-value="4.5" color="danger" density="compact" size="small"
                                        half-increments readonly>
                                    </v-rating>
                                    <span class="badge text-bg-danger rounded-pill">{{ post.property_type }}</span>
                                </div>
                                <div class="d-flex align-items-center justify-content-between">
                                    <p class="m-0 small">{{ post.area }}</p>
                                    <p class="m-0 small fw-bold fs-6">{{ post.price }}</p>
                                    
                                </div>
                                <v-divider :thickness="2" class="border-opacity-25 d-block"/>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="d-flex">
                                        <v-icon class="text-red">mdi-clock-time-eight-outline</v-icon>
                                        <span class="ms-1 text-grey">12d 8h 56m</span>
                                    </div>
                                    <div class="d-flex">
                                        <v-icon class="text-red">mdi-eye</v-icon>
                                        <span class="ms-1 text-grey" title="People watched times">1331</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>

        </div>
    </div>
</template>

<script>
export default {
    name: 'tempVue',

    props: {
        content: {
            type: Object,
            required: true
        }
    },

    data: () => ({
        posts : [],
        get_title: '',
        animations: ['fade-left', 'zoom-in-up', 'zoom-in-down', 'fade-up', 'fade-down', 'fade-right'],
        animated: false,
    }),

    computed: {

        slides() {
            let slides = [];
            const titleParts = this.content.title.split(' ');
            console.log(this.content.title);
            if (titleParts.length === 3) {
                const firstWord = titleParts[0];
                const secondWord = titleParts[1];
                slides = this.$store.state[firstWord].filter(slide => slide.category === secondWord);
            }
            return this.limitSlides(slides);
        },

        login_status() {
            return this.$store.getters.LoginData
        },


    },

    mounted() {
        this.fetchPosts();
        
        window.addEventListener('beforeunload', this.saveScrollPosition);
        this.restoreScrollPosition();

        // take second word of content
        this.get_title = this.content.title ? this.content.title.split(' ')[1] : '';
    },

    beforeUnmount() {
        window.removeEventListener('beforeunload', this.saveScrollPosition);
    },

    methods: {

        fetchPosts() {
        // Make API call to fetch posts from backend
        fetch('http://localhost:8083/gettestsellpost')
          .then(response => response.json())
          .then(data => {
            console.log(data);
            data.forEach(post => {
                this.posts.push({
                    province: post.locations.province,
                    region: post.locations.region,
                    country: post.locations.countries.country_name,
                    post_id: post.sell_post_id,
                    title: post.title,
                    description: post.description,
                    house_type: post.house_type,
                    property_type: post.property_type,
                    area: post.area,
                    price: post.price,
                    photo_url: 'data:image/jpeg;base64,' + post.image,
                });
            });
            // console.log(this.posts);
          })
          .catch(error => {
            console.error('Error fetching photos:', error);
          });
      },



    // fetchPosts() {
    //     // Make API call to fetch posts from backend
    //     fetch('http://localhost:8083/gettestsellpost')
    //       .then(response => response.json())
    //       .then(data => {
    //         console.log(data);
    //         data.forEach(post => {
    //             let images = post.image.split(';');
    //             let photo_urls = images.map(image => 'data:image/jpeg;base64,' + image); 
    //             this.posts.push({
    //                 province: post.locations.province,
    //                 region: post.locations.region,
    //                 country: post.locations.countries.country_name,
    //                 post_id: post.sell_post_id,
    //                 title: post.title,
    //                 description: post.description,
    //                 house_type: post.house_type,
    //                 property_type: post.property_type,
    //                 area: post.area,
    //                 price: post.price,
    //                 photo_url: photo_urls,
    //             });
    //         });
    //         // console.log(this.posts);
    //       })
    //       .catch(error => {
    //         console.error('Error fetching photos:', error);
    //       });
    //   },



        // Method to limit the number of slides based on the viewport size
        limitSlides(slides) {
            const maxSlides = window.innerWidth < 768 ? 4 : 8; // 768px is the breakpoint for mobile view
            return slides.slice(0, maxSlides);
        },

        saveScrollPosition() {
            sessionStorage.setItem('scrollPosition', window.scrollY);
        },

        restoreScrollPosition() {
            const scrollPosition = sessionStorage.getItem('scrollPosition');
            if (scrollPosition) {
                window.scrollTo(0, parseInt(scrollPosition));
            }
        },

        // Shuffle the animations array
        shuffleAnimations() {
            // Delay for 2 seconds before shuffling
            setTimeout(() => {
                for (let i = this.animations.length - 1; i > 0; i--) {
                    const j = Math.floor(Math.random() * (i + 1));
                    [this.animations[i], this.animations[j]] = [this.animations[j], this.animations[i]];
                }
                this.animated = !this.animated; // Toggle animated to refresh the animations
            }, 0);
        },
    },

    watch: {
        'content.title': {
            handler(newTitle, oldTitle) {
                // Check if the title has changed and is not empty
                if (newTitle && newTitle !== oldTitle) {

                    // to change text
                    this.get_title = newTitle.split(' ')[2];

                    // to shuffle the animation
                    this.shuffleAnimations();
                }
            },
            immediate: true, // Trigger the handler immediately on component mount
        },
    },
};
</script>