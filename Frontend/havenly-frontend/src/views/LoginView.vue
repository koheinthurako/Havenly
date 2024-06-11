<template>
  <div style=" height: 60px; color: white; background-color: #e86f52;">
    <h1><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Havenly </em></h1>
  </div>

  <div class="d-flex align-center justify-center mt-5 pt-5" style="height: 70vh;">
    <v-sheet width="400" class="mx-auto">
      <v-form fast-fail @submit.prevent="login">

        <v-text-field v-model="user.email" label="Email" :rules="[value => !!value || 'Required']"></v-text-field>

        <v-text-field v-model="user.password" :type="showPassword ? 'text' : 'Password'"
          :append-inner-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'" @click:append-inner=togglePasswordVisibility()
          label="password" :rules="[value => !!value || 'Required']">

        </v-text-field>


            <a href="/forgot" class="text-body-2 font-weight-regular">Forgot Password?</a>
            <v-row justify="space-around">
      <v-col cols="auto">
        
        <div class="text-center mr-2">
              <v-btn type="submit" block class="mt-2 bg-redbrick text-white mt-3" v-bind:rounded="true" style="height: 40px; width: 164px;">Sign in</v-btn>
      </div>
  

      </v-col> </v-row>

      </v-form>
      <div class="mt-2">
        <p class="text-body-2">Don't have an account? <a href="/registerakm">Sign Up</a></p>
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
      user: {

        email: '',
        password: ''

      },
      sub_user: [],
      showPassword: false
    };
  },

  mounted() {
    localStorage.removeItem('openTab');
  },

  methods: {

    togglePasswordVisibility() {
      this.showPassword = !this.showPassword;
    },

    login() {

      function httpErrorHandler(error) {
        if (axios.isAxiosError(error)) {
          const response = error?.response
          if (response) {
            const statusCode = response?.status
            if (statusCode === 400 || statusCode == 500) {
              Swal.fire({
                title: 'Invaild Information',
                text: 'Wrong email and password. Please try again!',
                icon: 'error',
                customClass: {
                  confirmButton: 'myCustomButton'
                },
                buttonsStyling: false,
                allowOutsideClick: false,
                allowEscapeKey: false
              })
            }
          }
        }
      }

      axios.post("http://localhost:8083/login", this.user)
        .then(function (response) {
          sessionStorage.setItem('login_user', JSON.stringify(response.data))

          const status = response.status
          console.log(status)
          if (status == '200') {
            const user = JSON.parse(sessionStorage.getItem('login_user'));
            const registerId = user.register_id;
            console.log("registerId to send backend to show subUser informations : " + registerId)
            axios.get('http://localhost:8083/subscribe/getSubUserInfo', {
              params: {
                registerId: registerId
              }
            })
              .then(response => {
                sessionStorage.setItem('sub_user', JSON.stringify(response.data))
              })
              .catch(error => {
                console.error('Error fetching data:', error); // Handle the error
              });
            // router.push('/');
            router.push('/').then(() => {
              window.location.href = '/';
            });
          }

          const aa = response.data
          console.log(aa)

        })
        .catch(httpErrorHandler)



    },

  },

}

</script>