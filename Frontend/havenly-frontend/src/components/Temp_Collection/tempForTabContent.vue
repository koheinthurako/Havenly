<template>
    <div class="tempOf-tabContent">
        <div class="mx-5 px-5">

            <div class="content-data d-flex mt-1 mb-3">

                <!-- for Desktop view -->

                <v-btn @click="gotoAllView(get_title)" size="large" class="d-none d-md-block content-btn ms-auto mb-3"
                    style="text-transform:capitalize;">See all post
                    of
                    <!-- <span class="ms-1 red">condo</span> <v-icon style="margin-left: 8px;font-size: 24px;"
                        class="custom-icon">mdi-chevron-double-right</v-icon> -->
                    <span class="ms-1 red">{{ get_title }}</span> <v-icon style=" margin-left: 8px;font-size: 24px;"
                        class="custom-icon">mdi-chevron-double-right</v-icon>
                </v-btn>

                <!-- <v-btn size="small" class="d-block d-sm-none content-btn ms-auto me-5 mb-1"
                    style="text-transform:capitalize;">See all
                    <span class="ms-1 red">{{ get_title }}</span> <v-icon style=" margin-left: 8px;font-size: 24px;"
                        class="custom-icon">mdi-chevron-double-right</v-icon>
                </v-btn> -->

            </div>


            <!-- Render real data from database-->
            <!-- :to="{ name: 'postDetail', params: { id: encryptId(post.p_id) } }" -->
            <div class="row mb-5 g-3">
                <div v-for="post in displayedPosts" :key="post.post_id" class="col-md-3"
                    @click="gotoDetailView(post.post_id)">
                    <div class="card-container">
                        <!-- TZH card styles -->
                        <div class="card" style="height: 550px;">
                            <!-- <div v-for="url in post.photo_urls" :key="url" class="cardImgBox mb-2">
                                <img :src="url" class="w-100 h-100" alt="Card image cap">
                            </div> -->
                            <div class="cardImgBox mb-2">

                                <v-img :src="post.photos[0]" class="w-100 h-100" alt="Card image cap" />
                            </div>
                            <div class="card-body p-3 d-flex flex-column">
                                <h5 class="card-title mb-3">{{ post.title }}</h5>
                                <p class="card-text small opacity-75" style="text-indent: 30px">{{
                                    truncateText(post.description, 75) }}</p>
                                <div class="d-flex mb-3 mt-3 justify-content-between">
                                    <span class="small opacity-75">Deposit : Deposit</span>
                                    <span class="small opacity-75">Contract : Contract</span>
                                </div>
                                <p class="card-text text-danger small mb-auto opacity-75 mb-auto ">
                                    <v-icon>mdi-map-marker-radius</v-icon>
                                    region , province , country
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
                                <v-divider :thickness="2" class="border-opacity-25 d-block" />
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
import router from '@/router';
// import axios from 'axios';

import AES from 'crypto-js/aes'
import Utf8 from 'crypto-js/enc-utf8';
// import { descriptors } from 'chart.js/dist/core/core.defaults';

// import postView from '../../views/PostsView.vue';

export default {

    name: 'tempVue',


    props: {
        content: {
            type: Object,
            required: true
        }
    },

    // components: {
    //     postView,
    // },

    data: () => ({
        img: 'https://shorturl.at/eW123',
        showPosts: [],
        get_title: '',
        animations: ['fade-left', 'zoom-in-up', 'zoom-in-down', 'fade-up', 'fade-down', 'fade-right'],
        animated: false,
    }),

    computed: {

        // slides() {
        //     let slides = [];
        //     const titleParts = this.content.title.split(' ');
        //     console.log(this.content.title);
        //     if (titleParts.length === 3) {
        //         const firstWord = titleParts[0];
        //         const secondWord = titleParts[1];
        //         slides = this.$store.state[firstWord].filter(slide => slide.category === secondWord);
        //     }
        //     return this.limitSlides(slides);
        // },

        displayedPosts() {
            // Filter posts based on the selected type
            const filteredPosts = this.showPosts.filter(post => post.property_type.toLowerCase() === this.get_title.toLowerCase());

            // Return only the first four filtered posts
            return filteredPosts.slice(0, 4);
        },


        login_status() {
            return this.$store.getters.LoginData
        },


    },

    mounted() {
        this.fetchPosts();
        // this.initialize();
        window.addEventListener('beforeunload', this.saveScrollPosition);
        this.restoreScrollPosition();

        // take second word of content
        // this.get_title = this.content.title ? this.content.title.split(' ')[1] : '';
        this.get_title = this.content.title;
    },

    beforeUnmount() {
        window.removeEventListener('beforeunload', this.saveScrollPosition);
    },

    methods: {

        truncateText(text, charLimit) {
            if (text.length > charLimit) {
                return text.slice(0, charLimit) + '...';
            }
            return text;
        },

        gotoDetailView(postId) {

            const encryptData = this.encryptId(postId);
            sessionStorage.setItem('postId', encryptData);
            this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
        },

        gotoAllView(data) {
            const encryptData = this.encryptData(data);
            sessionStorage.setItem('getPostType', encryptData);
            this.$router.push({ name: 'AllPostView', params: { postType: `${encryptData} Success` } });
        },

        encryptId(id) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
            return encryptedId
        },

        encryptData(data) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(data, secretKey).toString()
            return encryptedId
        },

        decryptId(encryptedId) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(encryptedId, secretKey);
            const decryptedId = decryptedBytes.toString(Utf8);
            return parseInt(decryptedId, 10);
        },

        // Fetch all post
        // async initialize() {
        //     try {
        //         const response = await axios.get('http://localhost:8083/');
        //         this.showPosts = response.data.map(post => {
        //             // Modify the object to include the profile image URL
        //             return {
        //                 post_id: post.post_id,
        //                 title: post.title,
        //                 description: post.description,
        //                 house_type: post.house_type,
        //                 property_type: post.property_type,
        //                 area: post.area,
        //                 price: post.price,
        //                 photos: post.photoUrls,
        //             };
        //         });
        //         console.log("Reached");
        //         console.log(this.showPosts);
        //     } catch (error) {
        //         console.error('Error fetching students:', error);
        //     }
        // },

        fetchPosts() {
            // Make API call to fetch posts from backend
            fetch('http://localhost:8083/posts/allComplete')
                .then(response => response.json())
                .then(data => {
                    data.forEach(post => {
                        if (post.testrentposts) {
                            console.log(post);
                            if (post.testrentposts.description.length > 100) {
                                let des = post.testrentposts.description;
                                post.testrentposts.description = des.substring(0, 100) + "...";
                            }

                            let imageUrls = Array.isArray(post.testrentposts.image) ? post.testrentposts.image : [post.testrentposts.image];
                            console.log(imageUrls)
                            console.log(post);
                            this.posts.unshift({
                                province: post.testrentposts.locations.province,
                                region: post.testrentposts.locations.region,
                                country: post.testrentposts.locations.countries.country_name,
                                post_id: post.testrentposts.sell_post_id,
                                title: post.testrentposts.title,
                                description: post.testrentposts.description,
                                property_type: post.testrentposts.property_type,
                                area: post.testrentposts.area,
                                price: post.testrentposts.price,
                                deposit: post.testrentposts.deposit,
                                least_contract: post.testrentposts.least_contract,
                                photo_url: imageUrls,
                            });
                            console.log(typeof (imageUrls))
                        } else if (post.testsellpostss) {
                            console.log(post);
                            if (post.testsellpostss.description.length > 100) {
                                let des = post.testsellpostss.description;
                                post.testsellpostss.description = des.substring(0, 100) + "...";
                            }

                            let imageUrls = Array.isArray(post.testsellpostss.image) ? post.testsellpostss.image : [post.testsellpostss.image];
                            console.log(imageUrls)
                            console.log(post);
                            this.posts.unshift({
                                province: post.testsellpostss.locations.province,
                                region: post.testsellpostss.locations.region,
                                country: post.testsellpostss.locations.countries.country_name,
                                post_id: post.testsellpostss.sell_post_id,
                                title: post.testsellpostss.title,
                                description: post.testsellpostss.description,
                                property_type: post.testsellpostss.property_type,
                                area: post.testsellpostss.area,
                                price: post.testsellpostss.price,
                                photo_url: imageUrls,
                            });
                            console.log(typeof (imageUrls))
                        }

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
        //         .then(response => response.json())
        //         .then(data => {
        //             console.log(data);
        //             data.forEach(post => {
        //                 let images = post.image.split(';');
        //                 let photo_urls = images.map(image => 'data:image/jpeg;base64,' + image);
        //                 this.posts.push({
        //                     province: post.locations.province,
        //                     region: post.locations.region,
        //                     country: post.locations.countries.country_name,
        //                     post_id: post.sell_post_id,
        //                     title: post.title,
        //                     description: post.description,
        //                     house_type: post.house_type,
        //                     property_type: post.property_type,
        //                     area: post.area,
        //                     price: post.price,
        //                     photo_url: photo_urls,
        //                 });
        //             });
        //             // console.log(this.posts);
        //         })
        //         .catch(error => {
        //             console.error('Error fetching photos:', error);
        //         });
        // },



        // Method to limit the number of slides based on the viewport size
        // limitSlides(slides) {
        //     const maxSlides = window.innerWidth < 768 ? 4 : 4; // 768px is the breakpoint for mobile view
        //     return slides.slice(0, maxSlides);
        // },

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

        clickPost(post) {
            console.log("You clicked post!")
            console.log(post.title);
            router.push('/PostsView')
        }

    },

    watch: {
        'content.title': {
            handler(newTitle, oldTitle) {
                // Check if the title has changed and is not empty
                if (newTitle !== oldTitle) {

                    // to change text
                    this.get_title = newTitle;

                    // to shuffle the animation
                    this.shuffleAnimations();

                }
            },
            immediate: true, // Trigger the handler immediately on component mount
        },
    },
};
</script>