<template>
  <v-app>
    <v-main>
      <navbar_vue />
      <router-view />
      <footer_vue />
    </v-main>
  </v-app>
</template>

<script>

import footer_vue from './components/For_App/footerVue.vue'
export default {
  name: 'App',

  components: {

    footer_vue
  },

  data: () => ({
    //
  }),
}
</script>
