<template>

    <v-container>
        <main>

            <div v-if="loading">
                    <v-row class="g-3 mt-4 d-flex flex-column">
                        <v-col cols="12" md="12">
                            <v-skeleton-loader class="mx-auto" elevation="2" height="300px"
                                type="card-avatar, article, actions"></v-skeleton-loader>
                        </v-col>

                        <v-col cols="12" md="12">
                            <v-skeleton-loader class="mx-auto" elevation="2" height="300px"
                                type="card-avatar, article, actions"></v-skeleton-loader>
                        </v-col>

                        <v-col cols="12" md="12">
                            <v-skeleton-loader class="mx-auto" elevation="2" height="300px"
                                type="card-avatar, article, actions"></v-skeleton-loader>
                        </v-col>

                        <v-col cols="12" md="12">
                            <v-skeleton-loader class="mx-auto" elevation="2" height="300px"
                                type="card-avatar, article, actions"></v-skeleton-loader>
                        </v-col>

                    </v-row>
                </div>
                <div v-else-if="displayError">{{ displayError }}</div>

            <div class="pt-5">
                <div class="row mb-3 g-3">
                    <div v-for="post in posts" :key="post.post_id" class="col-md-3 col-sm-12 element-to-scroll-to"
                        @click="clickPost(post.post_id)">
                        <div class="card-container">
                            <div class="card cursor-pointer" style="height: 390px;">
                                <div class="cardImgBox" style="width: 100%; height: 160px;">
                                    <img :src="post.photo_url[0]" class="h-100 w-100 m-auto" alt="Card image cap">
                                </div>
                                <div class="card-body p-3 d-flex flex-column">
                                    <h5 class="card-title mb-2">{{ post.title }}</h5>
                                    <p class="card-text small opacity-75 mb-1">{{ post.shortDescription }}</p>
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

        </main>
    </v-container>
    
</template>

<script>
import axios from 'axios';
import AES from 'crypto-js/aes';
import Utf8 from 'crypto-js/enc-utf8';
import Swal from 'sweetalert2';


    export default {
        name: 'PostByLocations',

        props: {
            encryptedLocationId: {
            type: String,
            required: true,
            },
        },

        data: () => ({
            loading: false,
            displayError: null,
            locations: [],
            mapLocations: [],
            posts: [],
            shortDescription: '',
            fullDescription: '',
        }),

        // mounted() {
        //     this.fetchPostsByLocation();
        // },

        watch: {
            encryptedLocationId(newId) {
                this.fetchPostsByLocation(newId);
            },
        },

        methods: {

            splitData(data) {
                return data.split(' ')[0];
            },

            encryptId(id) {
                const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
                const encryptedId = AES.encrypt(id.toString(), secretKey).toString();
                return encryptedId;
            },

            decryptId(encryptedId) {
                const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
                const decryptedBytes = AES.decrypt(encryptedId, secretKey);
                const decryptedId = decryptedBytes.toString(Utf8);
                console.log(decryptedId + "decryptedId");
                return parseInt(decryptedId, 10);
                // return decryptedId;
            },

            clickPost(post_id) {
                const afterEncrypt = this.encryptId(post_id);
                this.$router.push({ name: 'postDetailView', params: { id: `${afterEncrypt} Success` } });
            },

            async fetchLocations() {

                try {

                    Swal.fire({
                        title: 'Loading',
                        text: 'Fetching locations...',
                        icon: 'info',
                        allowOutsideClick: false,
                        allowEscapeKey: false,
                        showConfirmButton: false,
                        willOpen: () => {
                            Swal.showLoading();
                        }
                    });

                    const response = await fetch('http://localhost:8083/locations/getall');
                    const data = await response.json();
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
                    this.mapLocations = mappedData;
                    Swal.close();
                } catch (error) {
                    console.error('Error fetching locations:', error);
                }
            },

            getLocationsFromSessionStorage() {
                const data = sessionStorage.getItem('locations');
                return data ? JSON.parse(data) : null;
            },

            async fetchPostsByLocation(encryptedLocationId) {
                this.posts.splice(0, this.posts.length);
                this.loading = true;
                this.displayError = null;
                const decryptLocationId = this.decryptId(encryptedLocationId);
                console.log(decryptLocationId + " backend ko pot lite tae locationId");

                axios.get('http://localhost:8083/posts/postsByLocation', {
                    params: {
                        locationId: decryptLocationId
                    }
                })
                .then(response => {
                    response.data.forEach(post => {
                        console.log(post);
                        if (post.rentpost) {
                            let des = post.rentpost.description;
                            let shortDescription = des.length > 60 ? des.substring(0, 60) + "..." : des;

                            let imageUrls = Array.isArray(post.rentpost.image) ? post.rentpost.image : [post.rentpost.image];
                            console.log(imageUrls);
                            console.log(post);

                            this.posts.unshift({
                                post_id: post.post_id,
                                rentPostId: post.rentpost.rent_post_id,
                                province: post.rentpost.locations.province,
                                region: post.rentpost.locations.region,
                                amphoe: post.rentpost.locations.amphoe,
                                location_id: post.rentpost.locations.location_id,
                                country: post.rentpost.locations.countries.country_name,
                                title: post.rentpost.title,
                                shortDescription: shortDescription,
                                fullDescription: post.rentpost.description,
                                property_type: post.rentpost.property_type,
                                area: post.rentpost.area,
                                price: post.rentpost.price,
                                deposit: post.rentpost.deposit,
                                least_contract: post.rentpost.least_contract,
                                photo_url: imageUrls,
                                status: post.status,
                                post_type: post.post_type,
                            });
                        } else if (post.sellpost) {
                            console.log(post);
                            let des = post.sellpost.description;
                            let shortDescription = des.length > 60 ? des.substring(0, 60) + "..." : des;

                            let imageUrls = Array.isArray(post.sellpost.image) ? post.sellpost.image : [post.sellpost.image];
                            console.log(imageUrls);
                            console.log(post);

                            this.posts.unshift({
                                post_id: post.post_id,
                                sellPostId: post.sellpost.sell_post_id,
                                province: post.sellpost.locations.province,
                                region: post.sellpost.locations.region,
                                amphoe: post.sellpost.locations.amphoe,
                                location_id: post.sellpost.locations.location_id,
                                country: post.sellpost.locations.countries.country_name,
                                title: post.sellpost.title,
                                shortDescription: shortDescription,
                                fullDescription: post.sellpost.description,
                                property_type: post.sellpost.property_type,
                                area: post.sellpost.area,
                                price: post.sellpost.price,
                                photo_url: imageUrls,
                                status: post.status,
                                post_type: post.post_type,
                            });
                        }
                    });
                })
                .catch(error => {
                    console.error('Error fetching posts:', error);
                    this.displayError = 'Error fetching posts';
                })
                .finally(() => {
                    this.loading = false;  // Stop loading
                });

            }

        },

        mounted() {

            const cachedData = this.getLocationsFromSessionStorage();
            if(cachedData) {
                this.locations = cachedData;
                this.mapLocations = cachedData;
            } else {
                this.fetchLocations();
            }
            

            if (this.encryptedLocationId) {
                this.fetchPostsByLocation(this.encryptedLocationId);
            }
        },

    }

</script>

<style>



</style>