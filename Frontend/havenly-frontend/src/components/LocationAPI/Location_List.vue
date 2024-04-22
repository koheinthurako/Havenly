<template>
  <div>
    <h1>Locations</h1>
    <ul>
      <li v-for="location in locations" :key="location.id">
        {{ location.province }}, {{ location.amphoe }}, {{ location.district }}
        <button @click="viewImages(location)">View Images</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      locations: [],
    };
  },
  mounted() {
    this.fetchLocations();
  },
  methods: {
    async fetchLocations() {
      try {
        const response = await axios.get('http://localhost:3000/locations');
        this.locations = response.data;
      } catch (error) {
        console.error('Error fetching locations:', error);
      }
    },
    async viewImages(location) {
      try {
        const response = await axios.get(`/api/locations/${location.id}/images`);
        const images = response.data;
        // Display images or navigate to a new component
        console.log(images);
      } catch (error) {
        console.error('Error fetching images:', error);
      }
    },
  },
};
</script>
