<template>

    <main id="subscribe">
      <section class="subMainBox container mt-5 mt-lg-4">
        <div class="row subBox rounded-2 flex-md-row flex-column align-items-center">
          <div class="col-sm-5 col-12 imgBox p-4 p-lg-5">
            <img class="w-100 rounded-3" :src="img" alt="">
          </div>
          <div class="col-sm-7 col-12 contentBox p-4 p-lg-5">
            <form class="mx-auto">
              <p class="brick-text mb-1">Hello, {{name}}!</p>
              <h4 class="flex mb-3">Subscription Form</h4> 
              <v-form ref="form" fast-fail @submit.prevent="submitForm">
                <div class="d-flex gap-3">
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
                  :disabled="!selectedNRCCode"
                  label="Select Place"
                  id="name_en"
                  underlined
                  required
                ></v-select>
                </div>
                <div class="d-flex gap-3">
                  <v-select
                    v-model="selectedNRCType"
                    :items="nrcTypes"
                    :disabled="!selectedPlace"
                    label="Select NRC Type"
                    id="selectNRCtype"
                    underlined
                    required
                  ></v-select>
                  <v-text-field
                    v-model="nrcNumber"
                    :rules="[value => value.length===6 || 'NRC No. must be 6 numbers!']"
                    :disabled="!selectedNRCType"
                    label="NRC Number"
                    id="nrc_number"
                    underlined
                    required
                  ></v-text-field>
                </div>
                <v-text-field
                  v-model="user.email"
                  disabled
                  label="Email"
                  id="email"
                  underlined
                  required
                ></v-text-field>
                <v-row class="justify-content-center justify-content-md-start">
                  <v-col cols="auto">
                    <div class="text-center mr-2">
                      <v-btn type="submit" v-bind:rounded="true" block class="m-2 bg-redbrick text-white mt-3" 
                      style="height: 40px; width: 164px;">Subscribe</v-btn>
                    </div>
                  </v-col>
                </v-row>
              </v-form>
            </form>
          </div>
        </div>
      </section>
    </main>
  </template>
  
  <script>
    // import router from '@/router';
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
          name: '',
          
          nrcData: [],
          
          user: {
            nrc: '',
            email: '',
            packageType: 'Free Trial'
          },

          img: require('@/assets/img/subId2.png'),
      
        };
      },

      computed: {
        nrcCodes() {
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
        const name = loginUser.name;
        this.name = name;
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
                                   if(statusCode===204){console.log("data is empty")}
                                  // if(statusCode===400){console.log("User is subbed.")}
                                  // if(statusCode===404){console.log("User not found.")}
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
                // router.push('/'); 
                window.location.href = '/';
            });
          }catch {
            httpErrorHandler();
          }
        }
          this.resetForm();
        },

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
        localStorage.removeItem('openTab');
        this.fetchData();
      },
      
    }
  </script>

<style>

  /* Subscribe */

  #subscribe::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(to top, rgba(0, 0, 0, 0.9), rgba(255, 137, 108, 0.7));
  }

  #subscribe{
    background-image: url('../assets/img/h2.jpg');
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    min-height: 100vh;
    background-color: red;
    overflow: hidden;
  }

  #subscribe .subMainBox {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    padding-bottom: 20px;
  }

  #subscribe .subMainBox .subBox {
    background: rgba(255, 255, 255, 0.9);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(8px);
    -webkit-backdrop-filter: blur(8px);
    border: 1px solid rgba(255, 255, 255, 0.79);
  }

  .brick-text {
    color: #E97559; 
  }

  /* Responsive For Subscribe Page*/
  @media screen and (max-width: 990px) {
    
  }

  @media screen and (max-width: 450px) {

  }


</style>
  