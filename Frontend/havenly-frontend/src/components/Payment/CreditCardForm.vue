<template>
  <main id="payment">
    <section class="subMainBox container mt-5 mt-lg-4">
      <div class="row subBox rounded-2 flex-md-row flex-column-reverse p-4 p-lg-5">
        <div class="col-md-7 col-lg-6 col-12 contentBox">
          <form class="mx-auto">
            <div class="d-flex justify-content-between mb-2 mt-4 my-md-3 priceBox">
              <h5>Chosen Package : <span style="color: #E97559;">{{user.packageType}}</span></h5>  
              <h5>Price : <span style="color: #E97559;">$ {{user.amount}}</span></h5>
            </div>
            <v-form ref="form" fast-fail @submit.prevent="submitForm">
              <v-select required bg-color="#EDEDED" class="w-100" clear-icon="mdi-close-circle" clearable
                variant="solo" rounded="sm" elevation="0"
                v-model="selectedPaymentTypes" outlined="false"
                :items="paymentTypes" placeholder="Select Payment" persistent-placeholder>
              </v-select>
              <v-text-field
                v-model="cardNumber"
                type="number"
                :disabled="!selectedPaymentTypes"
                :rules="[v => !!v || 'Card number is required', v => !/^\s*$/.test(v) || 'Card number cannot be just spaces', v => v.length===16 || 'Card numbers must be 16 digits!']"
                label="Card Number (#### #### #### ####)" required>
              </v-text-field>
              <div class="d-flex gap-3 flex-md-row flex-column">
                <v-text-field class="col-12 col-md-6"
                  v-model="cardHolder"
                  label="Card Holder Name"
                  :disabled="!cardNumber || !selectedPaymentTypes"
                  :rules="[v => !!v || 'Card holder name is required', v => !/^\s*$/.test(v) || 'Card holder name cannot be just spaces']" required>
                </v-text-field>
                <v-text-field class="col-12 col-md-6"
                  v-model="expirationDate"
                  label="Expiration Date (MM/YY)"
                  :disabled="!cardHolder || !cardNumber || !selectedPaymentTypes"
                  :rules="[value => !!value|| 'Date must not be empty!']" required>
                </v-text-field>
              </div>
              <v-text-field
                v-model="cvv"
                type="number"
                label="CVV (###)"
                :disabled="!expirationDate || !cardHolder || !cardNumber || !selectedPaymentTypes"
                :rules="[value => value.length>2 || 'Wrong CVV format!']" required>
              </v-text-field>
              <v-row class="justify-content-center justify-content-md-start">
                <v-col cols="auto">
                  <div class="d-flex mt-2">
                    <button type="submit" class="btn rounded-pill m-2 px-4 bg-brick text-white mt-3">
                      Submit Payment
                    </button>
                </div>
                </v-col>
              </v-row>  
            </v-form>
          </form>
        </div>
        <div class="col-md-5 col-lg-6 col-12 imgBox">
          <img class="w-100 rounded-3" :src="img" alt="">
          <div class="visaContent">
            <div class="d-flex flex-column">
              <v-text v-model="cardHolder" class="text-right text-white fs-3 fw-bold">{{ cardHolder }}</v-text>
              <v-text v-model="cardNumber" class="text-right text-white fs-4">{{ cardNumber }}</v-text>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
  import axios from 'axios';
  import router from '@/router';
  import Swal from 'sweetalert2';

  export default {
    data() {
      return {
        cardNumber: '',
        cardHolder: '',
        expirationDate: '',
        cvv: '',
        valid: false,
        message: '',
        messageType: '',

        user :{
              email: '',
              packageType : '',
              amount: '',
            },
        login :{
          alreadyPurchased: '',
            },
        autoInput: '',
        paymentTypes: ['Visa'],
        selectedPaymentTypes: null,
        img: require('@/assets/img/creditcard.png'),
      }
    },

    mounted() {
      localStorage.removeItem('openTab');
    },

    created() {
      // Fetch session data from sessionStorage
      const packageData = JSON.parse(sessionStorage.getItem('packageData'));

      if(packageData!== null){
        this.user.packageType=packageData.packName;
        this.user.amount=packageData.price;
        console.log("package name : ", packageData.packName);
        console.log("amount: ", packageData.price)
      }else{ 
        console.log("no package data in session storage!");
      }

        const loginUser = JSON.parse(sessionStorage.getItem('login_user'));
        const email = loginUser.email;
        const subUser = JSON.parse(sessionStorage.getItem('sub_user'));
        this.user.email = email;
        console.log('User is logged in.');
        if(subUser && subUser.packageType == "Free Trial") {
          console.log("User is subbed."); 
          this.login.alreadyPurchased= subUser.packageType;
          console.log("packagetype : ", this.login.alreadyPurchased);
        } 
      
    },

    methods: {
      submitForm() {
        // Check if all fields are filled
        if (!this.cardNumber || !this.cardHolder || !this.expirationDate || !this.cvv) {
          Swal.fire({
            title: 'Missing Informations!',
            text: 'Please fill in all required fields.',
            icon: 'error',
            customClass: {
                confirmButton: 'myCustomButton'
            },
            buttonsStyling: false,
            allowOutsideClick: false,
            allowEscapeKey: false
          })
          return;
        }
        // Check if expiration date is in MM/YY format
        const expirationRegex = /^(0[1-9]|1[0-2])\/\d{2}$/;
        if (!expirationRegex.test(this.expirationDate)) {
          Swal.fire({
            title: 'Wrong Format!',
            text: 'Please correct the correct date.',
            icon: 'error',
            customClass: {
                confirmButton: 'myCustomButton'
            },
            buttonsStyling: false,
            allowOutsideClick: false,
            allowEscapeKey: false
          });
          return;
        }
      // Check if the input date is in the past
      // Split the input into month and year
      let [inputMonth, inputYear] = this.expirationDate.split('/');
      // Get the current month and year
      let currentYear = new Date().getFullYear().toString().substr(-2);
      let currentMonth = ('0' + (new Date().getMonth() + 1)).slice(-2);
      if (inputYear < currentYear || (inputYear == currentYear && inputMonth < currentMonth)) {
        Swal.fire({
            title: 'Card Expired!',
            text: 'Your card is expired! Try another card.',
            icon: 'error',
            customClass: {
                confirmButton: 'myCustomButton'
            },
            buttonsStyling: false,
            allowOutsideClick: false,
            allowEscapeKey: false
        });
          return;
      }

      console.log(this.user);
        function httpErrorHandler(error) {
          if (axios.isAxiosError(error)) {
              const response = error?.response
              if(response){
                console.log(response);
                  const statusCode = response?.status
                  if(statusCode===500){console.log("error");
                  alert("Error processing payment. Please try again later.");}
                  
                  if(statusCode===400){
                  alert("Error processing data.");
                }
                  }
              }
        }

        axios.post("http://localhost:8083/payment",this.user)
        .then(function(response){
          const status=JSON.parse(response.status);
          if(status=='200'){
            Swal.fire({
              title: 'Payment Success!',
              text: 'Thank you for buying our package.',
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
          } 
            sessionStorage.setItem('packageUser',JSON.stringify(response.data))
        })
        .catch(httpErrorHandler)
      },

    }
  }
</script>

<style>

  #payment {
    position: relative; /* Add this */
    min-height: 100vh;
    overflow: hidden;
  }

  #payment::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-image: url('../../assets/img/paymentBg.jpg');
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    filter: saturate(0%);
    opacity: 0.5;
  }

  #payment .subMainBox {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    padding-bottom: 20px;
  }

  #payment .subMainBox .subBox .imgBox {
    position: relative;
  }

  #payment .subMainBox .subBox .contentBox button {
    transition: 0.3s;
    background-color: #E97559;
  }

  #payment .subMainBox .subBox .contentBox button:hover {
    transform: translateY(-5px);
  }

  #payment .subMainBox .subBox .imgBox .visaContent{
    position: absolute;
    top: 33%;
    right: 16%;
    opacity: 0.7;
  }

  #payment .subMainBox .subBox .imgBox img {
    transform: scale(1.2);
  }

  .grey-text {
    color: #999; 
    vertical-align: auto;
  }

  /* Responsive For Payment Page */
  @media screen and (max-width: 990px) {
    #payment .subMainBox .subBox .imgBox .visaContent {
      top: 19% !important;
    }

    #payment .subMainBox .subBox .imgBox .visaContent v-text:first-child {
      font-size: 16px !important;
    }

    #payment .subMainBox .subBox .imgBox .visaContent v-text:last-child {
      font-size: 12px !important;
      letter-spacing: 1px !important;
    }
  }

  @media screen and (max-width: 450px) {

    #payment .subMainBox .subBox .priceBox h5{
      font-size: 15px !important;
    }

    #payment .subMainBox .subBox .imgBox img {
      transform: scale(1.4);
    }

    #payment .subMainBox .subBox .imgBox .visaContent {
      top: 43% !important;
    }

    #payment .subMainBox .subBox .imgBox .visaContent v-text:last-child {
      letter-spacing: 3px;
    }

  }

  </style>