<!-- <template>
    <div class="googleContainerBox mt-3 pt-5">
        <div class="button-group d-flex flex-column mt-5 py-5">
            <div class="filterBox">
                <div class="form-header">
                    <h5 class="text-white">Choose your desire</h5>
                </div>
                <form @submit.prevent="submit" class="form-edit">
                    <div class="row">
                        <div class="p-0 row-1">
                            
                            <v-select bg-color="white" v-model="selectedCountry" :items="uniqueCountries"
                                label="Select country" required></v-select>
                            <v-select bg-color="white" v-model="selectedProvince" :items="uniqueProvinces"
                                :disabled="!selectedCountry" label="Select province" required></v-select>
                            <v-select bg-color="white" v-model="selectedAmphoe" :items="uniqueAmphoes"
                                :disabled="!selectedProvince" label="Select amphoe" required></v-select>
                            <v-select bg-color="white" v-model="selectedRegion" :items="uniqueRegions"
                                :disabled="!selectedAmphoe" label="Select region" required></v-select>

                            <div class="form-btn-group" :hidden="!selectedRegion">
                                <v-btn class="me-3 submit" type="submit"
                                    @click="searchPostByLocations(selectedLocation)">Search</v-btn>


                                    delete start 
                                <router-link to="/all/posts/postbylocations">
                  </router-link>delete end 


                                <v-btn class="clear" @click="clearFields">Clear</v-btn>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <GoogleMap :key="mapLocations.length" api-key="AIzaSyBqvZfzDW7YlZHtfaR-5l1v8f0YkMzswQM" :center="center"
                :zoom="zoom" style="width: 100%; height: 400px; padding-bottom: 50px;">

                delete start 
                Add markers here if needed
                <Marker :options="{position: this.center}"/>
                delete start 

                <Marker v-for="(location, index) in mapLocations" :key="index"
                    :options="{ position: { lat: parseFloat(location.latitude), lng: parseFloat(location.longitude) } }" />
            </GoogleMap>
        </div>
    </div>
</template> -->

<template>

    <div class="first-index-apk mt-5">
        <v-container>
            <div class="row">

                <div class="col-12 map-container-edit p-0 m-0">

                    <!-- form start -->
                    <form @submit.prevent="submit" class="form-edit">
                        <div class="row ">
                            <div class="col-6  p-0 m-0">
                                <div class="map-header-text me-auto">
                                    <h4 class=" fw-bold">Select location</h4>
                                </div>
                            </div>

                            <div class="col-6  p-0 m-0">
                                <div class="map-header-text-2 ms-auto d-flex" v-if="selectedRegion">
                                    <v-btn class="btn btn-danger btn-edit me-2" data-bs-target="#editModal"
                                        type="submit" @click="searchPostByLocations(selectedLocation)">Search</v-btn>
                                    <button class="btn btn-danger btn-edit" @click="clearFields">Clear
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="row " style="backdrop-filter: blur(10px);">


                            <div class="col-12 col-md-6 col-lg-3 p-0 m-0">
                                <div class="card">
                                    <div class="card-body p-0 m-0" style="border-radius: none;">


                                        <v-select hide-details bg-color="white" v-model="selectedCountry"
                                            :items="uniqueCountries" label="Select country" required></v-select>



                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-md-6 col-lg-3 p-0 m-0">
                                <div class="card">
                                    <div class="card-body p-0 m-0">

                                        <v-select hide-details bg-color="white" v-model="selectedProvince"
                                            :items="uniqueProvinces" :disabled="!selectedCountry"
                                            label="Select province" required></v-select>




                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-md-6 col-lg-3 p-0 m-0">
                                <div class="card">
                                    <div class="card-body p-0 m-0">

                                        <v-select hide-details bg-color="white" v-model="selectedAmphoe"
                                            :items="uniqueAmphoes" :disabled="!selectedProvince" label="Select amphoe"
                                            required></v-select>


                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-md-6 col-lg-3 p-0 m-0">
                                <div class="card">
                                    <div class="card-body p-0 m-0">

                                        <v-select hide-details bg-color="white" v-model="selectedRegion"
                                            :items="uniqueRegions" :disabled="!selectedAmphoe" label="Select region"
                                            required></v-select>

                                    </div>
                                </div>
                            </div>


                        </div>
                    </form>
                    <!-- form end -->

                    <!-- map here -->
                    <div class="row">
                        <div class="col-12 map-data-edit p-0 m-0">
                            <!-- <GoogleMap :key="mapLocations.length" api-key="AIzaSyBqvZfzDW7YlZHtfaR-5l1v8f0YkMzswQM" :center="center"
                    :zoom="zoom" class="map-form w-100">
                    <Marker v-for="(location, index) in mapLocations" :key="index"
                      :options="{ position: { lat: parseFloat(location.latitude), lng: parseFloat(location.longitude) } }" />
                  </GoogleMap> -->

                            <v-progress-circular class="prograss-edit" v-if="showLoading" indeterminate :size="80"
                                color="#e86f52" :width="7"></v-progress-circular>

                            <GoogleMap :key="mapLocations.length" api-key="AIzaSyBqvZfzDW7YlZHtfaR-5l1v8f0YkMzswQM"
                                :center="center" :zoom="zoom" style="width: 100%; height: 100%;">

                                <Marker v-for="(location, index) in mapLocations" :key="index"
                                    :options="{ position: { lat: parseFloat(location.latitude), lng: parseFloat(location.longitude) } }" />
                            </GoogleMap>

                        </div>
                    </div>

                </div>

            </div>
        </v-container>

    </div>
</template>

<script>
import { GoogleMap, Marker } from '../../node_modules/vue3-google-map'
import { AES } from 'crypto-js';

export default {
    name: 'GoogleMapSearch',

    components: {
        GoogleMap,
        Marker
    },

    data: () => ({
        showLoading: false,


        locations: [],
        mapLocations: [],
        center: { lat: 16.90177, lng: 96.09596 }, // Initial center of the map
        zoom: 13,
        selectedCountry: '',
        selectedProvince: '',
        selectedAmphoe: '',
        selectedRegion: '',
        selectedLocation: '',
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
                (!this.selectedCountry || location.country_name === this.selectedCountry) &&
                (!this.selectedProvince || location.province === this.selectedProvince) &&
                (!this.selectedAmphoe || location.amphoe === this.selectedAmphoe) &&
                (!this.selectedRegion || location.region === this.selectedRegion)
            );
        },
    },

    watch: {
        selectedRegion(newRegion) {
            if (newRegion) {
                const selectedLocation = this.locations.find(location => location.region === newRegion);
                if (selectedLocation) {
                    this.selectedLocation = selectedLocation.location_id;
                    console.log(this.selectedLocation); // Log the location_id
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
        localStorage.removeItem('openTab');
        const cachedData = this.getLocationsFromSessionStorage();
        if (cachedData) {
            this.locations = cachedData;
            this.mapLocations = cachedData;
        } else {
            this.fetchLocations();
        }
    },

    methods: {

        scrollToBottom() {
            setTimeout(() => {
                window.scrollTo({
                    top: document.body.scrollHeight,
                    behavior: 'smooth'
                });
            }, 2300);
        },

        async fetchLocations() {
            try {
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
            } catch (error) {
                console.error('Error fetching locations:', error);
            }
        },

        getLocationsFromSessionStorage() {
            const data = sessionStorage.getItem('locations');
            return data ? JSON.parse(data) : null;
        },

        submit() {
            this.mapLocations = this.filteredLocations;
            if (this.mapLocations.length > 0) {
                const latitudes = this.mapLocations.map(location => parseFloat(location.latitude));
                const longitudes = this.mapLocations.map(location => parseFloat(location.longitude));

                const centerLat = (Math.min(...latitudes) + Math.max(...latitudes)) / 2;
                const centerLng = (Math.min(...longitudes) + Math.max(...longitudes)) / 2;

                this.center = { lat: centerLat, lng: centerLng };
            } else {
                // If there are no filtered markers, reset the center to the initial value
                this.center = { lat: 16.90177, lng: 96.09596 };
            }
            console.log('Form submitted');
        },

        encryptId(id) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
            return encryptedId;
        },

        searchPostByLocations(location_id) {
            this.showLoading = true;
            this.scrollToBottom();

            console.log(location_id + " location search htae ka id");
            const encryptedId = this.encryptId(location_id);
            this.$emit('location-selected', encryptedId);
            // this.$router.push({ name: 'PostsByLocation', params: { locationId: `${encryptedId} Success` } });
            // eventControl.emit('search-post-by-location', encryptedId);
        },

        clearFields() {
            // Clear selected fields
            console.log("clear clicked!")
            this.selectedCountry = '';
            this.selectedProvince = '';
            this.selectedAmphoe = '';
            this.selectedRegion = '';
            this.zipCode = '';
        },

    }


}

</script>

<style lang="scss" scoped>
.first-index-apk {
    width: 100%;
    height: auto;
    background-image: url("@/assets/img/main-bg.jpg");

    position: relative;

    padding: 60px 0px;
    background-repeat: no-repeat;
    background-size: cover;

    .map-container-edit {
        padding: 0;
        width: 100%;
        height: 100%;

        .map-header-text {
            border: 6px solid #e86f52;
            border-bottom: 0;
            width: 320px;
            text-align: center;
            padding-top: 16px;
            padding-bottom: 16px;
            border-radius: 20px 20px 0 0;
            backdrop-filter: blur(10px);
            -webkit-backdrop-filter: blur(10px);
            background: rgba(255, 255, 255, 0.5);
        }

        .map-header-text-2 {
            width: 320px;
            text-align: center;
            padding-top: 16px;
            padding-bottom: 16px;
            border-radius: 20px 20px 0 0;

            .btn-edit {
                width: 50%;
            }
        }

        .map-data-edit {
            width: 100%;
            height: 300px;
            z-index: 1;
            padding: 0;
            margin: 0;
            background-color: #fdc9bc;
            display: flex;
            align-items: center;
            justify-content: center;

            .prograss-edit {
                width: 100%;
                height: 100%;
                position: absolute;
                top: 50;
                left: 50;
                z-index: 100;
                display: flex;
                flex-direction: column;
                backdrop-filter: blur(20px);
                -webkit-backdrop-filter: blur(20px);
                border-radius: 50%;
            }

            .map-form {
                z-index: 30;
                display: flex;
                flex-direction: column;
                width: 100%;
                height: 100%;
            }
        }
    }
}

.first-index-apk::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4);
}

@media only screen and (max-width: 991px) {
    .first-index-apk {
        padding: 20px;

        .map-container-edit {
            .map-data-edit {
                height: 370px;
            }

            .map-search-btn {
                padding: 18px 28px;
            }

            .map-header-text {
                border-color: yellow;
                padding-top: 16px;
                padding-bottom: 24px;
                width: 320px;
            }

            .map-header-text-2 {
                width: 270px;
                display: flex;
                justify-content: space-around;
                border: 1px solid red;

                .btn-edit {
                    width: 35%;
                }
            }
        }
    }
}

@media only screen and (max-width: 620px) {
    .first-index-apk {
        .map-container-edit {

            .map-header-text {
                border-color: yellow;
                padding-top: 16px;
                padding-bottom: 24px;
                width: 300px;
            }

            .map-header-text-2 {
                width: 220px;
                display: flex;
                justify-content: space-around;
                border: 1px solid red;

                .btn-edit {
                    width: 35%;
                }
            }
        }
    }
}

@media only screen and (max-width: 555px) {
    .first-index-apk {
        .map-container-edit {
            .map-header-text {
                border-color: yellow;
                padding-top: 16px;
                padding-bottom: 24px;
                width: 250px;
                font-size: 12px !important;
            }

            .map-header-text-2 {
                width: 190px;
                display: flex;
                justify-content: space-around;
                border: 1px solid red;

                .btn-edit {
                    width: 35%;
                }
            }
        }
    }
}
</style>