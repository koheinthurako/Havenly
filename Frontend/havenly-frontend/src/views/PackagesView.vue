<template>
    <div class="d-flex align-center justify-center" style="height: 100vh">
        <v-sheet width="400" class="mx-auto">
            <v-form fast-fail @submit.prevent="buyPackages">

              <v-text-field variant="underlined" v-model="user.email" :rules="[value => !!value || 'Required']" label="Email" required></v-text-field>                
              <v-autocomplete variant="underlined" v-model="user.packageType" :rules="[value => !!value || 'Required']" label="Packages" required
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
                  <a href="/cancel"> Cancel </a>
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
          function httpErrorHandler(error) {
                        if (axios.isAxiosError(error)) {
                            const response = error?.response
                            if(response){
                                const statusCode = response?.status
                                if(statusCode===500){console.log("error")}
                                alert("Error purchasing package.")
                                if(statusCode===404){
                                alert("Please Register or Subscribe first to buy packages!");
                                router.push('/subscribe');
                              }
                                }
                            }
                    }
                    // const email = this.user.email;
                    // const packType = this.user.packType;
          axios.post("http://localhost:8083/packages/purchase",this.user)
     .then(function(response){
                const status=JSON.parse(response.status);
                if(status===200){
                  alert("Package bought successfully!");
                 } 
                 router.push('/home');
            })
            .catch(httpErrorHandler)
            //
        },
    },
  };
  </script>
  