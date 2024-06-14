<template>
    <div style=" height: 60px; color: white; background-color: #e86f52;"><h1><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Havenly</em></h1></div>

  <div class="d-flex align-center justify-center mt-5 pt-5" style="height: 80vh;">
      <v-sheet width="400" class="mx-auto">
        <h4 class="flex" style="height: 80px">Registeration Form</h4>
          <v-form ref="form" fast-fail @submit.prevent="signup">
              <v-text-field variant="underlined" v-model="user.name"  label="Name"  required ></v-text-field>
              <v-text-field variant="underlined" v-model="user.phone"  :rules="[value => value.length<12 || 'Ph no. must be 11 numbers']" label="Phone" required ></v-text-field>
              <v-text-field variant="underlined"  v-model="user.email"    label="Email" required  :rules="[value => !!value || 'Required']" ></v-text-field>
              <v-text-field variant="underlined" v-model="user.password"  label="password" required  
              :type="showPassword ? 'text' : 'Password'"
              :append-inner-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'"
            @click:append-inner=togglePasswordVisibility()
            :rules="[value => !!value || 'Required']"></v-text-field>
              <v-row justify="space-around">
      <v-col cols="auto">
        <div class="text-center">
              <v-btn type="submit" block class="mt-2 bg-redbrick text-white m-3" v-bind:rounded="true" style="height: 40px; width: 164px;">Sign up</v-btn>
              </div></v-col></v-row>
          </v-form>
          <div class="mt-2">
              <p class="text-body-2">
                  Already have an account? <a href="/loginakm">Sign in</a>
              </p>
          </div>
      </v-sheet>
  </div>
</template>
<script>
  import router from '@/router';
  import axios from 'axios'
  import Swal from 'sweetalert2';

  export default {
  
    data() {
        return {
          user :{
            name: '',
            phone:'',
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

      signup() {
      
        function httpErrorHandler(error) {
          if (axios.isAxiosError(error)) {
            const response = error?.response
            if(response){
              const statusCode = response?.status
              if(statusCode===500){
                Swal.fire({
                  title: 'Invalid Informations!',
                  text: 'Something Worng! Please use another email or another phone number.',
                  icon: 'error',
                  customClass: {
                    confirmButton: 'myCustomButton'
                  },
                  buttonsStyling: false,
                  allowOutsideClick: false,
                  allowEscapeKey: false
                });
              } else if(statusCode===400){
                Swal.fire({
                  title: 'Missing Informations!',
                  text: 'Please fill the information!',
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
        
        axios.post("http://localhost:8083/register",this.user)

          .then(function(response){
                  const status=JSON.parse(response.status);
                  if(status=='200'){
                    Swal.fire({
                      title: 'Register Success',
                      text: 'Welcome! Thank you for registering.',
                      icon: 'success',
                      customClass: {
                          confirmButton: 'myCustomSuccessButton'
                      },
                      buttonsStyling: false,
                      allowOutsideClick: false,
                      allowEscapeKey: false
                      }).then(() => {
                        router.push('/login'); 
                      });
                  }
              })
              .catch(httpErrorHandler)
              this.user.name='',
              this.user.phone='',
              this.user.email='',
              this.user.password=''
                  
    
            //
        },
    },
  };
</script>
