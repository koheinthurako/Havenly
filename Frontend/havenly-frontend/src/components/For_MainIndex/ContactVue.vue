<template>
    <div class="contact-page">
        <div class="contact-data">
            <div class="row">
                <div class="col-md-4">
                    <div class="left-data">
                        <div>
                            <h3>Contact us</h3>
                        </div>
                        <div>
                            <v-icon>mdi-home</v-icon>Yangon
                        </div>
                        <div>
                            <v-icon>mdi-email-outline</v-icon>helloworld@gmail.com
                        </div>
                        <div>
                            <v-icon>mdi-cellphone</v-icon>+95+9664493719
                        </div>
                        <p>You can contact or give advice to admin. We will always taking care to our customers.</p>
                    </div>
                </div>
                <div class="col-md-6">
                    <h3>Register Form</h3>

                    <form @submit.prevent="submit">
                        <div class="d-flex">
                            <v-text-field class="me-3" v-model="Firstname.value.value" :counter="10"
                                :error-messages="Firstname.errorMessage.value" label="First name"></v-text-field>

                            <v-text-field v-model="Lastname.value.value" :counter="10"
                                :error-messages="Lastname.errorMessage.value" label="Last Name"></v-text-field>
                        </div>

                        <v-text-field v-model="phone.value.value" :counter="7"
                            :error-messages="phone.errorMessage.value" label="Phone Number"></v-text-field>

                        <v-text-field v-model="email.value.value" :error-messages="email.errorMessage.value"
                            label="E-mail"></v-text-field>

                        <v-text-field v-model="address.value.value" :error-messages="address.errorMessage.value"
                            label="Address"></v-text-field>

                        <v-btn class="me-4 mt-3" type="submit" color="red">
                            submit
                        </v-btn>

                        <v-btn @click="handleReset" class="mt-3">
                            clear
                        </v-btn>
                    </form>

                </div>
                <div class="col-md-2">
                    <h3>Links </h3>
                    <router-link to="/" class="btn w-100 mb-2"><div style="color: #fff;">Home</div></router-link>
                    <router-link to="/" class="btn w-100 mb-2"><div style="color: #fff;">Address</div></router-link>
                    <router-link to="/" class="btn w-100 mb-2"><div style="color: #fff;">Contact</div></router-link>
                    <router-link to="/" class="btn w-100 mb-2"><div style="color: #fff;">About</div></router-link>
                    <v-btn variant="elevated" color="white" class="w-100">Call Now</v-btn>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
export default {
    name: 'ContactVue',

    data: () => ({

    }),
}

</script>

<script setup>
import { useField, useForm } from 'vee-validate'

const { handleSubmit, handleReset } = useForm({
    validationSchema: {
        Firstname(value) {
            if (value?.length >= 2) return true

            return 'First Name needs to be at least 2 characters.'
        },
        Lastname(value) {
            if (value?.length >= 2) return true

            return 'Last Name needs to be at least 2 characters.'
        },
        address(value) {
            if (value?.length >= 10) return true

            return 'Bro, it not your address ;).'
        },
        phone(value) {
            if (value?.length > 9 && /[0-9-]+/.test(value)) return true

            return 'Phone number needs to be at least 9 digits.'
        },
        email(value) {
            if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

            return 'Must be a valid e-mail.'
        },
    },
})
const Firstname = useField('Firstname')
const Lastname = useField('Lastname')
const address = useField('address')
const phone = useField('phone')
const email = useField('email')


const submit = handleSubmit(values => {
    alert(JSON.stringify(values, null, 2))
})
</script>

<style> 
/* .left-data {
    div {
        width: 100%;
        padding: 10px 0px;
    }
} */

</style>