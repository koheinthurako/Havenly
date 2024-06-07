<template>
    <div class="register-page">
        <div class="row p-0 m-0 mb-5">
            <div class="col-md-3">

            </div>

            <div class="col-md-6">

                <div :class="{ var_Nar_Go: !show_register, hor_wid_show2: show_register }">
                    <!-- Register data start -->
                    <div class="register-data">
                        <div class="register-form ">

                            <div class="register-header">

                                <div class="mx-auto my-auto px-auto py-auto">
                                    <h2 class="mx-auto me-auto">Create new Account</h2><br>
                                    <div class="d-flex">
                                        <p>If you already have account go to</p><span @click="change"
                                            class="ms-2 nav-link fw-bold"
                                            style="color: #525252; font-size: 24px; cursor: pointer;">Login</span>

                                    </div>
                                </div>

                            </div>

                            <div class="register-form-submit">
                                <form @submit.prevent="submit">

                                    <div class="row">
                                        <div class="col-3">
                                            <span class="float-right mt-3">User name<span class="text-red">*</span>
                                            </span>
                                        </div>
                                        <div class="col-9">
                                            <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable
                                                rounded="lg" variant="solo" v-model="name.value.value" :counter="10"
                                                :error-messages="name.errorMessage.value"
                                                label="User name"></v-text-field>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-3">
                                            <span class="float-right mt-3">Gmail<span class="text-red">*</span>
                                            </span>
                                        </div>
                                        <div class="col-9">
                                            <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable
                                                rounded="lg" variant="solo" v-model="email.value.value"
                                                :error-messages="email.errorMessage.value"
                                                label="G-mail"></v-text-field>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-3">
                                            <span class="float-right mt-3">Contact No<span class="text-red">*</span>
                                            </span>
                                        </div>
                                        <div class="col-9">
                                            <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable
                                                rounded="lg" variant="solo" v-model="phone.value.value" :counter="11"
                                                :error-messages="phone.errorMessage.value"
                                                label="Phone Number"></v-text-field>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-3">
                                            <span class="float-right mt-3">Password<span class="text-red">*</span>
                                            </span>
                                        </div>
                                        <div class="col-9">
                                            <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable
                                                rounded="lg" variant="solo" v-model="password.value.value"
                                                :error-messages="password.errorMessage.value"
                                                :append-icon="visible ? 'mdi-eye' : 'mdi-eye-off'"
                                                :rules="[rules.required, rules.min]"
                                                :type="visible ? 'text' : 'password'" class="input-group--focused"
                                                hint="At least 8 characters" label="Password" name="input-10-2"
                                                @click:append="visible = !visible"></v-text-field>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-3">
                                            <span class="float-right mt-3">Repeat password<span
                                                    class="text-red">*</span>
                                            </span>
                                        </div>
                                        <div class="col-9">
                                            <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable
                                                rounded="lg" variant="solo" v-model="confirm_password.value.value"
                                                :error-messages="confirm_password.errorMessage.value"
                                                :append-icon="visible1 ? 'mdi-eye' : 'mdi-eye-off'"
                                                :rules="[rules.required, rules.min]"
                                                :type="visible1 ? 'text' : 'password'" class=""
                                                hint="At least 8 characters" label="Repeat password" name="input-10-2"
                                                @click:append="visible1 = !visible1"></v-text-field>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-3">
                                            <span class="float-right mt-3"><v-icon
                                                    style="font-size: 28px;">mdi-information</v-icon>Rules<span
                                                    class="text-red">*</span>
                                            </span>
                                        </div>
                                        <div class="col-9">
                                            <v-checkbox v-model="checkbox.value.value"
                                                :error-messages="checkbox.errorMessage.value"
                                                label="Agree our Rules and Regulations." type="checkbox"
                                                value="1"></v-checkbox>

                                        </div>
                                    </div>




                                    <v-row cols="12" class="w-100">
                                        <v-btn rounded class="submit ms-auto me-3" type="submit">
                                            submit
                                        </v-btn>

                                        <v-btn rounded class="clear" @click="handleReset">
                                            clear
                                        </v-btn>
                                    </v-row>
                                </form>
                            </div>


                        </div>
                    </div>
                    <!-- Register data End -->
                </div>



                <div v-if="!show_register">
                    <div :class="{ hor_wid_show: !show_register, var_Nar_Go2: !show_register }">
                        <!-- Login data start -->
                        <div class="login-data">
                            <div class="login-form ">
                                <div class="login-header">
                                    <div class="mx-auto my-auto px-auto py-auto">
                                        <h2 class="mx-auto me-auto text-center">Login Here</h2><br>
                                        <div class="d-flex">
                                            <p>If you don't have an account go to</p><span @click="change"
                                                class="ms-2 nav-link fw-bold"
                                                style="color: #525252; font-size: 24px;cursor: pointer;">Register</span>

                                        </div>
                                    </div>
                                </div>

                                <div class="login-form-submit">
                                    <form @submit.prevent="submit">

                                        <div class="row">
                                            <div class="col-3">
                                                <span class="float-right mt-3">Gmail<span class="text-red">*</span>
                                                </span>
                                            </div>
                                            <div class="col-9">
                                                <v-text-field density="comfortable" clear-icon="mdi-close-circle"
                                                    clearable rounded="lg" variant="solo" v-model="email.value.value"
                                                    :error-messages="email.errorMessage.value"
                                                    label="G-mail"></v-text-field>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-3">
                                                <span class="float-right mt-3">Password<span class="text-red">*</span>
                                                </span>
                                            </div>
                                            <div class="col-9">
                                                <v-text-field density="comfortable" rounded="lg" variant="solo"
                                                    v-model="password.value.value"
                                                    :error-messages="password.errorMessage.value"
                                                    :append-inner-icon="visible ? 'mdi-eye' : 'mdi-eye-off'"
                                                    :rules="[rules.required, rules.min]"
                                                    :type="visible ? 'text' : 'password'" class="input-group--focused"
                                                    hint="At least 8 characters" label="Password" name="input-10-2"
                                                    @click:append="visible = !visible"></v-text-field>
                                            </div>
                                        </div>


                                        <v-row cols="12" class="w-100">
                                            <v-btn rounded class="submit ms-auto me-3" type="submit">
                                                Login
                                            </v-btn>

                                            <v-btn rounded class="clear" @click="handleReset">
                                                clear
                                            </v-btn>
                                        </v-row>
                                    </form>
                                </div>


                            </div>


                        </div>
                        <!-- Login data End -->
                    </div>

                </div>




            </div>
            <div class="col-md-3">

            </div>
        </div>

        <!-- Row two -->
        <div class="row">
            <small_ads />
        </div>
    </div>

</template>


<script>
import small_ads from '../Temp_Collection/ads-Medium.vue'
export default {
    components: {
        small_ads,
    },
    data() {
        return {
            visible: false,
            visible1: false,
            show_register: true,
            rules: {
                required: value => !!value || 'Required.',
                min: v => v.length >= 8 || 'Min 8 characters',
                emailMatch: () => (`The email and password you entered don't match`),
            },
        };
    },

    computed: {
        changer() {
            return !this.show_register;
        }
    },

    mounted() {
        localStorage.removeItem('openTab');
    },

    methods: {
        change() {
            this.show_register = this.changer
        }
    }

}
</script>

<style>
.register-page {
    width: 100%;
    height: auto;
    margin-top: 5%;

    .register-data {
        width: 100%;
        height: auto;

        .register-form {
            overflow: hidden;
            border-radius: 10px;
            padding-bottom: 10px;
            box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.5);

            .register-header {
                width: 100%;
                height: 15vh;
                background-color: #e86f52;
                display: flex;
                justify-content: center;
                align-items: center;
                color: #fff;
                line-height: 13px;
            }

            .register-form-submit {
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

    .login-data {
        width: 100%;
        height: auto;

        .login-form {
            overflow: hidden;
            border-radius: 10px;
            padding-bottom: 20px;
            box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.5);

            .login-header {
                width: 100%;
                height: 15vh;
                background-color: #e86f52;
                display: flex;
                justify-content: center;
                align-items: center;
                color: #fff;
                line-height: 13px;
            }

            .login-form-submit {
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

.var_Nar_Go {
    animation: myAnim 1s cubic-bezier(0.68, -0.6, 0.32, 1.6) 0s 1 normal both;
}

.var_Nar_Go2 {
    animation: myAnim 1.4s cubic-bezier(0.68, -0.6, 0.32, 1.6) 0s 1 normal both;
}

@keyframes VNG {
    0% {
        opacity: 0;
        transform: translateX(-250px) rotate(-200deg);
    }

    100% {
        opacity: 1;
        transform: translateX(0) rotate(0deg);
    }
}

.hor_wid_show {
    animation: JustOpacity 1.4s ease-in-out;
}

.hor_wid_show2 {
    animation: JustOpacity 1s ease-in-out;
}

@keyframes JustOpacity {
    0% {
        opacity: 0;
        transform: translateY(-300px);
    }

    50% {
        opacity: 0.1;
        transform: translateY(-200px);
    }

    75% {
        opacity: 0.3;
        transform: translateY(-50px) scale(1.3);
    }

    100% {
        opacity: 1;
        transform: scale(1);

    }
}

@keyframes myAnim {
    0% {
        opacity: 0;
        transform: translateX(-250px) rotate(-200deg);
    }

    100% {
        opacity: 1;
        transform: translateX(0) rotate(0deg);
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

        phone(value) {
            if (value?.length > 9 && /[0-9-]+/.test(value)) return true

            return 'Phone number needs to be at least 9 digits.'
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
const phone = useField('phone')
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
            phone_no: values.phone
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