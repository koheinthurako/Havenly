<template>
  <div style=" height: 60px; color: white; background-color: #e86f52;"><h1><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Havenly (Admin)</em></h1></div>
    <div class="d-flex align-center justify-center mt-5 pt-5" style="height: 70vh;">
        <v-sheet width="400" class="mx-auto">
            <v-form fast-fail @submit.prevent="login">
                
                <v-text-field v-model="user.email" label="User Name" :rules="[value => !!value || 'Required']" ></v-text-field>

                <v-text-field
                 v-model="user.password"  :type="showPassword ? 'text' : 'password'"
                :append-inner-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'"
                @click:append-inner=togglePasswordVisibility()  label="password" 
                :rules="[value => !!value || 'Required']" >

                </v-text-field>
               

                <a href="/forgotpassword" class="text-body-2 font-weight-regular">Forgot Password?</a>

                <v-btn type="submit" block class="mt-2 bg-redbrick text-white mt-3">Sign in</v-btn>

            </v-form>
           
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
        user :{
          email: '',
          password: ''
        },
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
          if(response){
            const statusCode = response?.status
            if(statusCode===400){
              Swal.fire({
                title: 'Invalid Informations!',
                text: 'Invalid Username or Password.Please try again!',
                icon: 'error',
                customClass: {
                  confirmButton: 'myCustomButton'
                },
                buttonsStyling: false,
                allowOutsideClick: false,
                allowEscapeKey: false
              });
            }
          }
        }
      }
      axios.post("http://localhost:8083/admin/login",this.user)
        
        .then(function(response){
          sessionStorage.setItem('admin_user',JSON.stringify(response.data))
                const status=JSON.parse(response.status);
                if(status=='200'){
                    router.push('/admin/post');
                }
                
                    })
        .catch(httpErrorHandler)
    },
  },
}
</script>