<template>

    <div class="register-page">
        <div class="container">
            <div class="register-data">

                <div class="row">
                    <div class="col-md-6 mx-auto p-0">
                        <div class="register-form ">
                            <div class="header">
                                <h2 class="mx-auto me-auto">Create new Account</h2>
                            </div>

                            <div class="form">
                                <form @submit.prevent="submit">

                                    <v-text-field v-model="name.value.value" :counter="10"
                                        :error-messages="name.errorMessage.value" label="User name"></v-text-field>

                                    <v-text-field v-model="email.value.value" :error-messages="email.errorMessage.value"
                                        label="E-mail"></v-text-field>

                                    <v-text-field v-model="password.value.value"
                                        :error-messages="password.errorMessage.value"
                                        :append-icon="visible ? 'mdi-eye' : 'mdi-eye-off'"
                                        :rules="[rules.required, rules.min]" :type="visible ? 'text' : 'password'"
                                        class="input-group--focused" hint="At least 8 characters" label="Password"
                                        name="input-10-2" @click:append="visible = !visible"></v-text-field>


                                    <v-checkbox v-model="checkbox.value.value"
                                        :error-messages="checkbox.errorMessage.value"
                                        label="Agree our Rules and Regulations." type="checkbox" value="1"></v-checkbox>

                                    <v-row cols="12" class="w-100">
                                        <v-btn class="submit ms-auto me-3" type="submit">
                                            submit
                                        </v-btn>

                                        <v-btn class="clear" @click="handleReset">
                                            clear
                                        </v-btn>
                                    </v-row>
                                </form>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>


<script>

export default {
    data: () => ({
        registerDialogVisible: false,
        loginDialogVisible: false,

        // for password field
        visible: false,
        password: 'Password',
        rules: {
            required: value => !!value || 'Required.',
            min: v => v.length >= 8 || 'Min 8 characters',
            emailMatch: () => (`The email and password you entered don't match`),
        },
    }),

}
</script>

<style>
.register-page {
    margin-top: 5%;
    padding: 30px 0px;
    width: 100%;
    height: auto;


    .register-data {
        width: 100%;
        height: auto;

        .register-form {
           overflow: hidden;

            border-radius: 10px;
            box-shadow: 0px 6px 25px 1px rgba(0, 0, 0, 0.5);

            .header {
                width: 100%;
                height: 15vh;
                background-color: #e86f52;
                display: flex;
                align-items: center;
                justify-content: start;

                h2 {
                    color: #fff;
                }
            }

            .form {
                padding: 20px;

                .submit {
                    background-color: #e86f52;
                    color: #fff;
                }

                .clear {
                    background-color: #525252;
                    color: #fff;
                }
            }
        }
    }
}
</style>

<script setup>

import { useField, useForm } from 'vee-validate'
import { useRouter } from 'vue-router'

const router = useRouter()

const { handleSubmit, handleReset } = useForm({
    validationSchema: {
        name(value) {
            if (value?.length >= 6) return true

            return 'Name needs to be at least 6 characters.'
        },
        email(value) {
            if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

            return 'Must be a valid e-mail.'
        },
 
        checkbox(value) {
            if (value === '1') return true

            return 'Must be checked.'
        },
        passdata(value) {
            if (value?.length >= 6) return true
            return 'Require at least 6 characters.'
        }
    },
})
const name = useField('name')
const email = useField('email')
const checkbox = useField('checkbox')
const password = useField('passdata')

const submit = handleSubmit(values => {
    const registrationSuccessful = true;
        console.log('reached');
        if (registrationSuccessful) {
            // Redirect to the home page
            router.push('/home');
        } else {
            // Handle registration failure
            console.error('Registration failed. Please try again.');
        }
})
</script>