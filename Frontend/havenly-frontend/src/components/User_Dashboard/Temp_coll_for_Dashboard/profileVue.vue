<template>
    <br><br><br><br><br>
    <div class="user-profile">
        <div class="row p-0 m-0">
            <div class="col-md-6 col-sm-12 p-2">
                <div class="profile-box h-auto">
                    <div class="profile-box-data pt-2 pb-5">
                        <!-- <v-img :src="acc_img" class="profile-img" /> -->
                        <div class="profile-img-container">
          <v-img
            :src="user_data.profilePicture || acc_img"
            class="profile-img"
            alt="Profile Picture"
            max-height="150"
            max-width="150"
            contain
          />
          <!-- <v-icon class="edit-icon">mdi-pencil</v-icon>
          <input
                                        type="file"
                                        accept="image/*"
                                        ref="fileInput"
                                        style="display: none;"
                                        @change="handleFileUpload"
                                    /> -->
        </div>
        <div class="mt-3 p-3 mx-auto">
                                <div :v-if="user_data !== null">
                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" 
                                        rounded="lg" variant="plain" v-model="user_data.name" label="Name"
                                        placeholder="User Name" readonly="true"></v-text-field>

                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" 
                                        rounded="lg" variant="plain" v-model="user_data.email" label="Email" 
                                        placeholder="Email" readonly="true"></v-text-field>

                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" 
                                        rounded="lg" variant="plain" v-model="user_data.phone" label="Phone" 
                                        placeholder="Contact No" readonly="true"></v-text-field>

                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" 
                                        rounded="lg" variant="plain" v-model="user_data.nrc" label="NRC" 
                                        placeholder="NRC" readonly="true"></v-text-field>
                                </div></div>
                        <!-- <form ref="form" fast-fail @submit.prevent="update">
                            <div class="mt-3 p-3 mx-auto">
                                <div :v-if="user_data !== null">
                                   
                                   

                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable
                                        rounded="lg" variant="solo" v-model="user.name" label="Update Name"></v-text-field>
                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable
                                        rounded="lg" variant="solo" v-model="user.phone" :rules="[value => value.length<12 || 'Ph no. should be less than 12 numbers']" label="Update Phone" required></v-text-field>
                                    <v-text-field v-model="user.email" label="Email of this account" :rules="[value => !!value || 'Required']" ></v-text-field>
                                    <input
                                        type="file"
                                        accept="image/*"
                                        ref="fileInput"
                                        style="display: none;"
                                        @change="handleFileUpload"
                                    />
                                    <br>
                                </div>

                                <v-row class="w-100">
                                    <v-btn elevation="10" class="submit mx-auto mt-2" type="submit"
                                        style="text-transform:capitalize;">
                                        Update
                                    </v-btn>
                                </v-row>
                            </div>
                        </form> -->
                   
        <!-- Profile Information with Single Edit Button -->
        <div class="d-flex justify-center mt-2">
          <p
            style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
            <span class="d-flex align-center" @click="openEditDialog">
              <v-icon>mdi-pencil</v-icon>&nbsp;Edit
            </span>
          </p>
        </div>
        <!-- Edit Dialog Start -->
        <v-dialog v-model="editDialog" class="create-pop-up" persistent>
          <form @submit.prevent="submitForm" class="form-edit2">
            <v-row cols="12" class="mx-auto mb-3">
              <h3>Edit Profile</h3>
            </v-row>
            <button class="close-btn" @click="closeEditDialog">
              <v-icon>mdi-close-circle</v-icon>
            </button>
            <div class="profile-pic">
          
            <div class="profile-img-container1" @click="triggerFileInput">
          <v-img
            :src="profilePicture"
            class="profile-img1"
            alt="Profile Picture"
            max-height="150"
            max-width="150"
            contain
          />
          <v-icon class="edit-icon">mdi-pencil</v-icon>
          <input
                type="file"
                accept="image/*"
                ref="fileInput"
                style="display: none;"
                @change="handleFileUpload"
            />
    </div>
    </div>
            <div>
              <v-text-field
                density="comfortable"
                rounded="lg"
                variant="solo"
                v-model="user_data.name"
                :error-messages="nameErrorMessages"
                label="Name"
                placeholder="Enter your name"
              ></v-text-field>

              <v-text-field
                density="comfortable"
                rounded="lg"
                variant="solo"
                v-model="user_data.phone"
                :error-messages="phoneErrorMessages"
                label="Phone Number"
                placeholder="Enter your phone number"
              ></v-text-field>
            </div>
            <v-row cols="12" class="w-100 mt-4">
              <v-btn elevation="10" class="submit ms-auto me-3" type="update">
                Update
              </v-btn>
            </v-row>
          </form>
        </v-dialog>
        <!-- Edit Dialog End -->
      
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
                                        Editable<i class="fa-solid fa-check ms-1"></i>
                                    </p>
                                </div>
                                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Purchased package</p>
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
                                                        {{ user_data.packageName || '' }}
                                                    </span></p>
                                            </div>
                                            <button class="close-btn1" @click="packageDialogClose"><v-icon>mdi-close-circle</v-icon>
                                            </button>                                        
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
                                                    :value="user_data.email || ''" placeholder="Email"
                                                    readonly="true"></v-text-field>

                                                <v-text-field density="comfortable" rounded="lg" variant="solo"
                                                    v-model="profile_password.value.value"
                                                    :error-messages="profile_password.errorMessage.value"
                                                    :append-icon="visible ? 'mdi-eye' : 'mdi-eye-off'"
                                                    :rules="[rules.required, rules.min]"
                                                    :type="visible ? 'text' : 'password'" :value="user_data.password || ''"
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
                                                    <span>{{ user_data.id }}</span>
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
                                <!-- <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Change location</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-map-marker-radius</v-icon>&nbsp;Change
                                        </span>
                                    </p>
                                </div> -->
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
                                        Editable<i class="fa-solid fa-check ms-1"></i></p>
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
                                <!-- <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Request an event</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-calendar-edit</v-icon>&nbsp;Create
                                        </span>


                                    </p>
                                </div> -->

                            </div>

                        </div>
                    </div>
                </div>

                <!-- <div class="row-12 p-2">
                    <div class="profile-box">
                        <div class="profile-box-data">
                            <div class="profile-box-info p-3">
                                <div class="d-flex w-100 p-0 justify-space-between">
                                    <h5>Account function status</h5>
                                    <p
                                        style="color: #fff; padding: 4px 12px; background-color: #4CAF50; border-radius: 17px;">
                                        Editable<i class="fa-solid fa-check ms-1"></i></p>
                                </div>
                                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Show Bio status</p>
                                    <p>

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-calendar-edit</v-icon>&nbsp;Create
                                            <label class="switch">
                                                <input type="checkbox" checked>
                                                <span class="slider round"></span>
                                            </label>
                                        </span>
                                    </p>
                                </div>
                            
                            </div>

                        </div>
                    </div>
                </div> -->

            </div>
        </div>
    </div>

</template>

<script>
import axios from 'axios';
import router from '@/router';

export default {
    name: 'profileVue',

    data: () => ({

        img: require('@/assets/img/9.jpg'),
        acc_img: require('@/assets/img/img_avatar.png'),
        resetdialog: false,
        packageDialog: false,
        editDialog: false,
        visible: false,
        visible1: false,
      
        user:{
            name: '',
            email:'',
            phone:'',
            profilePicture:null
        },
    profilePicture: require('@/assets/img/img_avatar.png'), // Placeholder image
      selectedFile: null,
        change_pw:{
            username:'',
            password:'',
            new_password:''
        },

        rules: {
            required: value => !!value || 'Required.',
            min: v => v.length >= 6 || 'Min 6 characters',
            emailMatch: () => (`The email and password you entered don't match`),
        },
    }),

    created(){
    const loginUserData = JSON.parse(sessionStorage.getItem('login_user'));
    if (loginUserData ==null ) { 
        router.push("/");
    }
    },
    
    computed: {
        user_data() {
            if (sessionStorage.getItem('login_user') !== null) {
                return JSON.parse(sessionStorage.getItem('login_user'));
            } else {
                return null;
            }
        },
    
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
        openEditDialog() {
    
      this.editDialog = true;
    },
    closeEditDialog() {
      this.editDialog = false;
    },

handleFileUpload(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.profilePicture = e.target.result;
        };
        reader.readAsDataURL(file);
        this.selectedFile = file;
      }
    },
    triggerFileInput() {
      this.$refs.fileInput.click();
    },
        update(){

            function httpErrorHandler(error) {
                        if (axios.isAxiosError(error)) {
                            const response = error?.response
                            if(response){
                                const statusCode = response?.status
                                if(statusCode===404){alert("Update Information failed!!!   Please check your E-mail and fill again!!")}
                                if(statusCode===500){alert("Update Information failed!!!   Please check your Phone number and fill again!!")}
                                console.log("error : ", response);
                            }
                            }
                    }

    //                 const formData = new FormData();
    //   formData.append('name', this.user.name);
    //   formData.append('email', this.user.email);
    //   if (this.selectedFile) {
    //     formData.append('profilePicture', this.selectedFile);
    //   } 
                this.user.email=this.user_data.email;
    if (this.selectedFile) {          
                this.user.profilePicture=this.selectedFile;
    }
            axios.put("http://localhost:8083/profile/update",this.user)
            .then(function(response){
                const status=JSON.parse(response.status);
                if(status=='200'){
                  alert("Profile updated successfully!");
                }
            })
            .catch(httpErrorHandler)  
            let userData = JSON.parse(sessionStorage.getItem('login_user')) || {};
                userData.name = this.user.name;
                userData.phone = this.user.phone;
                sessionStorage.setItem('login_user', JSON.stringify(userData));
        location.reload();
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

let show = true

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
.profile-pic{
    padding: 8px;
    padding-bottom: 10px;
    justify-content: center;
    align-items: center;
    border-radius: 10px;

    .profile-img1 {
                width: 130px;
                height: 130px;
                border-radius: 50%;
                margin: left;
            }
   .edit-icon {
                position: absolute;
                bottom: 10px;
                right: 5px;
                background-color: #E86F52;
                color: #fff;
                border-radius: 50%;
                padding: 5px;
                font-size: 24px;
} 
.profile-img-container1 {
                position: relative;
                display: inline-block;
                cursor: pointer;
            }
}

.pop-up-subscribe {

    width: 500px;
    height: auto;
    padding: 40px 60px;
    border-radius: 10px;
    background-color: #fff;
    margin: auto;

    .close-btn1 {
        
        position: absolute;
        top: 10px;
        right: 10px;
        font-size: 20px;
        
    }
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
    /* box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.5); */

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
    width: 60%;
    height: 90%;
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

            .profile-img-container {
                position: relative;
                display: inline-block;
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
