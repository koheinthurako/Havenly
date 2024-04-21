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
                            <v-icon class="me-1">mdi-home</v-icon>Yangon
                        </div>
                        <div>
                            <v-icon class="me-1">mdi-email-outline</v-icon>helloworld@gmail.com
                        </div>
                        <div>
                            <v-icon class="me-1">mdi-cellphone</v-icon>+95+9664493719
                        </div>
                        <p>You can contact or give advice to admin. We will always taking care to our customers.</p>
                    </div>
                </div>
                <div class="col-md-6">
                    <h3>User feedback form</h3>

                    <form @submit.prevent="submit">
                         
                        <v-text-field v-model="email.value.value" :error-messages="email.errorMessage.value"
                            label="E-mail"></v-text-field>

                        <v-textarea v-model="textarea.value.value" :error-messages="textarea.errorMessage.value" label="Label"></v-textarea>
 
                        <v-btn :elevation="20" class="me-4 mt-3 submit" type="submit" >
                            submit
                        </v-btn>

                        <v-btn :elevation="20" @click="handleReset" class="mt-3 clear">
                            clear
                        </v-btn>
                    </form>

                </div>
                <div class="col-md-2">
                    <h3>Links </h3>
                    <div class="link-colletion">
                        <router-link to="/" class="nav-link ">Home</router-link>
                        <router-link to="/" class="nav-link ">Address</router-link>
                        <router-link to="/" class="nav-link">Contact</router-link>
                        <router-link to="/" class="nav-link">About</router-link>
                        <v-btn variant="elevated" class="call-bg">Call Now</v-btn>
                    </div>
                    
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
        word(value) {
            if (value?.length >= 6) return true

            return 'Feed back is not that short!'
        },
        email(value) {
            if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

            return 'Must be a valid e-mail.'
        },
    },
})

const email = useField('email')
const textarea = useField('word')


const submit = handleSubmit(values => {
    alert(JSON.stringify(values, null, 2))
})
</script>

<style> 
.left-data {
    div {
        width: 100%;
        padding: 10px 0px;
    }
}

</style>