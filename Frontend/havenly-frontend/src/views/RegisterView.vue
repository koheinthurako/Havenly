<template>
  <div class="d-flex align-center justify-center" style="height: 100vh">
      <v-sheet width="400" class="mx-auto">
          <v-form ref="form" fast-fail @submit.prevent="signup">
              <v-text-field variant="underlined" v-model="user.name"  label="Name"  required ></v-text-field>
              <v-text-field variant="underlined" v-model="user.phone"   label="Phone" required ></v-text-field>
              <v-text-field variant="underlined"  v-model="user.email"    label="Email" required ></v-text-field>
              <v-text-field variant="underlined" v-model="user.password"  label="password" required ></v-text-field>

              <v-btn type="submit" block class="mt-2 bg-redbrick text-white">Sign up</v-btn>
          </v-form>
          <div class="mt-2">
              <p class="text-body-2">
                  Already have an account? <a href="/login">Sign in</a>
              </p>
          </div>
      </v-sheet>
  </div>
</template>
<script>
import axios from 'axios'
export default {
 
  data() {
      return {
        user :{
          name: '',
          phone:'',
          email: '',
          password: ''
        }
      };
  },
  methods: {
      signup() {
       
        function httpErrorHandler(error) {
                        if (axios.isAxiosError(error)) {
                            const response = error?.response
                            if(response){
                                const statusCode = response?.status
                                if(statusCode===500){alert("Something Worng!!Please use another email or another phone number!!!")}
                                else if(statusCode===400){alert("Please fill the information!!!")}
                                
                            }
                            }
                    }
        axios.post("http://localhost:8083/register",this.user)

        .then(function(response){
                const status=JSON.parse(response.status);
                if(status=='200'){
                  alert("Registered Successfully")
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
