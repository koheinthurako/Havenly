<template>
    <v-container>
        <main>
            <div>
                <div class="row mb-5 g-3">
                <div v-for="post in posts" :key="post.post_id" class="col-md-3">
                    <div class="card-container">
                        <!-- TZH card styles -->
                        <div class="card" style="height: 490px;">
                            <!-- <div v-for="url in post.photo_urls" :key="url" class="cardImgBox mb-2">
                                <img :src="url" class="w-100 h-100" alt="Card image cap">
                            </div> -->
                            <div class="cardImgBox mb-2">
                                <img :src="post.photo_url[0]" class="w-100 h-100" alt="Card image cap">
                            </div>
                            <div class="card-body p-3 d-flex flex-column">
                                <div class="mb-3">
                                    <span class="px-3 badge rounded-pill text-uppercase small d-inline" 
                                    :class="getStatusClass(post.status)">{{ post.status }}</span>
                                </div>
                                <h5 class="card-title mb-3">{{ post.title }}</h5>
                                <p class="card-text small opacity-75">{{ post.description }}</p>
                                <div class="d-flex mb-3 justify-content-between">
                                    <span v-if="post.deposit" class="small opacity-75">Deposit : {{ post.deposit }}</span>
                                    <span v-if="post.least_contract" class="small opacity-75">Contract : {{ post.least_contract }}</span>
                                </div>
                                <p class="card-text text-danger small mb-auto opacity-75 mb-auto">
                                    <v-icon >mdi-map-marker-radius</v-icon>
                                    {{ post.region }} , {{ post.province }} , {{ post.country }}
                                </p>
                                
                                <div class="buttonBox d-flex justify-content-between gap-3">
                                    <button @click="editPost(post)" class="btn btn-outline-danger w-100">Edit</button>
                                    <button class="btn btn-danger w-100">Delete</button>
                                </div>
                                <!-- <div class="d-flex align-items-center justify-content-between mb-2">
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
                                </div> -->
                            </div>
                        </div>

                    </div>

                </div>
            </div>
            </div>
        </main>
    </v-container>
</template>

<script>
import axios from 'axios';


    export default {
        name: 'uploadedAllPosts',

        data: () => ({
            posts : [],
        }),

        mounted() {
            this.fetchPosts();
        },

        methods: {

            fetchPosts() {
                const user = JSON.parse(sessionStorage.getItem('sub_user'));
                const subUserId = user.subUserId;
                // Make API call to fetch posts from backend
                axios.get('http://localhost:8083/posts/allPosts', {
                      params: {
                        subUserId: subUserId
                      }
                })
                .then(response => {
                    console.log(response.data)
                    response.data.forEach(post => {
                        if(post.testrentposts) {
                            console.log(post);
                            if(post.testrentposts.description.length > 100) {
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
                                status: post.status,
                            });
                            console.log(typeof(imageUrls))
                        } else if (post.testsellpostss) {
                            console.log(post);
                            if(post.testsellpostss.description.length > 100) {
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
                                status: post.status,
                            });
                        }
                        
                    });
                })
            },

            editPost(post) {
                console.log(post.title + " ko click lite tl")
                console.log(post);
            },

            getStatusClass(status) {
                switch (status) {
                    case 'pending':
                    return 'text-bg-danger';  // အနီရောင် background
                    case 'complete':
                    return 'text-bg-success'; // အစိမ်းရောင် background
                    default:
                    return 'text-bg-secondary'; // Default background
                }
            },

        },



    }
            
        

</script>

<style>

</style>