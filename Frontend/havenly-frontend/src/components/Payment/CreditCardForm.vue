<template>
   <div class="d-flex align-center justify-center" style="height: 130vh">
  <v-sheet width="400" class="mx-auto">
    <h4 class="flex" style="height: 80px">Payment for purchasing Package</h4>
    <div><v-flex class="grey-text">
        <p>Chosen Package : {{user.packageName}}</p>  
        <p>Price : {{user.amount}}</p> 
      </v-flex></div> 
    <v-form fast-fail @submit.prevent="submitForm">
      <v-text-field v-model="cardNumber" :rules="[value => value.length>15 || 'Card number must be at least 16 numbers!']" label="Card Number (#### #### #### ####)" required></v-text-field>
      <v-text-field v-model="cardHolder" label="Card Holder Name" :rules="[value => value.length>0 || 'Name must not be empty!']" required></v-text-field>
      <v-text-field v-model="expirationDate" label="Expiration Date (MM/YY)" :rules="[value => !!value|| 'Date must not be empty!']" required></v-text-field>
      <v-text-field v-model="cvv" label="CVV (###)" :rules="[value => value.length>2 || 'Wrong CVV format!']" required></v-text-field>
      <v-row justify="space-around">
      <v-col cols="auto">
        <div class="text-center"> 
                <v-btn type="submit" block class="m-2 bg-redbrick text-white mt-3" 
                v-bind:rounded="true" style="height: 40px; width: 164px;">
                Submit Payment</v-btn>
      </div></v-col>
    </v-row>    </v-form>
    <!-- <v-alert v-if="message" :type="messageType">{{ message }}</v-alert> -->
    <p><a href="/packages"> Go Back </a></p> 
  </v-sheet>
  </div>
</template>

<script>
import axios from 'axios';
import router from '@/router';

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
            packageName : '',
            amount: '',
          },
      login :{
        alreadyPurchased: '',
          },
    }
  },
  created() {
    // Fetch session data from sessionStorage
    const packageData = JSON.parse(sessionStorage.getItem('packageData'));
    if(packageData!==null){
      this.user.packageType=packageData.name;
      this.user.amount=packageData.price;
      console.log("package name : ", packageData.name);
      console.log("amount: ", packageData.price)
    }else{ 
      console.log("no package data in session storage!");
    }
    const loginUserData = JSON.parse(sessionStorage.getItem('login_user'));
    if (loginUserData !==null ) { 
      this.user.email = loginUserData.email;
      console.log('User is logged in.');
      if(loginUserData.nrc !== null){
        console.log("User is subbed."); 
        this.login.alreadyPurchased = loginUserData.packageName;
        console.log("package : ", this.login.alreadyPurchased);
       }
    // else if(subbedData !== null ){
    //   console.log("User is subbed.");   
    // this.login.alreadyPurchased = subbedData.packageName;
    //   console.log("package : ", this.login.alreadyPurchased);
    // }
    else{
      console.log("user is not subbed!");
      alert("You must be subscribed to buy our packages!");
      router.push('/subscribe');
  }
    } else {
      alert("Log in or register first to buy packages!");
      console.error('User email not found in sessionStorage.');
      router.push('/loginakm');
      
    }
   
  },
  methods: {
    submitForm() {
      // Check if all fields are filled
      if (!this.cardNumber || !this.cardHolder || !this.expirationDate || !this.cvv) {
        alert("Please fill in all required fields.");
        console.log("required fields missing.");
        return;
      }
       // Check if expiration date is in MM/YY format
       const expirationRegex = /^(0[1-9]|1[0-2])\/\d{2}$/;
      if (!expirationRegex.test(this.expirationDate)) {
        alert("Wrong Format");
        console.log("wrong date format");
        return;
      }
    // Check if the input date is in the past
    // Split the input into month and year
    let [inputMonth, inputYear] = this.expirationDate.split('/');
    // Get the current month and year
    let currentYear = new Date().getFullYear().toString().substr(-2);
    let currentMonth = ('0' + (new Date().getMonth() + 1)).slice(-2);
    if (inputYear < currentYear || (inputYear == currentYear && inputMonth < currentMonth)) {
        alert("Your card is expired");
        console.log("card is expired");
        return;
    }
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
                  axios.post("http://localhost:8083/packages/payment",this.user)
     .then(function(response){
                const status=JSON.parse(response.status);
                if(status===200){
                  alert("Payment Success! Thank you for buying our package!");
                  sessionStorage.removeItem('packageName');
                 } 
                 sessionStorage.setItem('packageUser',JSON.stringify(response.data))
                 router.push('/home');

                let userData = JSON.parse(sessionStorage.getItem('login_user')) || {};
                userData.packageName = this.user.packageName;
                sessionStorage.setItem('login_user', JSON.stringify(userData));
            })
            .catch(httpErrorHandler)

    }
  }
}
</script>

<style>
    .grey-text {
    color: #999; 
    vertical-align: auto;
  }
  </style>