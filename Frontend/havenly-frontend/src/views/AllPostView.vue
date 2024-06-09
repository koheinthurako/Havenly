<template>

    <div class="all-post-view">
        <v-container>
            <div class="row px-5">
                <div class="col-12">
                    <div class="left">
                        <div class="header mb-2">
                            <h3>See all post of {{ getName }} </h3>

                        </div>

                        <!-- display temp page -->
                        <!-- <temp-page :key="tempPageKey" :data="tempPageData"></temp-page> -->

                        <div v-if="loading">
                            <v-row class="g-1 mb-3">
                                <v-col cols="12" md="4">
                                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, article"
                                        style="height: 380px; overflow:hidden;"></v-skeleton-loader>
                                </v-col>

                                <v-col cols="12" md="4">
                                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, article"
                                        style="height: 380px; overflow:hidden;"></v-skeleton-loader>
                                </v-col>

                                <v-col cols="12" md="4">
                                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, article"
                                        style="height: 380px; overflow:hidden;"></v-skeleton-loader>
                                </v-col>


                            </v-row>
                        </div>
                        <div v-else>
                            <div class="row mb-3 g-3 " style="cursor: pointer;">
                                <div v-for="post in displayedPosts" :key="post.post_id" class="display-post col-md-4"
                                    @click="clickPost(post)">
                                    <div class="card-container">
                                        <!-- TZH card styles -->
                                        <div class="card" style="height: 380px;">
                                            <!-- <div v-for="url in post.photo_urls" :key="url" class="cardImgBox mb-2">
                                                    <img :src="url" class="w-100 h-100" alt="Card image cap">
                                                </div> -->
                                            <div class="cardImgBox" style="width: 100%; height: 200px;">
                                                <img :src="post.photo_url[0]" class="h-100 w-100 m-auto"
                                                    alt="Card image cap">
                                            </div>
                                            <div class="card-body px-3 py-2 d-flex flex-column">
                                                <h5 class="card-title mb-3">{{ truncateText(post.title, 30) }}</h5>
                                                <p class="card-text small opacity-75" style="text-indent: 30px;">{{
                                                    truncateText(post.description, 80) }}
                                                </p>
                                                <!-- <div class="d-flex mb-3 justify-content-between">
                                                        <span v-if="post.deposit" class="small opacity-75">Deposit : {{ post.deposit
                                                            }}</span>
                                                        <span v-if="post.least_contract" class="small opacity-75">Contract : {{
                                                            post.least_contract }}</span>
                                                    </div> -->
                                                <p class="card-text text-danger small opacity-75 mb-1 ">
                                                    <v-icon>mdi-map-marker-radius</v-icon>
                                                    {{ post.region }} , {{ post.province }} , {{ post.country }}
                                                </p>
                                                <div class="d-flex align-items-center justify-content-between ">
                                                    <span class="badge text-bg-danger rounded-pill">{{
                                                        post.property_type }}</span>
                                                    <div class="d-flex text-danger">
                                                        <v-icon class="mt-2 fs-3">mdi-currency-usd</v-icon>
                                                        <p class="m-0 small fw-bold fs-3">{{ post.price
                                                            }}</p>
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
                        </div>





                    </div>
                </div>
                <!-- <div class="col-md-4 d-none d-sm-block right h-100">
                    <div class="right">
                        <div class="filter-search" :class="{ 'scrolled': isScrolled }">
                            <h5 class="header">Search filter</h5><br><br>
                            <div class="body">
                                <v-radio-group v-model="setType" inline class="p-0">
                                    <v-radio color="orange" label="Sell Posts" value="sell"></v-radio>
                                    <v-spacer></v-spacer>
                                    <v-radio color="orange" label="Rent Posts" value="rent"></v-radio>
                                </v-radio-group>
                                <hr class="pb-3 m-0 mx-4">
                                <v-select v-model="selectedType" bg-color="#EDEDED" filled variant="outlined"
                                    density="compact" rounded="lg" clearable label="Select type"
                                    :items="['Condo', 'Apartment', 'House', 'Office']"></v-select>
                                <v-select v-model="selectedRoom" bg-color="#EDEDED" filled variant="outlined"
                                    density="compact" rounded="lg" clearable label="Select Room"
                                    :items="['Single room', 'Double room']"></v-select>
                                <v-select v-model="selectedArea" bg-color="#EDEDED" filled variant="outlined"
                                    density="compact" rounded="lg" clearable label="Select Square feet"
                                    :items="['under 600', 'between 600 and 1000', 'between 1000 and 1500', 'between 1500 and 2000', 'above 2400']"></v-select>
                                <hr class="pb-3 m-0 mx-4">
                                <div class="d-flex justify-space-between">
                                    <v-btn class="all-btn" @click="getAllFromTempPage"><v-icon
                                            class="me-1">mdi-all-inclusive</v-icon>All
                                        posts</v-btn>
                                    <v-btn class="search-btn"
                                        @click="sendDataToTempPage"><v-icon>mdi-magnify</v-icon>Search</v-btn>
                                </div>
                            </div>
                        </div>


                    </div>
                </div> -->
            </div>

        </v-container>
    </div>

</template>

<script>
import AES from 'crypto-js/aes'
import Utf8 from 'crypto-js/enc-utf8'

// import tempPage from '@/views/TempCollection/TempForAllPostView.vue'

export default {
    name: 'AllPostView',


    data() {
        return {
            loading: false,
            posts: [],
            // get datas
            setType: 'sell',
            selectedArea: '',
            selectedType: '',
            selectedRoom: '',
            tempPageData: null,
            tempPageKey: 0,
            isScrolled: false,
            getName: '',
            getPostType: null,

        }
    },

    // components: {
    //     tempPage,
    // },

    mounted() {
        this.fetchPosts();

        // this.getPostType = sessionStorage.getItem('getPostType');
        this.changeName(this.splitData(this.$route.params.postType));
        console.log("Get post type : ", this.changeName(this.splitData(this.$route.params.postType)));
        // if (!this.getPostType) {
        //     console.error('No postType found in sessionStorage');
        // } else {
        //     this.changeName(this.getPostType);

        //     const data = {
        //         setPost: '',
        //         postType: this.changeName(this.getPostType),
        //         area: '',
        //         room: '',
        //     };
        //     this.tempPageData = data;
        //     this.tempPageKey += 1;

        // }

        window.addEventListener('scroll', this.handleScroll);
    },

    beforeUnmount() {
        window.removeEventListener('scroll', this.handleScroll);
    },

    computed: {
        displayedPosts() {
            // Filter posts based on the selected type
            const filteredPosts = this.posts.filter(post => post.property_type.toLowerCase() === this.decryptData(this.splitData(this.$route.params.postType)).toLowerCase());

            // Return only the first four filtered posts
            return filteredPosts.slice(0, 8);

        },

    },

    methods: {
        clickPost(post) {
            // router.push('/PostsView')
            // const encryptData = this.encryptId(post);
            // this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
            console.log(post);
            // this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
        },

        splitData(data) {
            return data.split(' ')[0];
        },

        truncateText(text, charLimit) {
            if (text.length > charLimit) {
                return text.slice(0, charLimit) + '...';
            }
            return text;
        },

        // Fetch all posts and filter them
        fetchPosts() {
            this.loading = true;
            fetch('http://localhost:8083/posts/allComplete')
                .then(response => response.json())
                .then(data => {
                    data.forEach(post => {
                        if (post.rentpost) {
                            this.processPost(post.rentpost, 'rentpost');
                        } else if (post.sellpost) {
                            this.processPost(post.sellpost, 'sellpost');
                        }
                    });
                    this.loading = false;
                })
                .catch(error => {
                    console.error('Error fetching photos:', error);
                    this.loading = false;
                });
        },
        // Process post data
        processPost(postData, type) {
            if (postData.description.length > 100) {
                postData.description = postData.description.substring(0, 100) + '...';
            }

            const imageUrls = Array.isArray(postData.image) ? postData.image : [postData.image];

            const processedPost = {
                province: postData.locations.province,
                region: postData.locations.region,
                country: postData.locations.countries.country_name,
                post_id: postData.sell_post_id,
                title: postData.title,
                description: postData.description,
                property_type: postData.property_type,
                area: postData.area,
                price: postData.price,
                photo_url: imageUrls
            };

            if (type === 'rentpost') {
                processedPost.deposit = postData.deposit;
                processedPost.least_contract = postData.least_contract;
            }

            this.posts.unshift(processedPost);
        },

        // // firstly fetch all posts and filter
        // fetchPosts() {
        //     // Make API call to fetch posts from backend
        //     fetch('http://localhost:8083/posts/allComplete')
        //         .then(response => response.json())
        //         .then(data => {
        //             data.forEach(post => {
        //                 if (post.rentpost) {
        //                     console.log(post);
        //                     if (post.rentpost.description.length > 100) {
        //                         let des = post.rentpost.description;
        //                         post.rentpost.description = des.substring(0, 100) + "...";
        //                     }

        //                     let imageUrls = Array.isArray(post.rentpost.image) ? post.rentpost.image : [post.rentpost.image];
        //                     console.log(imageUrls)
        //                     console.log(post);
        //                     this.posts.unshift({
        //                         province: post.rentpost.locations.province,
        //                         region: post.rentpost.locations.region,
        //                         country: post.rentpost.locations.countries.country_name,
        //                         post_id: post.rentpost.sell_post_id,
        //                         title: post.rentpost.title,
        //                         description: post.rentpost.description,
        //                         property_type: post.rentpost.property_type,
        //                         area: post.rentpost.area,
        //                         price: post.rentpost.price,
        //                         deposit: post.rentpost.deposit,
        //                         least_contract: post.rentpost.least_contract,
        //                         photo_url: imageUrls,
        //                     });
        //                     console.log(typeof (imageUrls))
        //                 } else if (post.sellpost) {
        //                     console.log(post);
        //                     if (post.sellpost.description.length > 100) {
        //                         let des = post.sellpost.description;
        //                         post.sellpost.description = des.substring(0, 100) + "...";
        //                     }

        //                     let imageUrls = Array.isArray(post.sellpost.image) ? post.sellpost.image : [post.sellpost.image];
        //                     console.log(imageUrls)
        //                     console.log(post);
        //                     this.posts.unshift({
        //                         province: post.sellpost.locations.province,
        //                         region: post.sellpost.locations.region,
        //                         country: post.sellpost.locations.countries.country_name,
        //                         post_id: post.sellpost.sell_post_id,
        //                         title: post.sellpost.title,
        //                         description: post.sellpost.description,
        //                         property_type: post.sellpost.property_type,
        //                         area: post.sellpost.area,
        //                         price: post.sellpost.price,
        //                         photo_url: imageUrls,
        //                     });
        //                     console.log(typeof (imageUrls))
        //                 }

        //             });
        //             // console.log(this.posts);
        //         })
        //         .catch(error => {
        //             console.error('Error fetching photos:', error);
        //         });
        // },

        handleScroll() {
            this.isScrolled = window.scrollY >= 15;
            console.log(this.isScrolled);
        },

        encryptId(id) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
            return encryptedId
        },

        decryptId(encryptedId) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(encryptedId, secretKey);
            const decryptedId = decryptedBytes.toString(Utf8);
            return decryptedId; // Return the decrypted string directly
        },

        encryptData(data) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(data, secretKey).toString()
            return encryptedId
        },

        decryptData(data) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(data, secretKey);
            const decryptedData = decryptedBytes.toString(Utf8);
            return decryptedData;
        },

        changeName(get) {
            this.getName = this.decryptData(get);
            return this.getName;
        },

        mapAreaValue(selectedArea) {
            switch (selectedArea) {
                case 'under 600':
                    return { min: 0, max: 600 };
                case 'between 600 and 1000':
                    return { min: 600, max: 1000 };
                case 'between 1000 and 1500':
                    return { min: 1000, max: 1500 };
                case 'between 1500 and 2000':
                    return { min: 1500, max: 2000 };
                case 'above 2400':
                    return { min: 2400, max: Infinity };
                default:
                    return null;
            }
        },

        sendDataToTempPage() {
            if (this.selectedType) {
                this.changeName(this.encryptData(this.selectedType));
            }


            const data = {
                setPost: this.setType,
                postType: this.selectedType,
                area: this.selectedArea,
                room: this.selectedRoom,
            };
            this.tempPageData = data;
            this.tempPageKey += 1;

        },

        getAllFromTempPage() {

            this.getPostType = sessionStorage.getItem('getPostType');
            if (this.getPostType) {
                this.changeName(this.getPostType);
            }

            // first clean the fields
            this.selectedType = '';
            this.selectedArea = '';
            this.selectedRoom = '';

            const data = {
                setPost: '',
                postType: '',
                area: '',
                room: '',
            };
            this.tempPageData = data;
            this.tempPageKey += 1;

        }
    }
}


</script>

<style lang="scss">
.scrolled {
    position: fixed !important;
    top: 12%;
    width: 340px;
    box-shadow: 0px 6px 20px 1px rgba(0, 0, 0, 0.4);
    z-index: 1;
}

.display-post {

    &:hover {
        .card-title {
            color: #e86f52;
        }
    }
}
</style>