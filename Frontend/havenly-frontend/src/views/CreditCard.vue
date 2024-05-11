<template>
  <!-- <HelloWorld /> -->
  <!-- <VCreditCard/> -->
  <credit-card-form/>
</template>

<script>
import { defineComponent } from 'vue';
// import VCreditCard from 'v-credit-card';
import CreditCardForm from '@/components/Payment/CreditCardForm.vue';
// Components
// import HelloWorld from '../components/HelloWorld.vue';

export default defineComponent({
  name: 'CreditCard',

  components: {
    // HelloWorld,
    // VCreditCard,
    'credit-card-form' : CreditCardForm
  },
});
</script>
