<template>
  <HelloWorld />
  <VCreditCard/>
</template>

<script>
import { defineComponent } from 'vue';
import VCreditCard from 'v-credit-card';

// Components
import HelloWorld from '../components/HelloWorld.vue';

export default defineComponent({
  name: 'HomeView',

  components: {
    HelloWorld,
    VCreditCard,
  },
});
</script>
