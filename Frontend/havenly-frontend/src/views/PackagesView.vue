<template>
    <div class="d-flex align-center justify-center" style="height: 100vh">
      
        <v-sheet width="400" class="mx-auto">
          <h4 class="flex" style="height: 80px">Buy Packages</h4>
            <v-form fast-fail @submit.prevent="buyPackages">

              <v-text-field variant="underlined" v-model="user.email" :rules="[value => !!value || 'Required']" label="Email" required></v-text-field>                
              <v-autocomplete variant="underlined" v-model="user.packageType" :rules="[value => !!value || 'Required']" label="Packages" required
                :auto-select-first="packages.autoSelectFirst"
                :items="packages.packageNames" 
                :value="user.packageType"
                @change="onChange"></v-autocomplete>
                <v-row justify="space-around">
      <v-col cols="auto">
        <div class="text-center">
                <v-btn type="submit" block class="m-2 bg-redbrick text-white mt-3" v-bind:rounded="true" style="height: 40px; width: 164px;">Buy</v-btn>
      </div> </v-col>
    </v-row>
            </v-form>
            <div class="mt-2">
              <br>
                <p class="text-body-2">
                  selected package : {{ user.packageType }}
                </p>
                <p class="text-body-2">
                  <a href="/packages"> Go Back </a>
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
  