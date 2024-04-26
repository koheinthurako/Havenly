<template>

    <div class="register-page">
        <div class="container">
            <div class="register-data">

                <div class="row">
                    <div class="col-md-6 mx-auto p-0">
                        <div class="register-form ">
                            <div class="header">
                                <div class="mx-auto my-auto px-auto py-auto">
                                    <h2 class="mx-auto me-auto text-center">Login Here</h2><br>
                                    <div class="d-flex">
                                        <p>If you don't have an account go to</p><router-link to="/register"
                                            class="ms-2 nav-link fw-bold"
                                            style="color: #525252; font-size: 24px;">Register</router-link>

                                    </div>
                                </div>
                            </div>

                            <div class="form">
                                <form @submit.prevent="submit">


                                    <v-text-field v-model="email.value.value" :error-messages="email.errorMessage.value"
                                        label="E-mail"></v-text-field>

                                    <v-text-field v-model="password.value.value"
                                        :error-messages="password.errorMessage.value"
                                        :append-icon="visible ? 'mdi-eye' : 'mdi-eye-off'"
                                        :rules="[rules.required, rules.min]" :type="visible ? 'text' : 'password'"
                                        class="input-group--focused" hint="At least 8 characters" label="Password"
                                        name="input-10-2" @click:append="visible = !visible"></v-text-field>


                                    <v-row cols="12" class="w-100">
                                        <v-btn class="submit ms-auto me-3" type="submit">
                                            Login
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
            padding-bottom: 20px;
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

const router = useRouter();


const { handleSubmit, handleReset } = useForm({
    validationSchema: {

        email(value) {
            if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

            return 'Must be a valid e-mail.'
        },


        password(value) {
            if (value?.length >= 6) {

                return true;
            } else {
                return 'Require at least 6 characters.'
            }
        },



    },
})

const email = useField('email')
const password = useField('password')


const submit = handleSubmit(values => {
    var Load_Data = store.state.registerData

    for (const pp of Load_Data) {
        if (pp.gmail === values.email) {
            if (pp.pass === values.password) {

                // Load Login action to State
                store.dispatch('To_Login_Action', pp.id);

                Swal.fire({
                    title: 'Logined Successfully!',
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

            } else {

                Swal.fire({
                    title: "Error!",
                    text: "Incorrect Password!",
                    icon: "warning"
                });

            }
        } else {
            Swal.fire({
                title: "Error!",
                text: "Gmail is not Registered Yet!",
                icon: "warning"
            });

        }
    }

})
</script>