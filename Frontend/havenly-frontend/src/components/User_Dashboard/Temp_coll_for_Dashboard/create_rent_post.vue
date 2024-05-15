<template>
    <div class="create-sell-post-section px-4">
        <div class="row">
            <div class="col-md-7 p-0">

                <!-- TZH Form -->
                <div class="create-post">
                    <div class="header mb-3 d-flex justify-content-between px-5">
                        <div class="d-flex align-items-center">
                            <v-icon>mdi-information</v-icon>
                            <p class="mt-3 ms-2">Create Rent Post</p>
                        </div>
                        <div class="d-flex align-items-center">
                            <span>Available Posts : </span>
                            <h5 class="text-red m-0">&nbsp; 15</h5>
                        </div>
                    </div>

                    <div class="body">
                        <form @submit.prevent="submit" enctype="multipart/form-data" class="w-100 px-4 py-3">
                            <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12">
                                    <span class="float-left mt-2 small">Title <span class="text-red">*</span></span>
                                </div>
                                <div class="col-md-9 col-sm-12">
                                    <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                        v-model="title.value.value" :counter="10"
                                        :error-messages="title.errorMessage.value" placeholder="Title"></v-text-field>
                                </div>
                            </div>

                            <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12 py-0">
                                    <span class="float-left mt-2 small">Description<span class="text-red">*</span>
                                    </span>
                                </div>
                                <div class="col-md-9 col-sm-12 py-0">
                                    <v-textarea bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="Description.value.value"
                                        :counter="10000" :error-messages="Description.errorMessage.value"
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
                            <!-- <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12">
                                    <span class="float-left mt-2 small"> House Type </span>
                                </div>
                                <div class="col-md-9 col-sm-12">
                                    <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="houseTypes.value.value"
                                        :error-messages="houseTypes.errorMessage.value" :items="HouseTypes"
                                        placeholder="Select house type"></v-select>
                                </div>
                            </div> -->

                            <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12">
                                    <span class="float-left mt-2 small"> Property Type </span>
                                </div>
                                <div class="col-md-9 col-sm-12">
                                    <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact"
                                        v-model="propertyTypes.value.value"
                                        :error-messages="propertyTypes.errorMessage.value" :items="PropertyTypes"
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
                                        v-model="price.value.value" :error-messages="price.errorMessage.value"
                                        placeholder="price"></v-text-field>
                                </div>
                            </div>


                            <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12">
                                    <span class="float-left mt-2 small">Area</span>
                                </div>
                                <div class="col-md-9 col-sm-12">
                                    <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                        v-model="area.value.value" placeholder="Area"></v-text-field>
                                </div>
                            </div>
                            <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12">
                                    <span class="float-left mt-2 small">Deposit</span>
                                </div>
                                <div class="col-md-9 col-sm-12">
                                    <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
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
                                        v-model="least_contract.value.value" placeholder="Least Contract"></v-text-field>
                                </div>
                            </div>

                            <!-- <div class="row justify-content-between">
                                <div class="col-md-3 col-sm-12 py-0">
                                    <span class="float-left mt-2 small">Choose Image<span class="text-red">*</span> </span>
                                </div>
                                <div class="col-md-9 col-sm-12 py-0">
                                    <v-file-input counter multiple color="deep-purple-accent-4" chips
                                        truncate-length="15" v-model="image.value.value"
                                        :error-messages="image.errorMessage.value" :rules="rules"
                                        accept="image/png, image/jpeg, image/bmp" @change="showUploadPhoto" placeholder="Pick an avatar"
                                        prepend-icon="mdi-camera"></v-file-input>
                                </div>
                            </div> -->


                            <div class="row justify-content-between">
                                <div class="col-md-3 col-sm-12 py-0">
                                    <span class="float-left mt-2 small">Choose Image<span class="text-red">*</span>
                                    </span>
                                </div>
                                <div class="col-md-9 col-sm-12 py-0">
                                    <v-file-input counter multiple color="deep-purple-accent-4" chips
                                        truncate-length="15" v-model="image.value.value"
                                        :error-messages="image.errorMessage.value" :rules="rules"
                                        accept="image/png, image/jpeg, image/bmp" @change="showUploadPhoto"
                                        prepend-icon="mdi-camera"></v-file-input>
                                </div>
                            </div>


                            <!-- <div class="row justify-content-between">
                                <div class="col-md-2 col-sm-12">
                                    <span class="float-left mt-2 small">Image Url<span class="text-red">*</span></span>
                                </div>
                                <div class="col-md-9 col-sm-12">
                                    <v-text-field bg-color="#EDEDED" filled variant="solo" density="compact"
                                        rounded="lg" clear-icon="mdi-close-circle" clearable class="w-100"
                                        v-model="image.value.value" placeholder="Enter your image url"></v-text-field>
                                </div>
                            </div> -->


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

            <div class="col-md-5 p-0">
                <!-- Display post section start -->

                <div class="display-post">

                    <div class="header mb-3">
                        <v-icon>mdi-information</v-icon>
                        <p class="mt-3 ms-2">Recently created Sell posts</p>
                    </div>

                    <div class="body">

                        <!-- post card start -->
                        <div class="post-card bg-white" v-for="data in rent_data" :key="data">
                            <div class="row">
                                <div class="col-6 p-0 m-0 left-edit">

                                    <div class="top-section">
                                        <v-icon class="me-1">mdi-format-list-bulleted-type</v-icon>
                                        {{ data.type }}

                                    </div>

                                    <v-img :src="data.img"></v-img>
                                    <div class="btn-section">
                                        <v-btn to="/detailview">&nbsp;&nbsp; edit &nbsp;&nbsp;</v-btn>
                                        <v-btn>delete</v-btn>
                                    </div>

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
        }
    },

    mounted() {
        const cachedData = this.getLocationsFromSessionStorage();
        if (cachedData) {
            this.locations = cachedData;
        } else {
            this.fetchLocations();
        }
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

    },

}
</script>


<script setup>
    import { ref } from 'vue'
    import { useField } from 'vee-validate'
    import axios from 'axios';

    /* Field collection */
    const title = useField('title')
    const Description = useField('Description')
    // const houseTypes = useField('houseTypes')
    const propertyTypes = useField('propertyTypes')
    const price = useField('price')
    const area = useField('area')
    const deposit = useField('deposit');
    const least_contract = useField('least_contract');
    const image = useField('image')
    let photoList = null

    const PropertyTypes = ref([
        'Condo',
        'Apartment',
        'House'
    ])

    // const HouseTypes = ref([
    //     'Stand-alone House',
    //     'Two-story House',
    //     'Three-story House'
    // ])

    

    const selectedLocation = ref('')
    const submit = async () => {

    const formData = new FormData();
    formData.append('title', title.value.value);
    formData.append('description', Description.value.value);
    // formData.append('house_type', houseTypes.value.value);
    formData.append('property_type', propertyTypes.value.value);
    formData.append('price', price.value.value);
    formData.append('area', area.value.value);
    formData.append('deposit', deposit.value.value);
    formData.append('least_contract', least_contract.value.value);
    formData.append('location_id', selectedLocation.value);
    // Append the files as an array
    photoList.forEach((file) => {
        formData.append('files', file);
    });

    try {
    const response = await axios.post('http://localhost:8083/saverentpost', formData, {
        headers: {
        'Content-Type': 'multipart/form-data'
        }
    });
        console.log(response.data);
        title.resetField();
        Description.resetField();
        propertyTypes.resetField();
        price.resetField();
        area.resetField();
        deposit.resetField();
        least_contract.resetField();
        image.resetField();
    } catch (error) {
        console.error(error);
    }

    };  

    function showUploadPhoto() {
        photoList = Object.values(image.value.value);
        console.log(title.value.value)
        console.log(Description.value.value)
        console.log(photoList);
        console.log(selectedLocation.value)
    }

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
</style>