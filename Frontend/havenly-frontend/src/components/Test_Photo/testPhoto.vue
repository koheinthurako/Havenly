<template>
    <div class="my-5 text-center">
      <h1>Photo Gallery</h1>
      <div v-for="photo in photos" :key="photo.photo_id" class="my-3">
        <h2>Photo ID: {{ photo.photo_id }}</h2>
        <div v-for="url in photo.photo_urls" :key="url" class="w-100 d-inline">
          <img :src="url" alt="photo" style="width: 300px; height: 300px;">
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        photos: []
      };
    },
    mounted() {
      this.fetchPhotos();
    },
    methods: {
      fetchPhotos() {
        // Make API call to fetch posts  from backend
        fetch('http://localhost:8083/testphoto/getall')
          .then(response => response.json())
          .then(data => {
            this.photos = data.map(post => ({
              post_id: post.post_id,
              title: post.title,
              description: post.description,
              house_type: post.house_type,
              property_type: post.property_type,
              area: post.area,
              price: post.price,
              photo_urls: post.photoUrls,
             }));
          })
          .catch(error => {
            console.error('Error fetching photos:', error);
          });
      }
    }
  };
  </script>
  