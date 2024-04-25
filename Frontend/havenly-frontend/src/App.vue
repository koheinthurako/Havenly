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

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #656565;
  margin-top: 10px;
}
</style>
