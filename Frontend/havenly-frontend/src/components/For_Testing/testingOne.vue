<template>

  <div style="margin-top: 7%;"></div>
  <div class="w-100 d-flex p-0 m-0">
    <span class="mt-2 me-2">Choose an option : </span>
    <v-radio-group v-model="change_type" inline>
      <v-radio label="Option one" value="one" color="orange"></v-radio>
      <v-radio label="Option two" value="two" color="orange"></v-radio>
    </v-radio-group>
  </div>

  <div v-if="box_data">
    <v-btn @click="change">
      Off
    </v-btn>
  </div>
  <div v-if="!box_data">
    <v-btn @click="change">On</v-btn>
  </div>


  <div class="row">
    <div class="col-md-6 mx-auto"
      style="transition: all 0.4s ease-in-out;padding: 20px; background-color: #ddd; position: sticky; top: 0;">
      <form @submit.prevent="submit">
        <v-text-field v-model="name.value.value" fast-fail="true" :counter="10"
          :error-messages="name.errorMessage.value" label="Name"></v-text-field>

        <v-text-field v-model="phone.value.value" :counter="7" :error-messages="phone.errorMessage.value"
          label="Phone Number"></v-text-field>

        <v-text-field v-model="email.value.value" :error-messages="email.errorMessage.value"
          label="E-mail"></v-text-field>

        <v-select v-model="select.value.value" :error-messages="select.errorMessage.value" :items="items"
          label="Select"></v-select>

        <div :class="{ fade_in: change_type === 'one', fade_right_gone: change_type !== 'one' }">
          <v-checkbox v-model="checkbox.value.value" :error-messages="checkbox.errorMessage.value" label="Option"
            type="checkbox" value="1"></v-checkbox>
        </div>

        <v-btn :class="{ up_ward: change_type !== 'one' }" class="me-4" type="submit">
          submit
        </v-btn>

        <v-btn @click="handleReset">
          clear
        </v-btn>
      </form>
    </div>
  </div>
</template>

<script>

export default {
  data: () => ({
    change_type: 'one',
    box_data: true
  }),

  computed: {
    changer() {
      return !this.box_data;
    }
  },

  methods: {
    change() {
      this.box_data = this.changer;
    }
  }
}

</script>

<script setup>
import { ref } from 'vue'
import { useField, useForm } from 'vee-validate'

const { handleSubmit, handleReset } = useForm({
  validationSchema: {
    name(value) {
      if (value?.length >= 2) return true

      return 'Name needs to be at least 2 characters.'
    },
    phone(value) {
      if (value?.length > 9 && /[0-9-]+/.test(value)) return true

      return 'Phone number needs to be at least 9 digits.'
    },
    email(value) {
      if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

      return 'Must be a valid e-mail.'
    },
    select(value) {
      if (value) return true

      return 'Select an item.'
    },
    checkbox(value) {
      if (value === '1') return true

      return 'Must be checked.'
    },
  },
})
const name = useField('name')
const phone = useField('phone')
const email = useField('email')
const select = useField('select')
const checkbox = useField('checkbox')

const items = ref([
  'Item 1',
  'Item 2',
  'Item 3',
  'Item 4',
])

const submit = handleSubmit(values => {
  alert(JSON.stringify(values, null, 2))
})
</script>

<style>
.v-text-field,
.v-select,
.v-checkbox {
  transition: all 0.4s ease-in-out both;
}

.up_ward {
  animation: upward 1s ease-in-out both;
}

.fade_right_gone {
  animation: fadeRightGone 0.6s ease-in-out both;
}

@keyframes fadeRightGone {
  0% {
    transform: translateX(0px);
  }

  45% {
    opacity: 0.5;
    transform: translateX(-50px);
  }

  100% {
    display: none;
    opacity: 0;
    transform: translateX(-200px);
  }
}

@keyframes upward {
  0% {
    transform: translateY(0px);
  }

  50% {
    transform: translateY(-50px);
  }

  100% {
    transform: translateY(0);
  }
}

.fade_in {
  animation: ScaleIn 0.3s cubic-bezier(0.68, -0.6, 0.32, 1.6) 0s 1 normal both;
}

.fade_out {
  animation: myAnim 0.1s cubic-bezier(0.68, -0.6, 0.32, 1.6) 0s 1 normal both;
}

@keyframes ScaleIn {
  0% {
    transform: scale(0);
  }

  100% {
    transform: scale(1);
  }
}

@keyframes myAnim {
  0% {
    transform: rotate(0);
    transform-origin: top;
  }

  100% {
    display: none;
    transform: rotate(360deg);
    transform-origin: top;
  }
}
</style>