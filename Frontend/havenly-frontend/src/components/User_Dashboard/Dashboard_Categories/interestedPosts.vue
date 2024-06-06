<template>
    <div class="interested-posts">
        <v-container>
            <h3>Your interested posts</h3>


            <div class="body">
                <div v-if="loading">
                    <v-row class="g-1 mt-4">
                        <v-col cols="12" md="3">
                            <v-skeleton-loader class="mx-auto" elevation="2" max-width="300"
                                type="card-avatar, article, actions"></v-skeleton-loader>
                        </v-col>

                        <v-col cols="12" md="3">
                            <v-skeleton-loader class="mx-auto" elevation="2" max-width="300"
                                type="card-avatar, article, actions"></v-skeleton-loader>
                        </v-col>

                        <v-col cols="12" md="3">
                            <v-skeleton-loader class="mx-auto" elevation="2" max-width="300"
                                type="card-avatar, article, actions"></v-skeleton-loader>
                        </v-col>

                        <v-col cols="12" md="3">
                            <v-skeleton-loader class="mx-auto" elevation="2" max-width="300"
                                type="card-avatar, article, actions"></v-skeleton-loader>
                        </v-col>

                    </v-row>
                </div>
                <div v-else-if="error">{{ error }}</div>
                <div v-else>
                    <div v-if="posts.length > 0">
                        <!-- card start -->
                        <div class="row mb-3 g-3 mt-3">
                            <div v-for="post in posts" :key="post.post_id" class="col-md-3 col-sm-12">
                                <div class="card-container">
                                    <!-- TZH card styles -->
                                    <div class="card" style="height: 400px;">
                                        <div class="cardImgBox mb-1" style="width: 100%; height: 150px;">
                                            <img :src="post.photo_url[0]" class="h-100 w-100 m-auto"
                                                alt="Card image cap">
                                        </div>
                                        <div class="card-body px-3 py-1 d-flex flex-column">
                                            <h5 class="card-title mb-2">{{ truncateText(post.title, 20) }}</h5>
                                            <p class="card-text small opacity-75" style="text-indent: 30px;">
                                                {{ truncateText(post.description, 65) }}
                                            </p>
                                            <p class="card-text text-danger small opacity-75 mb-1 ">
                                                <v-icon>mdi-map-marker-radius</v-icon>
                                                {{ post.region }} , {{ post.province }} , {{ post.country }}

                                            </p>
                                            <div class="d-flex align-items-center justify-content-between ">
                                                <span class="badge text-bg-danger rounded-pill">
                                                    {{ post.property_type }}
                                                </span>
                                                <div class="d-flex text-danger">
                                                    <v-icon class="mt-2 fs-3">mdi-currency-usd</v-icon>
                                                    <p class="m-0 small fw-bold fs-3">
                                                        {{ post.price }}
                                                    </p>
                                                </div>
                                            </div>

                                        </div>

                                        <v-card-actions class="py-0 m-0">
                                            <v-btn @click="clickPost(post.post_id)" elevation="0" variant="outlined">
                                                Details
                                            </v-btn>
                                            <v-spacer></v-spacer>
                                            <v-btn color="error" @click="openDeleteDialog(post.post_id)" elevation="0"
                                                variant="outlined">
                                                Remove
                                            </v-btn>
                                        </v-card-actions>
                                    </div>

                                </div>

                            </div>
                        </div>
                        <!-- card end -->
                    </div>
                    <div v-else>
                        You maked no interest.<br><br>
                        <v-btn to="/" color="primary" rounded>
                            See all posts
                        </v-btn>
                    </div>
                </div>




            </div>
        </v-container>
    </div>
</template>


<script>
import axios from 'axios';
import Swal from 'sweetalert2';
import AES from 'crypto-js/aes'

export default {
    name: 'interestedPosts',

    data: () => ({
        // get logined data
        getUser: [],
        subUser: [],
        loading: false,
        error: null,

        // temp posts
        posts: [],

        // temp post id
        tempPostId: '',

        // temp Register data 
        registerData: '',

    }),

    mounted() {

        this.getUser = JSON.parse(sessionStorage.getItem('login_user'));
        this.subUser = JSON.parse(sessionStorage.getItem('sub_user'));
        this.fetchInterestedPosts();
        this.fetchRegisterUser();

        this.fetchNoti();
    },

    methods: {
        truncateText(text, charLimit) {
            if (text.length > charLimit) {
                return text.slice(0, charLimit) + '...';
            }
            return text;
        },

        encryptId(id) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
            return encryptedId
        },

        clickPost(post) {

            // router.push('/PostsView')
            const encryptData = this.encryptId(post);
            // this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
            this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
        },

        openDeleteDialog(postID) {
            this.tempPostId = postID;

            Swal.fire({
                icon: 'question',
                title: 'Confirm Remove',
                text: 'Are you sure you want to remove this item?',
                showCancelButton: true,
                confirmButtonText: 'Confirm',
                cancelButtonText: 'Cancel',
            }).then((result) => {
                if (result.isConfirmed) {
                    this.deletePost();
                }
            });
        },

        async fetchInterestedPosts() {
            this.loading = true;
            this.error = null;
            this.posts = [];

            try {

                // fetch post by Registered Id
                const response = await axios.get(`http://localhost:8083/posts/getInterestedPostsByRegId/${this.getUser.register_id}`);
                if (response.status !== 200) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                if (response.status === 404) {
                    throw new Error(`You got not interested post!: ${response.status}`);
                }

                // Parse the response as JSON
                const data = response.data;

                // Assuming data is an array of posts, iterate over each post
                data.forEach(postData => {
                    if (postData.sellpost) {
                        this.processPostData(postData.sellpost, postData);
                    } else if (postData.rentpost) {
                        this.processPostData(postData.rentpost, postData);
                    } else {
                        console.error('Unexpected data format:', postData);
                    }
                });

            } catch (err) {
                console.error('Error fetching data:', err);
                this.error = 'You have no interested post!'; // Set a more informative error message
            } finally {
                this.loading = false;
            }
        },

        processPostData(postData, upperData) {
            const imageUrls = Array.isArray(postData.image) ? postData.image : [postData.image];
            const post = {
                province: postData.locations.province,
                region: postData.locations.region,
                country: postData.locations.countries.country_name,
                latitude: postData.locations.latitude,
                longitude: postData.locations.longitude,
                post_id: upperData.post_id,
                title: postData.title,
                description: postData.description,
                property_type: postData.property_type,
                area: postData.area,
                price: postData.price,
                photo_url: imageUrls,
                deposit: postData.deposit || '',
                least_contract: postData.least_contract || ''
            };
            this.posts.push(post);
        },


        async fetchRegisterUser() {
            try {
                const response = await fetch(`http://localhost:8083/findByMail/${this.getUser.email}`);

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                this.registerData = await response.json();
                console.log("getting Register data : ", this.registerData.register_id);


            } catch (error) {
                console.error('Error fetching post:', error);
            }
        },

        async deletePost() {
            const user_id = this.registerData.register_id;

            try {
                const response = await axios.delete(`http://localhost:8083/interest/deleteByPostIdAndUserId/${this.tempPostId}/${user_id}`);

                if (response.status !== 200) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                // close the dialog
                this.showDeleteDialog = false;


                this.tempPostId = '';

                this.fetchInterestedPosts();

            } catch (error) {
                console.error('Error fetching post:', error);
            }
        },

        async fetchNoti() {


            try {
                const response = await fetch(`http://localhost:8083/interest/getAllNoti/${this.subUser.subUserId}`);

                if (response.status !== 200) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                console.log("Got noti or not : ", response.data);


            } catch (error) {
                console.error('Error fetching post:', error);
            }
        },

    }

}


</script>