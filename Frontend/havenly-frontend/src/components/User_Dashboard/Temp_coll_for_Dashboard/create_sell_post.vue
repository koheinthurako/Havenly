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
                                        variant="solo" rounded="lg" density="compact" v-model="Description"
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
                                        variant="solo" rounded="lg" density="compact" v-model="selectedProperty"
                                        :items="items" placeholder="Select property type"></v-select>
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
                                        truncate-length="15" accept="image/png, image/jpeg, image/bmp"
                                        @change="showUploadPhoto" prepend-icon="mdi-camera"></v-file-input>
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
                                <v-btn class="me-4" @click="saveAllPosts" rounded="xl" color="#E86F52">
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
                        <div class="post-card bg-white" v-for="data in showPosts" :key="data">
                            <div class="row">
                                <div class="col-6 p-0 m-0 left-edit">

                                    <div class="top-section">
                                        <v-icon class="me-1">mdi-format-list-bulleted-type</v-icon>
                                        {{ data.post_id }}

                                    </div>

                                    <v-img :src="data.photos[0]"></v-img>
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
import axios from 'axios';
export default {
    name: 'create_post',
    data: () => ({
        showPosts: [],

        location_id: '',
        title: '',
        description: '',
        // image: [],
        image: '',
        price: '',
        area: '',
        files: [],
        house_type: '',
        property_type: '',
        locations: [],
        selectedCountry: '',
        selectedProvince: '',
        selectedAmphoe: '',
        selectedRegion: '',
        selectedLocation: '',

        change_type: 'sell',
        photoList: [],

        selectedProperty: null,
        items: ['Condo', 'Apartment', 'House', 'Office'],


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
        this.initialize();

        const cachedData = this.getLocationsFromSessionStorage();
        if (cachedData) {
            this.locations = cachedData;
        } else {
            this.

                Locations();
        }
    },

    methods: {
        showUploadPhoto(event) {
            this.files = event.target.files;
        },

        async saveAllPosts() {

            const formData = new FormData();
            formData.append('title', this.title);
            formData.append('description', this.Description);
            // formData.append('house_type', houseTypes.value);
            formData.append('property_type', this.selectedProperty);
            formData.append('price', this.price);
            formData.append('area', this.area);
            formData.append('location_id', this.selectedLocation);

            // Append the files as an array
            for (let i = 0; i < this.files.length; i++) {
                formData.append('files', this.files[i]);
            }

            try {
                const response = await axios.post('http://localhost:8083/savetestsellpost', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
                console.log(response.data);
                // Reset form fields
                this.title.resetField();
                this.Description.resetField();
                // houseTypes.resetField();
                this.propertyTypes.resetField();
                this.price.resetField();
                this.area.resetField();
                this.image.resetField(); // Ensure this handles the reset of photoList appropriately
            } catch (error) {
                console.error(error);
                // Add user feedback for error here if necessary
            }

        },

        async initialize() {
            try {
                const response = await axios.get('http://localhost:8083/');
                this.showPosts = response.data.map(post => {
                    // Modify the object to include the profile image URL
                    return {
                        post_id: post.post_id,
                        title: post.title,
                        description: post.description,
                        house_type: post.house_type,
                        property_type: post.property_type,
                        area: post.area,
                        price: post.price,
                        photos: post.photoUrls,
                    };
                });
                this.showPosts.sort((a, b) => b.post_id - a.post_id);
            } catch (error) {
                console.error('Error fetching students:', error);
            }
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

        // getLocationIdByRegion(region, selectedRegion) {
        //     const location = this.locations.find(location => location.region === region);
        //     return location ? location.location_id : null;
        // }

    },

}
</script>

<script setup>
// import { ref } from 'vue'
// import { useField } from 'vee-validate'


// /* Field collection */
// const title = useField('title')
// const Description = useField('Description')
// // const houseTypes = useField('houseTypes')
// const propertyTypes = useField('propertyTypes')
// const price = useField('price')
// const area = useField('area')
// const image = useField('image')


// const PropertyTypes = ref([
//     'Condo',
//     'Apartment',
//     'House'
// ])

// const HouseTypes = ref([
// 'Stand-alone House',
// 'Two-story House',
// 'Three-story House'
// ])



// const selectedLocation = ref('')


// if(Description.value.value.length > 100) {
// console.log("Yes it is longer than 100!")
// } else {
// console.log("Checking count letter false!")
// }

// const submit = async () => {

// // const formData = {
// // title: title.value.value,
// // description: Description.value.value,
// // house_type: houseTypes.value.value,
// // property_type: propertyTypes.value.value,
// // price: price.value.value,
// // area: area.value.value,
// // file: photoList[0],
// // location_id: selectedLocation.value
// // };

// // const formData = {
// // title: title.value.value,
// // description: Description.value.value,
// // house_type: houseTypes.value.value,
// // property_type: propertyTypes.value.value,
// // price: price.value.value,
// // area: area.value.value,
// // files: [...photoList],
// // location_id: selectedLocation.value
// // };

// // console.log(formData.files[0]);


// // try {
// // const response = await axios.post('http://localhost:8083/savetestsellpost', formData, {
// // headers: {
// // 'Content-Type': 'multipart/form-data'
// // }
// // });
// // console.log(response.data);
// // title.resetField();
// // Description.resetField();
// // houseTypes.resetField();
// // propertyTypes.resetField();
// // price.resetField();
// // area.resetField();
// // image.resetField();
// // } catch (error) {
// // console.error(error);
// // }



// const formData = new FormData();
// formData.append('title', title.value);
// formData.append('description', Description.value);
// // formData.append('house_type', houseTypes.value);
// formData.append('property_type', propertyTypes.value);
// formData.append('price', price.value);
// formData.append('area', area.value);
// formData.append('location_id', selectedLocation.value);

// // Append the files as an array
// photoList.forEach((file) => {
// formData.append('files', file);
// });

// try {
// const response = await axios.post('http://localhost:8083/savetestsellpost', formData, {
// headers: {
// 'Content-Type': 'multipart/form-data'
// }
// });
// console.log(response.data);
// // Reset form fields
// title.resetField();
// Description.resetField();
// // houseTypes.resetField();
// propertyTypes.resetField();
// price.resetField();
// area.resetField();
// image.resetField(); // Ensure this handles the reset of photoList appropriately
// } catch (error) {
// console.error(error);
// // Add user feedback for error here if necessary
// }

// };

// function showUploadPhoto() {
// photoList = Object.values(image.value.value);
// console.log(title.value.value)
// console.log(Description.value.value)
// console.log(photoList);
// console.log(selectedLocation.value)
// }


</script>
