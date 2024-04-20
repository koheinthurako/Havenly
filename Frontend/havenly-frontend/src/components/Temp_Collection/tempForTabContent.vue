<template>
    <div class="tempOf-tabContent">
        <div class="container">

            <div class="content-data d-flex mt-1 mb-3">

                <!-- for Desktop view -->

                <v-btn size="large"  class="d-none d-md-block content-btn ms-auto mb-3" style="text-transform:capitalize;">See all post of
                    <span class="ms-1 red">{{ get_title }}</span> <v-icon
                        style="margin-left: 8px;font-size: 24px;" class="custom-icon">mdi-chevron-double-right</v-icon>
                </v-btn>

                <v-btn size="small" class="d-block d-sm-none content-btn ms-auto me-5 mb-1"  style="text-transform:capitalize;">See all
                    <span class="ms-1 red">{{ get_title }}</span> <v-icon
                        style=" margin-left: 8px;font-size: 24px;" class="custom-icon">mdi-chevron-double-right</v-icon>
                </v-btn>

            </div>

            <div class="row mb-1">
                <div v-for="(slide, index) in slides" :style="{ animationDelay: `${index * 0.2}s` }" :key="index"
                    class="col-md-3">

                    <div class="card-container">

                        <routerlink to="" class="card mb-4"
                            :class="[animations[index % animations.length], { 'fade-up': animated }]">
                            <div class="custom-save">
                                <v-icon >mdi-content-save-outline</v-icon>
                            </div>
                            <div class="image-container mb-2">
                                <img :src="slide.image" class="card-img-top" alt="Card image cap">
                            </div>

                            <div class="card-body p-3">
                                <div class="d-flex py-0">
                                    <div class="card-kind">
                                        <p class="bg-hover" :title="'This post is for ' + slide.type">{{ slide.type }}
                                        </p>
                                    </div>
                                    <div class="heart-sign d-flex ms-auto py-0">
                                        <v-icon class="ms-auto custom-heart">
                                            mdi-heart-outline
                                        </v-icon>
                                        <p class="text-grey ms-1">1500</p>
                                    </div>
                                </div>
                                <div class="card-title w-100 py-0">
                                    <h3>{{ slide.title }}</h3>
                                </div>
                                <div class="w-100 py-0">
                                    <div class="text-grey ">
                                        {{ slide.description }}
                                    </div>
                                </div>
                                <div class="w-100 d-flex ">
                                    <v-rating :model-value="4.5" color="success" density="compact" size="small"
                                        half-increments readonly></v-rating>

                                    <div class="text-grey">
                                        4.5 (413)
                                    </div>
                                    <p class="ms-auto custom-m fw-bold">
                                        3450 (kyats)
                                    </p>
                                </div>
                                <div class="w-100 d-flex py-0">
                                    <v-icon class="custom-icon">mdi-map-marker</v-icon>
                                    <p class="ms-1">{{ slide.location }}</p>
                                </div>
                                
                                <v-divider :thickness="2" class="border-opacity-25" />
                                <div class="d-flex py-0">
                                    <div class="clock-side d-flex">
                                        <v-icon class="text-red">mdi-clock-time-eight-outline</v-icon>
                                        <p class="ms-1 text-grey">12d 8h 56m</p>
                                    </div>
                                    <div class="d-flex ms-auto">
                                        <v-icon class="text-red">mdi-eye</v-icon>
                                        <p class="ms-1 text-grey" title="People watched times">1331</p>
                                    </div>
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
