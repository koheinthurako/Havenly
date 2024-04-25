<template>
    <div class="d-flex align-center justify-center" style="height: 100vh">
        <v-sheet width="400" class="mx-auto">
            <v-form fast-fail @submit.prevent="buyPackages">
                
                <v-text-field variant="underlined" v-model="user.name" label="Name"></v-text-field>
                <v-text-field variant="underlined" v-model="user.nrc" label="NRC"></v-text-field>
                <v-text-field variant="underlined" v-model="user.email" label="Email"></v-text-field>
                <v-autocomplete variant="underlined" v-model="user.packageType" label="Packages" 
                :items="packages.packageNames" 
                :value="user.packageType"
                @change="onChange"></v-autocomplete>
                <v-btn type="submit" color="#e86f52"  block class="mt-2">Buy</v-btn>
            </v-form>
            <div class="mt-2">
                <p class="text-body-2">
                  selected package : {{ user.packageType }}
                </p>
                <p class="text-body-2">
                  <a href="http://localhost:8081/cancel"> Cancel Subscription </a>
                </p>
            </div>
        </v-sheet>
    </div>
  </template>
  <script>
  import axios from 'axios';
  export default {
    data() {
        return {
          
          user :{
            name: '',
            nrc: '',
            email: '',
            packageType : '',
          },

          packages : {
            packageNames :
            ['Free Trial', 'Normal', 'Premium']
          }
        };
    },
    methods: {
        buyPackages() {
          axios.post("http://localhost:8083/buy",this.user)
     .then(response => console.log(response))
            //
        },
    },
  };
  </script>
  