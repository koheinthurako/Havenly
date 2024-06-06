<template>
  <div>
    <h1>Locations</h1>
    <ul>
      <li v-for="location in locations" :key="location.region">
        {{ location.province }}, {{ location.amphoe }}, {{ location.region }}:
        Latitude: {{ location.latitude }}, Longitude: {{ location.longitude }}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      locations: [
        {
          "province": "Krabi",
          "amphoe": "Klongtom",
          "region": "Khlong Thom Nuea",
          "latitude": 0,
          "longitude": 0,
          "countries": {
            "country_id": 2
          }
        },
        {
          "province": "Krabi",
          "amphoe": "Klongtom",
          "region": "Klongtom Tai",
          "latitude": 0,
          "longitude": 0,
          "countries": {
            "country_id": 2

          }
        },
        {
          "province": "Krabi",
          "amphoe": "Klongtom",
          "region": "Klongphon",
          "latitude": 0,
          "longitude": 0,
          "countries": {
            "country_id": 2

          }
        },
      ],
      apiKey: 'YOUR_GOOGLE_MAPS_API_KEY'
    };
  },
  mounted() {
    this.fetchCoordinates();
  },
  methods: {
    async fetchCoordinates() {
      const promises = this.locations.map(location => this.getCoordinates(location));
      const results = await Promise.all(promises);
      this.locations = results.filter(result => result !== null);
    },
    async getCoordinates(location) {
      const { province, amphoe, region } = location;
      let address = `${region}, ${amphoe}, ${province}, Thailand`;
      let url = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${this.apiKey}`;

      console.log(`Querying address: ${address}`);

      try {
        let response = await axios.get(url);
        let data = response.data;
        if (data.results.length > 0) {
          const { lat, lng } = data.results[0].geometry.location;
          return { ...location, latitude: lat, longitude: lng };
        } else {
          console.warn(`No results found for ${address}. Trying fallback.`);
          // Fallback to a more generic search
          address = `${amphoe}, ${province}, Thailand`;
          url = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${this.apiKey}`;
          response = await axios.get(url);
          data = response.data;
          if (data.results.length > 0) {
            const { lat, lng } = data.results[0].geometry.location;
            return { ...location, latitude: lat, longitude: lng };
          } else {
            console.error(`No results found for fallback ${address}`);
            return null;
          }
        }
      } catch (error) {
        console.error(`Error fetching coordinates for ${address}:`, error);
        return null;
      }
    }
  }
};
</script>
