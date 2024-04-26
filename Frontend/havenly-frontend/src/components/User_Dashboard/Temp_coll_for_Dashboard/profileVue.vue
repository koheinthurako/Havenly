<template>
    <div class="user-profile">
        <div class="row p-0 m-0">
            <div class="col-md-6 col-sm-12 p-2">
                <div class="profile-box h-auto">
                    <div class="profile-box-data pb-5">
                        <v-img :src="acc_img" class="profile-img" />
                        <form @submit.prevent="submit">


                            <div class="form-control">
                                <div :v-if="user_data !== null">
                                    <input type="text" :value="user_data?.name || ''" :counter="10" label="User name">
                                    <input type="email" :value="user_data?.gmail || ''" label="E-mail">
                                    <input type="phone" :value="user_data?.phone || ''" label="phone">

                                </div>
                            </div>


                            <v-row class="w-100">

                                <v-btn elevation="10" class="submit mx-auto mt-2" type="submit"
                                    style="text-transform:capitalize;">
                                    Save chane
                                </v-btn>


                            </v-row>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-md-6 col-sm-12 p-0 ">

                <div class="row-12 p-2">
                    <div class="profile-box">
                        <div class="profile-box-data">
                            <div class="p-3">
                                <div class="d-flex w-100 p-0 justify-space-between">
                                    <h5>Account info status</h5>
                                    <p
                                        style="color: #fff; padding: 4px 12px; background-color: #4CAF50; border-radius: 17px;">
                                        Ediable<i class="fa-solid fa-check ms-1"></i></p>
                                </div>
                                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Subscribed package</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-store</v-icon>&nbsp;Check
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Reset password</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center" @click="openDialog">
                                            <v-icon>mdi-lock</v-icon>&nbsp;Reset
                                        </span>
                                    </p>

                                    <!-- Dialog start -->
                                    <v-dialog v-model="resetdialog" class="create-pop-up" persistent>


                                        <form @submit.prevent="submit" class="form-edit2">
                                            <v-row cols="12" class="mx-auto mb-3">
                                                <h3>Change Password</h3>
                                            </v-row>
                                            <button class="close-btn"
                                                @click="closeDialog"><v-icon>mdi-close-circle</v-icon></button>

                                            <v-text-field v-model="email.value.value"
                                                :error-messages="email.errorMessage.value"
                                                label="G-mail"></v-text-field>

                                            <v-text-field v-model="password.value.value"
                                                :error-messages="password.errorMessage.value"
                                                class="input-group--focused" hint="At least 6 characters"
                                                label="Current Password" name="input-10-2"
                                                @click:append="visible = !visible"></v-text-field>

                                            <v-text-field v-model="confirm_password.value.value"
                                                :error-messages="confirm_password.errorMessage.value"
                                                class="input-group--focused" hint="At least 6 characters"
                                                label="New password" name="input-10-2"
                                                @click:append="visible1 = !visible1"></v-text-field>

                                            <v-row cols="12" class="w-100 mt-4">

                                                <v-btn elevation="10" @click="handleSubmit" class="submit ms-auto me-3"
                                                    type="submit">
                                                    submit
                                                </v-btn>

                                                <v-btn elevation="10" class="clear" @click="handleReset">
                                                    clear
                                                </v-btn>
                                            </v-row>
                                        </form>


                                    </v-dialog>
                                    <!-- Dialog end -->


                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Change location</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-map-marker-radius</v-icon>&nbsp;Change
                                        </span>
                                    </p>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="row-12 p-2">
                    <div class="profile-box">
                        <div class="profile-box-data">
                            <div class="p-3">
                                <div class="d-flex w-100 p-0 justify-space-between">
                                    <h5>Account progress status</h5>
                                    <p
                                        style="color: #fff; padding: 4px 12px; background-color: #4CAF50; border-radius: 17px;">
                                        Ediable<i class="fa-solid fa-check ms-1"></i></p>
                                </div>
                                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Deleted pending Posts</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-delete-clock</v-icon>&nbsp;Check
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Update pending Posts</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-update</v-icon>&nbsp;Check
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Request an event</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-calendar-edit</v-icon>&nbsp;Create
                                        </span>


                                    </p>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>

                <div class="row-12 p-2">
                    <div class="profile-box">
                        <div class="profile-box-data">
                            <div class="profile-box-info p-3">
                                <div class="d-flex w-100 p-0 justify-space-between">
                                    <h5>Account function status</h5>
                                    <p
                                        style="color: #fff; padding: 4px 12px; background-color: #4CAF50; border-radius: 17px;">
                                        Ediable<i class="fa-solid fa-check ms-1"></i></p>
                                </div>
                                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Show Bio status</p>
                                    <p>

                                        <span class="d-flex align-center">
                                            <!-- <v-icon>mdi-calendar-edit</v-icon>&nbsp;Create -->
                                            <label class="switch">
                                                <input type="checkbox" checked>
                                                <span class="slider round"></span>
                                            </label>
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Show birth status</p>
                                    <p>

                                        <span class="d-flex align-center">
                                            <!-- <v-icon>mdi-calendar-edit</v-icon>&nbsp;Create -->
                                            <label class="switch">
                                                <input type="checkbox" checked>
                                                <span class="slider round"></span>
                                            </label>
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Show Relationship status</p>
                                    <p>

                                        <span class="d-flex align-center">
                                            <!-- <v-icon>mdi-calendar-edit</v-icon>&nbsp;Create -->
                                            <label class="switch">
                                                <input type="checkbox">
                                                <span class="slider round"></span>
                                            </label>
                                        </span>
                                    </p>
                                </div>

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
    name: 'profileVue',


    data: () => ({

        img: require('@/assets/img/9.jpg'),
        acc_img: require('@/assets/img/img_avatar.png'),
        resetdialog: false,

        rules: {
            required: value => !!value || 'Required.',
            min: v => v.length >= 6 || 'Min 6 characters',
            emailMatch: () => (`The email and password you entered don't match`),
        },
    }),

    computed: {
        user_data() {
            if (this.$store.getters.get_LoginedId) {
                return this.$store.getters.Take_Userinfo;
            } else {
                return null;
            }
        }
    },

    methods: {

        openDialog() {
            this.resetdialog = true;
        },

        closeDialog() {
            this.resetdialog = false;
        }

    }
}

</script>

<script setup>

import { useField, useForm } from 'vee-validate'


const { handleSubmit, handleReset } = useForm({
    validationSchema: {

        password(value) {
            if (value?.length >= 6) {

                return true;
            } else {
                return 'Cannot be empty!'
            }
        },

        confirm_password(value) {
            if (value?.length >= 6) {
                return true;
            } else {
                return 'Cannot be empty!'
            }
        },
        email(value) {
            if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

            return 'Must be a valid e-mail.'
        },

    },
})

const password = useField('password')
const confirm_password = useField('confirm_password')
const email = useField('email')

function showAlert(data) {
    alert(
        data
    )
}

const submit = handleSubmit(values => {
    if (values.password !== values.confirm_password) {
        showAlert("Password didn't match!");
    } else {
        showAlert("Correct Password!");
    }
});
</script>

<style>
.form-edit2 {
    margin: 0px auto;
    overflow: hidden;
    width: 500px;
    height: auto;
    padding: 40px 60px;
    border-radius: 10px;
    background-color: #fff;
    position: relative;
    box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.5);

    .close-btn {
        position: absolute;
        top: 10px;
        right: 10px;
        font-size: 20px;
    }

    .submit,
    .clear {
        border-radius: 20px;
    }
}

.user-profile {
    width: 100%;
    height: 100%;

    .profile-box {
        padding: 8px;
        justify-content: center;
        align-items: center;
        border-radius: 10px;

        box-shadow: 0px 6px 20px 2px rgba(0, 0, 0, 0.4);

        background-color: #FEFCFF;

        .profile-box-data {


            .v-img {
                border-radius: 6px;
            }

            .profile-img {
                width: 200px;
                height: 200px;
                border-radius: 50%;
                margin: auto;
            }

            .form-control {
                width: 100%;
                height: auto;
                padding: 10px 0px;
                display: block;
                background-color: transparent;
                border: none;

                input {
                    width: 100%;
                    background-color: #fff;
                    margin: 10px 0px;
                    font-size: 18px;
                    padding: 10px;
                    border-radius: 10px;
                    border: 1px solid #F6F5F2;
                    box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.4);

                    &:focus {
                        outline: none;
                    }
                }

            }

            .v-btn {
                border-radius: 20px;
                background-color: #E86F52;
                color: #fff;
            }





        }



    }



    .switch {
        position: relative;
        display: inline-block;
        width: 60px;
        height: 34px;
    }

    .switch input {
        opacity: 0;
        width: 0;
        height: 0;
    }

    .slider {
        position: absolute;
        cursor: pointer;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: #ccc;
        -webkit-transition: .4s;
        transition: .4s;
    }

    .slider:before {
        position: absolute;
        content: "";
        height: 26px;
        width: 26px;
        left: 4px;
        bottom: 4px;
        background-color: #fff;
        -webkit-transition: .4s;
        transition: .4s;
        box-shadow: 0px 4px 20px 1px rgba(0, 0, 0, 0.4);
    }

    input:checked+.slider {
        background-color: #E97559;
    }

    input:focus+.slider {
        box-shadow: 0 0 1px #E97559;
    }

    input:checked+.slider:before {
        -webkit-transform: translateX(26px);
        -ms-transform: translateX(26px);
        transform: translateX(26px);
    }

    .slider.round {
        border-radius: 34px;
    }

    .slider.round:before {
        border-radius: 50%;
    }
}
</style>
