<template>
 <v-container>
  <v-row align="center" justify="center">
<v-col cols="12" sm="10">
  <v-card class="elevation-6 mt-10">
    <v-window v-model="step">
      <v-window-item :value="1">
        <v-row>
          <v-col  cols="12" sm="6">
            <v-card-text class="mt-12">
              <h4 class="text-center">LOGIN Into Your Account</h4>
              <v-row align="center" justify="center">
                <v-col cols="12" sm="8">
                  <v-text-field label="E-mail" outlined dense color="orange" autocomplete="false" class="mt-16"></v-text-field>
                  <v-text-field label="Password" outlined dense color="orange" autocomplete="false" type="password"></v-text-field>
                  <v-row>
                    <v-col cols="12" sm="7">
                      <span style="color: #FF6E40;">Forget password?</span>
                    </v-col>
                  </v-row>
                  <br>
                  <v-btn  style="color:whitesmoke; background-color: #FF6E40;" dark block tile >Log in</v-btn>
                </v-col>
              </v-row>
            </v-card-text>
          </v-col>
          <v-col cols="12" sm="6" style="background-color: #FF6E40;border-bottom-left-radius:90%;">
            <div style="text-align: center; padding: 180px 0;">
              <v-card-text class="orange--text">
                <h4  style="color: white;">Don't Have an Account Yet?</h4>
                <h6 style="color: whitesmoke;">Let's get you an account</h6>
              </v-card-text>
              <div class="text-center">
                <v-btn style="color:#FF6E40;background-color: whitesmoke;"  @click="step++">SIGN UP</v-btn>
              </div>
            </div>
          </v-col>
        </v-row>
      </v-window-item>
      <v-window-item :value="2">
      <v-row>
        <v-col cols="12" sm="6" style="background-color: #FF6E40;border-bottom-right-radius:90%;">
          <div style="text-align: center; padding: 195px 0;">
            <v-card-text class="orange--text">
              <h4  style="color: white;">Already Signed up?</h4>
              <h6 style="color: whitesmoke;">Go back to log in</h6>

              <div class="text-center">
                <br>
                <v-btn style="color:#FF6E40;background-color: whitesmoke;" @click="step--">Log in</v-btn>
              </div>
            </v-card-text>
          </div>
        </v-col>
        <v-card-text class="mt-12">
          <h4 class="text-center">SIGN UP for an Account</h4>
          <h6 class="text-center" style="opacity: 70%;">Let's get you all set up</h6>
          <v-row align="center" justify="center">
             <v-col cols="12" sm="8">
              <v-row >
                <v-col cols="12" >
                  <br>
                  <v-text-field label="Name" outlined dense color="orange" autocomplete="false" ></v-text-field>
                  <v-text-field label="Email" outlined dense color="orange" autocomplete="false" ></v-text-field>
                  <v-text-field label="Phone" outlined dense color="orange" autocomplete="false" ></v-text-field>
                  <v-text-field label="Password" outlined dense color="orange"  ></v-text-field>
                </v-col>
                  <v-row align="center" justify="center">
                  <v-btn style="color:whitesmoke; background-color: #FF6E40;" dark block tile >SIGN UP</v-btn>
                  </v-row>
              </v-row>
             </v-col>
          </v-row>
        </v-card-text>
      </v-row>
      </v-window-item>
    </v-window>
  </v-card>
</v-col>
  </v-row>
 </v-container>
</template>

<script>



export default {
  data:()=>({
    step: 1
  }),
  propos:{
    source: String
  }

  
};
</script>

<style>

</style>
