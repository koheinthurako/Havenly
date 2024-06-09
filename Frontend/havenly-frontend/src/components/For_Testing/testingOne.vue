<template>
  <div>
    <h1>Update Coordinates</h1>
    <button @click="updateCoordinates">Update Coordinates</button>
    <div v-if="loading">Loading...</div>
    <div v-else>
      <pre>{{ updatedLocations }}</pre>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      locations: [
        {
          province: "Krabi",
          amphoe: "Plai Phraya",
          region: "เขาเขน",
          latitude: 0,
          longitude: 0,
          countries: { country_id: 2 },
        },
        {
          province: "Krabi",
          amphoe: "Lam Thap",
          region: "ดินอุดม",
          latitude: 0,
          longitude: 0,
          countries: { country_id: 2 },
        },
        {
          province: "Krabi",
          amphoe: "Lam Thap",
          region: "ดินแดง",
          latitude: 0,
          longitude: 0,
          countries: { country_id: 2 },
        },
        {
          province: "Krabi",
          amphoe: "Lam Thap",
          region: "ทุ่งไทรทอง",
          latitude: 0,
          longitude: 0,
          countries: { country_id: 2 },
        },
        {
          province: "Krabi",
          amphoe: "Lam Thap",
          region: "ลำทับ",
          latitude: 0,
          longitude: 0,
          countries: { country_id: 2 },
        },
      ],
      updatedLocations: [],
      loading: false,
      apiKey: 'https://maps.googleapis.com/maps/api/js?sensor=false&callback=myMap', // Replace with your actual Google Maps API key
    };
  },
  methods: {
    async updateCoordinates() {
      this.loading = true;
      const promises = this.locations.map(async (location) => {
        const address = `${location.region}, ${location.amphoe}, ${location.province}, Thailand`;
        try {
          const response = await axios.get('https://maps.googleapis.com/maps/api/geocode/json', {
            params: {
              address: address,
              key: this.apiKey,
            },
          });

          const results = response.data.results;
          if (results.length > 0) {
            const { lat, lng } = results[0].geometry.location;
            return {
              ...location,
              latitude: lat,
              longitude: lng,
            };
          } else {
            return location;
          }
        } catch (error) {
          console.error(`Error fetching geocode for ${address}:`, error);
          return location;
        }
      });

      this.updatedLocations = await Promise.all(promises);
      this.loading = false;
    },
  },
};
</script>

<style scoped>
button {
  padding: 10px 20px;
  font-size: 16px;
  margin-bottom: 20px;
}
</style>


<!-- <template>
  <div>
    <h1>Update Coordinates</h1>
    <div v-for="(location, index) in locations" :key="index">
      <h2>{{ location.region }}, {{ location.amphoe }}, {{ location.province }}, Thailand</h2>
      <div>
        Latitude: <input type="text" v-model="location.latitude">
        Longitude: <input type="text" v-model="location.longitude">
      </div>
      <hr>
    </div>
    <button @click="showUpdatedData">Show Updated Data</button>
    <div v-if="showData">
      <pre>{{ updatedLocations }}</pre>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      locations: [
        {
          province: "Krabi",
          amphoe: "Plai Phraya",
          region: "เขาเขน",
          latitude: "", // Leave these blank initially
          longitude: "",
          countries: { country_id: 2 },
        },
        {
          province: "Krabi",
          amphoe: "Lam Thap",
          region: "ดินอุดม",
          latitude: "",
          longitude: "",
          countries: { country_id: 2 },
        },
        // Add more locations here...
      ],
      showData: false,
    };
  },
  methods: {
    showUpdatedData() {
      this.showData = true;
      this.updatedLocations = this.locations.map(location => {
        return {
          ...location,
          latitude: parseFloat(location.latitude), // Convert to number
          longitude: parseFloat(location.longitude),
        };
      });
    },
  },
};
</script> -->
