<template>
    <br><br><br><br><br>
    <div class="user-profile">
        <div class="row p-0 m-0">
            <div class="col-md-6 col-sm-12 p-2">
                <div class="profile-box h-auto">
                    <div class="profile-box-data pt-2 pb-5">
                        <div class="profile-img-container">
          <v-img
            :src="profileImage || acc_img"
            class="profile-img"
            alt="Profile Picture"
            max-height="150"
            max-width="150"
            contain
          />
    
        </div>
        <div class="mt-3 p-3 mx-auto">
                                <div :v-if="user_data !== null">
                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" 
                                        rounded="lg" variant="plain" v-model="items.name" label="Name"
                                        placeholder="User Name" readonly="true"></v-text-field>

                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" 
                                        rounded="lg" variant="plain" v-model="items.email" label="Email" 
                                        placeholder="Email" readonly="true"></v-text-field>

                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" 
                                        rounded="lg" variant="plain" v-model="items.phone" label="Phone" 
                                        placeholder="Contact No" readonly="true"></v-text-field>

                                    <v-text-field density="comfortable" clear-icon="mdi-close-circle" 
                                        rounded="lg" variant="plain" v-model="items.nrc" label="NRC" 
                                        placeholder="NRC" readonly="true"></v-text-field>
                                </div></div>
                       
                   
        <!-- Edit or logout -->
        <div class="d-flex justify-center mt-2">
          <p
            style="width: 90px; height:40px"
            class="mr-2 me-3">
            <span class="custom-button d-flex align-center" @click="openEditDialog" style="width: 100%; justify-content: center;">
              <v-icon>mdi-pencil</v-icon>&nbsp;Edit
            </span>
          </p>
          <p
            style="width: 90px; height:40px"
            class="ml-2">
            <span class="custom-button d-flex align-center" @click="logout" style="width: 100%; justify-content: center;">
              Logout
            </span>
          </p>
        
        <!-- Edit Dialog Start -->
        <v-dialog v-model="editDialog" class="create-pop-up" persistent>
          <form @submit.prevent="update" class="form-edit2">
            <v-row cols="12" class="mx-auto mb-3">
              <h3>Edit Profile</h3>
            </v-row>
            <button type="button" class="close-btn" @click="closeEditDialog">
              <v-icon>mdi-close-circle</v-icon>
            </button>
            <div class="profile-pic">

            <div class="profile-img-container1" @click="triggerFileInput">
          <v-img
            :src="profileImage || profilePicture"
            class="profile-img1"
            alt="Profile Picture"
            max-height="150"
            max-width="150"
            contain
          />
          <v-icon class="edit-icon">mdi-pencil</v-icon>
          <input
                type="file"
                accept="image/png, image/jpeg, image/bmp"
                ref="fileInput"
                style="display: none;"
                prepend-icon="mdi-camera"
                @change="handleFileUpload"
            />
    </div>
    </div>
            <div>
              <v-text-field
                density="comfortable"
                rounded="lg"
                variant="solo"
                v-model="user.name"
                :error-messages="nameErrorMessages"
                label="Name"
                placeholder="Enter your name"
              ></v-text-field>

              <v-text-field
                density="comfortable"
                rounded="lg"
                variant="solo"
                v-model="user.phone"
                :error-messages="phoneErrorMessages"
                label="Phone Number"
                placeholder="Enter your phone number"
              ></v-text-field>
            </div>
        
            <p>
            <v-row cols="12" class="w-100 mt-4">
              <v-btn class="submit ms-auto me-3"  type="update" style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                Update
              </v-btn>
            </v-row>
        </p>
          </form>
        </v-dialog>
        <!-- Edit Dialog End -->
    </div>
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
                                            <p>
                                            <v-row cols="12" class="w-100 mt-4">
                                                <v-btn @click="handleSubmit" class="submit ms-auto me-3" type="submit"
                                                style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                                                    Reset
                                                </v-btn>
                                            </v-row></p>
                                        </form>
                                    </v-dialog>
                                    <!-- Dialog end -->


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
                                    <!-- <p
                                        style="color: #fff; padding: 4px 12px; background-color: #4CAF50; border-radius: 17px;">
                                        Editable<i class="fa-solid fa-check ms-1"></i>
                                    </p> -->
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

                            </div>

                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

</template>

<script>
import axios from 'axios';
import router from '@/router';

import Swal from 'sweetalert2';

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
      
        items: [],

        user:{
            name: '',
            email:'',
            phone:'',
            profilePicture:null
        },
    profilePicture: require('@/assets/img/img_avatar.png'), // Placeholder image
    selectedFile: null,
    profileImage: null,
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
    else{
        this.user.name = loginUserData.name;
        this.user.phone = loginUserData.phone;
    }
    },
    mounted() {
      this.fetchData();
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
    fetchData() {
        const user = JSON.parse(sessionStorage.getItem('login_user'));
        const registerId = user.register_id;
        axios.get('http://localhost:8083/getLoginUser',{ 
            params: {
                          registerId: registerId
                      }
                    }) 
          .then(response => {
            this.items = response.data; 
            console.log("Login name", response.data.name);
            const profilepic = response.data.profileImg;
            if (profilepic !== null) {
          this.profileImage = profilepic;
          console.log(profilepic);
          console.log("Profile image exists!");
        }
          })
          .catch(error => {
            console.error('Error fetching data:', error);
          });
    },
handleFileUpload(event) {
      const file = event.target.files[0];
      if (file) {
        this.selectedFile = file;
        const reader = new FileReader();
        reader.onload = (e) => {
          this.profileImage = e.target.result;
        };
        reader.readAsDataURL(file);
        
      }
    },
    triggerFileInput() {
      this.$refs.fileInput.click();
    },
    async update(){
            this.user.email=this.user_data.email;
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

    const formData = new FormData();
      formData.append('name', this.user.name);
      formData.append('phone', this.user.phone);
      formData.append('email', this.user.email);
      if (this.selectedFile) {
        formData.append('profileImg', this.selectedFile);
      }else{
        formData.append('profileImg', this.profileImage);
      }
               
      try {
    const response = await axios.put("http://localhost:8083/profile/update", formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });

    const status = response.status;
    if (status === 200) {
      alert("Profile updated successfully!");

      let userData = JSON.parse(sessionStorage.getItem('login_user')) || {};
      userData.name = this.user.name;
      userData.phone = this.user.phone;
    //   userData.profilePicture = this.selectedFile;
      sessionStorage.setItem('login_user', JSON.stringify(userData));

      window.location.reload();
    }
  } catch (error) {
    httpErrorHandler(error);
  }
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
                  alert(" Password Updated Successfully!")
                }
            })
            .catch(httpErrorHandler)
            this.user.name='',
                  this.change_pw.username='',
                  this.change_pw.password='',
                  this.change_pw.new_password=''
        },

        logout() {
            Swal.fire({
                title: 'Logout',
                text: "Are you sure you want to logout?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Logout',
                cancelButtonText: 'Cancel',
                confirmButtonColor: '#E86F52',
                cancelButtonColor: '#999'
            }).then((result) => {
                if (result.isConfirmed) {
                    sessionStorage.clear();
        router.push("/").then(() => {
          // Reload the page after navigation
          window.location.reload();
        });
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    this.$router.push('/userdashboard');
                }
                else {
                    this.$router.push('/userdashboard'); // Redirect to dashboard after timeout
                }
            });
        }

    }
}

</script>

<script setup>

import { useField, useForm } from 'vee-validate'
// import Swal from 'sweetalert2';

// let show = true

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
.custom-button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #E86F52;
  color: #fff;
  border-radius: 20px;
  width:100%;
  height:100%;
  cursor: pointer;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.1); /* Custom shadow */
  transition: box-shadow 0.3s ease-in-out;
}

.custom-button:hover {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.3); /* Slightly larger shadow on hover */
}

.create-pop-up {
    width: 100%;
    height: auto;
    z-index: 100;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .main-close {
        position: absolute;
        top: -50px;
        right: 0;
        padding: 0;
        z-index: 300;
        width: 30px !important;
        height: 62px;
        border-radius: 50%;
        background-color: #e86f52;
        color: #fff;
        box-shadow: 0px 6px 20px -2px rgba(0, 0, 0, 0.3);

        .v-icon {
            font-size: 24px;
        }
    }

}

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
