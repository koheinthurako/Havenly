<template>
  <nav class="navbar navbar-expand-lg p-0 fixed-top py-2">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <h2>Havenly</h2>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mx-auto">
          <!-- <li class="nav-item">
            <router-link to="/testingPage" class="nav-link">Testing</router-link>
          </li> -->

          <li class="nav-item">
            <router-link to="/" :class="{
              'nav-link': true, active:
                ('home')
            }">Home</router-link>
          </li>

          <li class="nav-item">
            <router-link to="/"
              :class="{ 'nav-link': true, active: isNavLinkActive('category') }">Category</router-link>
          </li>

          <li class="nav-item">
            <router-link to="/package"
              :class="{ 'nav-link': true, active: isNavLinkActive('package') }">Packages</router-link>
          </li>

          <li class="nav-item">
            <router-link to="/subscribe"
              :class="{ 'nav-link': true, active: isNavLinkActive('documentation') }">Subscribe</router-link>
          </li>

          <li class="nav-item">
            <router-link to="/" :class="{ 'nav-link': true, active: isNavLinkActive('contact') }">Contact</router-link>
          </li>


          <!-- <li class="nav-item">
            <router-link to="/userDashBoardNew"
              :class="{ 'nav-link': true, active: isNavLinkActive('profile') }">Profile</router-link>
          </li> -->
          <li class="nav-item">
            <router-link to="/userdashboard"
              :class="{ 'nav-link': true, active: isNavLinkActive('profile') }">Profile</router-link>
          </li>
        </ul>


        <ul class="navbar-nav">
          <li class="nav-item">
            <div v-if="register_statue">
              <div v-if="login_status">
                <button class="nav-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                  <v-icon class=" me-1">mdi-account-box</v-icon>{{ user_data.name }}
                </button>
                <ul class="dropdown-menu p-0">
                  <li>
                    <router-link to="/userdashboard" class="dropdown-item"><v-icon
                        class="me-1">mdi-account-circle</v-icon>User Profile</router-link>
                  </li>
                  <!-- <li>
                    <router-link to="/userdashboard" class="dropdown-item"><v-icon
                        class="me-1">mdi-view-dashboard</v-icon>User dashboard</router-link>
                  </li> -->
                  <li>
                    <div @click="logout" class="dropdown-item"><v-icon class="me-1">mdi-logout-variant</v-icon>Logout
                    </div>
                  </li>
                </ul>
              </div>
              <!-- <div v-else>
                <router-link to="/login" class="nav-link">Login</router-link>
              </div> -->
            </div>
            <div v-else>
              <router-link v-model="loginText" to="/loginakm" class="nav-link">{{ loginText }}</router-link>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
export default {
  name: 'navbarVue',


  setup() {

    const activeNavLink = ref('');
    const router = useRouter();

    const isNavLinkActiveSetup = (id) => {
      return id === activeNavLink.value;
    };

    const handleScroll = () => {
      const scrollPosition = window.scrollY;
      if (router.currentRoute.value.path === '/') {
        if (scrollPosition >= -10 && scrollPosition < 350) {
          activeNavLink.value = 'home';
        } else if (scrollPosition < 2100) {
          activeNavLink.value = 'category';
        } else if (scrollPosition < 2900) {
          activeNavLink.value = 'package';
        } else if (scrollPosition < 3500) {
          activeNavLink.value = 'blog';
        } else {
          activeNavLink.value = 'contact';
        }
      } else if (router.currentRoute.value.path === '/package') {
        activeNavLink.value = 'package';
      }
    };


    onMounted(() => {
      window.addEventListener('scroll', handleScroll);
    });


    return {
      isNavLinkActiveSetup
    };
  },

  data() {
    return {
      notificationCount: 5,
      activeDataLink: '',
      loginText: 'Login',
    };
  },

  computed: {

    user_data() {
      return this.$store.getters.Take_Userinfo
    },

    register_statue() {
      return this.$store.getters.RegisterData
    },

    login_status() {
      return this.$store.getters.LoginData
    }

  },

  methods: {

    isNavLinkActive(routeName) {
      // Return whether the route name matches the current route
      return this.$route.name === routeName;
    },

    // isLoggedIn() {
    //   // Check if user information is stored in session storage
    //   console.log("User is included in session storage");
    //   const user = sessionStorage.getItem('login_user');
    //   return !!user; // Return true if user is logged in, false otherwise
    // },

    logout() {
      this.$store.dispatch('To_Logout_Action');
      this.$router.push('/home');
    }
  }
};
</script>

<style>
.active {
  background-color: #e86f52 !important;
  color: #fff !important;
}
</style>
