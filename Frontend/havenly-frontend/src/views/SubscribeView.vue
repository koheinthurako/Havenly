<template>
    <div class="d-flex align-center justify-center" style="height: 100vh">
        <v-sheet width="400" class="mx-auto">
            <v-form fast-fail @submit.prevent="subscription">
                <v-text-field variant="underlined" v-model="user.nrc" label="NRC"></v-text-field>
                <v-combobox v-model="user.packName" label="Packages" :items="['Free Trial', 'Normal', 'Premium']"></v-combobox>
                <v-btn type="submit" color="primary"  block class="mt-2">Subscribe</v-btn>
            </v-form>
            <div class="mt-2">
                <p class="text-body-2">
                   <a href="#">See more</a>
                </p>
            </div>
        </v-sheet>
    </div>
  </template>
  <script>
  import axios from 'axios'
  export default {
   
    data() {
        return {
          user :{
            nrc: '',
            packName: ''
          }
        };
    },
    methods: {
        subscription() {
          axios.post("http://localhost:8083/subscribe",this.user)
     .then(response => console.log(response))
            //
        },
    },
  };
  </script>
  