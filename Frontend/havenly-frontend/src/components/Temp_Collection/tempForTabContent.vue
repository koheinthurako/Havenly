<template>
    <div class="tempOf-tabContent">
        <div class="container">

            <div class="content-data d-flex mt-1 mb-3">

                <!-- for Desktop view -->

                <v-btn size="large" :elevation="20" class="d-none d-md-block content-btn ms-auto mb-2">See all post of <span
                        class="red">{{ get_title }}</span> <v-icon
                        style="margin-left: 8px;font-size: 24px; transform: rotate(90deg);">mdi-triangle</v-icon>
                </v-btn>

                <v-btn size="small" :elevation="20" class="d-block d-sm-none content-btn ms-auto me-5 mb-1">See all <span
                    class="red">{{ get_title }}</span> <v-icon
                    style="margin-left: 8px;font-size: 24px; transform: rotate(90deg);">mdi-triangle</v-icon>
            </v-btn>




            </div>

            <div class="row mb-1">
                <div v-for="(slide, index) in slides" :style="{ animationDelay: `${index * 0.2}s` }" :key="index"
                    class="col-md-3">

                    <div class="card-container">

                        <routerlink to="" class="card mb-4"
                            :class="[animations[index % animations.length], { 'fade-up': animated }]">
                            <div class="image-container mb-2">
                                <img :src="slide.image" class="card-img-top" alt="Card image cap">
                            </div>
                            <div class="card-body p-0">
                                <div class="text-row">
                                    <h4 class="text-center">{{ slide.title }}</h4>
                                    <p class="text-center">hello world</p>
                                </div>
                                <div class="btn-row">
                                    <router-link to="" class="v-btn"
                                        :elevation="20"><v-icon>mdi-home-floor-a</v-icon>Item a</router-link>
                                    <router-link to="" class="v-btn"><v-icon>mdi-alpha-b-box</v-icon>Item
                                        b</router-link>
                                </div>
                                <div class="btn-row">
                                    <router-link to="" class="v-btn"><v-icon>mdi-thumb-up</v-icon>Item c</router-link>
                                    <router-link to="" class="v-btn"><v-icon>mdi-emoticon-cool-outline</v-icon>Item
                                        d</router-link>
                                </div>
                                <div class="footer-row">
                                    <v-btn color="red" variant="outlined">Add to Cart
                                        <v-icon>mdi-chevron-double-right</v-icon></v-btn>
                                </div>
                            </div>
                        </routerlink>

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
        get_title: '',
        animations: ['fade-left', 'zoom-in-up', 'zoom-in-down', 'fade-up', 'fade-down', 'fade-right'],
        animated: false,
    }),

    computed: {

        slides() {
            let slides = [];
            const titleParts = this.content.title.split(' ');
            if (titleParts.length === 3) {
                const firstWord = titleParts[0];
                const secondWord = titleParts[1];
                slides = this.$store.state[firstWord].filter(slide => slide.category === secondWord);
            }
            return this.limitSlides(slides);
        },

        darkMode() {
            return this.$store.getters.darkMode;
        }
    },

    mounted() {
        window.addEventListener('beforeunload', this.saveScrollPosition);
        this.restoreScrollPosition();

        // take second word of content
        this.get_title = this.content.title ? this.content.title.split(' ')[1] : '';
    },

    beforeUnmount() {
        window.removeEventListener('beforeunload', this.saveScrollPosition);
    },

    methods: {
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
