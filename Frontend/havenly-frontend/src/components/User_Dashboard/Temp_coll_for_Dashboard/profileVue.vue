<template>
    <div class="user-profile">
        <div class="row p-0 m-0">
            <div class="col-md-6 col-sm-12 p-2">
                <div class="profile-box h-auto">
                    <div class="profile-box-data pb-5">
                        <v-img :src="acc_img" class="profile-img" />
                        <form ref="form" fast-fail @submit.prevent="update">
                            <div class="mt-3 p-3 mx-auto">
                                <div :v-if="user_data !== null">

                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable
                                        rounded="lg" variant="solo" v-model="get_username"
                                        :value="user_data?.name || ''" placeholder="User Name"></v-text-field>

                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable
                                        rounded="lg" variant="solo" v-model="gmail" :value="user_data?.gmail || ''"
                                        placeholder="Gmail" readonly="true"></v-text-field>

                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable
                                        rounded="lg" variant="solo" v-model="phone" :value="user_data?.phone || ''"
                                        placeholder="Contact No"></v-text-field>
                                    <!-- <input type="text" :value="user_data?.name || ''" label="User name">
                                    <input type="email" :value="user_data?.gmail || ''" label="E-mail">
                                    <input type="phone" :value="user_data?.phone || ''" label="phone"> -->

                                    <v-text-field v-model="user.name" label="Name"></v-text-field>
                                    <v-text-field v-model="user.phone" :rules="[value => value.length<12 || 'Ph no. must be 11 numbers']" label="Phone"  ></v-text-field>
                                    <v-text-field v-model="user.email" label="Email of this account" :rules="[value => !!value || 'Required']" ></v-text-field>
                                </div>


                                <v-row class="w-100">
                                    <v-btn elevation="10" class="submit mx-auto mt-2" type="submit"
                                        style="text-transform:capitalize;">
                                        Update
                                    </v-btn>
                                </v-row>
                            </div>
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
                                        Ediable<i class="fa-solid fa-check ms-1"></i>
                                    </p>
                                </div>
                                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Subscribed package</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span @click="packageDialogOpen" class="d-flex align-center">
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

                                    <!-- package dialog start -->
                                    <v-dialog v-model="packageDialog" class="create-pop-up" persistent>
                                        <div class="pop-up-subscribe">
                                            <div class="d-flex justify-space-between">
                                                <p>Subscribed Package</p>
                                                <p><span :v-if="user_data !== null">
                                                        {{ user_data?.subscribe_package || '' }}
                                                    </span></p>
                                            </div>
                                            <v-btn @click="packageDialogClose">Close</v-btn>
                                        </div>
                                    </v-dialog>
                                    <!-- package dialog end -->

                                    <!-- Dialog start -->
                                    <v-dialog v-model="resetdialog" class="create-pop-up" persistent>
                                        <form @submit.prevent="submit" class="form-edit2">
                                            <v-row cols="12" class="mx-auto mb-3">
                                                <h3>Change Password</h3>
                                            </v-row>
                                            <button class="close-btn"
                                                @click="closeDialog"><v-icon>mdi-close-circle</v-icon></button>
                                            <div :v-if="user_data !== null">

                                                <v-text-field density="comfortable" clear-icon="mdi-close-circle"
                                                    clearable rounded="lg" variant="solo" v-model="email.value.value"
                                                    :value="user_data?.gmail || ''" placeholder="G-mail"
                                                    readonly="true"></v-text-field>

                                                <v-text-field density="comfortable" rounded="lg" variant="solo"
                                                    v-model="profile_password.value.value"
                                                    :error-messages="profile_password.errorMessage.value"
                                                    :append-icon="visible ? 'mdi-eye' : 'mdi-eye-off'"
                                                    :rules="[rules.required, rules.min]"
                                                    :type="visible ? 'text' : 'password'" :value="user_data?.pass || ''"
                                                    class="input-group--focused" hint="At least 8 characters"
                                                    label="Password" name="input-10-2"
                                                    @click:append="visible = !visible"></v-text-field>

                                                <v-text-field density="comfortable" rounded="lg" variant="solo"
                                                    v-model="profile_confirm_password.value.value"
                                                    :error-messages="profile_confirm_password.errorMessage.value"
                                                    :append-icon="visible1 ? 'mdi-eye' : 'mdi-eye-off'"
                                                    :rules="[rules.required, rules.min]"
                                                    :type="visible1 ? 'text' : 'password'" class="input-group--focused"
                                                    hint="At least 8 characters" label="Password" name="input-10-2"
                                                    @click:append="visible1 = !visible1"></v-text-field>

                                            </div>
                                            <v-row cols="12" class="w-100 mt-4">
                                                <div v-if="show">
                                                    <span>{{ register_info.id }}</span>
                                                </div>
                                                <v-btn elevation="10" @click="handleSubmit" class="submit ms-auto me-3"
                                                    type="submit">
                                                    submit
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
import axios from 'axios'

export default {
    name: 'profileVue',


    data: () => ({

        img: require('@/assets/img/9.jpg'),
        acc_img: require('@/assets/img/img_avatar.png'),
        resetdialog: false,
        packageDialog: false,
        visible: false,
        visible1: false,


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

        packageDialogOpen() {
            this.packageDialog = true;
        },

        packageDialogClose() {
            this.packageDialog = false;
        },

        openDialog() {
            this.resetdialog = true;
        },

        closeDialog() {
            this.resetdialog = false;
        },

        update(){

            function httpErrorHandler(error) {
                        if (axios.isAxiosError(error)) {
                            const response = error?.response
                            if(response){
                                const statusCode = response?.status
                                if(statusCode===404){alert("Upadte Information failed!!!   Please check your E-mail and fill again!!")}
                                if(statusCode===500){alert("Upadte Information failed!!!   Please check your Phone number and fill again!!")}
                            }
                            }
                    }



            axios.put("http://localhost:8083/profile/update",this.user)
            .then(function(response){
                const status=JSON.parse(response.status);
                if(status=='200'){
                  alert("updated Successfully")
                }
            })
            .catch(httpErrorHandler)
                 
        },

        change(){
            function httpErrorHandler(error) {
                        if (axios.isAxiosError(error)) {
                            const response = error?.response
                            if(response){
                                const statusCode = response?.status
                                if(statusCode===404 || statusCode===400){alert("Password Update Unsuccessful!! Please fill your G-mail and Password again!!!")}
                            }
                            }
                    }



            axios.put("http://localhost:8083/pwdUpdate",this.change_pw)
            .then(function(response){
                const status=JSON.parse(response.status);
                if(status=='200'){
                  alert(" Password Updated Successfully")
                }
            })
            .catch(httpErrorHandler)
            this.user.name='',
                  this.change_pw.username='',
                  this.change_pw.password='',
                  this.change_pw.new_password=''
        }

    }
}

</script>

<script setup>

import { useField, useForm } from 'vee-validate'
// import Swal from 'sweetalert2';
import store from '../../../store/index.js';

let show = true
var register_info = store.getters.Take_Userinfo

const { handleSubmit } = useForm({
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

const profile_password = useField('password')
const profile_confirm_password = useField('confirm_password')
const email = useField('email')

const submit = handleSubmit(values => {
    if (values.profile_password == values.profile_confirm_password) {
        console.log("Reached");
    }
});
</script>

<style>
.pop-up-subscribe {

    width: 500px;
    height: auto;
    padding: 40px 60px;
    border-radius: 10px;
    background-color: #fff;
    margin: auto;
}

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
    width: 70%;
    height: 100%;
    margin: auto;

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
                width: 130px;
                height: 130px;
                border-radius: 50%;
                margin: left;
                margin-left: 10px;
            }



            .form-control {
                width: 100%;
                height: auto;
                background-color: transparent;
                border: none;


                

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
