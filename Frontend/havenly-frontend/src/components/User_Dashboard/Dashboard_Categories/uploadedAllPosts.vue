<template>
    <v-container>
        <main>
            <div>
                <div class="row mb-5 g-3">
                <div v-for="post in posts" :key="post.post_id" class="col-md-3">
                    <div class="card-container">
                        <!-- TZH card styles -->
                        <div class="card cursor-pointer" style="height: 460px;">
                            <!-- <div v-for="url in post.photo_urls" :key="url" class="cardImgBox mb-2">
                                <img :src="url" class="w-100 h-100" alt="Card image cap">
                            </div> -->
                            <div class="cardImgBox mb-2">
                                <img :src="post.photo_url[0]" class="w-100 h-100" alt="Card image cap">
                            </div>
                            <div class="card-body p-3 d-flex flex-column">
                                <div class="d-flex gap-1">
                                    <div class="mb-2">
                                        <span class="px-3 badge rounded-pill text-uppercase small d-inline" 
                                        :class="getStatusClass(post.post_type)">{{ post.post_type }}</span>
                                    </div>
                                    <div class="mb-2">
                                        <span class="px-3 badge rounded-pill text-uppercase small d-inline" 
                                        :class="getStatusClass(post.status)">{{ post.status }}</span>
                                    </div>
                                </div>
                                <h5 class="card-title mb-3">{{ post.title }}</h5>
                                <p class="card-text small opacity-75">{{ post.description }}</p>
                                <!-- <div class="d-flex mb-3 justify-content-between">
                                    <span v-if="post.deposit" class="small opacity-75">Deposit : {{ post.deposit }}</span>
                                    <span v-if="post.least_contract" class="small opacity-75">Contract : {{ post.least_contract }}</span>
                                </div> -->
                                <p class="card-text text-danger small mb-auto opacity-75 mb-auto">
                                    <v-icon >mdi-map-marker-radius</v-icon>
                                    {{ post.region }} , {{ post.province }} , {{ post.country }}
                                </p>
                                
                                <div class="buttonBox d-flex justify-content-between gap-3 mb-3">
                                    <button class="btn btn-outline-danger w-100" @click="editPost(post)" data-bs-toggle="modal" data-bs-target="#editModal">Edit</button>
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

                <!-- Edit Modal Box -->
                <div class="editModalBox">
                    <div class="modal fade" id="editModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg d-flex align-items-center fixed-top">
                            <div class="modal-content" style="max-width: 2000px !important;">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">EDIT {{ currentPost.post_type }}</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body" style="overflow-y:auto; max-height: 70vh;">
                                    <form @submit.prevent="submit" enctype="multipart/form-data" class="w-100 px-4 py-3">
                                        <div class="row justify-content-between">
                                            <div class="col-md-2 col-sm-12">
                                                <span class="float-left mt-2 small">Title <span class="text-red">*</span></span>
                                            </div>
                                            <div class="col-md-9 col-sm-12">
                                                <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                                    rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                                    v-model="currentPost.title" placeholder="Title"></v-text-field>
                                            </div>
                                        </div>

                                        <div class="row justify-content-between">
                                            <div class="col-md-2 col-sm-12 py-0">
                                                <span class="float-left mt-2 small">Description<span class="text-red">*</span>
                                                </span>
                                            </div>
                                            <div class="col-md-9 col-sm-12 py-0">
                                                <v-textarea bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                                    variant="solo" rounded="lg" density="compact" v-model="currentPost.description"
                                                    :counter="10000"
                                                    placeholder="Description"></v-textarea>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="p-0 row-1 d-flex">
                                            <v-select bg-color="white" v-model="selectedCountry" :items="uniqueCountries"
                                                label="Select country" required></v-select>
                                            <v-select bg-color="white" v-model="selectedProvince" :items="uniqueProvinces"
                                                :disabled="!selectedCountry" label="Select province" required></v-select>
                                            <v-select bg-color="white" v-model="selectedAmphoe" :items="uniqueAmphoes"
                                                :disabled="!selectedProvince" label="Select amphoe" required></v-select>
                                            <v-select bg-color="white" v-model="selectedRegion" :items="uniqueRegions"
                                                :disabled="!selectedAmphoe" label="Select region" required></v-select>
                                            <v-select bg-color="white" v-model="selectedLocation" :items="uniqueLocations"
                                                :disabled="!selectedRegion" label="Country_id" required></v-select>
                                        </div>

                                        <div class="row justify-content-between">
                                            <div class="col-md-2 col-sm-12">
                                                <span class="float-left mt-2 small"> Property Type </span>
                                            </div>
                                            <div class="col-md-9 col-sm-12">
                                                <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                                    variant="solo" rounded="lg" density="compact"
                                                    v-model="currentPost.property_type" :items="PropertyTypes"
                                                    placeholder="Select property type"></v-select>
                                            </div>
                                        </div>

                                        <div class="row justify-content-between">
                                            <div class="col-md-2 col-sm-12">
                                                <span class="float-left mt-2 small">Price($Dollar)<span
                                                        class="text-red">*</span></span>
                                            </div>
                                            <div class="col-md-9 col-sm-12">
                                                <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                                    rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                                    v-model="currentPost.price" placeholder="price"></v-text-field>
                                            </div>
                                        </div>

                                        <div class="row justify-content-between">
                                            <div class="col-md-2 col-sm-12">
                                                <span class="float-left mt-2 small">Area</span>
                                            </div>
                                            <div class="col-md-9 col-sm-12">
                                                <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                                    rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                                    v-model="currentPost.area" placeholder="Area"></v-text-field>
                                            </div>
                                        </div>

                                        <div class="row justify-content-between">
                                            <div class="col-md-3 col-sm-12 py-0">
                                                <span class="float-left mt-2 small">Choose Image<span class="text-red">*</span>
                                                </span>
                                            </div>
                                            <div class="col-md-9 col-sm-12 py-0" @click="showUploadPhoto(currentPost.photo_url)">
                                                <v-file-input counter multiple color="deep-purple-accent-4" chips
                                                    truncate-length="15" v-model="currentPost.photo_url"
                                                    accept="image/png, image/jpeg, image/bmp" 
                                                    prepend-icon="mdi-camera"></v-file-input>
                                            </div>
                                        </div>

                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-danger">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Offcanvas -->
                <!-- <div class="editOffcanvasBox" v-for="post in posts" :key="post.post_id">
                    <div class="offcanvas offcanvas-top p-3" tabindex="-1" id="editOffcanvas" aria-labelledby="offcanvasTopLabel">
                        <form @submit.prevent="submit" enctype="multipart/form-data" class="w-100 px-4 py-3">
                            <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12">
                                    <span class="float-left mt-2 small">Title <span class="text-red">*</span></span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div> -->

            </div>
            </div>
        </main>
    </v-container>
</template>

<script>
import axios from 'axios';
// import * as bootstrap from 'bootstrap';

    export default {
        name: 'uploadedAllPosts',

        data: () => ({
            posts : [],
            currentPost: {},
            title: '',
            description: '',
            propertyTypes: '',
            price: '',
            locations: [],
            image: [],
        }),

        computed: {

                // uniqueCountries() {
                //     return [...new Set(this.locations.map(location => location.country_name))];
                // },

                // uniqueProvinces() {
                // // return [...new Set(this.locations.map(location => location.province))];
                //     return [...new Set(this.locations.filter(location => location.country_name === this.selectedCountry).map(location => location.province))];
                // },

                // uniqueAmphoes() {
                //     return [...new Set(this.locations.filter(location => location.province === this.selectedProvince).map(location => location.amphoe))];
                // },

                // uniqueRegions() {
                //     return [...new Set(this.locations.filter(location => location.amphoe === this.selectedAmphoe).map(location => location.region))];
                // },

                // uniqueLocations() {
                //     return [...new Set(this.locations.filter(location => location.region === this.selectedRegion).map(location => location.location_id))];
                // },

                // filteredLocations() {
                // return this.locations.filter(location =>
                //     location.country_name === this.selectedCountry &&
                //     location.province === this.selectedProvince &&
                //     location.amphoe === this.selectedAmphoe &&
                //     location.region === this.selectedRegion
                // );
                // }
            },

        mounted() {
            const cachedData = this.getLocationsFromSessionStorage();
            if(cachedData) {
                this.locations = cachedData;
            } else {
                this.fetchLocations();
            }

            this.fetchPosts();
        },

        methods: {

            fetchLocations() {
            fetch('http://localhost:8083/locations/getall')
            .then(response => response.json())
            .then(data => {
                const mappedData = data.map(location => ({
                    location_id: location.location_id,
                    country_name: location.country_name,
                    province: location.province,
                    amphoe: location.amphoe,
                    region: location.region,
                    latitude: location.latitude,
                    longitude: location.longitude
                }));
                sessionStorage.setItem('locations', JSON.stringify(mappedData));
                this.locations = mappedData;
            })
            .catch(error => {
                console.error('Error fetching locations:', error);
            });
            },

            getLocationsFromSessionStorage() {
                const data = sessionStorage.getItem('locations');
                return data ? JSON.parse(data) : null;
            },

            showUploadPhoto(images) {
                console.log("images ko click lite tl")
                console.log(images);
            },

            fetchPosts() {
                const user = JSON.parse(sessionStorage.getItem('sub_user'));
                const subUserId = user.subUserId;
                // Make API call to fetch posts from backend
                axios.get('http://localhost:8083/posts/allSubuserPosts', {
                      params: {
                        subUserId: subUserId
                      }
                })
                .then(response => {
                    console.log(response.data)
                    response.data.forEach(post => {
                        if(post.testrentposts) {
                            if(post.testrentposts.description.length > 60) {
                                let des = post.testrentposts.description;
                                post.testrentposts.description = des.substring(0, 60) + "...";
                            }
                            
                            let imageUrls = Array.isArray(post.testrentposts.image) ? post.testrentposts.image : [post.testrentposts.image];
                            console.log(imageUrls)
                            console.log(post);
                            this.posts.unshift({
                                post_id: post.post_id,
                                province: post.testrentposts.locations.province,
                                region: post.testrentposts.locations.region,
                                country: post.testrentposts.locations.countries.country_name,
                                title: post.testrentposts.title,
                                description: post.testrentposts.description,
                                property_type: post.testrentposts.property_type,
                                area: post.testrentposts.area,
                                price: post.testrentposts.price,
                                deposit: post.testrentposts.deposit,
                                least_contract: post.testrentposts.least_contract,
                                photo_url: imageUrls,
                                status: post.status,
                                post_type: 'RENT POST',
                            });
                            console.log(typeof(imageUrls))
                        } else if (post.testsellpostss) {
                            console.log(post);
                            if(post.testsellpostss.description.length > 60) {
                                let des = post.testsellpostss.description;
                                post.testsellpostss.description = des.substring(0, 60) + "...";
                            }
                            
                            let imageUrls = Array.isArray(post.testsellpostss.image) ? post.testsellpostss.image : [post.testsellpostss.image];
                            console.log(imageUrls)
                            console.log(post);
                            this.posts.unshift({
                                post_id: post.post_id,
                                province: post.testsellpostss.locations.province,
                                region: post.testsellpostss.locations.region,
                                country: post.testsellpostss.locations.countries.country_name,
                                title: post.testsellpostss.title,
                                description: post.testsellpostss.description,
                                property_type: post.testsellpostss.property_type,
                                area: post.testsellpostss.area,
                                price: post.testsellpostss.price,
                                photo_url: imageUrls,
                                status: post.status,
                                post_type: 'SELL POST',
                            });
                        }
                        
                    });
                })
            },

            editPost(post) {
                this.currentPost = { ...post };
                console.log(this.currentPost);
                // this.showModal();
                console.log(post.title + " ko click lite tl")
                const { post_id } = post;
                // console.log(Object.values(post));
                console.log(post_id);
            },

            // showModal() {
            //     // Ensure the modal element is available before initializing the modal
            //     this.$nextTick(() => {
            //         const modalElement = document.getElementById('editModal');
            //         if (modalElement) {
            //             const editModal = new bootstrap.Modal(modalElement);
            //             editModal.show();
            //         }
            //     });
            // },

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

<script setup>
    import { ref } from 'vue'

    // const propertyTypes = useField('propertyTypes')
    const PropertyTypes = ref([
        'Condo',
        'Apartment',
        'House'
    ])

</script>

<style>

    .card-container .card {
        transition: 0.2s
    }

    .card-container .card:hover {
        transform: scale(1.007);
    }

    .editOffcanvasBox {
        z-index: 3000;
    }

    .editModalBox {
        position: absolute;
        z-index: 2000;
    }

    body.modal-open {
        overflow: hidden;
    }

    .modal {
        display: flex;
        align-items: center;
    }
    

</style>