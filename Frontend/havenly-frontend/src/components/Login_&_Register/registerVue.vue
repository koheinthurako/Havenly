<template>

    <div class="register-page">
        <div class="container">
            <div class="register-data">

                <div class="row">
                    <div class="col-md-6 mx-auto p-0">
                        <div class="register-form ">

                            <div class="header">

                                <div class="mx-auto my-auto px-auto py-auto">
                                    <h2 class="mx-auto me-auto">Create new Account</h2><br>
                                    <div class="d-flex">
                                        <p>If you already have account go to</p><router-link
                                            to="/login" class="ms-2 nav-link fw-bold" style="color: #525252; font-size: 24px;">Login</router-link>

                                    </div>
                                </div>

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

                                    <v-text-field v-model="confirm_password.value.value"
                                        :error-messages="confirm_password.errorMessage.value"
                                        :append-icon="visible1 ? 'mdi-eye' : 'mdi-eye-off'"
                                        :rules="[rules.required, rules.min]" :type="visible1 ? 'text' : 'password'"
                                        class="" hint="At least 8 characters" label="Repeat password" name="input-10-2"
                                        @click:append="visible1 = !visible1"></v-text-field>

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
    data() {
        return {
            visible: false,
            visible1: false,
            rules: {
                required: value => !!value || 'Required.',
                min: v => v.length >= 8 || 'Min 8 characters',
                emailMatch: () => (`The email and password you entered don't match`),
            },
        };
    },

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
            padding-bottom: 10px;
            box-shadow: 0px 6px 25px 1px rgba(0, 0, 0, 0.5);

            .header {
                width: 100%;
                height: 15vh;
                background-color: #e86f52;
                display: flex;
                justify-content: center;
                align-items: center;
                color: #fff;
                line-height: 13px;
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
import Swal from 'sweetalert2';

// import Store Path 
import store from '../../store/index.js'; // Update the path as per your project structure

const router = useRouter()

var register_data = []

const { handleSubmit, handleReset } = useForm({
    validationSchema: {
        name(value) {
            if (value?.length >= 5) return true

            return 'Name needs to be at least 5 characters.'
        },
        email(value) {
            if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

            return 'Must be a valid e-mail.'
        },

        checkbox(value) {
            if (value === '1') return true

            return 'Must be checked.'
        },
        password(value) {
            if (value?.length >= 6) {

                return true;
            } else {
                return 'Require at least 6 characters.'
            }
        },

        confirm_password(value) {
            if (value?.length >= 6) {
                return true;
            } else {
                return 'Require at least 6 characters.'
            }
        }

    },
})
const name = useField('name')
const email = useField('email')
const checkbox = useField('checkbox')
const password = useField('password')
const confirm_password = useField('confirm_password')

const submit = handleSubmit(values => {
    var matchPassword = false;

    if (values.password != values.confirm_password) {

        Swal.fire({
            title: "Error!",
            text: "Password didn't match.",
            icon: "warning"
        });

    } else {

        // Data that gonna store in state
        register_data.push({
            username: values.name,
            usermail: values.email,
            password: values.password,

        })
        console.log(register_data);
        // firstly store register data in state 
        store.dispatch('Register_Action', register_data);
        matchPassword = true;
    }

    if (matchPassword) {
        Swal.fire({
            title: 'Create New Account Successfully!',
            text: "Now, you can subscribe packages.",
            icon: 'success',
            showCancelButton: true,
            confirmButtonText: 'Go Home',
            cancelButtonText: 'Go Dashboard',
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33'
        }).then((result) => {
            if (result.isConfirmed) {
                router.push('/home');
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                router.push('/userdashboard');
            }
            else {
                router.push('/home'); // Redirect to dashboard after timeout
            }
        });

    }

})
</script>