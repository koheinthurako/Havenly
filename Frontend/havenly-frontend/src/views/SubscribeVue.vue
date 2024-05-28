<template>
  <div class="d-flex align-center justify-center" style="height: 130vh">
    <v-sheet width="400" class="mx-auto">
      <h4 class="flex" style="height: 80px">Subscription Form</h4>
    <v-form fast-fail @submit.prevent="submitForm">
      <v-select
        v-model="selectedNRCCode"
        :items="nrcCodes"
        label="Select NRC Code"
        id="nrc_code"
        @update:model-value="updatePlaces"
        underlined
        required
      ></v-select>  
      <v-select
        v-model="selectedPlace"
        :items="places"
        label="Select Place"
        id="name_en"
        underlined
        required
      ></v-select>
      <v-select
        v-model="selectedNRCType"
        :items="nrcTypes"
        label="Select NRC Type"
        id="selectNRCtype"
        underlined
        required
      ></v-select>
      <v-text-field
        v-model="nrcNumber"
        :rules="[value => value.length<7 || 'NRC no. must be 6 numbers at most!']"
        label="NRC Number"
        id="nrc_number"
        underlined
        required
      ></v-text-field>
      <v-text-field
        v-model="user.email"
        disabled
        label="Email"
        id="email"
        underlined
        required
      ></v-text-field>
    <v-row justify="space-around">
    <v-col cols="auto">
      <div class="text-center">
  <v-btn type="submit" v-bind:rounded="true" block class="m-2 bg-redbrick text-white mt-3" 
  style="height: 40px; width: 164px;">Subscribe</v-btn>
    </div>
    </v-col> </v-row>
    </v-form>
    <!-- Display the data string -->
    <!-- <div >
      <p>User data string: {{ user.dataString }}</p>
      <p>User email: {{ user.email }}</p>
    </div> -->
    
    
     <!-- Cancel // Return to home -->
    <div class="mt-2">
    <br>
              <p class="text-body-2">
                <a href="/home"> Cancel </a>
              </p>
              
<div><v-flex class="grey-text">
       NRC : {{combinedValues}}
       <br>
       logged in as : {{user.email}}
      </v-flex></div>
            </div>
          </v-sheet>
    </div>
  </template>
  
  <script>
    import router from '@/router';
    import axios from 'axios';
    import Swal from 'sweetalert2';
    
    export default {
      data() {
        return {
          selectedNRCCode: null,
          selectedPlace: null,
          selectedNRCType: null,
          nrcNumber: '',
          userIsSubbed: '',
          
          nrcData: [],
          
          user: {
            nrc: '',
            email: '',
            packageType: 'Free Trial'
          },
      
        };
      },

      computed: {
      nrcCodes() {
        //return this.nrcData.map(item => item.nrc_code);
        return ['1','2','3','4','5','6','7','8', '9','10','11','12','13','14'];
      },
      nrcTypes() {
        return ['(N)', '(Other)'];
      },
      places() {
        return this.nrcData.filter(item => item.nrc_code==this.selectedNRCCode).map(item=>item.name_en);
      },
      combinedValues() {
        const nrcCode = this.selectedNRCCode || '';
        const nameEn = this.selectedPlace || '';
        const nrcType = this.selectedNRCType || '';
        const nrcNum = this.nrcNumber || '';
        return `${nrcCode}/${nameEn}${nrcType}${nrcNum}`;
      },

    },
    watch: {
      // Watch for changes in selected Values and textField Values
      selectedNRCCode() {
        this.updateCombinedValues();
      },
      selectedPlace() {
        this.updateCombinedValues();
      },
      selectedNRCType() {
        this.updateCombinedValues();
      },
      nrcNumber() {
        this.updateCombinedValues();
      },
    },

    created() {

      if (sessionStorage.getItem('login_user')) {    
        const loginUser = JSON.parse(sessionStorage.getItem('login_user'));
        const email = loginUser.email;
        this.user.email = email;
        console.log('User is logged in.');
      }
    
    },

    methods: {
      
      updatePlaces(){
        console.log("selected nrc code");
        console.log(this.selectedNRCCode);
        this.selectedPlace = '';
      },
      updateCombinedValues() {
        const nrcCode = this.selectedNRCCode || '';
        const nameEn = this.selectedPlace || '';
        const nrcType = this.selectedNRCType || '';
        const nrcNum = this.nrcNumber || '';
        this.user.nrc = `${nrcCode}/${nameEn}${nrcType}${nrcNum}`;
      },
        submitForm() {
          
          function httpErrorHandler(error) {
                          if (axios.isAxiosError(error)) {
                              const response = error?.response
                              if(response){
                                  const statusCode = response?.status
                                  if(statusCode===500){console.log("error")}
                                  if(statusCode===422){console.log("nrc data is empty")}
                                  if(statusCode===404){
                                  alert("Register or login first to subscribe!");
                                  router.push('/register');
                                }
                                  }
                              }
                      }
        if (
          !this.selectedNRCCode ||
          !this.selectedPlace ||
          !this.selectedNRCType ||
          this.nrcNumber.trim() === ""
        ) {
          Swal.fire({
            title: 'Missing Informations',
            text: 'Please fill in all required fields!',
            icon: 'info',
            customClass: {
                confirmButton: 'myCustomButton'
            },
            buttonsStyling: false,
            allowOutsideClick: false,
            allowEscapeKey: false
            });
          return;
        }else{
          try {
            axios.post("http://localhost:8083/subscribe",this.user).then(response => console.log(response));
            this.userIsSubbed = true;
            console.log(this.userIsSubbed);
            Swal.fire({
              title: 'Subscribed Success!',
              text: 'Thank you for subscription. Enjoy your free trial!',
              icon: 'success',
              customClass: {
                  confirmButton: 'myCustomSuccessButton'
              },
              buttonsStyling: false,
              allowOutsideClick: false,
              allowEscapeKey: false
              }).then(() => {
                router.push('/'); 
              });
          }catch {
            httpErrorHandler();
          }
        }
          this.resetForm();
        },

        // fetchSubUser() {
        //   const user = JSON.parse(sessionStorage.getItem('login_user'));
        //   const registerId = user.register_id;
        //   console.log("registerId to send backend to show subUser informations : " + registerId)
        //   axios.get('http://localhost:8083/subscribe/getSubUserInfo', {
        //       params: {
        //           registerId: registerId
        //       }
        //   })
        //   .then(response => {
        //     sessionStorage.setItem('sub_user',JSON.stringify(response.data))
        //   })
        //   .catch(error => {
        //     console.error('Error fetching data:', error); // Handle the error
        //   });    
        // },

      resetForm() {
        this.selectedNRCCode = null;
        this.selectedPlace = null;
        this.selectedNRCType = null;
        this.nrcNumber = '';
        this.user.email = '';
      },

        fetchData() {
          function  httpErrorHandler(error) {
          if (axios.isAxiosError(error)) {
          const response = error?.response
          if(response){
            const statusCode = response?.status
              if(statusCode===500){console.log("error")}
              if(statusCode===404){
              alert("Error loading page.");
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
    }
  </script>

<style>
  .grey-text {
  color: #999; 
}
</style>
  