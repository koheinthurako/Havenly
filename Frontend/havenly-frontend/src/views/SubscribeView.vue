<template>
    <div class="d-flex align-center justify-center" style="height: 100vh">
        <v-sheet width="400" class="mx-auto">
          <h4 class="flex" style="height: 80px">Subscription Form</h4>
            <v-form fast-fail @submit.prevent="subscription">
                <v-text-field variant="underlined" v-model="user.nrc" :rules="[value => !!value || 'Required']" label="NRC" required></v-text-field>
                <v-text-field variant="underlined" v-model="user.email" :rules="[value => !!value || 'Required']" label="Email" @click="autofill" required></v-text-field>
            <v-row justify="space-around">
      <v-col cols="auto">
        <div class="text-center">
         
            <v-btn type="submit" v-bind:rounded="true" block class="m-2 bg-redbrick text-white mt-3" style="height: 40px; width: 164px;">Subscribe</v-btn></div>
      </v-col> </v-row>
                
            </v-form>
            <div class="mt-2">
      <br>
                <p class="text-body-2">
                  <a href="/home"> Cancel </a>
                </p>
            </div>
            <div><v-flex class="grey-text">
       logged in as : {{login.userMail}}
      </v-flex></div>
        </v-sheet>
    </div>
  </template>

  <script>
  import router from '@/router';
  import axios from 'axios';
  
  export default {

    data() {
        return {
          
          login: {
            userIsLoggedIn: false,
            userMail: '',
          },

          user :{
            nrc: '',
            email: '',
          },
        };
    },

    created() {
    // Fetch session data from sessionStorage
    const userEmail = JSON.parse(sessionStorage.getItem('users'));
    if (userEmail) {
      this.login.userMail = userEmail;
      this.login.userIsLoggedIn = true;
      console.log('User is logged in.');
    } else {
      alert("Log in first to subscribe!");
        router.push('/login');
      console.error('User email not found in sessionStorage.');
    }
  },
    methods: {
      autofill() {
      // Simulate autofill by setting the value of the text field
      this.user.email = this.login.userMail;
    },
        subscription() {
          function httpErrorHandler(error) {
                        if (axios.isAxiosError(error)) {
                            const response = error?.response
                            if(response){
                                const statusCode = response?.status
                                if(statusCode===500){console.log("error")}
                                if(statusCode===400){alert("You are already subscribed!")}
                                if(statusCode===204){alert("Wrong Email format.")}
                                if(statusCode===404){
                                alert("Register first to subscribe!");
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
                  const userIsSubbed = true;
                  sessionStorage.setItem('subuser', userIsSubbed);
                 } 
                 router.push('/home');
            })
        .catch(httpErrorHandler)
            },
    },
  };
  </script>
  <style>
  .grey-text {
  color: #999; 
}
</style>
  