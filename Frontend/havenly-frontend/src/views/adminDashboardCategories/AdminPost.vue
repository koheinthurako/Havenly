<template>

    <div class="container">
        <h1 style="color: #e86f52;">Users' pending posts!</h1>
        <div v-if="loading">
            <v-row class="g-1 mb-3">
                <v-col cols="12" md="3">
                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, actions"
                        style="height: 400px; overflow:hidden;"></v-skeleton-loader>
                </v-col>

                <v-col cols="12" md="3">
                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, actions"
                        style="height: 400px; overflow:hidden;"></v-skeleton-loader>
                </v-col>

                <v-col cols="12" md="3">
                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, actions"
                        style="height: 400px; overflow:hidden;"></v-skeleton-loader>
                </v-col>

                <v-col cols="12" md="3">
                    <v-skeleton-loader class="mx-auto" elevation="2" type="image, article, actions"
                        style="height: 400px; overflow:hidden;"></v-skeleton-loader>
                </v-col>


            </v-row>
        </div>
        <div v-else>
            <div class="row mt-3 mb-5 box2">
                <div v-for="post in posts" :key="post.post_id" class="col-md-3" @click="clickPost(post)">

                    <div class="card-container">
                        <!-- TZH card styles -->
                        <div class="card" style="height: 360px; cursor: pointer;">
                            <div class="cardImgBox mb-1" style="width: 100%; height: 150px;">
                                <img :src="post.photo_url[0]" class="h-100 w-100 m-auto" alt="Card image cap">
                            </div>
                            <div class="card-body px-3 py-1 m-0 d-flex flex-column">
                                <h5 class="card-title mb-2">{{ truncateText(post.title, 20) }}</h5>
                                <p class="card-text small opacity-75" style="text-indent: 30px;">
                                    {{ truncateText(post.description, 65) }}
                                </p>
                                <p class="card-text text-danger small opacity-75 mb-0">
                                    <v-icon>mdi-map-marker-radius</v-icon>
                                    {{ post.region }} , {{ post.province }} , {{ post.country }}

                                </p>
                                <div class="d-flex align-items-center justify-content-between mb-3 mt-auto">
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

                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>

</template>

<script>
import axios from 'axios';
import router from '@/router';
import Swal from 'sweetalert2';

import AES from 'crypto-js/aes';
export default {

    data: () => ({
        posts: [],
        loading: false,
    }),

    mounted() {
        localStorage.removeItem('openTab');
        this.fetchPendingPosts();
    },

    methods: {
        encryptId(id) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
            return encryptedId
        },

        // clickPost(post_id) {
        //     // router.push('/PostsView')
        //     const afterEncrypt = this.encryptId(post_id);
        //     // this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
        //     this.$router.push({ name: 'postDetailView', params: { id: `${afterEncrypt} Admin_View` } });
        // },

        clickPost(post) {
            this.$emit('selectedPost', post);
            console.log("you clicked post!")
            console.log(post.photo_url);
        },

        truncateText(text, charLimit) {
            if (text.length > charLimit) {
                return text.slice(0, charLimit) + '...';
            }
            return text;
        },

        async fetchPendingPosts() {
            this.loading = true;
            try {
                const response = await axios.get('http://localhost:8083/posts/allPending');
                const data = response.data;

                data.forEach(post => {
                    let processedPost;
                    console.log(post);
                    if (post.rentpost) {
                        processedPost = this.processPostData(post, 'rentpost');
                    } else if (post.sellpost) {
                        processedPost = this.processPostData(post, 'sellpost');
                    }

                    if (processedPost) {
                        this.posts.unshift(processedPost);
                    }
                });
            } catch (error) {
                console.error('Error fetching posts:', error);
            } finally {
                this.loading = false;
            }
        },
        processPostData(post, type) {
            const postDetails = post[type];
            const imgUrls = Array.isArray(postDetails.image) ? postDetails.image : [postDetails.image];
            console.log(post);
            return {
                registerid: post.subUser.reg_user.register_id,
                subUserId: post.subUser.subUserId,
                post_id: post.post_id,
                status: post.status,
                province: postDetails.locations.province,
                region: postDetails.locations.region,
                country: postDetails.locations.countries.country_name,
                title: postDetails.title,
                description: postDetails.description,
                house_type: postDetails.house_type,
                property_type: postDetails.property_type,
                area: postDetails.area,
                price: postDetails.price,
                deposit: postDetails.deposit,  // Only available for rentpost, so it will be undefined for sellpost
                least_contract: postDetails.least_contract,  // Only available for rentpost, so it will be undefined for sellpost
                photo_url: imgUrls,
            };
        },

        approve(post) {

            function httpErrorHandler(error) {
                if (axios.isAxiosError(error)) {
                    const response = error?.response
                    if (response) {
                        const statusCode = response?.status
                        if (statusCode === 500) { alert("Please try again!!!") }
                        else if (statusCode === 400) { alert("Please try again!!!") }

                    }
                }
            }

            axios.put("http://localhost:8083/posts/update", post)
                .then(function (response) {
                    const status = JSON.parse(response.status);
                    if (status == '200') {
                        Swal.fire({
                            title: 'Successfully Approved!',
                            icon: 'success',
                            customClass: {
                                confirmButton: 'myCustomSuccessButton'
                            },
                            buttonsStyling: false,
                            allowOutsideClick: false,
                            allowEscapeKey: false
                        }).then(() => {
                            window.location.reload();
                        });
                    }
                })
                .catch(httpErrorHandler)
        },

        reject(post) {

            console.log(post);

            function httpErrorHandler(error) {
                if (axios.isAxiosError(error)) {
                    const response = error?.response
                    if (response) {
                        const statusCode = response?.status
                        if (statusCode === 500) { alert("Please try again!!!") }
                        else if (statusCode === 400) { alert("Please try again!!!") }

                    }
                }
            }

            axios.delete("http://localhost:8083/posts/decliePost", {
             params: {
                 subUserId: post.subUserId, postId: post.post_id}
             })
            .then(function (response) {
                const status = JSON.parse(response.status);
                if (status == '200') {
                    Swal.fire({
                        title: 'Successfully Canceled!',
                        icon: 'success',
                        customClass: {
                            confirmButton: 'myCustomSuccessButton'
                        },
                        buttonsStyling: false,
                        allowOutsideClick: false,
                        allowEscapeKey: false
                    }).then(() => {
                        window.location.reload();
                    });
                }
            })
            .catch(httpErrorHandler)
        },
        pushy() {
            router.push('/admin')
        },
        ban() {
            router.push('/admin/ban')
        },
        logout() {
            router.push('/admin/login')
        },
        pushhh() {
            router.push('/admin/post')
        }

    }
}

</script>

<style>
/*
.container {
    display: flex;
    margin-top: 20px;
    border-top: solid #e86f52 1px;
    border-bottom: solid #e86f52 1px;
    border-right: solid #e86f52 1px;

}
*/

.box1 {
    /* Distribute available space equally */
    padding: 20px;



    background-color: #e86f52;
    color: white;
    height: auto;


}

.box2 {
    /* Distribute available space equally */


}

.sidebar-link {


    text-decoration: none;
    color: white;
    background-color:
        #e86f52;



}
</style>