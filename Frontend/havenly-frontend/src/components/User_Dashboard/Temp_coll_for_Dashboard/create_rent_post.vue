<template>
    <div class="create-rent-post-section px-4">
        <div class="row">
            <div class="col-md-7 p-0">

                <!-- TZH Form -->
                <div class="create-post">
                    <div class="header">
                        <v-icon>mdi-information</v-icon>
                        <p class="mt-3 ms-2">Create Post For Rent</p>
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
                                        :counter="200" :error-messages="Description.errorMessage.value"
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
                                    <span class="float-left mt-2 small"> House Type </span>
                                </div>
                                <div class="col-md-9 col-sm-12">
                                    <v-select bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                                        variant="solo" rounded="lg" density="compact" v-model="houseTypes.value.value"
                                        :error-messages="houseTypes.errorMessage.value" :items="HouseTypes"
                                        placeholder="Select house type"></v-select>
                                </div>
                            </div>

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

                    <div class="header">
                        <v-icon>mdi-information</v-icon>
                        <p class="mt-3 ms-2">Recent created Rent posts</p>
                    </div>

                    <div class="body">

                        <!-- post card start -->
                        <div class="post-card" v-for="data in rent_data" :key="data">
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
                                <div class="col-6 px-2 py-2 m-0 right-edit">

                                    <h5>{{ truncatedTitle(data.title) }}</h5>
                                    <hr>
                                    <p>{{ truncatedParagraph(data.paragraph) }}</p>
                                    <hr>

                                    <div class="d-flex">

                                        <v-icon>mdi-currency-usd</v-icon>
                                        <h5 class="me-2">2400</h5>
                                        <span>Lakh(kyats)</span>

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
        house_type: '',
        property_type: '',
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
                return !value || !value.length || value[0].size < 2000000 || 'Avatar size should be less than 2 MB!'
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

        // limit the header ;)
        truncatedTitle(title) {
            const maxLength = 23; // Maximum number of characters
            if (title.length > maxLength) {
                return title.slice(0, maxLength) + " ...";
            } else {
                return title;
            }
        },

        // limit the paragraph ;)
        truncatedParagraph(paragraph) {
            const maxLength = 150; // Maximum number of characters
            if (paragraph.length > maxLength) {
                return paragraph.slice(0, maxLength) + " ...";
            } else {
                return paragraph;
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


<!-- <script setup>
    import { ref } from 'vue'
    import { useField } from 'vee-validate'
    // import axios from 'axios';

    /* Field collection */
    const title = useField('title')
    const Description = useField('Description')
    const houseTypes = useField('houseTypes')
    const propertyTypes = useField('propertyTypes')
    const price = useField('price')
    const area = useField('area')
    const image = useField('image')
    let photoList = null

    const HouseTypes = ref([
        'Stand-alone House',
        'Two-story House',
        'Three-story House'
    ])

    const PropertyTypes = ref([
        'Condo',
        'Apartment'
    ])

    const selectedLocation = ref('')

    function showUploadPhoto() {
        photoList = Object.values(image.value.value);
        console.log(photoList);
    }

    const submit = async () => {

        const formData = {
            title: title.value.value,
            description: Description.value.value,
            house_type: houseTypes.value.value,
            property_type: propertyTypes.value.value,
            price: price.value.value,
            area: area.value.value,
            photos: photoList,
            locations: {
                location_id: selectedLocation.value
            }
        };

        // for (let i = 0; i < image.value.value.length; i++) {
        //     formData.append('photos', image.value.value[i]);
        // }

        // console.log(formData);

        // try {
        //     const response = await axios.post('http://localhost:8083/savesellpost', formData)
        //     console.log(response.data)
        //     title.resetField();
        //     Description.resetField();
        //     houseTypes.resetField();
        //     propertyTypes.resetField();
        //     price.resetField();
        //     area.resetField();
        //     image.resetField();
        // } catch (error) {
        // console.error(error)
        // }


/* ---------------------------------------- */


// const formData = new FormData();

// // Append other form fields
// formData.append('title', title.value.value);
// formData.append('description', Description.value.value);
// formData.append('house_type', houseTypes.value.value);
// formData.append('property_type', propertyTypes.value.value);
// formData.append('price', price.value.value);
// formData.append('area', area.value.value);
// formData.append('locations[location_id]', selectedLocation.value.value);

// // Append image files
// for (let i = 0; i < image.value.value.length; i++) {
//     formData.append('photos', image.value.value[i].name);
// }

// console.log(formData);

// fetch('http://localhost:8083/savesellpost', {
//     method: 'POST',
//     body: formData,
//     headers: {
//         // No need to set Content-Type, FormData handles it automatically
//     }
// })
// .then(response => response.json())
// .then(data => {
//     console.log(data);
//     title.resetField();
//     Description.resetField();
//     houseTypes.resetField();
//     propertyTypes.resetField();
//     price.resetField();
//     area.resetField();
//     image.resetField();
// })
// .catch(error => {
//     console.error(error);
// });




/* ---------------------------------------- */

    // const formData = new FormData();
    // formData.append('sellPost', JSON.stringify({
    //     title: title.value.value,
    //     description: Description.value.value,
    //     house_type: houseTypes.value.value,
    //     property_type: propertyTypes.value.value,
    //     price: price.value.value,
    //     area: area.value.value,
    //     photos: photoList,
    //     locations: {
    //         location_id: selectedLocation.value
    //     }
    // }));

    // formData.append('sellPost', {
    //     title: title.value.value,
    //     description: Description.value.value,
    //     house_type: houseTypes.value.value,
    //     property_type: propertyTypes.value.value,
    //     price: price.value.value,
    //     area: area.value.value,
    //     locations: {
    //         location_id: selectedLocation.value
    //     }
    // });

    // Append each file to formData
    // for (let i = 0; i < image.value.value.length; i++) {
    //     formData.append('photos', image.value.value[i]);
    // }

    // console.log(formData);

    // try {
    //     const response = await axios.post('http://localhost:8083/savesellpost', formData, {
    //         headers: {
    //             'Content-Type': 'multipart/form-data'
    //         }
    //     });
    //     console.log(response.data);
    //     title.resetField();
    //     Description.resetField();
    //     houseTypes.resetField();
    //     propertyTypes.resetField();
    //     price.resetField();
    //     area.resetField();
    //     image.resetField();
    // } catch (error) {
    //     console.error(error);
    // }

    // const formDataBlob = new Blob([formData], { type: 'multipart/form-data;boundary=' + formData.boundary });

    fetch('http://localhost:8083/savesellpost', {
        method: 'POST',
        body: formData,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            title.resetField();
            Description.resetField();
            houseTypes.resetField();
            propertyTypes.resetField();
            price.resetField();
            area.resetField();
            image.resetField();
        })
        .catch(error => {
            console.error(error);
        });

    }

    

</script> -->






<script setup>
import { ref } from 'vue'
import { useField } from 'vee-validate'
import axios from 'axios';

/* Field collection */
const title = useField('title')
const Description = useField('Description')
const houseTypes = useField('houseTypes')
const propertyTypes = useField('propertyTypes')
const price = useField('price')
const area = useField('area')
const image = useField('image')
let photoList = null

const HouseTypes = ref([
    'Stand-alone House',
    'Two-story House',
    'Three-story House'
])

const PropertyTypes = ref([
    'Condo',
    'Apartment'
])

const selectedLocation = ref('')

function showUploadPhoto() {
    photoList = Object.values(image.value.value);
    console.log(title.value.value)
    console.log(Description.value.value)
    console.log(photoList[0]);
    console.log(selectedLocation.value)
}

// const submit = async () => {
//     // const formData = new FormData();
//     // formData.append('file', photoList[0]); // Assuming image is a File object
//     // formData.append('title', title.value.value);
//     // formData.append('description', Description.value.value);
//     // formData.append('house_type', houseTypes.value.value);
//     // formData.append('property_type', propertyTypes.value.value);
//     // formData.append('price', price.value.value);
//     // formData.append('area', area.value.value);
//     // formData.append('locations[location_id]', selectedLocation.value); // Assuming selectedLocation is the location ID

//     const formData = {
//         title: title.value.value,
//         description: Description.value.value,
//         house_type: houseTypes.value.value,
//         property_type: propertyTypes.value.value,
//         price: price.value.value,
//         area: area.value.value,
//         image: photoList[0],
//         locations: {
//             location_id: selectedLocation.value
//         }
//     };

//     console.log(formData);

//     // fetch('http://localhost:8083/savetestsellpost', {
//     //     method: 'POST',
//     //     body: formData
//     // })
//     // .then(response => {
//     //     if (!response.ok) {
//     //         throw new Error('Network response was not ok');
//     //     }
//     //     return response.json();
//     // })
//     // .then(data => {
//     //     console.log(data);
//     //     title.resetField();
//     //     Description.resetField();
//     //     houseTypes.resetField();
//     //     propertyTypes.resetField();
//     //     price.resetField();
//     //     area.resetField();
//     //     image.resetField();
//     // })
//     // .catch(error => {
//     //     console.error('There was a problem with the fetch operation:', error);
//     // });

//     try {
//         const response = await axios.post('http://localhost:8083/savesellpost', formData, {
//             headers: {
//                 'Content-Type': 'multipart/form-data'
//             }
//         });
//         console.log(response.data);
//         title.resetField();
//         Description.resetField();
//         houseTypes.resetField();
//         propertyTypes.resetField();
//         price.resetField();
//         area.resetField();
//         image.resetField();
//     } catch (error) {
//         console.error(error);
//     }


const submit = async () => {
    // const formData = new FormData();
    // formData.append('file', photoList[0]); // Use 'file' as the key
    // formData.append('title', title.value.value);
    // formData.append('description', Description.value.value);
    // formData.append('house_type', houseTypes.value.value);
    // formData.append('property_type', propertyTypes.value.value);
    // formData.append('price', price.value.value);
    // formData.append('area', area.value.value);
    // formData.append('locations', selectedLocation.value); // Append the location ID directly

    const formData = {
        title: title.value.value,
        description: Description.value.value,
        house_type: houseTypes.value.value,
        property_type: propertyTypes.value.value,
        price: price.value.value,
        area: area.value.value,
        file: photoList[0],
        location_id: selectedLocation.value
        // locations: {
        //     location_id: selectedLocation.value
        // }
    };

    console.log(formData);

    try {
        const response = await axios.post('http://localhost:8083/savetestsellpost', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        console.log(response.data);
        title.resetField();
        Description.resetField();
        houseTypes.resetField();
        propertyTypes.resetField();
        price.resetField();
        area.resetField();
        image.resetField();
    } catch (error) {
        console.error(error);
    }
};

</script>
