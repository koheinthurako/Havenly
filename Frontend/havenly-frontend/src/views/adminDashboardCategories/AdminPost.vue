<template>

    <div class="container">
        <h1>All pending posts!</h1>
        <!-- <div class="box1">
            <div id="sidebar" ref="sidebar" :class="{ expand: isExpanded }">
                <div class="d-flex">


                    <v-icon>mdi-view-grid</v-icon>


                    <ul class="sidebar-nav ">
                        <li class="sidebar-item">
                            <a class="sidebar-link" @click="pushhh()">
                                <span>Pending Posts</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" @click="pushy()">
                                <span>Users</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" @click="ban()">

                                <span>Ban List</span>
                            </a>
                        </li>



                    </ul>

                </div>

                <div class="d-flex custom-logout" @click="logout" style="cursor:pointer;">
                    <button class="toggle-btn" type="button">
                        <v-icon>mdi-logout</v-icon>
                    </button>
                    <div class="sidebar-logo">
                        <a class="sidebar-link">&nbsp; &nbsp; &nbsp; &nbsp;Logout</a>
                    </div>
                </div>

            </div>
        </div> -->

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
                <div v-for="post in posts" :key="post.post_id" class="col-md-3">


                    <div class="card-container">
                        <!-- TZH card styles -->
                        <div class="card" style="height: 400px;">
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
                                <!-- <p class="mb-0">{{ post.status }}</p> -->
                                <div class="d-flex align-items-center justify-content-between mb-0">
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
                                <v-btn elevation="0" variant="outlined" @click="approve(post)"
                                    style="text-transform:capitalize;">Approve</v-btn>
                                <v-spacer></v-spacer>
                                <v-btn elevation="0" variant="outlined" @click="cancel(post)"
                                    style="text-transform:capitalize;">Cancel</v-btn>
                            </v-card-actions>
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

            return {
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

        // fetchPosts() {
        //     // Make API call to fetch posts from backend
        //     fetch('http://localhost:8083/posts/allPending')
        //         .then(response => response.json())
        //         .then(data => {
        //             console.log(data);
        //             data.forEach(post => {
        //                 if (post.rentpost) {
        //                     let imgUrls = Array.isArray(post.rentpost.image) ? post.rentpost.image : [post.rentpost.image]

        //                     console.log(post)
        //                     this.posts.unshift({
        //                         post_id: post.post_id,
        //                         status: post.status,
        //                         province: post.rentpost.locations.province,
        //                         region: post.rentpost.locations.region,
        //                         country: post.rentpost.locations.countries.country_name,
        //                         title: post.rentpost.title,
        //                         description: post.rentpost.description,
        //                         house_type: post.rentpost.house_type,
        //                         property_type: post.rentpost.property_type,
        //                         area: post.rentpost.area,
        //                         price: post.rentpost.price,
        //                         deposit: post.rentpost.deposit,
        //                         least_contract: post.rentpost.least_contract,
        //                         photo_url: imgUrls,
        //                     });
        //                     console.log(imgUrls)
        //                 } else if (post.sellpost) {
        //                     let imgUrls = Array.isArray(post.sellpost.image) ? post.sellpost.image : [post.sellpost.image]

        //                     console.log(post)
        //                     this.posts.unshift({
        //                         post_id: post.post_id,
        //                         status: post.status,
        //                         province: post.sellpost.locations.province,
        //                         region: post.sellpost.locations.region,
        //                         country: post.sellpost.locations.countries.country_name,
        //                         title: post.sellpost.title,
        //                         description: post.sellpost.description,
        //                         house_type: post.sellpost.house_type,
        //                         property_type: post.sellpost.property_type,
        //                         area: post.sellpost.area,
        //                         price: post.sellpost.price,
        //                         photo_url: imgUrls,
        //                     });
        //                     console.log(imgUrls)
        //                 }

        //             });
        //             // console.log(this.posts);
        //         })
        //         .catch(error => {
        //             console.error('Error fetching photos:', error);
        //         });
        // },

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
        cancel(post) {

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

            axios.put("http://localhost:8083/posts/decline", post)
                .then(function (response) {
                    const status = JSON.parse(response.status);
                    if (status == '200') {
                        Swal.fire({
                            title: 'Successfully Canceled!',
                            icon: 'success',
                            customClass: {
                                confirmButton: 'myCustomButton'
                            },
                            buttonsStyling: false,
                            allowOutsideClick: false,
                            allowEscapeKey: false
                        }).then(() => {
                            window.location.reload();
                        });
                        alert("Canceled  Successfully")

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