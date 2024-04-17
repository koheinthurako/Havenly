<template>
  <v-app>
    <v-main>
      <navbar />
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
import navbar from './components/For_App/navbarVue.vue'

export default {
  name: 'App',

  components: {
    navbar,

  },

  data: () => ({
    //
  }),
}
</script>
