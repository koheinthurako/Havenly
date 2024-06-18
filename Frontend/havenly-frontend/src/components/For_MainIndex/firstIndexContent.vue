  <template>

    <div class="first-index mt-5">
      <div class="button-group d-flex flex-column mt-5 pt-3">
        <GoogleMap :key="mapLocations.length" api-key="AIzaSyBqvZfzDW7YlZHtfaR-5l1v8f0YkMzswQM"
                :center="center"
                :zoom="zoom"
                style="width: 100%; height: 430px;">
            <Marker v-for="(location, index) in mapLocations"
            :key="index"
            :options="{position: {lat: parseFloat(location.latitude), lng: parseFloat(location.longitude)}}"/>
        </GoogleMap>
        <div class="d-flex justify-content-end mt-5">
          <button class="btn btn-lg btn-light text-danger rounded-pill px-5" @click="searchPostByLocations(selectedLocation)">Search posts by location >></button>
        </div>
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

  mounted() {
    localStorage.removeItem('openTab');
    const cachedData = this.getLocationsFromSessionStorage();
    if(cachedData) {
        this.locations = cachedData;
        this.mapLocations = cachedData;
    }
    this.fetchSubUser();
  },

  methods: {

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
      this.$router.push({ name: 'MainLocationPosts', params: { locationId: `${encryptedId} Success` } });
    },

  },
}
</script>

<style scoped>
.v-select .v-select__selection:hover,
.v-select .v-select__selection:focus {
    background-color: red !important;
}
</style>