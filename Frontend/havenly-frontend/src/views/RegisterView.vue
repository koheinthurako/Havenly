<template>

  <div id="register" class="d-flex" style="height: 100vh;">
    <div class="col-12 col-lg-6 mt-lg-4 me-4">
      <v-sheet class="m-5 ms-lg-5 ps-lg-5">
        <h4 class="flex mb-1">Registeration Form</h4>
        <h1 class="mb-3" style="color: #e86f52;"><strong>Create Your Account</strong></h1>
        <v-form ref="form" v-model="valid" fast-fail @submit.prevent="signup">
          <v-text-field required variant="underlined" v-model="user.name" label="Name"></v-text-field>
          <v-text-field variant="underlined" v-model="user.phone"
            :rules="[value => value.length < 12 || 'Ph no. must be 11 numbers']" label="Phone" required></v-text-field>
          <v-text-field variant="underlined" v-model="user.email" label="Email" required
            :rules="[v => !!v || 'Email is required', v => !/^\s*$/.test(v) || 'Email cannot be just spaces', v => /.+@.+\..+/.test(v) || 'Email must be valid']"></v-text-field>
          <v-text-field variant="underlined" v-model="user.password" label="password" required
            :type="showPassword ? 'text' : 'Password'" :append-inner-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'"
            @click:append-inner=togglePasswordVisibility() :rules="[value => !!value || 'Required']"></v-text-field>
          <div class="my-3">
            <p class="text-body-2">
              Already have an account? <a href="/login">Sign in</a>
            </p>
          </div>
          <div>
            <button type="submit" class="btn text-white px-5 w-fit-content rounded-pill mt-3"
              style="background-color: #e86f52 !important;">Register</button>
          </div>
        </v-form>

      </v-sheet>
    </div>
    <div class="col-12 col-lg-6 imgBox">
      <div class="realImg w-100 h-100">
      </div>
    </div>
  </div>
</template>
<script>
import router from '@/router';
import axios from 'axios'
import Swal from 'sweetalert2';

export default {

  data() {
    return {
      user: {
        name: '',
        phone: '',
        email: '',
        password: ''
      },
      showPassword: false,
      valid: false,
    };
  },

  mounted() {
    localStorage.removeItem('openTab');
  },

  methods: {

    togglePasswordVisibility() {
      this.showPassword = !this.showPassword;
    },

    async signup() {
      if (this.$refs.form.validate()) {
        try {
          const response = await axios.post("http://localhost:8083/register", this.user)
          if (response.status === 200) {
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
            this.user.name = '',
              this.user.phone = '',
              this.user.email = '',
              this.user.password = ''
          }
        } catch (error) {
          this.httpErrorHandler(error);
        }
      }
    },

    httpErrorHandler(error) {
      if (axios.isAxiosError(error)) {
        const response = error.response;
        if (response) {
          const statusCode = response.status;
          if (statusCode === 500) {
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
          } else if (statusCode === 400) {
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
  },
};
</script>

<style></style>