<template>
  <v-app>
    <v-main>
      <navbar_vue v-if="showNavbar" />
      <router-view />
      <footer_vue />
    </v-main>
  </v-app>
</template>

<script>
import navbar_vue from './components/For_App/navbarVue.vue'
import footer_vue from './components/For_App/footerVue.vue'
export default {
  name: 'App',

  components: {
    navbar_vue,
    footer_vue
  },

  computed: {
    showNavbar() {
      return !this.$route.path.startsWith('/admin/dashboard');
    }
  },

  data: () => ({
    //
  }),
}
</script>