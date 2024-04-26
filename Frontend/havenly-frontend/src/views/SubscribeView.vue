<template>
    <div class="d-flex align-center justify-center" style="height: 100vh">
        <v-sheet width="400" class="mx-auto">
            <v-form fast-fail @submit.prevent="subscription">
                
                <v-text-field variant="underlined" v-model="user.nrc" :rules="[value => !!value || 'Required']" label="NRC" required></v-text-field>
                <v-text-field variant="underlined" v-model="user.email" :rules="[value => !!value || 'Required']" label="Email" required></v-text-field>
                
                <v-btn type="submit" color="#e86f52"  block class="mt-2">Subscribe</v-btn>
            </v-form>
            <div class="mt-2">
      
                <p class="text-body-2">
                  <a href="/register"> Register </a>
                </p>
            </div>
        </v-sheet>
    </div>
  </template>
  <script>
  import router from '@/router';
  import axios from 'axios';
  export default {
    data() {
        return {
          
          user :{
            nrc: '',
            email: '',
          },

        };
    },
    methods: {
        subscription() {
          function httpErrorHandler(error) {
                        if (axios.isAxiosError(error)) {
                            const response = error?.response
                            if(response){
                                const statusCode = response?.status
                                if(statusCode===500){console.log("error")}
                                if(statusCode===400){alert("You are already subscribed!")}
                                if(statusCode===404){
                                alert("Register or login first to subscribe!");
                                router.push('/register');
                              }
                                }
                            }
                    }
          axios.post("http://localhost:8083/subscribe",this.user)
     .then(function(response){
                const status=JSON.parse(response.status);
                if(status===200){
                  alert("Subscribed Successfully!");
                 } 
                 router.push('/home');
            })
            .catch(httpErrorHandler)
        },
    },
  };
  </script>
  