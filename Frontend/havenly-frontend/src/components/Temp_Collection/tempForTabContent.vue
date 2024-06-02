<template>
    <div class="tempOf-tabContent">
        <v-container>
            <!-- Render real data from database-->
            <div v-if="loading">
                <v-row class="g-1 mb-3">
                    <v-col cols="12" md="3">
                        <v-skeleton-loader class="mx-auto" elevation="2" max-width="300" type="image, article, article"
                            style="height: 300px; overflow:hidden;"></v-skeleton-loader>
                    </v-col>

                    <v-col cols="12" md="3">
                        <v-skeleton-loader class="mx-auto" elevation="2" max-width="300" type="image, article, article"
                            style="height: 300px; overflow:hidden;"></v-skeleton-loader>
                    </v-col>

                    <v-col cols="12" md="3">
                        <v-skeleton-loader class="mx-auto" elevation="2" max-width="300" type="image, article, article"
                            style="height: 300px; overflow:hidden;"></v-skeleton-loader>
                    </v-col>

                    <v-col cols="12" md="3">
                        <v-skeleton-loader class="mx-auto" elevation="2" max-width="300" type="image, article, article"
                            style="height: 300px; overflow:hidden;"></v-skeleton-loader>
                    </v-col>

                </v-row>
            </div>
            <div v-else>

                <!-- All posts showing start -->

                <div class="row mb-3 g-3">
                    <div v-for="post in limitedPosts" :key="post.post_id" class="col-md-3 col-sm-12"
                        @click="clickPost(post.post_id)">
                        <div class="card-container">
                            <!-- TZH card styles -->
                            <div class="card" style="height: 390px;">
                                <!-- <div v-for="url in post.photo_urls" :key="url" class="cardImgBox mb-2">
                                    <img :src="url" class="w-100 h-100" alt="Card image cap">
                                </div> -->
                                <div class="cardImgBox" style="width: 100%; height: 160px;">
                                    <img :src="post.photo_url[0]" class="h-100 w-100 m-auto" alt="Card image cap">
                                </div>
                                <div class="card-body p-3 d-flex flex-column">
                                    <h5 class="card-title mb-2">{{ post.title }}</h5>
                                    <p class="card-text small opacity-75 mb-1">{{ post.description }}</p>
                                    <p class="card-text text-danger small opacity-75">
                                        <v-icon>mdi-map-marker-radius</v-icon>
                                        {{ post.region }} , {{ post.province }} , {{ post.country }}
                                    </p>
                                    <div class="d-flex mb-3 justify-content-between mb-auto">
                                        <span v-if="post.deposit" class="small opacity-75">Deposit : {{ post.deposit
                                            }}</span>
                                        <span v-if="post.least_contract" class="small opacity-75">Contract : {{
                                            post.least_contract }}</span>
                                    </div>
                                    <div class="d-flex align-items-center justify-content-between ">
                                        <span class="badge text-bg-danger rounded-pill">{{ post.property_type }}</span>
                                        <div class="d-flex text-danger">
                                            <!-- <v-icon class="mt-2 fs-3">mdi-currency-usd</v-icon> -->
                                            <p class="m-0 small fw-bold fs-3">
                                                {{ post.price }}
                                            </p>
                                        </div>
                                    </div>

                                    <!-- <div class="d-flex align-items-center justify-content-between">
                                        <p class="m-0 small">{{ post.area }} sqft</p>
                                    </div> -->

                                </div>
                            </div>

                        </div>

                    </div>
                </div>

                <!-- All posts showing end -->
            </div>




            <div class="content-data d-flex mt-1 mb-3">

                <!-- for Desktop view -->

                <v-btn @click="gotoAllView(get_title)" size="large" class="d-none d-md-block content-btn ms-auto mb-3"
                    style="text-transform:capitalize;">See all post of
                    <span class="ms-1 red">{{ get_title }}</span> <v-icon style="margin-left: 8px;font-size: 24px;"
                        class="custom-icon">mdi-chevron-double-right</v-icon>
                </v-btn>

            </div>

        </v-container>
    </div>
</template>

<script>
// import router from '@/router';
import AES from 'crypto-js/aes';
import Utf8 from 'crypto-js/enc-utf8';
import axios from 'axios';
export default {

    name: 'tempVue',

    props: {
        content: {
            type: Object,
            required: true
        }
    },


    data: () => ({
        loading: false,
        posts: [],
        get_title: '',
        animations: ['fade-left', 'zoom-in-up', 'zoom-in-down', 'fade-up', 'fade-down', 'fade-right'],
        animated: false,
    }),

    computed: {

        login_status() {
            return this.$store.getters.LoginData
        },


        // displayedPosts() {
        //     // Filter posts based on the selected type
        //     const filteredPosts = this.posts.filter(post => post.property_type.toLowerCase() === this.get_title.toLowerCase());

        //     // Return only the first four filtered posts
        //     return filteredPosts.slice(0, 8);
        // },

        limitedPosts() {
            return this.posts.slice(0, 8); // posts array မှ 8 ခုကိုသာ ဖြတ်ယူပါမည်
        },


    },

    mounted() {
        this.fetchPosts();

        window.addEventListener('beforeunload', this.saveScrollPosition);
        this.restoreScrollPosition();

        // take second word of content
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

        async fetchPosts() {
            this.loading = true;
            try {
                // Make API call to fetch posts from backend
                const response = await axios.get('http://localhost:8083/posts/allComplete');
                const data = response.data;

                data.forEach(post => {
                    const mainId = post.post_id;
                    if (post.rentpost) {
                        if (post.rentpost.description.length > 60) {
                            let des = post.rentpost.description;
                            post.rentpost.description = des.substring(0, 60) + "...";
                        }

                        let imageUrls = Array.isArray(post.rentpost.image) ? post.rentpost.image : [post.rentpost.image];

                        this.posts.unshift({
                            province: post.rentpost.locations.province,
                            region: post.rentpost.locations.region,
                            country: post.rentpost.locations.countries.country_name,
                            post_id: mainId,
                            title: post.rentpost.title,
                            description: post.rentpost.description,
                            property_type: post.rentpost.property_type,
                            area: post.rentpost.area,
                            price: post.rentpost.price,
                            deposit: post.rentpost.deposit,
                            least_contract: post.rentpost.least_contract,
                            photo_url: imageUrls,
                        });

                    } else if (post.sellpost) {
                        if (post.sellpost.description.length > 60) {
                            let des = post.sellpost.description;
                            post.sellpost.description = des.substring(0, 60) + "...";
                        }

                        let imageUrls = Array.isArray(post.sellpost.image) ? post.sellpost.image : [post.sellpost.image];

                        this.posts.unshift({
                            province: post.sellpost.locations.province,
                            region: post.sellpost.locations.region,
                            country: post.sellpost.locations.countries.country_name,
                            post_id: mainId,
                            title: post.sellpost.title,
                            description: post.sellpost.description,
                            property_type: post.sellpost.property_type,
                            area: post.sellpost.area,
                            price: post.sellpost.price,
                            photo_url: imageUrls,
                        });
                    }
                });

            } catch (error) {
                console.error('Error fetching photos:', error);
            } finally {
                this.loading = false; // Set loading to false after the API call is done
            }
        },


        // fetchPosts() {
        //     // Make API call to fetch posts from backend
        //     fetch('http://localhost:8083/posts/allComplete')
        //         .then(response => response.json())
        //         .then(data => {
        //             data.forEach(post => {
        //                 const mainId = post.post_id;
        //                 if (post.rentpost) {

        //                     if (post.rentpost.description.length > 100) {
        //                         let des = post.rentpost.description;
        //                         post.rentpost.description = des.substring(0, 100) + "...";
        //                     }

        //                     let imageUrls = Array.isArray(post.rentpost.image) ? post.rentpost.image : [post.rentpost.image];

        //                     this.posts.unshift({
        //                         province: post.rentpost.locations.province,
        //                         region: post.rentpost.locations.region,
        //                         country: post.rentpost.locations.countries.country_name,
        //                         post_id: mainId,
        //                         title: post.rentpost.title,
        //                         description: post.rentpost.description,
        //                         property_type: post.rentpost.property_type,
        //                         area: post.rentpost.area,
        //                         price: post.rentpost.price,
        //                         deposit: post.rentpost.deposit,
        //                         least_contract: post.rentpost.least_contract,
        //                         photo_url: imageUrls,
        //                     });

        //                 } else if (post.sellpost) {

        //                     if (post.sellpost.description.length > 100) {
        //                         let des = post.sellpost.description;
        //                         post.sellpost.description = des.substring(0, 100) + "...";
        //                     }

        //                     let imageUrls = Array.isArray(post.sellpost.image) ? post.sellpost.image : [post.sellpost.image];

        //                     this.posts.unshift({
        //                         province: post.sellpost.locations.province,
        //                         region: post.sellpost.locations.region,
        //                         country: post.sellpost.locations.countries.country_name,
        //                         post_id: mainId,
        //                         title: post.sellpost.title,
        //                         description: post.sellpost.description,
        //                         property_type: post.sellpost.property_type,
        //                         area: post.sellpost.area,
        //                         price: post.sellpost.price,
        //                         photo_url: imageUrls,
        //                     });

        //                 }

        //             });
        //             // console.log(this.posts);
        //         })
        //         .catch(error => {
        //             console.error('Error fetching photos:', error);
        //         });
        // },

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

        decryptData(encryptedId) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(encryptedId, secretKey);
            const decryptedId = decryptedBytes.toString(Utf8);
            return decryptedId;
        },

        decryptId(encryptedId) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(encryptedId, secretKey);
            const decryptedId = decryptedBytes.toString(Utf8);
            return parseInt(decryptedId, 10);
        },

        clickPost(post_id) {
            // router.push('/PostsView')
            const afterEncrypt = this.encryptId(post_id);
            // this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
            this.$router.push({ name: 'postDetailView', params: { id: `${afterEncrypt} Success` } });
        },

        gotoAllView(get) {
            const encryptData = this.encryptData(get);
            // sessionStorage.setItem('getPostType', encryptData);
            this.$router.push({ name: 'AllPostView', params: { postType: `${encryptData}Success` } });
        },

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