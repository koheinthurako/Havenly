<template>
    <v-container>
        <main>
            <div>
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
                <div v-else-if="displayError">{{ displayError }}</div>
                <div v-else>
                    <div v-if="posts.length > 0">
                        <!-- display posts start -->
                        <div class="row mb-5 g-3">
                            <div v-for="post in posts" :key="post.post_id" class="col-md-3">
                                <div class="card-container">
                                    <!-- TZH card styles -->
                                    <div class="card cursor-pointer" style="height: 460px;">
                                        <div class="cardImgBox mb-2">
                                            <img :src="post.photo_url[0]" class="w-100 h-100" alt="Card image cap">
                                        </div>
                                        <div class="card-body p-3 d-flex flex-column" @click="clickPost(post.post_id)">
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
                                            <h5 class="card-title mb-3">{{ post.shortTitle }}</h5>
                                            <p class="card-text small opacity-75">{{ post.shortDescription }}</p>
                                            <p class="card-text text-danger small mb-auto opacity-75 mb-auto">
                                                <v-icon>mdi-map-marker-radius</v-icon>
                                                {{ post.region }} , {{ post.province }} , {{ post.country }}
                                            </p>

                                        </div>
                                        <div class="buttonBox d-flex justify-content-between gap-3 mb-3 px-3">
                                            <button v-if="post.status != 'rejected'" class="btn btn-outline-danger w-100" @click="editPost(post)"
                                                data-bs-toggle="modal" data-bs-target="#editModal">Edit</button>
                                            <button class="btn btn-danger w-100"
                                                @click="deletePost(post)">Delete
                                            </button>
                                        </div>
                                    </div>

                                </div>

                            </div>

                            <!-- Edit Modal Box -->
                            <div class="editModalBox">
                                <div class="modal fade" id="editModal" data-bs-backdrop="static" tabindex="-1"
                                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg d-flex align-items-center fixed-top">
                                        <div class="modal-content" style="max-width: 2000px !important;">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalLabel">Edit {{
                                                    currentPost.post_type }}</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close" @click="reloadWindow"></button>
                                            </div>
                                            <div class="modal-body" style="overflow-y:auto; max-height: 70vh;">
                                                <form @submit.prevent="submit" enctype="multipart/form-data"
                                                    class="w-100 px-4 py-3">
                                                    <div class="row justify-content-between">
                                                        <div class="col-md-2 col-sm-12">
                                                            <span class="float-left mt-2 small">Title <span
                                                                    class="text-red">*</span></span>
                                                        </div>
                                                        <div class="col-md-9 col-sm-12">
                                                            <v-text-field required bg-color="#EDEDED" filled variant="solo"
                                                                density="compact" rounded="lg" clear-icon="mdi-close-circle"
                                                                clearable class="w-100" v-model="fullTitle"
                                                                :rules="[v => !!v || 'Title is required', v => !/^\s*$/.test(v) || 'Title cannot be just spaces']"
                                                                placeholder="Title"></v-text-field>
                                                        </div>
                                                    </div>

                                                    <div class="row justify-content-between">
                                                        <div class="col-md-2 col-sm-12 py-0">
                                                            <span class="float-left mt-2 small">Description<span
                                                                    class="text-red">*</span>
                                                            </span>
                                                        </div>
                                                        <div class="col-md-9 col-sm-12 py-0">
                                                            <v-textarea required bg-color="#EDEDED" class="w-100"
                                                                clear-icon="mdi-close-circle" clearable variant="solo"
                                                                rounded="lg" density="compact" v-model="fullDescription"
                                                                :rules="[v => !!v || 'Description is required', v => !/^\s*$/.test(v) || 'Description cannot be just spaces']"
                                                                :counter="10000" placeholder="Description"></v-textarea>
                                                        </div>
                                                    </div>
                                                    <hr>
                                                    <div class="p-0 row-1 d-flex">
                                                        <v-select bg-color="white" v-model="selectedCountry"
                                                            :items="uniqueCountries" label="Select country"
                                                            required></v-select>
                                                        <v-select bg-color="white" v-model="selectedProvince"
                                                            :items="uniqueProvinces" :disabled="!selectedCountry"
                                                            label="Select province" required></v-select>
                                                        <v-select bg-color="white" v-model="selectedAmphoe"
                                                            :items="uniqueAmphoes" :disabled="!selectedProvince"
                                                            label="Select amphoe" required></v-select>
                                                        <v-select bg-color="white" v-model="selectedRegion"
                                                            :items="uniqueRegions" :disabled="!selectedAmphoe"
                                                            label="Select region" required></v-select>
                                                    </div>

                                                    <div class="row justify-content-between">
                                                        <div class="col-md-2 col-sm-12">
                                                            <span class="float-left mt-2 small"> Property Type </span>
                                                        </div>
                                                        <div class="col-md-9 col-sm-12">
                                                            <v-select required bg-color="#EDEDED" class="w-100"
                                                                clear-icon="mdi-close-circle" clearable variant="solo"
                                                                rounded="lg" density="compact" v-model="property_type"
                                                                :items="PropertyTypes"
                                                                placeholder="Select property type"></v-select>
                                                        </div>
                                                    </div>

                                                    <div class="row justify-content-between">
                                                        <div class="col-md-2 col-sm-12">
                                                            <span class="float-left mt-2 small">Price($Dollar)<span
                                                                    class="text-red">*</span></span>
                                                        </div>
                                                        <div class="col-md-9 col-sm-12">
                                                            <v-text-field required bg-color="#EDEDED" filled variant="solo"
                                                                density="compact" rounded="lg" clear-icon="mdi-close-circle"
                                                                clearable class="w-100" v-model="price"
                                                                :rules="[v => !!v || 'Price is required', v => !/^\s*$/.test(v) || 'Price cannot be just spaces']"
                                                                placeholder="price"></v-text-field>
                                                        </div>
                                                    </div>

                                                    <div class="row justify-content-between">
                                                        <div class="col-md-2 col-sm-12">
                                                            <span class="float-left mt-2 small">Area</span>
                                                        </div>
                                                        <div class="col-md-9 col-sm-12">
                                                            <v-text-field required bg-color="#EDEDED" filled variant="solo"
                                                                density="compact" rounded="lg" clear-icon="mdi-close-circle"
                                                                clearable class="w-100" v-model="area"
                                                                :rules="[v => !!v || 'Area is required', v => !/^\s*$/.test(v) || 'Area cannot be just spaces']"
                                                                placeholder="Area"></v-text-field>
                                                        </div>
                                                    </div>
                                                    <div v-if="currentPost.deposit" class="row justify-content-between">
                                                        <div class="col-md-2 col-sm-12">
                                                            <span class="float-left mt-2 small">Deposit</span>
                                                        </div>
                                                        <div class="col-md-9 col-sm-12">
                                                            <v-text-field required bg-color="#EDEDED" filled variant="solo"
                                                                density="compact" rounded="lg" clear-icon="mdi-close-circle"
                                                                clearable class="w-100" v-model="deposit"
                                                                :rules="[v => !!v || 'Deposit is required', v => !/^\s*$/.test(v) || 'Deposit cannot be just spaces']"
                                                                placeholder="Deposit"></v-text-field>
                                                        </div>
                                                    </div>
                                                    <div v-if="currentPost.least_contract"
                                                        class="row justify-content-between">
                                                        <div class="col-md-2 col-sm-12">
                                                            <span class="float-left mt-2 small">Least Contract</span>
                                                        </div>
                                                        <div class="col-md-9 col-sm-12">
                                                            <v-text-field required bg-color="#EDEDED" filled variant="solo"
                                                                density="compact" rounded="lg" clear-icon="mdi-close-circle"
                                                                clearable class="w-100" v-model="least_contract"
                                                                :rules="[v => !!v || 'Least contract is required', v => !/^\s*$/.test(v) || 'Least contract cannot be just spaces']"
                                                                placeholder="Least Contract"></v-text-field>
                                                        </div>
                                                    </div>

                                                    <div :key="forceRerender">
                                                        <div class="row justify-content-between">
                                                            <div class="col-md-3 col-sm-12 py-0">
                                                                <span class="float-left mt-2 small">Replace Image<span
                                                                        class="text-red">*</span></span>
                                                            </div>
                                                            <div class="col-md-9 col-sm-12 py-0 d-flex flex-column flex-md-row flex-wrap">

                                                                <!-- Render original photos -->
                                                                <div v-for="(photo, index) in currentPost.photo_url" :key="'photo_' + index" class="mb-2 col-12 col-md-4 position-relative">
                                                                    <img :src="photo" class="img-thumbnail" alt="Current Post Image">
                                                                    <button v-if="canDeletePhoto" @click="deletePhoto(index, 'original')" class="bg-white rounded-start-2 p-2 imgDeleteIcon position-absolute top-0 end-0 ">
                                                                        <v-icon class="text-danger">mdi-close</v-icon>
                                                                    </button>
                                                                </div>

                                                                <!-- Render additional photos -->
                                                                <div v-for="(photo, index) in additionalPhotos" :key="'extra_photo_' + index" class="mb-2 col-12 col-md-4 position-relative">
                                                                    <img :src="photo" class="img-thumbnail" alt="Additional Photo">
                                                                    <button v-if="canDeletePhoto" @click="deletePhoto(index, 'additional')" class="bg-white rounded-start-2 p-2 imgDeleteIcon position-absolute top-0 end-0 ">
                                                                        <v-icon class="text-danger">mdi-close</v-icon>
                                                                    </button>
                                                                </div>

                                                                <!-- Hidden file input -->
                                                                <input type="file" ref="fileInput" style="display: none;"
                                                                    @change="handleFileChange">
                                                                <div class="mb-2 col-12 col-md-4">
                                                                    <button @click.prevent="triggerFileInput" class="btn btn-outline-danger w-100 h-100 fs-1"> + </button>
                                                                    <input type="file" ref="fileExtraInput" multiple @change="addMorePhotos" style="display: none;">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-outline-danger"
                                                    data-bs-dismiss="modal" @click="reloadWindow">Close</button>
                                                <button type="button" class="btn btn-danger"
                                                    @click="submit(currentPost)">Save
                                                    changes</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                    </div>
                    </div>
                    <div v-else>
                        No post found
                    </div>
                    <!-- display posts end -->
                </div>

            </div>
        </main>
    </v-container>
</template>

<script>
import axios from 'axios';
import { toRaw, shallowRef } from 'vue';

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
        loading: false,
        displayError: null,
        posts: [],
        post_id: '',
        // currentPost: shallowRef({}),
        currentPost: shallowRef({ photo_url: [] }),
        additionalPhotos: [],
        clickedPhotoType: '',
        location_id: '',
        title: '',
        shortTitle: '',
        fullTitle: '',
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
        modalOpen: true,
    }),

    computed: {

        totalPhotos() {
            return (this.currentPost.photo_url ? this.currentPost.photo_url.length : 0) + this.additionalPhotos.length;
        },
        canDeletePhoto() {
            return this.totalPhotos > 1;
        },

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

    watch: {
        selectedRegion(newRegion) {
        if (newRegion) {
            const selectedLocation = this.locations.find(location => location.region === newRegion);
            if(selectedLocation) {
                this.selectedLocation = selectedLocation.location_id;
                console.log(this.selectedLocation);
            }
        }
        }
    },

    mounted() {
        this.fetchPosts();

        document.addEventListener('keydown', (event) => {
            if (event.key === 'Escape' && this.modalOpen) {
                this.closeModal();
            }
        });
    },

    methods: {

        reloadWindow() {
            window.location.reload();
        },

        closeModal() {
            this.modalOpen = false;
            this.reloadWindow();
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
            return parseInt(decryptedId, 10);
        },

        clickPost(post_id) {
            // router.push('/PostsView')
            const afterEncrypt = this.encryptId(post_id);
            // this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
            this.$router.push({ name: 'postDetailView', params: { id: `${afterEncrypt} details` } });
        },

        async fetchLocations() {
            try {

                Swal.fire({
                    title: 'Loading Information',
                    text: 'Please wait we are getting your information...',
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

        showUploadPhoto(images) {
            console.log("images ko click lite tl")
            console.log(images);
        },

        setClickedPhotoIndex(index, type) {
            this.clickedPhotoIndex = index;
            this.clickedPhotoType = type;
            this.$refs.fileInput.click();
            console.log(this.title);
            console.log(this.fullDescription);
            console.log(this.selectedLocation);
            console.log(this.property_type);
            console.log(this.price);
            console.log(this.area);
            console.log(this.deposit);
            console.log(this.least_contract);
        },

        handleFileChange(event) {
            const file = event.target.files[0];
            const maxSize = 30 * 1024 * 1024; // 30 MB
            if (file.size > maxSize) {
                alert('File size exceeds the limit of 30 MB.');
                return;
            }
            console.log(file.size);
            if (file) {
                let reader = new FileReader();
                reader.onload = () => {
                if (this.clickedPhotoType === 'original') {
                    this.currentPost.photo_url.splice(this.clickedPhotoIndex, 1, reader.result);
                } else if (this.clickedPhotoType === 'additional') {
                    this.additionalPhotos.splice(this.clickedPhotoIndex, 1, reader.result);
                }
                this.forceRerender = !this.forceRerender;
                };
                reader.readAsDataURL(file);
                console.log(this.currentPost.photo_url);
            }
        },

        addMorePhotos(event) {
            const files = event.target.files;
            for (let i = 0; i < files.length; i++) {
                const file = files[i];
                const maxSize = 30 * 1024 * 1024; // 30 MB
                if (file.size > maxSize) {
                alert('File size exceeds the limit of 30 MB.');
                continue;
                }
                let reader = new FileReader();
                reader.onload = () => {
                    this.additionalPhotos.push(reader.result);
                    this.forceRerender = !this.forceRerender;
                };
                reader.readAsDataURL(file);
            }
        },

        deletePhoto(index, type) {

            if (!this.canDeletePhoto) {
                alert("Can't delete this photo.");
                return;
            }
            
            if (type === 'original') {
                this.currentPost.photo_url.splice(index, 1);
            } else if (type === 'additional') {
                this.additionalPhotos.splice(index, 1);
            }
            this.forceRerender = !this.forceRerender;
        
            
        },

        validateBeforeSubmit() {
            if (!this.fullTitle || !this.fullDescription || !this.property_type || !this.price || !this.area || !this.selectedCountry || !this.selectedProvince || !this.selectedAmphoe || !this.selectedRegion) {
                Swal.fire({
                    title: "Incomplete Form!",
                    text: "Please fill in all required fields.",
                    icon: "error",
                    showConfirmButton: false,
                });
                return false;
            } else if (this.additionalPhotos.length === 0 && this.currentPost.photo_url.length === 0) {
                Swal.fire({
                    title: "Empty Photo!",
                    text: "Please add at least one photo.",
                    icon: "error",
                    showConfirmButton: false,
                });
                return false;
            }
            return true;
        },

        async submit(currentPost) {

            if (!this.validateBeforeSubmit()) {
                return;
            }

            const formData = new FormData();
            formData.append('postId', currentPost.post_id);
            formData.append('title', this.fullTitle);
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

            // Add additional photos
            for (const base64String of this.additionalPhotos) {
                const blob = await this.base64ToBlob(base64String);
                const resizedFile = await this.resizeImage(blob);
                formData.append('files', resizedFile);
            }

            formData.forEach((value, key) => {
                console.log(`${key}:`, value);
            });

            Swal.fire({
                title: 'Saving...',
                text: 'Your post is being submitted. Please wait...',
                allowOutsideClick: false,
                allowEscapeKey: false,
                didOpen: () => {
                    Swal.showLoading(); // Show loading spinner
                }
            });

            if (this.currentPost.rentPostId) {
                const rentId = currentPost.rentPostId;
                formData.append('rentPostId', rentId);
                formData.append('deposit', this.deposit);
                formData.append('least_contract', this.least_contract);
                
                formData.forEach((value, key) => {
                    console.log(`${key}:`, value);
                });

                try {
                    const response = await axios.put('http://localhost:8083/rentpost/editRentPost', formData, {});
                    console.log(response.status);
                    if (response.status === 200) {
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
            } else if (this.currentPost.sellPostId) {
                const sellId = currentPost.sellPostId;
                formData.append('sellPostId', sellId);

                formData.forEach((value, key) => {
                    console.log(`${key}:`, value);
                });

                try {
                    const response = await axios.put('http://localhost:8083/sellpost/editsellpost', formData, {});
                    if (response.status === 200) {
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
                reader.onload = function (event) {
                    const img = new Image();
                    img.onload = function () {
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
            this.loading = true;  // Start loading
            this.displayError = null;  // Clear previous errors

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
                    if (post.rentpost) {
                        let tt = post.rentpost.title;
                        let shortTt = tt.length > 20 ? tt.substring(0, 20) + "..." : tt;
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
                            // title: post.rentpost.title,
                            shortTitle: shortTt,
                            fullTitle: post.rentpost.title,
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
                        let tt = post.sellpost.title;
                        let shortTt = tt.length > 20 ? tt.substring(0, 20) + "..." : tt;
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
                            // title: post.sellpost.title,
                            shortTitle: shortTt,
                            fullTitle: post.sellpost.title,
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
        },

        editPost(post) {
            const cachedData = this.getLocationsFromSessionStorage();
            if (cachedData) {
                this.locations = cachedData;
            } else {
                this.fetchLocations();
            }

            this.currentPost = toRaw(post);
            this.fullTitle = this.currentPost.fullTitle;
            this.fullDescription = this.currentPost.fullDescription;
            this.property_type = this.currentPost.property_type;
            this.price = this.currentPost.price;
            this.area = this.currentPost.area;
            if (this.currentPost.rentPostId) {
                this.deposit = this.currentPost.deposit;
                this.least_contract = this.currentPost.least_contract;
            }
            this.selectedCountry = post.country;
            this.selectedProvince = post.province;
            this.selectedAmphoe = post.amphoe;
            this.selectedRegion = post.region;
            this.selectedLocation = post.location_id;

            if (!Array.isArray(this.currentPost.photo_url)) {
                this.currentPost.photo_url = [];
            }

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
                    Swal.fire({
                    title: "Deleted!",
                    text: "Your file has been deleted.",
                    icon: "success",
                    customClass: {
                        confirmButton: 'myCustomSuccessButton'
                    },
                }).then (() => {
                    axios.delete(`http://localhost:8083/posts/deletepost/${this.post_id}`)
                    .catch(error => {
                        console.error("There was an error deleting the post!", error);
                    });
                    window.location.reload();
                    this.fetchPosts();
                });
                }
                
            });
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
import AES from 'crypto-js/aes';
import Utf8 from 'crypto-js/enc-utf8';
import { ref } from 'vue'
import Swal from 'sweetalert2';

// const propertyTypes = useField('propertyTypes')
const PropertyTypes = ref([
    'Condo',
    'Apartment',
    'House'
])

const fileExtraInput = ref(null);

function triggerFileInput() {
    fileExtraInput.value.click();
}

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