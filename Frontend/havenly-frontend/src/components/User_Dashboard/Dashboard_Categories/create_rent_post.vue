<template>
    <div class="create-sell-post-section">
        <v-container>
            <div class="row">
                <div class="col-md-7 p-0 col-sm-12" v-if="displayCreateView">

                    <!-- TZH Form -->
                    <div class="create-post">

                        <!-- <div class="header mb-3 row">
                            <div class=" left-edit  col-sm-12 col-md-6">
                                <div class="specific-edit d-flex align-items-center">
                                    <v-icon class="me-2">mdi-information</v-icon>
                                    <p class="mt-3">Create Rent Post</p>
                                </div>
                            </div>
                            <div class=" right-edit col-sm-12 col-md-6">
                                <div class="specific-edit d-flex align-items-center">
                                    <span>Available Posts : </span>
                                    <h5 class="text-red m-0">&nbsp; {{ availPosts }}</h5>
                                </div>
                                <v-btn rounded class="toggle-btn" @click="toggleDisplay">show posts</v-btn>
                            </div>
                        </div> -->

                        <div class="header mb-3 row">
                            <div class=" left-edit  col-sm-12 col-md-6">
                                <div class="specific-edit d-flex align-items-center">
                                    <v-icon class="me-2">mdi-information</v-icon>
                                    <p class="mt-3">Create Rent Post</p>
                                </div>
                            </div>
                            <div class=" right-edit col-sm-12 col-md-6">
                                <div class="specific-edit d-flex align-items-center">
                                    <span>Available Posts : </span>
                                    <h5 class="text-red m-0">&nbsp; {{ availPosts }}</h5>
                                </div>
                                <v-btn rounded class="toggle-btn mb-2" @click="toggleDisplay">show posts</v-btn>
                            </div>
                        </div>

                        <div class="body">
                            <form @submit.prevent="submit" enctype="multipart/form-data" class="w-100 px-2 py-3">
                                <div class="row justify-content-between">
                                    <div class="col-md-2 col-sm-12">
                                        <span class="float-left mt-2 small">Title <span class="text-red">*</span></span>
                                    </div>
                                    <div class="col-md-9 col-sm-12">
                                        <v-text-field required bg-color="#EDEDED" filled variant="solo"
                                            density="compact" rounded="lg" clear-icon="mdi-close-circle" clearable
                                            class="w-100" v-model="title.value.value" :counter="50"
                                            :rules="[v => !!v || 'Title is required', v => !/^\s*$/.test(v) || 'Title cannot be just spaces']"
                                            placeholder="Title"></v-text-field>
                                    </div>
                                </div>

                                <div class="row justify-content-between">
                                    <div class="col-md-2 col-sm-12 py-0">
                                        <span class="float-left mt-2 small">Description<span class="text-red">*</span>
                                        </span>
                                    </div>
                                    <div class="col-md-9 col-sm-12 py-0">
                                        <v-textarea required bg-color="#EDEDED" class="w-100"
                                            clear-icon="mdi-close-circle" clearable variant="solo" rounded="lg"
                                            density="compact" v-model="Description.value.value"
                                            :rules="[v => !!v || 'Description is required', v => !/^\s*$/.test(v) || 'Description cannot be just spaces']"
                                            :counter="10000" placeholder="Description"></v-textarea>
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
                                </div>

                                <div class="row justify-content-between">
                                    <div class="col-md-2 col-sm-12">
                                        <span class="float-left mt-2 small"> Property Type </span>
                                    </div>
                                    <div class="col-md-9 col-sm-12">
                                        <v-select required bg-color="#EDEDED" class="w-100"
                                            clear-icon="mdi-close-circle" clearable variant="solo" rounded="lg"
                                            density="compact" v-model="propertyTypes.value.value" :items="PropertyTypes"
                                            placeholder="Select property type"></v-select>
                                    </div>
                                </div>

                                <div class="row justify-content-between">
                                    <div class="col-md-2 col-sm-12">
                                        <span class="float-left mt-2 small">Price<span class="text-red">*</span></span>
                                    </div>
                                    <div class="col-md-9 col-sm-12">
                                        <v-text-field required bg-color="#EDEDED" filled variant="solo"
                                            density="compact" rounded="lg" clear-icon="mdi-close-circle" clearable
                                            class="w-100"
                                            :rules="[v => !!v || 'Price is required', v => !/^\s*$/.test(v) || 'Price cannot be just spaces']"
                                            v-model="price.value.value" placeholder="price"></v-text-field>
                                    </div>
                                </div>


                                <div class="row justify-content-between">
                                    <div class="col-md-2 col-sm-12">
                                        <span class="float-left mt-2 small">Area</span>
                                    </div>
                                    <div class="col-md-9 col-sm-12">
                                        <v-text-field required bg-color="#EDEDED" filled variant="solo"
                                            density="compact" rounded="lg" clear-icon="mdi-close-circle" clearable
                                            class="w-100"
                                            :rules="[v => !!v || 'Area is required', v => !/^\s*$/.test(v) || 'Area cannot be just spaces']"
                                            v-model="area.value.value" placeholder="Area"></v-text-field>
                                    </div>
                                </div>
                                <div class="row justify-content-between">
                                    <div class="col-md-2 col-sm-12">
                                        <span class="float-left mt-2 small">Deposit</span>
                                    </div>
                                    <div class="col-md-9 col-sm-12">
                                        <v-text-field required bg-color="#EDEDED" filled variant="solo"
                                            density="compact" rounded="lg" clear-icon="mdi-close-circle" clearable
                                            class="w-100"
                                            :rules="[v => !!v || 'Deposit is required', v => !/^\s*$/.test(v) || 'Deposit cannot be just spaces']"
                                            v-model="deposit.value.value" placeholder="Deposit"></v-text-field>
                                    </div>
                                </div>
                                <div class="row justify-content-between">
                                    <div class="col-md-2 col-sm-12">
                                        <span class="float-left mt-2 small">Least Contract</span>
                                    </div>
                                    <div class="col-md-9 col-sm-12">
                                        <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                            rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                            :rules="[v => !!v || 'Least contract is required', v => !/^\s*$/.test(v) || 'Least contract cannot be just spaces']"
                                            v-model="least_contract.value.value"
                                            placeholder="Least Contract"></v-text-field>
                                    </div>
                                </div>

                                <div class="row justify-content-between">
                                    <div class="col-md-3 col-sm-12 py-0">
                                        <span class="float-left mt-2 small">Choose Image<span class="text-red">*</span>
                                        </span>
                                    </div>
                                    <div class="col-md-9 col-sm-12 py-0">
                                        <v-file-input required class="disableClearBtn" counter multiple
                                            color="deep-purple-accent-4" chips truncate-length="15"
                                            v-model="combinedImages" :rules="rules"
                                            accept="image/png, image/jpeg, image/bmp" @change="showUploadPhoto"
                                            prepend-icon="mdi-camera" show-input="false"></v-file-input>
                                    </div>
                                </div>

                                <div class="row mt-3">
                                    <div v-for="(photo, index) in photoList" :key="index"
                                        class="col-md-4 col-sm-6 mb-3">
                                        <v-card class="customImgBox">
                                            <v-card-actions>
                                                <v-icon @click="removeImage(index)"
                                                    class="imgDeleteIcon">mdi-close</v-icon>
                                            </v-card-actions>
                                            <v-img :src="photo.url" class="customImg" height="160px"></v-img>
                                        </v-card>
                                    </div>
                                    <div v-if="combinedImages.length > 0" class="col-md-4 col-sm-6 mb-3">
                                        <button @click.prevent="triggerFileInput"
                                            class="btn btn-outline-danger w-100 h-100 fs-1"> + </button>
                                        <input type="file" ref="fileInput" multiple @change="addMorePhotos"
                                            style="display: none;">
                                    </div>
                                </div>

                                <div class="w-100 d-flex mt-3 justify-content-end">
                                    <v-btn class="me-4" type="submit" rounded="xl" color="#E86F52">
                                        submit
                                    </v-btn>

                                    <v-btn @click="handleReset" rounded="xl" color="#525252">
                                        clear
                                    </v-btn>
                                </div>
                            </form>

                        </div>

                    </div>

                </div>

                <div class="col-md-5 col-sm-12 p-0 col-sm-12" v-if="displayApproveView">
                    <!-- Display post section start -->

                    <div class="display-post">

                        <!-- <div class="header mb-3">
                            <v-icon>mdi-information</v-icon>
                            <p class="mt-3 ms-2">Approved Rent posts</p>
                        </div>
                        <div class="w-100 d-flex justify-content-right">
                            <v-btn rounded class="toggle-btn ms-auto me-3" @click="toggleDisplay2">Create posts</v-btn>
                        </div> -->

                        <div class="header mb-3 row">
                            <div class=" left-edit col-sm-12 col-md-12">
                                <div class="specific-edit d-flex align-items-center justify-content-center">
                                    <v-icon class="me-1">mdi-information</v-icon>
                                    <p class="mt-3">Approved posts</p>
                                </div>
                            </div>
                            <div class=" right-edit col-sm-12 only-767">
                                <div class="specific-edit d-flex align-items-center">
                                    <p class="p-0 m-0 me-2">Approved By</p>
                                    <h5 class="color-brick m-0">Admin</h5>
                                </div>
                                <v-btn rounded class="toggle-btn mb-2" @click="toggleDisplay2">show posts</v-btn>
                            </div>
                        </div>

                        <div class="body">

                            <div v-if="limitedPosts && limitedPosts.length > 0">
                                <!-- post card start -->
                                <div class="post-card bg-white" v-for="post in limitedPosts" :key="post">
                                    <div class="row">
                                        <div class="col-6 p-0 m-0 left-edit">

                                            <div class="top-section">
                                                <v-icon class="me-1">mdi-format-list-bulleted-type</v-icon>
                                                {{ post.status }}

                                            </div>

                                            <v-img :src="post.photo_url[0]"></v-img>

                                            <div class="btn-section d-flex justify-content-center gap-3 px-4">
                                                <button class="w-100 btn btn-sm btn-outline-danger"
                                                    @click="clickPost(post.post_id)">View</button>
                                            </div>

                                        </div>
                                        <div class="col-6">
                                            <h6 class="my-3">{{ post.title }}</h6>
                                            <p class="small col-10">{{ post.description }}</p>
                                            <p class="card-text text-danger small mb-3 opacity-75 ">
                                                <v-icon>mdi-map-marker-radius</v-icon>
                                                {{ post.region }} , {{ post.province }} , {{ post.country }}
                                            </p>
                                        </div>
                                    </div>

                                </div>
                                <!-- post card end -->
                                <!-- post card for mobile start -->
                                <div class="post-card-mobile bg-white" v-for="post in limitedPosts" :key="post">

                                    <div class="header-image">
                                        <v-img :src="post.photo_url[0]" cover></v-img>
                                    </div>
                                    <div class="body-content py-3 px-3">
                                        <h4 class="mb-2 color-birck">{{ post.title }}</h4>
                                        <p class="small col-10">{{ post.description }}</p>
                                        <p class="text-danger small mb-3 opacity-75 ">
                                            <v-icon>mdi-map-marker-radius</v-icon>
                                            {{ post.region }} , {{ post.province }} , {{ post.country }}
                                        </p>
                                        <div class="d-flex justify-space-between align-items-center">
                                            <p class="color-brick">Post status :</p>
                                            <v-btn rounded variant="text" class="mb-3 status">
                                                <v-icon class="me-1">mdi-format-list-bulleted-type</v-icon>
                                                {{ post.status }}

                                            </v-btn>
                                        </div>
                                        <div class="action-btns d-flex justify-space-between align-items-center">
                                            <v-btn variant="outlined" @click="clickPost(post.id)">View</v-btn>
                                            <v-btn>Edit</v-btn>
                                        </div>
                                    </div>

                                </div>
                                <!-- post card for mobile end -->
                            </div>
                            <div v-else class="d-flex justify-content-center align-items-center">



                                <v-progress-circular v-if="showLoading" indeterminate :size="80" color="#e86f52"
                                    :width="7"></v-progress-circular>
                                <v-alert v-if="!showLoading" text="Currently, there is no post available yet!"
                                    title="Post status" type="info"></v-alert>

                            </div>


                        </div>
                    </div>

                    <!-- Display post section end -->

                </div>
            </div>
        </v-container>
    </div>

</template>

<script>
import AES from 'crypto-js/aes'
export default {
    name: 'create_post',
    data: () => ({
        showLoading: false,

        //specific view 
        displayCreateView: true,
        displayApproveView: window.innerWidth >= 768,
        progressCircular: false,

        location_id: '',
        title: '',
        description: '',
        image: '',
        combinedImages: [],
        price: '',
        area: '',
        property_type: '',
        deposit: '',
        least_contract: '',
        locations: [],
        selectedCountry: '',
        selectedProvince: '',
        selectedAmphoe: '',
        selectedRegion: '',
        selectedLocation: '',
        availPosts: '',
        rentPosts: [],
        change_type: 'sell',

        img: require('@/assets/img/house-7.jpg'),

        rent_data: [

            {
                id: 1, title: 'International Playground', img: require('@/assets/img/house-7.jpg'), type: 'Condo', paragraph: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt quae aliquam at eaque odio suscipit tempore nihil rerum adipisci ut? Facilis, commodi minima quo voluptas doloremque quidem laudantium omnis rem.',
            },
            {
                id: 2, title: 'Swimming Pool', img: require('@/assets/img/house-6.jpg'), type: 'Mini Condo', paragraph: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt quae aliquam at eaque odio suscipit tempore nihil rerum adipisci ut? Facilis, commodi minima quo voluptas doloremque quidem laudantium omnis rem.'
            },
            {
                id: 3, title: 'Shopping Mall', img: require('@/assets/img/house-5.jpg'), type: 'Indistrual', paragraph: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt quae aliquam at eaque odio suscipit tempore nihil rerum adipisci ut? Facilis, commodi minima quo voluptas doloremque quidem laudantium omnis rem.',
            }, {
                id: 4, title: 'Apartment Zone', img: require('@/assets/img/house-4.jpg'), type: 'Office', paragraph: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt quae aliquam at eaque odio suscipit tempore nihil rerum adipisci ut? Facilis, commodi minima quo voluptas doloremque quidem laudantium omnis rem.',
            },
            {
                id: 5, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-3.jpg'), type: 'Restaurant', paragraph: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt quae aliquam at eaque odio suscipit tempore nihil rerum adipisci ut? Facilis, commodi minima quo voluptas doloremque quidem laudantium omnis rem.',
            }, {
                id: 6, title: 'Apartment Zone', img: require('@/assets/img/house-2.jpg'), type: 'Stand Alone House', paragraph: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt quae aliquam at eaque odio suscipit tempore nihil rerum adipisci ut? Facilis, commodi minima quo voluptas doloremque quidem laudantium omnis rem.',
            },
            {
                id: 7, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-1.jpg'), type: 'Market', paragraph: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt quae aliquam at eaque odio suscipit tempore nihil rerum adipisci ut? Facilis, commodi minima quo voluptas doloremque quidem laudantium omnis rem.',
            }, {
                id: 8, title: 'Apartment Zone', img: require('@/assets/img/house-7.jpg'), type: 'Shopping Mall', paragraph: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt quae aliquam at eaque odio suscipit tempore nihil rerum adipisci ut? Facilis, commodi minima quo voluptas doloremque quidem laudantium omnis rem.',
            }

        ],


        rules: [

            value => {
                return !value || !value.length || value[0].size < 5000000 || 'Avatar size should be less than 5 MB!'
            },
        ],
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
        },

        limitedPosts() {
            return this.rentPosts.slice(0, 4);
        },

    },

    watch: {
        selectedRegion(newRegion) {
            if (newRegion) {
                const selectedLocation = this.locations.find(location => location.region === newRegion);
                if (selectedLocation) {
                    this.selectedLocation = selectedLocation.location_id;
                    console.log(this.selectedLocation);
                }
            }
        },

        showLoading(newVal) {
            if (newVal) {
                setTimeout(() => {
                    this.showLoading = false;
                }, 2000); // 3 seconds
            }
        },
    },

    mounted() {
        const cachedData = this.getLocationsFromSessionStorage();
        if (cachedData) {
            this.locations = cachedData;
        } else {
            this.fetchLocations();
        }
        this.fetchSubUserInfo();
        this.fetchAllRentPosts();

        //fake loader 
        this.showLoading = true;
    },

    methods: {

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
            this.$router.push({ name: 'postDetailView', params: { id: `${afterEncrypt} Success` } });
        },

        handleReset() {
            Swal.fire({
                title: "Are you sure?",
                text: "All rent post information will be lost!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#E86F52",
                cancelButtonColor: "##525252",
                confirmButtonText: "Yes delete all!",
                reverseButtons: true,
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.reload();
                }
            });
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

        fetchSubUserInfo() {
            const user = JSON.parse(sessionStorage.getItem('login_user'));
            const registerId = user.register_id;
            console.log("registerId to send backend to show subUser informations from rent post : " + registerId)
            axios.get('http://localhost:8083/subscribe/getSubUserInfo', {
                params: {
                    registerId: registerId
                }
            })
                .then(response => {
                    console.log(response.data.availPosts);
                    this.availPosts = response.data.availPosts;
                    if (this.availPosts <= 0) {
                        Swal.fire({
                            title: 'Buy More Packages!',
                            text: 'Your available post is 0.',
                            icon: 'info',
                            customClass: {
                                confirmButton: 'myCustomButton'
                            },
                            buttonsStyling: false,
                            allowOutsideClick: false,
                            allowEscapeKey: false
                        }).then(() => {
                            router.push('/package');
                        });
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error); // Handle the error
                });
        },

        fetchAllRentPosts() {
            const user = JSON.parse(sessionStorage.getItem('sub_user'));
            const subUserId = user.subUserId;
            console.log(subUserId);
            // Make API call to fetch posts from backend
            axios.get('http://localhost:8083/posts/allRentPost', {
                params: {
                    subUserId: subUserId
                }
            })
                .then(response => {
                    response.data.forEach(post => {

                        if (post.rentpost != null) {
                            if (post.rentpost.title.length > 20) {
                                let tt = post.rentpost.title;
                                post.rentpost.title = tt.substring(0, 20) + "...";
                            }

                            if (post.rentpost.description.length > 50) {
                                let des = post.rentpost.description;
                                post.rentpost.description = des.substring(0, 50) + "...";
                            }

                            let imageUrls = Array.isArray(post.rentpost.image) ? post.rentpost.image : [post.rentpost.image];
                            console.log(imageUrls)
                            console.log(post);
                            this.rentPosts.unshift({
                                post_id: post.post_id,
                                province: post.rentpost.locations.province,
                                region: post.rentpost.locations.region,
                                country: post.rentpost.locations.countries.country_name,
                                title: post.rentpost.title,
                                description: post.rentpost.description,
                                property_type: post.rentpost.property_type,
                                area: post.rentpost.area,
                                price: post.rentpost.price,
                                deposit: post.rentpost.deposit,
                                least_contract: post.rentpost.least_contract,
                                photo_url: imageUrls,
                                status: 'Complete',
                            });
                            console.log(typeof (imageUrls));
                        }
                    });
                })
        }

    },

}
</script>


<script setup>
import { ref } from 'vue'
import { getCurrentInstance } from 'vue';
import { useField } from 'vee-validate'
import axios from 'axios';
import router from '@/router';
import Swal from 'sweetalert2';
import AES from 'crypto-js/aes';
import Utf8 from 'crypto-js/enc-utf8';


/* Field collection */
const title = useField('title')
const Description = useField('Description')
const propertyTypes = useField('propertyTypes')
const price = useField('price')
const area = useField('area')
const deposit = useField('deposit');
const least_contract = useField('least_contract');
const photoList = ref([]);

const PropertyTypes = ref([
    'Condo',
    'Apartment',
    'House'
])

const fileInput = ref(null);
const allFiles = ref([]);
const combinedImages = ref([]);


const { proxy } = getCurrentInstance();

function triggerFileInput() {
    fileInput.value.click();
}

function showUploadPhoto() {
    const files = Object.values(combinedImages.value);
    const fileReadPromises = files.map((file) => {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.onload = (e) => {
                resolve({ file, url: e.target.result });
            };
            reader.onerror = reject;
            reader.readAsDataURL(file);
        });
    });

    Promise.all(fileReadPromises).then((results) => {
        photoList.value = results;
        allFiles.value = files;
        combinedImages.value = allFiles.value;
        console.log('Photo list:', photoList.value);
    }).catch((error) => {
        console.error('Error reading files:', error);
    });
}

function addMorePhotos(event) {
    const files = Object.values(event.target.files);
    const fileReadPromises = files.map((file) => {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.onload = (e) => {
                resolve({ file, url: e.target.result });
            };
            reader.onerror = reject;
            reader.readAsDataURL(file);
        });
    });

    Promise.all(fileReadPromises).then((results) => {
        photoList.value = photoList.value.concat(results);
        allFiles.value = allFiles.value.concat(files);
        combinedImages.value = allFiles.value;
        console.log('Updated photo list:', allFiles.value);
    }).catch((error) => {
        console.error('Error reading files:', error);
    });
}


function removeImage(index) {
    photoList.value.splice(index, 1);
    combinedImages.value = allFiles.value;
    allFiles.value.splice(index, 1);
}

const submit = async () => {

    const subUser = JSON.parse(sessionStorage.getItem('sub_user'));
    const subUserId = subUser.subUserId;
    const formData = new FormData();
    formData.append('subUserId', subUserId);
    formData.append('title', title.value.value);
    formData.append('description', Description.value.value);
    formData.append('property_type', propertyTypes.value.value);
    formData.append('price', price.value.value);
    formData.append('area', area.value.value);
    formData.append('deposit', deposit.value.value);
    formData.append('least_contract', least_contract.value.value);
    formData.append('location_id', proxy.selectedLocation);

    allFiles.value.forEach((file) => {
        formData.append('files', file);
    });

    formData.forEach((value, key) => {
        console.log(`${key}:`, value);
    });

    console.log("------------------- auto submit when add additional photo ---------------------")

    Swal.fire({
        title: 'Posting...',
        text: 'Your post is being submitted. Please wait...',
        allowOutsideClick: false,
        allowEscapeKey: false,
        didOpen: () => {
            Swal.showLoading(); // Show loading spinner
        }
    });

    try {
        if (proxy.availPosts > 0) {
            const response = await axios.post('http://localhost:8083/rentpost/saverentpost', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            if (response.status === 200) {
                Swal.fire({
                    title: 'Successfully Posted',
                    text: 'Your post is requested to admin now!',
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
        } else {
            Swal.fire({
                title: 'Buy Package',
                text: 'Your package is gone! Please buy another package!',
                icon: 'error',
                customClass: {
                    confirmButton: 'myCustomButton'
                },
                buttonsStyling: false,
                allowOutsideClick: false,
                allowEscapeKey: false
            }).then(() => {
                router.push('/package');
            });
        }
    } catch (error) {
        Swal.fire({
            title: 'Error',
            text: 'There was an error submitting your post. Please try again.',
            icon: 'error',
            customClass: {
                confirmButton: 'myCustomErrorButton'
            },
            buttonsStyling: false,
            allowOutsideClick: false,
            allowEscapeKey: false
        });
    }
};

</script>


<style lang="scss" scoped>
@media (min-width: 767px) {
    .only-767 {
        display: none !important;
    }
}
</style>