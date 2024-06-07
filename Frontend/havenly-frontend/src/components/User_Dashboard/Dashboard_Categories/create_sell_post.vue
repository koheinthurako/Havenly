<template>
    <div class="create-sell-post-section px-4">
        <div class="row">
            <div class="col-md-7 p-0">

                <!-- TZH Form -->
                <div class="create-post">
                    <div class="header mb-3 d-flex justify-content-between px-5">
                        <div class="d-flex align-items-center">
                            <v-icon>mdi-information</v-icon>
                            <p class="mt-3 ms-2">Create Sell Post</p>
                        </div>
                        <div class="d-flex align-items-center">
                            <span>Available Posts : </span>
                            <h5 class="text-red m-0">&nbsp; {{ availPosts }}</h5>
                        </div>
                    </div>

                    <div class="body">
                        <form @submit.prevent="submit" enctype="multipart/form-data" class="w-100 px-4 py-3">
                            <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12">
                                    <span class="float-left mt-2 small">Title <span class="text-red">*</span></span>
                                </div>
                                <div class="col-md-9 col-sm-12">
                                    <v-text-field required bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                        v-model="title.value.value" :counter="50"
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
                                    <v-textarea required bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="Description.value.value"
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
                                    <v-select required bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact"
                                        v-model="propertyTypes.value.value"
                                        :items="PropertyTypes" placeholder="Select property type"></v-select>
                                </div>
                            </div>

                            <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12">
                                    <span class="float-left mt-2 small">Price<span
                                            class="text-red">*</span></span>
                                </div>
                                <div class="col-md-9 col-sm-12">
                                    <v-text-field required bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                        :rules="[v => !!v || 'Price is required', v => !/^\s*$/.test(v) || 'Price cannot be just spaces']"
                                        v-model="price.value.value" placeholder="price"></v-text-field>
                                </div>
                            </div>


                            <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12">
                                    <span class="float-left mt-2 small">Area</span>
                                </div>
                                <div class="col-md-9 col-sm-12">
                                    <v-text-field required bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                        :rules="[v => !!v || 'Area is required', v => !/^\s*$/.test(v) || 'Area cannot be just spaces']"
                                        v-model="area.value.value" placeholder="Area"></v-text-field>
                                </div>
                            </div>

                            <div class="row justify-content-between">
                                <div class="col-md-3 col-sm-12 py-0">
                                    <span class="float-left mt-2 small">Choose Image<span class="text-red">*</span>
                                    </span>
                                </div>
                                <div class="col-md-9 col-sm-12 py-0">
                                    <v-file-input required class="disableClearBtn" counter multiple color="deep-purple-accent-4"
                                        chips truncate-length="15" v-model="combinedImages"
                                        :rules="rules" accept="image/png, image/jpeg, image/bmp" @change="showUploadPhoto"
                                        prepend-icon="mdi-camera" show-input="false"></v-file-input>
                                </div>
                            </div>

                            <div class="row mt-3">
                                <div v-for="(photo, index) in photoList" :key="index" class="col-md-4 col-sm-6 mb-3">
                                    <v-card class="customImgBox">
                                        <v-card-actions>
                                            <v-icon @click="removeImage(index)" class="imgDeleteIcon">mdi-close</v-icon>
                                        </v-card-actions>
                                        <v-img :src="photo.url" class="customImg" height="160px"></v-img>
                                    </v-card>
                                </div>
                                <div v-if="combinedImages.length > 0" class="col-md-4 col-sm-6 mb-3">
                                    <button @click.prevent="triggerFileInput" class="btn btn-outline-danger w-100 h-100 fs-1"> + </button>
                                    <input type="file" ref="fileInput" multiple @change="addMorePhotos" style="display: none;">
                                </div>
                            </div>

                            <div class="w-100 d-flex justify-content-end mb-3">
                                <v-btn class="me-2" type="submit" rounded="xl" color="#E86F52">
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

            <div class="col-md-5 p-0">
                <!-- Display post section start -->

                <div class="display-post">

                    <div class="header mb-3">
                        <v-icon>mdi-information</v-icon>
                        <p class="mt-3 ms-2">Recently approved sell posts by admin team.</p>
                    </div>

                    <div class="body">

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
                                        <button class="w-50 btn btn-sm btn-outline-danger"
                                            to="/detailview">View</button>
                                        <button class="w-50 btn btn-sm btn-danger">Edit</button>
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

                    </div>
                </div>

                <!-- Display post section end -->

            </div>
        </div>
    </div>

</template>

<script>
export default {
    name: 'create_post',
    data: () => ({
        location_id: '',
        title: '',
        description: '',
        // image: [],
        image: '',
        combinedImages: [],
        price: '',
        area: '',
        house_type: '',
        property_type: '',
        locations: [],
        selectedCountry: '',
        selectedProvince: '',
        selectedAmphoe: '',
        selectedRegion: '',
        selectedLocation: '',
        secondselectlocation: '',
        availPosts: '',
        sellPosts: [],
        change_type: 'sell',

        // titleRules: [
        //     v => !!v || 'Title is required!',
        // ],

        // descriptionRules: [
        //     v => !!v || 'Description is required!',
        // ],

        sell_data: [

            {
                id: 1, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-1.jpg'), type: 'Sell'
            },
            {
                id: 2, title: 'Apartment Zone', img: require('@/assets/img/house-2.jpg'), type: 'Sell'
            },
            {
                id: 3, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-3.jpg'), type: 'Sell'
            }, {
                id: 4, title: 'Apartment Zone', img: require('@/assets/img/house-4.jpg'), type: 'Sell'
            },
            {
                id: 5, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-5.jpg'), type: 'Sell'
            }, {
                id: 6, title: 'Apartment Zone', img: require('@/assets/img/house-6.jpg'), type: 'Sell'
            },
            {
                id: 7, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-7.jpg'), type: 'Sell'
            }, {
                id: 8, title: 'Apartment Zone', img: require('@/assets/img/house-1.jpg'), type: 'Sell'
            }

        ],

        rent_data: [

            {
                id: 1, title: 'International Playground', img: require('@/assets/img/house-7.jpg'), type: 'Rent'
            },
            {
                id: 2, title: 'Swimming Pool', img: require('@/assets/img/house-6.jpg'), type: 'Rent'
            },
            {
                id: 3, title: 'Shopping Mall', img: require('@/assets/img/house-5.jpg'), type: 'Rent'
            }, {
                id: 4, title: 'Apartment Zone', img: require('@/assets/img/house-4.jpg'), type: 'Rent'
            },
            {
                id: 5, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-3.jpg'), type: 'Sell'
            }, {
                id: 6, title: 'Apartment Zone', img: require('@/assets/img/house-2.jpg'), type: 'Rent'
            },
            {
                id: 7, title: 'Area 51 Industrial zone', img: require('@/assets/img/house-1.jpg'), type: 'Sell'
            }, {
                id: 8, title: 'Apartment Zone', img: require('@/assets/img/house-7.jpg'), type: 'Rent'
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
            return this.sellPosts.slice(0, 4);
        },

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
        const cachedData = this.getLocationsFromSessionStorage();
        if (cachedData) {
            this.locations = cachedData;
        } else {
            this.Locations();
        }

        this.fetchSubUserInfo();
        this.fetchAllSellPosts();
    },

    methods: {

        handleReset() {
            Swal.fire({
                title: "Are you sure?",
                text: "All sell post information will be lost!",
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

        fetchSubUserInfo() {
            const user = JSON.parse(sessionStorage.getItem('login_user'));
            const registerId = user.register_id;
            console.log("registerId to send backend to show subUser informations : " + registerId)
            axios.get('http://localhost:8083/subscribe/getSubUserInfo', {
                params: {
                    registerId: registerId
                }
            })
                .then(response => {
                    console.log(response.data);
                    this.availPosts = response.data.availPosts
                    if (this.availPosts === 0) {
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

        fetchAllSellPosts() {
            const user = JSON.parse(sessionStorage.getItem('sub_user'));
            const subUserId = user.subUserId;
            console.log(subUserId);
            // Make API call to fetch posts from backend
            axios.get('http://localhost:8083/sellpost/allSubuserSellPosts', {
                params: {
                    subUserId: subUserId
                }
            })
                .then(response => {
                    response.data.forEach(post => {
                        if (post.description.length > 50) {
                            let des = post.description;
                            post.description = des.substring(0, 50) + "...";
                        }

                        let imageUrls = Array.isArray(post.image) ? post.image : [post.image];
                        console.log(imageUrls)
                        console.log(post);
                        this.sellPosts.unshift({
                            post_id: post.post_id,
                            province: post.locations.province,
                            region: post.locations.region,
                            country: post.locations.countries.country_name,
                            title: post.title,
                            description: post.description,
                            property_type: post.property_type,
                            area: post.area,
                            price: post.price,
                            photo_url: imageUrls,
                            status: 'Complete',
                        });
                        console.log(typeof (imageUrls))

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

/* Field collection */
const title = useField('title')
const Description = useField('Description')
const propertyTypes = useField('propertyTypes')
const price = useField('price')
const area = useField('area')
// const image = useField('image')
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
    // const files = Object.values(image.value.value);
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
    // Append new photos to the existing photo list
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
    // image.value.value.splice(index, 1);
    combinedImages.value = allFiles.value;
    allFiles.value.splice(index, 1);
}

const submit = async () => {

const subUser = JSON.parse(sessionStorage.getItem('sub_user'));
console.log(subUser.subUserId);
const subUserId = subUser.subUserId;

const formData = new FormData();
formData.append('subUserId', subUserId);
formData.append('title', title.value.value);
formData.append('description', Description.value.value);
formData.append('property_type', propertyTypes.value.value);
formData.append('price', price.value.value);
formData.append('area', area.value.value);
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
            const response = await axios.post('http://localhost:8083/sellpost/savesellpost', formData, {
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
















<style>
.create-post-section {
    width: 100%;
    height: auto;

    /* Create post */
    .create-post {
        overflow: hidden;
        width: 100%;
        height: auto;
        padding: 8px 12px !important;

        background-color: #fff;

        .form-header {
            background-color: #D9EDF7;
            padding: 1px 0px;
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 15px;
        }

        .form-body {
            padding: 10px;
            border-radius: 10px;
            box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.3);

        }
    }

    /* Display post */
    .display-post-section {
        /* box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.4); */
        background-color: #d3d3d3;
        width: 100%;
        height: 100%;
        padding: 6px 16px;
        border-radius: 10px;

        /* background: linear-gradient(to bottom left, cyan 50%, palegoldenrod 50%); */

        .display-post {
            width: 100%;
            max-height: 220px !important;
            overflow: hidden;
            background-color: #D9EDF7;
            box-shadow: 0px 5px 22px 1px rgba(0, 0, 0, 0.5);
            margin-bottom: 14px;
            border-radius: 4px;
            animation: aniOne 0.8s cubic-bezier(0.68, -0.6, 0.32, 1.6) 0s 1 normal both;

            .display-left {
                position: relative;

                .overlay {
                    width: 100%;
                    height: 24%;
                    left: 0;
                    bottom: 74px;
                    position: absolute;
                    background-color: rgba(255, 255, 255, 0.8);
                    backdrop-filter: blur(40px);
                    z-index: 1;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    opacity: 0;
                    transform: translateY(200px);
                    border-bottom-left-radius: 4px;
                    transition: opacity 0.3s ease-in, transform 0.3s ease-in;

                    .v-btn {
                        text-transform: capitalize;
                    }
                }
            }

            .display-right {
                padding: 10px 20px;
                background-color: rgba(255, 255, 255, 0.7);
                backdrop-filter: blur(30px);
                -webkit-backdrop-filter: blur(30px);
                position: relative;

                h5 {
                    color: #E86F52;
                }

                p {
                    text-indent: 30px;
                }

            }


        }

        .display-post:hover .overlay {
            opacity: 1;
            transform: translateY(0);
        }


    }
}

@keyframes aniOne {
    0% {
        animation-timing-function: ease-in;
        opacity: 0;
        transform: scale(0);
    }

    38% {
        animation-timing-function: ease-out;
        opacity: 1;
        transform: scale(1);
    }

    55% {
        animation-timing-function: ease-in;
        transform: scale(0.7);
    }

    72% {
        animation-timing-function: ease-out;
        transform: scale(1);
    }

    81% {
        animation-timing-function: ease-in;
        transform: scale(0.84);
    }

    89% {
        animation-timing-function: ease-out;
        transform: scale(1);
    }

    95% {
        animation-timing-function: ease-in;
        transform: scale(0.95);
    }

    100% {
        animation-timing-function: ease-out;
        transform: scale(1);
    }
}

.disableClearBtn .v-field__clearable {
    display: none !important;
}
</style>