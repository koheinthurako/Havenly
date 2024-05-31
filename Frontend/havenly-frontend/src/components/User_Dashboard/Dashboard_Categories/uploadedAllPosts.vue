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
                                <p class="card-text small opacity-75">{{ post.shortDescription }}</p>
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
                                    <button class="btn btn-danger w-100" @click="deletePost(post)">Delete</button>
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
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Edit {{ currentPost.post_type }}</h1>
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
                                                    v-model="title" placeholder="Title"></v-text-field>
                                            </div>
                                        </div>

                                        <div class="row justify-content-between">
                                            <div class="col-md-2 col-sm-12 py-0">
                                                <span class="float-left mt-2 small">Description<span class="text-red">*</span>
                                                </span>
                                            </div>
                                            <div class="col-md-9 col-sm-12 py-0">
                                                <v-textarea bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                                    variant="solo" rounded="lg" density="compact" v-model="fullDescription"
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
                                                    v-model="property_type" :items="PropertyTypes"
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
                                                    v-model="price" placeholder="price"></v-text-field>
                                            </div>
                                        </div>

                                        <div class="row justify-content-between">
                                            <div class="col-md-2 col-sm-12">
                                                <span class="float-left mt-2 small">Area</span>
                                            </div>
                                            <div class="col-md-9 col-sm-12">
                                                <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                                    rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                                    v-model="area" placeholder="Area"></v-text-field>
                                            </div>
                                        </div>
                                        <div v-if="currentPost.deposit" class="row justify-content-between">
                                            <div class="col-md-2 col-sm-12">
                                                <span class="float-left mt-2 small">Deposit</span>
                                            </div>
                                            <div class="col-md-9 col-sm-12">
                                                <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                                    rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                                    v-model="deposit" placeholder="Deposit"></v-text-field>
                                            </div>
                                        </div>
                                        <div v-if="currentPost.least_contract" class="row justify-content-between">
                                            <div class="col-md-2 col-sm-12">
                                                <span class="float-left mt-2 small">Least Contract</span>
                                            </div>
                                            <div class="col-md-9 col-sm-12">
                                                <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                                    rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                                    v-model="least_contract" placeholder="Least Contract"></v-text-field>
                                            </div>
                                        </div>

                                        <!-- <div class="row justify-content-between">
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
                                        </div> -->

                                        <div :key="forceRerender">
                                            <div class="row justify-content-between">
                                                <div class="col-md-3 col-sm-12 py-0">
                                                    <span class="float-left mt-2 small">Replace Image<span class="text-red">*</span></span>
                                                </div>
                                                <div class="col-md-9 col-sm-12 py-0 d-flex flex-column flex-md-row">
                                                    <div v-for="(photo, index) in currentPost.photo_url" :key="'photo_' + index" class="mb-2 col-12 col-md-4">
                                                        <img :src="photo" class="img-thumbnail" @click="setClickedPhotoIndex(index)" alt="Current Post Image">
                                                    </div>
                                                    <!-- <div v-for="(photo, index) in currentPost.photo_url" :key="'photo_' + index" class="mb-2">
                                                        <img :src="photo" class="img-thumbnail" @click="setClickedPhotoIndex(index)" alt="Current Post Image">
                                                    </div> -->
                                                    <!-- Hidden file input -->
                                                    <input type="file" ref="fileInput" style="display: none;" @change="handleFileChange">
                                                </div>
                                            </div>
                                        </div>

                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-danger" @click="submit(currentPost)">Save changes</button>
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
import { toRaw, shallowRef } from 'vue';
// import * as bootstrap from 'bootstrap';

function dataURLtoFile(dataUrl) {
  const arr = dataUrl.split(',');
  const mime = arr[0].match(/:(.*?);/)[1];
  const bstr = atob(arr[1]);
  let n = bstr.length;
  const u8arr = new Uint8Array(n);
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n);
  }
  return new File([u8arr], 'resizedImage.jpg', { type: mime });
}

    export default {
        name: 'uploadedAllPosts',

        data: () => ({
            posts : [],
            post_id: '',
            currentPost: shallowRef({}),
            location_id: '',
            title: '',
            shortDescription: '',
            fullDescription: '',
            property_type: '',
            price: '',
            area: '',
            deposit: '',
            least_contract: '',
            locations: [],
            selectedCountry: '',
            selectedProvince: '',
            selectedAmphoe: '',
            selectedRegion: '',
            selectedLocation: '',
            image: shallowRef([]),
            clickedPhotoIndex: null,
            forceRerender: false,
        }),

        computed: {

                uniqueCountries() {
                    return [...new Set(this.locations.map(location => location.country_name))];
                },

                uniqueProvinces() {
                // return [...new Set(this.locations.map(location => location.province))];
                    return [...new Set(this.locations.filter(location => location.country_name === this.selectedCountry).map(location => location.province))];
                },

                uniqueAmphoes() {
                    return [...new Set(this.locations.filter(location => location.province === this.selectedProvince).map(location => location.amphoe))];
                },

                uniqueRegions() {
                    return [...new Set(this.locations.filter(location => location.amphoe === this.selectedAmphoe).map(location => location.region))];
                },

                uniqueLocations() {
                    return [...new Set(this.locations.filter(location => location.region === this.selectedRegion).map(location => location.location_id))];
                },

                filteredLocations() {
                return this.locations.filter(location =>
                    location.country_name === this.selectedCountry &&
                    location.province === this.selectedProvince &&
                    location.amphoe === this.selectedAmphoe &&
                    location.region === this.selectedRegion
                );
                }
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

            setClickedPhotoIndex(index) {
                this.clickedPhotoIndex = index;
                this.$refs.fileInput.click();
                console.log(this.title);
                console.log(this.fullDescription);
                console.log(this.selectedLocation);
                console.log(this.property_type);
                console.log(this.price);
                console.log(this.area);
                console.log(this.deposit);
                console.log(this.least_contract);
                // console.log(this.image);
            },

            handleFileChange(event) {
                const file = event.target.files[0];
                const maxSize = 30 * 1024 * 1024; // 30 MB
                if (file.size > maxSize) {
                    alert('File size exceeds the limit of 30 MB.');
                    return;
                }
                console.log(file.size);
                const clickedIndex = this.clickedPhotoIndex;
                if (file) {
                    let reader = new FileReader();
                    reader.onload = () => {
                        this.currentPost.photo_url.splice(clickedIndex, 1, reader.result);
                        this.forceRerender = !this.forceRerender;
                    };
                    reader.readAsDataURL(file);
                    console.log(this.currentPost.photo_url);
                }
            },



            async submit(currentPost) {
                const formData = new FormData();
                
                formData.append('postId', currentPost.post_id);
                formData.append('title', this.title);
                formData.append('description', this.fullDescription);
                formData.append('property_type', this.property_type);
                formData.append('price', this.price);
                formData.append('area', this.area);
                formData.append('location_id', this.selectedLocation);

                for (const base64String of currentPost.photo_url) {
                    // Convert base64 string to Blob object
                    const blob = await this.base64ToBlob(base64String);
                    const resizedFile = await this.resizeImage(blob);
                    formData.append('files', resizedFile);
                }

                Swal.fire({
                    title: 'Saving...',
                    text: 'Your post is being submitted. Please wait...',
                    allowOutsideClick: false,
                    allowEscapeKey: false,
                    didOpen: () => {
                    Swal.showLoading(); // Show loading spinner
                    }
                });

                if(this.currentPost.rentPostId) {
                    const rentId = currentPost.rentPostId;
                    formData.append('rentPostId', rentId);
                    formData.append('deposit', this.deposit);
                    formData.append('least_contract', this.least_contract);
                    try {
                        const response = await axios.put('http://localhost:8083/rentpost/editRentPost', formData, {});
                        console.log(response.status);
                        if(response.status === 200) {
                            Swal.fire({
                                title: 'Successfully Edited',
                                text: 'Your post is requested to admin now!',
                                icon: 'success',
                                customClass: {
                                    popup: 'mySwal',
                                    confirmButton: 'myCustomSuccessButton'
                                },
                                buttonsStyling: false,
                                allowOutsideClick: false,
                                allowEscapeKey: false
                            }).then(() => {
                                window.location.reload();
                            });
                        }
                    } catch (error) {
                        if (error.response) {
                            console.error('Error response:', error.response);
                            console.error('Error response data:', error.response.data);
                            console.error('Error response status:', error.response.status);
                            if (error.response.status === 413) {
                                alert('File size exceeds the limit!');
                            } else if (error.response.status === 400) {
                                alert('Bad Request');
                            } else {
                                alert(`Error: ${error.response.status} - ${error.response.data}`);
                            }
                        } else if (error.request) {
                            console.error('Error request:');
                            alert('No response received from server.');
                        } else {
                            alert('An error occurred while updating the post.');
                        }
                    }
                } else if(this.currentPost.sellPostId) {
                    const sellId = currentPost.sellPostId;
                    formData.append('sellPostId', sellId);
                    try {
                        const response = await axios.put('http://localhost:8083/sellpost/editsellpost', formData, {});
                        if(response.status === 200) {
                            Swal.fire({
                                title: 'Successfully Edited',
                                text: 'Your post is requested to admin now!',
                                icon: 'success',
                                customClass: {
                                    popup: 'mySwal',
                                    confirmButton: 'myCustomSuccessButton'
                                },
                                buttonsStyling: false,
                                allowOutsideClick: false,
                                allowEscapeKey: false
                            }).then(() => {
                                window.location.reload();
                            });
                        }
                    } catch (error) {
                        if (error.response) {
                            console.error('Error response:', error.response);
                            console.error('Error response data:', error.response.data);
                            console.error('Error response status:', error.response.status);
                            if (error.response.status === 413) {
                                alert('File size exceeds the limit!');
                            } else if (error.response.status === 400) {
                                alert('Bad Request');
                            } else {
                                alert(`Error: ${error.response.status} - ${error.response.data}`);
                            }
                        } else if (error.request) {
                            console.error('Error request:');
                            alert('No response received from server.');
                        } else {
                            alert('An error occurred while updating the post.');
                        }
                    }
                }
            },

            async base64ToBlob(base64String) {
                const byteCharacters = atob(base64String.split(',')[1]);
                const byteNumbers = new Array(byteCharacters.length);
                for (let i = 0; i < byteCharacters.length; i++) {
                    byteNumbers[i] = byteCharacters.charCodeAt(i);
                }
                const byteArray = new Uint8Array(byteNumbers);
                return new Blob([byteArray], { type: 'image/jpeg' }); // Change type if needed
            },

            async resizeImage(file) {
                return new Promise((resolve) => {
                    const reader = new FileReader();
                    reader.onload = function(event) {
                        const img = new Image();
                        img.onload = function() {
                            const canvas = document.createElement('canvas');
                            const ctx = canvas.getContext('2d');
                            const maxWidth = 800; // Set your desired maximum width
                            const maxHeight = 600; // Set your desired maximum height
                            let width = img.width;
                            let height = img.height;

                            // Calculate new dimensions while maintaining aspect ratio
                            if (width > height) {
                                if (width > maxWidth) {
                                    height *= maxWidth / width;
                                    width = maxWidth;
                                }
                            } else {
                                if (height > maxHeight) {
                                    width *= maxHeight / height;
                                    height = maxHeight;
                                }
                            }

                            canvas.width = width;
                            canvas.height = height;
                            ctx.drawImage(img, 0, 0, width, height);

                            // Convert the canvas content to base64
                            const resizedDataUrl = canvas.toDataURL('image/jpeg'); // Change format if needed
                            resolve(dataURLtoFile(resizedDataUrl));
                        };
                        img.src = event.target.result;
                    };
                    reader.readAsDataURL(file);
                });
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
                    response.data.forEach(post => {
                        console.log(post);
                        if(post.rentpost) {
                            let des = post.rentpost.description;
                            let shortDescription;
                            if(des.length > 60) {
                                shortDescription = des.substring(0, 60) + "...";
                                // post.rentpost.description = des.substring(0, 60) + "...";
                            }
                            
                            let imageUrls = Array.isArray(post.rentpost.image) ? post.rentpost.image : [post.rentpost.image];
                            console.log(imageUrls)
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
                            console.log(typeof(imageUrls))
                        } else if (post.sellpost) {
                            console.log(post);
                            let des = post.sellpost.description;
                            let shortDescription;
                            if(des.length > 60) {
                                shortDescription = des.substring(0, 60) + "...";
                                // post.testrentposts.description = des.substring(0, 60) + "...";
                            }
                            
                            let imageUrls = Array.isArray(post.sellpost.image) ? post.sellpost.image : [post.sellpost.image];
                            console.log(imageUrls)
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
            },

            editPost(post) {
                this.currentPost = toRaw(post);
                this.title = this.currentPost.title;
                this.fullDescription = this.currentPost.fullDescription;
                this.property_type = this.currentPost.property_type;
                this.price = this.currentPost.price;
                this.area = this.currentPost.area;
                if(this.currentPost.rentPostId) {
                    this.deposit = this.currentPost.deposit;
                    this.least_contract = this.currentPost.least_contract;
                }
                this.selectedCountry = post.country;
                this.selectedProvince = post.province;
                this.selectedAmphoe = post.amphoe;
                this.selectedRegion = post.region;
                this.selectedLocation = post.location_id;
                
                console.log(this.currentPost);
            },

            deletePost(post) {
                console.log(post);
                this.post_id = post.post_id;
                console.log(this.post_id);
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
                        axios.delete(`http://localhost:8083/posts/deletepost/${this.post_id}`)
                        .then(response => {
                            console.log(response.data);
                            this.fetchPosts();
                            window.location.reload();
                        })
                        .catch(error => {
                            console.error("There was an error deleting the post!", error);
                        });
                        Swal.fire({
                            title: "Deleted!",
                            text: "Your file has been deleted.",
                            icon: "success",
                            showConfirmButton: false,
                        });
                    }
                });
                // const confirmed = window.confirm("Do you want to delete this post?");
                // if(confirmed) {
                //     axios.delete(`http://localhost:8083/posts/deletepost/${this.post_id}`)
                //     .then(response => {
                //         console.log(response.data);
                //         this.fetchPosts();
                //         window.location.reload();
                //     })
                //     .catch(error => {
                //         console.error("There was an error deleting the post!", error);
                //     });
                // }
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

<script setup>
    import { ref } from 'vue'
    import Swal from 'sweetalert2';

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