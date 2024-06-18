<template>
  <v-app>
    <v-main>
      <navbar_vue v-if="showNavbar" />
      <router-view />
      <footer_vue v-if="showFooter"/>
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
      const currentPath = this.$route.path;
      return !currentPath.startsWith('/admin/dashboard') && currentPath !== '/admin/login' && currentPath !== '/login' && currentPath !== '/register';
    },

    showFooter() {
      const currentPath = this.$route.path;
      return !currentPath.startsWith('/login') && currentPath !== '/admin/login' && currentPath !== '/register' && currentPath !== '/forgot';
    }
  },

  data: () => ({
    //
  }),
}
</script>