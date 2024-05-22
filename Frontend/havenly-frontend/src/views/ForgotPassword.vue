<template>
    <div style=" height: 60px; color: white; background-color: #e86f52;"><h1><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Havenly </em></h1></div>
  
  <div class="d-flex align-center justify-center mt-5 pt-5" style="height: 70vh;">
      <v-sheet width="400" class="mx-auto">
          <v-form >
              
              <v-text-field v-model="user.email" label="Email" :rules="[value => !!value || 'Required']" ></v-text-field>
  
             
             
  
              
  
              <v-btn @click="confirm(user.email)" type="submit" block class="mt-2 bg-redbrick text-white mt-3">Confirm</v-btn>
  
          </v-form>
          
      </v-sheet>
  </div>
  </template>
  <script>
  
  
  import axios from 'axios';
  
  
  export default {
  data() {
    return {
      user :{
         
        email: ''
      
       
      },
    
    };
  },
  
  
  
  methods: {
  
  
      confirm(email) {
         
          
         
        axios.put(`http://localhost:8083/forgotpassword/${email}`)
        
        .then(alert(" We've sent a link to update your password to your email...Please check your E-mail!!!"))
         
        
               
          
         
               },
  },
  }
  </script>