<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <h2>Havenly</h2>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <router-link to="/testingPage" class="nav-link">Testing</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/home" class="nav-link">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/home" class="nav-link">Category</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/home" class="nav-link">Contact</router-link>
          </li>

          <li class="nav-item">
            <div v-if="register_statue">
              <div v-if="login_status">
                <!-- card start -->

                <button class="nav-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                  <v-icon class=" me-1">mdi-account-box</v-icon>{{ user_data.name }}
                </button>
                <ul class="dropdown-menu p-0">
                  <li>
                    <router-link to="/userdashboard" class="dropdown-item"><v-icon
                        class="me-1">mdi-account-circle</v-icon>User Profile</router-link>
                  </li>
                  <li>
                    <router-link to="/userdashboard" class="dropdown-item"><v-icon
                        class="me-1">mdi-view-dashboard</v-icon>User dashboard</router-link>
                  </li>
                  <li>
                    <div @click="logout" class="dropdown-item"><v-icon class="me-1">mdi-logout-variant</v-icon>Logout
                    </div>
                  </li>
                </ul>

                <!-- card end -->
              </div>
              <div v-else>
                <router-link to="/login" class="nav-link">Login</router-link>
              </div>
            </div>
            <div v-else>
              <router-link to="/register" class="nav-link">Register</router-link>
            </div>
          </li>

        </ul>

      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'navbarVue',

  computed: {
    login_status() {
      return this.$store.getters.LoginData;
    },
    register_statue() {
      return this.$store.getters.RegisterData;
    },
    user_data() {
      return this.$store.getters.Take_Userinfo;
    }
  },

  methods: {
    logout() {
      this.$store.dispatch('To_Logout_Action');
      this.$router.push('/home');
    }
  }
};
</script>
