<template>
  <!-- <VCreditCard/> -->
  <credit-card-form/>
</template>

<script>
import { defineComponent } from 'vue';
// import VCreditCard from 'v-credit-card';
import CreditCardForm from '@/components/Payment/CreditCardForm.vue';

export default defineComponent({
  name: 'PaymentVue',

  components: {
    // HelloWorld,
    // VCreditCard,
    'credit-card-form' : CreditCardForm
  },
});
</script>
