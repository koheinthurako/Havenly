<template>
   <div class="d-flex align-center justify-center" style="height: 130vh">
  <v-sheet width="400" class="mx-auto">
    <h4 class="flex" style="height: 80px">Payment for purchasing Package</h4>
    <div><v-flex class="grey-text">
        <p>Chosen Package : {{user.packageType}}</p>  
        <p>Price : {{user.amount}}</p> 
      </v-flex></div> 
    <v-form fast-fail @submit.prevent="submitForm">
      <v-text-field v-model="cardNumber" :rules="[value => value.length>15 || 'Card number must be at least 16 numbers!']" label="Card Number (#### #### #### ####)" required></v-text-field>
      <v-text-field v-model="cardHolder" label="Card Holder Name" :rules="[value => value.length>0 || 'Name must not be empty!']" required></v-text-field>
      <v-text-field v-model="expirationDate" label="Expiration Date (MM/YY)" :rules="[value => !!value|| 'Date must not be empty!']" required></v-text-field>
      <v-text-field v-model="cvv" label="CVV (###)" :rules="[value => value.length>2 || 'Wrong CVV format!']" required></v-text-field>
      <v-row justify="space-around">
      <v-col cols="auto">
        <div class="d-flex justify-center mt-2">
        <div class="text-center mr-2"> 
                <v-btn type="submit" block class="m-2 bg-redbrick text-white mt-3" 
                v-bind:rounded="true" style="height: 40px; width: 164px;">
                Submit Payment</v-btn>
      </div>
      <!-- <div class="text-center ml-2">
                <v-btn to="/package" v-bind:rounded="true" block class="m-2 bg-grey text-white mt-3" 
                style="height: 40px; width: 164px;">Cancel</v-btn>
      </div> -->
    </div>
    </v-col>
    </v-row>    
  </v-form>
    <p><a href="/package"> Go Back </a></p> 
  </v-sheet>
  </div>
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
    .grey-text {
    color: #999; 
    vertical-align: auto;
  }
  </style>