<!-- <template>
    <div class="first-index">
      <div class="button-group d-flex flex-column">
        <div class="filterBox">
            <div class="form-header">
          <h5 class="text-white">Choose your desire</h5>
        </div>
        <form @submit.prevent="submit" class="form-edit">
          <div class="row">
            <div class="p-0 row-1">
              <v-select bg-color="white" v-model="selectedCountry" :items="uniqueCountries" label="Select country" required></v-select>
              <v-select bg-color="white" v-model="selectedProvince" :items="uniqueProvinces" :disabled="!selectedCountry" label="Select province" required></v-select>
              <v-select bg-color="white" v-model="selectedAmphoe" :items="uniqueAmphoes" :disabled="!selectedProvince" label="Select amphoe" required></v-select>
              <v-select bg-color="white" v-model="selectedRegion" :items="uniqueDistricts" :disabled="!selectedAmphoe" label="Select region" required></v-select>
              <div class="form-btn-group" :hidden="!selectedRegion">
                <v-btn class="me-3 submit" type="submit">Search</v-btn>
                <v-btn class="clear" @click="clearFields">Clear</v-btn>
              </div>
            </div>
          </div>
        </form>
        </div>
        <GMapMap>
            <g-map-map
                :center="center"
                :zoom="zoom"
                style="width: 100%; height: 400px"
            >
            </g-map-map>
        </GMapMap>
      </div>
    </div>
  </template> -->


  <template>
    <div class="first-index mt-5 pt-5">
      <div class="button-group d-flex flex-column mt-5 py-5">
        <div class="filterBox">
          <div class="form-header">
            <h5 class="text-white">Choose your desire</h5>
          </div>
          <form @submit.prevent="submit" class="form-edit">
            <div class="row">
              <div class="p-0 row-1">
                <v-select bg-color="white" v-model="selectedCountry" :items="uniqueCountries" label="Select country" required></v-select>
                <v-select bg-color="white" v-model="selectedProvince" :items="uniqueProvinces" :disabled="!selectedCountry" label="Select province" required></v-select>
                <v-select bg-color="white" v-model="selectedAmphoe" :items="uniqueAmphoes" :disabled="!selectedProvince" label="Select amphoe" required></v-select>
                <v-select bg-color="white" v-model="selectedRegion" :items="uniqueRegions" :disabled="!selectedAmphoe" label="Select region" required></v-select>
                <div class="form-btn-group" :hidden="!selectedRegion">
                  <v-btn class="me-3 submit" type="submit" @click="searchPostByLocations(selectedLocation)">Search</v-btn>
                  <!-- <router-link to="/all/posts/postbylocations">
                  </router-link> -->
                  <v-btn class="clear" @click="clearFields">Clear</v-btn>
                </div>
              </div>
            </div>
          </form>
        </div>
        <GoogleMap :key="mapLocations.length" api-key="AIzaSyBqvZfzDW7YlZHtfaR-5l1v8f0YkMzswQM"
                :center="center"
                :zoom="zoom"
                style="width: 100%; height: 400px; padding-bottom: 50px;">
            <!-- Add markers here if needed -->
            <!-- <Marker :options="{position: this.center}"/> -->
            <Marker v-for="(location, index) in mapLocations"
            :key="index"
            :options="{position: {lat: parseFloat(location.latitude), lng: parseFloat(location.longitude)}}"/>
        </GoogleMap>
      </div>
    </div>
  </template>


<script>
import json_data from '../../assets/json/thailand_location.json'
import { GoogleMap, Marker } from '../../../node_modules/vue3-google-map'
import axios from 'axios';
import { AES } from 'crypto-js';

export default {
  name: 'firstIndexContent',

  components: {
    GoogleMap,
    Marker
  },

  data() {
    return {
      locations: [],
      users: json_data,
      selectedCountry: '',
      selectedProvince: '',
      selectedAmphoe: '',
      selectedRegion: '',
      selectedLocation: '',
      zipCode: '',
      center: { lat: 16.90177, lng: 96.09596 }, // Initial center of the map
      zoom: 13,
      mapLocations: [],
    }
  },

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
    }
  },

  mounted() {
    localStorage.removeItem('openTab');
    const cachedData = this.getLocationsFromSessionStorage();
    if(cachedData) {
        this.locations = cachedData;
        this.mapLocations = cachedData;
    } else {
        this.fetchLocations();
    }
    this.fetchSubUser();
  },

  methods: {

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

    // fetchLocations() {
    //   fetch('http://localhost:8083/locations/getall')
    //   .then(response => response.json())
    //   .then(data => {
    //         const mappedData = data.map(location => ({
    //           location_id: location.location_id,
    //           country_name: location.country_name,
    //           province: location.province,
    //           amphoe: location.amphoe,
    //           region: location.region,
    //           latitude: location.latitude,
    //           longitude: location.longitude
    //       }));
    //       sessionStorage.setItem('locations', JSON.stringify(mappedData));
    //       this.locations = mappedData;
    //       this.mapLocations = mappedData;
    //   })
    //   .catch(error => {
    //       console.error('Error fetching locations:', error);
    //   });
    // },

    getLocationsFromSessionStorage() {
        const data = sessionStorage.getItem('locations');
        return data ? JSON.parse(data) : null;
    },

    async fetchSubUser() {
      const user = sessionStorage.getItem('login_user');
      if (user) {
        const parsedUser = JSON.parse(user);
        const registerId = parsedUser.register_id;
        console.log("registerId to send backend to show subUser informations: " + registerId);

        try {
          const response = await axios.get('http://localhost:8083/subscribe/getSubUserInfo', {
            params: { registerId: registerId }
          });
          sessionStorage.setItem('sub_user', JSON.stringify(response.data));
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }
    },

    // fetchSubUser() {
    //   if(sessionStorage.getItem('login_user')) {
    //     const user = JSON.parse(sessionStorage.getItem('login_user'));
    //     const registerId = user.register_id;
    //     console.log("registerId to send backend to show subUser informations : " + registerId)
    //     axios.get('http://localhost:8083/subscribe/getSubUserInfo', {
    //         params: {
    //             registerId: registerId
    //         }
    //     })
    //     .then(response => {
    //       sessionStorage.setItem('sub_user',JSON.stringify(response.data))
    //     })
    //     .catch(error => {
    //       console.error('Error fetching data:', error); // Handle the error
    //     }); 
    //   }
    // },

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

    clearFields() {
      // Clear selected fields
      console.log("clear clicked!")
      this.selectedCountry = '';
      this.selectedProvince = '';
      this.selectedAmphoe = '';
      this.selectedRegion = '';
      this.zipCode = '';
    },

    encryptId(id) {
      const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
      const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
      return encryptedId;
    },

    searchPostByLocations(location_id) {
      console.log(location_id + " location search htae ka id");
      const encryptedId = this.encryptId(location_id);
      this.$router.push({ name: 'PostsByLocation', params: { locationId: `${encryptedId} Success` } });
    }

  },
}
</script>

<style scoped>
.v-select .v-select__selection:hover,
.v-select .v-select__selection:focus {
    background-color: red !important;
}
</style>