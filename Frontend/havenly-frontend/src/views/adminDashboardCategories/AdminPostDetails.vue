<template>
    <div v-if="post" id="adminPostView">
        <div class="mainContainer px-5 mb-lg-5">
            <div class="d-flex align-items-center justify-content-between" style="margin-top: -50px;">
                <button @click="goBack" class="btn ps-4 pe-5 btn-outline-danger bg-redbrick">
                <v-icon class="s-icon">mdi-chevron-double-left</v-icon> Back
            </button>
                <div v-if="regUser!=null" class="d-flex flex-column align-items-end">
                    <h5 class="text-brick">{{ regUser.email }}</h5>
                    <p class="small m-0">{{ regUser.nrc }}</p>
                </div>
            </div>
            <h2 class="my-lg-4 p-0"><strong>{{ post.title }}</strong></h2>
            
            <div class="row flex-wrap" :class="imageControl(post)">
                <div v-for="(photo, index) in post.photo_url" :key="index" class="col m-0 p-0">
                    <v-img :src="photo" class="upper-image card-img w-100 h-100" style="cursor: pointer;"></v-img>
                </div>
            </div>
            <hr class="my-lg-5">
            <h3 class="color-brick mb-4">Post Description</h3>
            <p class="mb-4">{{ post.description }}</p>
            <hr class="my-lg-5">
            <div class="row">
                <div class="row p-0">
                    <div class="col-6">
                        <div class="d-flex">
                            <v-icon class="s-icon">mdi-map-marker</v-icon>
                            <p> &nbsp; {{ post.province }} / {{
                                post.region }} / {{
                                    post.country }}
                            </p>
                        </div>
                        <div class="d-flex">
                            <v-icon class="s-icon">mdi-office-building</v-icon>
                            <p> &nbsp; {{ post.property_type }}</p>
                        </div>

                        <div class="d-flex">
                            <v-icon class="s-icon">mdi-arrow-expand-all</v-icon>
                            <p> &nbsp; {{ post.area }} Square Ft </p>
                        </div>

                        <div v-if="post.deposit" class="d-flex">
                            <v-icon class="s-icon">mdi-cash</v-icon>
                            <p> &nbsp; {{ post.deposit }} (Deposit)</p>
                        </div>

                        <div v-if="post.least_contract" class="d-flex">
                            <v-icon class="s-icon">mdi-file</v-icon>
                            <p> &nbsp; {{ post.least_contract }} (Contract)</p>
                        </div>

                    </div>
                    <div class="col-6">
                        <div class="d-flex">
                            <v-icon class="s-icon">mdi-tag-multiple</v-icon>
                            <p> &nbsp; Price</p><br>
                        </div>
                        <p class="price"> {{ post.price }}</p>

                    </div>
                </div>
            </div>
            <hr class="my-lg-5">
            <div class="d-flex gap-3">
                <button @click="approve(post)" class="btn px-5 btn-success">Approve</button>
                <button @click="decline(post)" class="btn px-5 btn-outline-danger">Decline</button>
            </div>
        </div>
        
    </div>
    <div v-else>
        <p>No post selected</p>
    </div>
</template>

<script>

    import Swal from 'sweetalert2';
    import axios from 'axios';

    export default {
        props: {
            post: {
                type: Object,
                default: null,
            },
        },

        data: () => ({
            regUser: null,
        }),

        mounted() {
            this.fetchSubUser(this.post);
        },

        methods: {
            goBack() {
                this.$emit('back'); // Emitting 'back' event when back button is clicked
            },

            approve(post) {
                function httpErrorHandler(error) {
                    if (axios.isAxiosError(error)) {
                        const response = error?.response
                        if (response) {
                            const statusCode = response?.status
                            if (statusCode === 500 || statusCode === 400) {
                                Swal.fire({
                                    title: 'Error!',
                                    text: 'Please try again!!!',
                                    icon: 'error',
                                    customClass: {
                                        confirmButton: 'myCustomErrorButton'
                                    },
                                    buttonsStyling: false,
                                    allowOutsideClick: false,
                                    allowEscapeKey: false
                                });
                            }
                        }
                    }
                }

                Swal.fire({
                    title: 'Are you sure?',
                    text: "Do you want to approve this post?",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: "#E86F52",
                    cancelButtonColor: "##525252",
                    confirmButtonText: 'Yes, approve it!',
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire({
                            title: 'Processing...',
                            text: 'Please wait while the post is being approved.',
                            allowOutsideClick: false,
                            allowEscapeKey: false,
                            didOpen: () => {
                                Swal.showLoading();
                            }
                        });

                        axios.put("http://localhost:8083/posts/update", post)
                            .then(function (response) {
                                const status = response.status; // No need to parse status
                                if (status === 200) {
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
                            .catch(httpErrorHandler);
                    }
                });
            },

            decline(post) {
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

                Swal.fire({
                    title: "Are you sure?",
                    text: "You won't be able to revert this post!",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#E86F52",
                    cancelButtonColor: "##525252",
                    confirmButtonText: "Yes, delete it!",
                    reverseButtons: true,
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire({
                            title: 'Processing...',
                            text: 'Please wait while the post is being deleted.',
                            allowOutsideClick: false,
                            allowEscapeKey: false,
                            didOpen: () => {
                                Swal.showLoading();
                            }
                        });
                        axios.delete("http://localhost:8083/posts/decliePost", {
                        params: {
                            subUserId: post.subUserId, postId: post.post_id}
                        })
                        .then(function (response) {
                            const status = JSON.parse(response.status);
                            if (status == '200') {
                                Swal.fire({
                                    title: 'Successfully Declined!',
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
                    }

                });

                
            },

            imageControl(post) {
                if (!post.photo_url || !Array.isArray(post.photo_url)) {
                    return 'row-cols-1';
                }

                const photoLength = post.photo_url.length;
                console.log(post);
                console.log(photoLength);

                if (photoLength >= 3) {
                    return 'row-cols-4';
                } else if (photoLength >= 2) {
                    return 'row-cols-3';
                } else if (photoLength >= 1) {
                    return 'row-cols-2';
                } else {
                    return 'row-cols-1';
                }
            },

            fetchSubUser(post) {
                try {
                    console.log(post.registerid + " Dr ka register Id from fetchSubUser");
                    axios.get('http://localhost:8083/subscribe/getSubUserInfo', {
                        params: { registerId: post.registerid }
                    }).then(response => {
                        this.regUser = response.data;
                        console.log(this.regUser);
                    })
                } catch (error) {
                    console.error('Error fetching data:', error);
                }
            },

        }
    
    };

</script>

<style>

    #adminPostView .mainContainer .v-responsive .v-img__img.v-img__img--contain{
        object-fit: fill !important;
    }

</style>