<template>
  <div class="col-10 messageBox" v-if="successSend">
      <h6>We have successfully send update password link to your email address. Please check your email!</h6>
  </div>
  <div class="d-flex align-center justify-center flex-column mt-5 pt-5" style="height: 70vh;">
    <v-sheet width="400" class="mx-auto">
        <v-form @submit.prevent="submitForm">
          <v-text-field required v-model="user.email" label="Email" :rules="[value => !!value || 'Required']" ></v-text-field>
          <v-btn type="submit" block class="mt-2 bg-redbrick text-white mt-3">Confirm</v-btn>
        </v-form>
    </v-sheet>
  </div>
  </template>
  <script>
  import axios from 'axios';
  import Swal from 'sweetalert2';
  
  
  export default {
  data() {
    return {
      user :{
        email: ''
      },
      loading: false,
      successSend: false,
    };
  },
  
  
  
  methods: {
  
      async submitForm() {
        Swal.fire({
          title: 'Loading...',
          text: 'We are sending update password link to your email. Please wait...',
          allowOutsideClick: false,
          allowEscapeKey: false,
          didOpen: () => {
            Swal.showLoading();
          }
        });
        try {
          const response = await axios.put(`http://localhost:8083/forgotpassword/${this.user.email}`)
          Swal.hideLoading();
          if(response.status === 200) {
            Swal.fire({
              title: 'Sending Successfully',
              text: 'We have send password update link to your email. Please check your email.',
              icon: 'success',
              customClass: {
                confirmButton: 'myCustomSuccessButton'
              },
            });
            this.successSend = true;
          }
        } catch {
          Swal.fire({
            title: 'Send Failed!',
            text: 'Please enter a valid email address.',
            icon: 'error',
            customClass: {
              confirmButton: 'myCustomButton'
            },
            buttonsStyling: false,
            allowOutsideClick: false,
            allowEscapeKey: false
          });
        }      
        

      },
  },
  }
  </script>

  <style>

    .messageBox {
      position: relative;
      top: 150px;
      margin: 0 auto;
      text-align: center;
    }

  </style>