<template>

  <section id="login" class="container my-5" style="height: 82vh; display: flex; justify-content: center; align-items: center;">
    <div class="row d-flex justify-content-center align-items-center flex-column flex-md-row w-100">
      <div class="col-lg-7 col-12">
        <div class="coverImgBox" style="height: 500px;">
          <div class="contentBox d-flex justify-content-center align-items-center flex-column h-100">
            <p class="m-0 p-0 fs-4 text-white">Welcome to</p>
            <h1 class="text-white">Havenly</h1>
          </div>
        </div>
      </div>
      <div class="col-lg-5 col-12">
          <div class="loginForm">
            <v-form fast-fail @submit.prevent="login">
              <h2 class="my-4 text-center"><span style="color: #e86f52;">Login</span> Your Account</h2>
              <v-text-field v-model="user.email" label="Email" :rules="[value => !!value || 'Required']"></v-text-field>

              <v-text-field v-model="user.password" :type="showPassword ? 'text' : 'Password'"
                :append-inner-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'" @click:append-inner=togglePasswordVisibility()
                label="password" :rules="[value => !!value || 'Required']">

              </v-text-field>


              <div class="mt-2 mb-2 d-flex justify-content-between flex-wrap align-items-center">
                <p class="text-body-2 m-0">Don't have an account? <a href="/register">Register Now</a></p>
                <a href="/forgot" class="text-body-2 font-weight-regular">Forgot Password?</a>
              </div>
                <v-row justify="space-around">
              <v-col cols="auto">

              <div class="text-center mt-4">
                    <button type="submit" class="btn text-white px-5 w-fit-content rounded-pill mt-3" style="background-color: #e86f52 !important;">Login</button>
              </div>


              </v-col> </v-row>

            </v-form>
            
          </div>
        </div>
    </div>
  </section>


  <!-- <div class="d-flex align-center justify-center mt-5 pt-5" style="height: 70vh;">
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
        <p class="text-body-2">Don't have an account? <a href="/register">Sign Up</a></p>
      </div>
    </v-sheet>
  </div> -->
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
          console.log(status);
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

<style>

  .coverImgBox {
    background-image: url('../assets/img/loginCover.jpg');
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    border-radius: 15px;
  }

  .coverImgBox .contentBox::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    border-radius: 15px;
    background: linear-gradient(to top, rgba(0, 0, 0, 0.7), rgba(255, 137, 108, 0.7));
    z-index: 1;
  }

  .coverImgBox .contentBox {
    position: relative;
    z-index: 2;
    background: none; /* Remove any background from contentBox */
    /* background: linear-gradient(0deg, #e86f52 20%, transparent) */
  }

  .coverImgBox .contentBox p,
  .coverImgBox .contentBox h1 {
    position: relative;
    z-index: 3;
    user-select: none;
  }

  #login .loginForm button {
    transition: 0.3s;
  }

  #login .loginForm button:hover {
    transform: translateY(-5px);
  }

  /* Responsive For Login Page */
  @media screen and (max-width: 990px) {
    #login {
      height: 140vh !important;
    }
  }

  @media screen and (max-width: 450px) {
    #login {
      height: 100vh !important;
    }

    #login .coverImgBox {
      height: 340px !important;
    }

  }

</style>