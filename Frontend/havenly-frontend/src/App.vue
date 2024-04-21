<template>
  <v-app>
    <v-main>
      <navbar />
      <router-view/>
      <footerpage />
    </v-main>
  </v-app>
</template>

<script>
import navbar from './components/For_App/navbarVue.vue'
import footerpage from './components/For_App/footerVue.vue'

export default {
  name: 'App',

  components: {
    navbar,
    footerpage,

  },

  data: () => ({
    //
  }),
}
</script>
