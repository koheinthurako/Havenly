<template>
    <div class="d-flex align-center justify-center" style="height: 130vh">
      <v-sheet width="400" class="mx-auto">
        <h4 class="flex" style="height: 80px">Subscription Form</h4>
      <v-form fast-fail @submit.prevent="submitForm">
        <v-select
          v-model="selectedNRCCode"
          :rules="[value => !!value || 'Required']"
          :items="nrcCodes"
          label="Select NRC Code"
          id="nrc_code"
          underlined
          required
        ></v-select>  
        <v-select
          v-model="selectedPlace"
          :rules="[value => !!value || 'Required']"
          :items="places"
          label="Select Place"
          id="name_en"
          underlined
          required
        ></v-select>
        <v-select
          v-model="selectedNRCType"
          :rules="[value => !!value || 'Required']"
          :items="nrcTypes"
          label="Select NRC Type"
          id="selectNRCtype"
          underlined
          required
        ></v-select>
        <v-text-field
          v-model="nrcNumber"
          :rules="[value => !!value || 'Required']"
          label="NRC Number"
          id="nrc_number"
          underlined
          required
        ></v-text-field>
        <v-text-field
          v-model="user.email"
          :rules="[value => !!value || 'Required']"
          label="Email"
          id="email"
          underlined
          required
        ></v-text-field>
      </v-form>
      <!-- Display the data string -->
      <!-- <div >
        <p>User data string: {{ user.dataString }}</p>
        <p>User email: {{ user.email }}</p>
      </div> -->
      <v-row justify="space-around">
      <v-col cols="auto">
        <div class="text-center">
        <v-btn type="submit" v-bind:rounded="true" block class="m-2 bg-redbrick text-white mt-3" style="height: 40px; width: 164px;">Subscribe</v-btn>
      </div>
      </v-col> </v-row>
      
       <!-- Cancel // Return to home -->
      <div class="mt-2">
      <br>
                <p class="text-body-2">
                  <a href="/home"> Cancel </a>
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
        selectedNRCCode: '',
        selectedPlace: null,
        selectedNRCType: '',
        nrcNumber: '',
        email: '',
        nrcData: [],
        
        user: {
          nrc: '',
          email: ''
        },
      };
    },
    computed: {
    nrcCodes() {
      //return this.nrcData.map(item => item.nrc_code);
      return ['1/','2/','3/','4/','5/','6/','7/','8/','10/','11/','12/','13/','14/'];
    },
    nrcTypes() {
      return ['(N)', '(Other)'];
    },
    places() {
      return this.nrcData.map(item => item.name_en);
    }
  },
    methods: {
    
      submitForm() {
        // Generate the data string and save it along with email
        const selectedPlaceObj = this.data.find(item => item.name_en === this.selectedPlace);
      this.user.nrc = `${this.selectedNRCCode}${selectedPlaceObj.name_en}${this.selectedNRCType}${this.nrcNumber}`;
      this.user.email = this.email;
        // Reset form after submission
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
            .catch(httpErrorHandler);
  
        this.resetForm();
      },
      resetForm() {
        this.selectedNRCCode = '';
        this.selectedPlace = null;
        this.selectedNRCType = '';
        this.nrcNumber = '';
        this.email = '';
      },

      fetchData() {
        function  httpErrorHandler(error) {
        if (axios.isAxiosError(error)) {
        const response = error?.response
        if(response){
          const statusCode = response?.status
            if(statusCode===500){console.log("error")}
            if(statusCode===404){
            alert("No data found!");
                }
                console.log("error"); 
                  }
      }
    }
        axios.get('http://localhost:8083/nrc/getall') 
        .then(response => {
          this.nrcData = response.data; // Store fetched NRC data
        })
        .catch(httpErrorHandler);
      }
    },
    mounted() {
      this.fetchData();
    }
  };
  </script>