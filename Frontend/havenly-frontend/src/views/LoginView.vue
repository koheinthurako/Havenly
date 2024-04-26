<template>
    <div class="d-flex align-center justify-center" style="height: 100vh">
        <v-sheet width="400" class="mx-auto">
            <v-form fast-fail @submit.prevent="login">
                
                <v-text-field v-model="user.email" label="User Name" :rules="[value => !!value || 'Required']" ></v-text-field>

                <v-text-field
                 v-model="user.password"  :type="showPassword ? 'text' : 'password'"
                :append-inner-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'"
                @click:append-inner=togglePasswordVisibility()  label="password" 
                :rules="[value => !!value || 'Required']" >

                </v-text-field>
               

                <a href="#" class="text-body-2 font-weight-regular">Forgot Password?</a>

                <v-btn type="submit" block class="mt-2 bg-redbrick text-white mt-3">Sign in</v-btn>

            </v-form>
            <div class="mt-2">
                <p class="text-body-2">Don't have an account? <a href="/register">Sign Up</a></p>
            </div>
        </v-sheet>
    </div>
</template>
<script>

import router from '@/router';
import axios from 'axios';


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
                                if(statusCode===400){alert("Invalid Username or Password...Please try again!!!")}
                            }
                            }
                    }
          axios.post("http://localhost:8083/login",this.user)
           
            .then(function(response){
                    const status=JSON.parse(response.status);
                    if(status=='200'){
                        router.push('/home');
                    }
                    
                        })
            .catch(httpErrorHandler)
                 },
    },
}
</script>