<template>
    <div>
      <h1>Photos</h1>
      <ul>
        <li v-for="photo in photos" :key="photo.photo_id">
          <img v-for="url in photo.photo_urls" :key="url" :photo_urls="url" alt="photo">
        </li>
      </ul>
    </div>
  </template>
  
  <script>
// import axios from 'axios';

  export default {
    data() {
      return {
        photos: []
      }
    },
    mounted() {
    fetch('http://localhost:8083/testphoto')
     .then(response => response.json())
     .then(data => {
        this.photos = data;
      })
     .catch(error => {
        console.error(error);
      });
  }
  }
  </script>