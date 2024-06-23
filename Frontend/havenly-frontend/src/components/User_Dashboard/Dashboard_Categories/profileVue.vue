<template>

    <div class="user-profile">
        <div class="row justify-content-lg-center">

            <div class="col-md-10 p-2">
                <div class="card my-lg-5">
                    <div class="card-body">
                        <div class="p-lg-5 row">
                            <div class="col-4 col-lg-3">
                                <div class="d-flex flex-column justify-content-center">
                                    <v-img :src="profileImage || profilePicture" class="profile-img" alt="Profile Picture"
                                        padding=5px max-height="150" max-width="100%" contain/>
                                    <div class="mt-2 mt-lg-4">
                                        <button class="btn btn-danger bg-redbrick py-2 rounded-pill px-5 d-flex align-center" @click="openEditDialog"
                                            style="width: 100%; justify-content: center;">
                                            <v-icon>mdi-pencil</v-icon>&nbsp;Edit
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-8 col-lg-9">
                                <div class="p-3 mx-auto">
                                    <div :v-if="user_data !== null">
                                        <div class="d-flex gap-lg-3 flex-column flex-md-row">
                                            <v-text-field class="w-100" density="comfortable" clear-icon="mdi-close-circle" rounded="lg"
                                                variant="outlined" v-model="items.name" label="Name" placeholder="User Name"
                                                readonly="true">
                                            </v-text-field>

                                            <v-text-field class="w-100" density="comfortable" clear-icon="mdi-close-circle" rounded="lg"
                                                variant="outlined" v-model="items.email" label="Email" placeholder="Email"
                                                readonly="true">
                                            </v-text-field>
                                        </div>

                                        <div class="d-flex gap-lg-3 flex-column flex-md-row">
                                            <v-text-field class="w-100" density="comfortable" clear-icon="mdi-close-circle" rounded="lg"
                                                variant="outlined" v-model="items.phone" label="Phone" placeholder="Contact No"
                                                readonly="true">
                                            </v-text-field>

                                            <v-text-field class="w-100" density="comfortable" clear-icon="mdi-close-circle" rounded="lg"
                                                variant="outlined" v-model="items.nrc" label="NRC" placeholder="NRC"
                                                readonly="true">
                                            </v-text-field>
                                        </div>

                                        <hr class="mb-4">

                                        <div class="row gap-lg-3 flex-wrap pt-2">
                                            <div class="col-12 d-flex gap-4 gap-lg-2 flex-column flex-lg-row">
                                               <div class="d-flex col-12 col-lg-6 justify-content-between align-items-center">
                                                    <v-icon class="fs-1 me-3 pb-2 text-brick">mdi-gift</v-icon>
                                                    <button class="btn btn-outline-danger py-2 w-100 me-2" @click="packageDialogOpen">
                                                        Purchased package
                                                    </button>
                                               </div>
                                               <div class="d-flex col-12 col-lg-6 justify-content-between align-items-center">
                                                    <v-icon class="fs-1 me-3 pb-2 text-brick">mdi-lock</v-icon>
                                                    <button class="btn btn-outline-danger py-2 w-100 me-2" @click="openDialog">
                                                        Change Password
                                                    </button>
                                               </div>
                                            </div>
                                            <div class="d-flex justify-space-between align-items-center">
                                                <v-dialog v-model="packageDialog" class="create-pop-up">
                                                    <div class="pop-up-subscribe">
                                                        <div class="d-flex justify-space-between">
                                                            <p class="m-0">Subscribed Package</p>
                                                            <div class="d-flex col-3 justify-content-between align-items-center">
                                                                <p class="m-0">
                                                                    <span class="text-brick" :v-if="user_data !== null">
                                                                        {{ items.packageName || '' }}
                                                                    </span>
                                                                </p>
                                                                <button class="close-btn1"
                                                                    @click="packageDialogClose"><v-icon>mdi-close-circle</v-icon>
                                                                </button>
                                                            </div>
                                                        </div>
                                                        
                                                    </div>
                                                </v-dialog>

                                                <v-dialog v-model="resetdialog" class="create-pop-up" persistent>
                                                    <form @submit.prevent="submit" class="form-edit2">
                                                        <v-row cols="12" class="mx-auto mb-3">
                                                            <h3>Change Password</h3>
                                                        </v-row>
                                                        <button class="close-btn"
                                                            @click="closeDialog"><v-icon>mdi-close-circle</v-icon></button>
                                                        <div :v-if="user_data !== null">

                                                            <v-text-field density="comfortable" clear-icon="mdi-close-circle"
                                                                clearable rounded="lg" variant="solo" v-model="items.email"
                                                                :rules="[validateGmail]" label="G-mail"></v-text-field>

                                                            <v-text-field density="comfortable" rounded="lg" variant="solo"
                                                                v-model="change_pw.password"
                                                                :append-icon="visible ? 'mdi-eye' : 'mdi-eye-off'"
                                                                :rules="[validateResetPassword]"
                                                                :type="visible ? 'text' : 'password'" class="input-group--focused"
                                                                hint="At least 8 characters" label="Password" name="input-10-2"
                                                                @click:append="visible = !visible"></v-text-field>

                                                            <v-text-field density="comfortable" rounded="lg" variant="solo"
                                                                v-model="change_pw.new_password"
                                                                :append-icon="visible1 ? 'mdi-eye' : 'mdi-eye-off'"
                                                                :rules="[validateResetConfirmPassword]"
                                                                :type="visible1 ? 'text' : 'password'" class="input-group--focused"
                                                                hint="At least 8 characters" label="New Password" name="input-10-2"
                                                                @click:append="visible1 = !visible1"></v-text-field>
                                                        </div>
                                                        <div class="d-flex justify-content-between align-items-center mt-3">
                                                            <a href="/forgot" class="text-body-2 font-weight-regular">Forgot Password?</a>
                                                            <v-row cols="12" class="">
                                                                <v-btn @click="resetPassword" class="submit ms-auto me-3"
                                                                    type="submit"
                                                                    style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                                                                    Reset
                                                                </v-btn>
                                                            </v-row>
                                                        </div>
                                                    </form>
                                                </v-dialog>
                                            </div>
                                        </div>
                                        
                                    </div>
                                </div>

                                <div class="d-flex justify-start mt-2 ps-3">
                                    <v-dialog v-model="editDialog" class="create-pop-up" persistent>
                                        <form @submit.prevent="update" class="form-edit2">
                                            <v-row cols="12" class="mx-auto mb-3">
                                                <h3>Edit Profile</h3>
                                            </v-row>
                                            <button type="button" class="close-btn" @click="closeEditDialog">
                                                <v-icon>mdi-close-circle</v-icon>
                                            </button>
                                            <div class="profile-pic">

                                                <div class="profile-img-container1 align-center" @click="triggerFileInput">
                                                    <v-img :src="profileImage || profilePicture" class="profile-img1 mb-2"
                                                        alt="Profile Picture" max-height="150" max-width="150" contain />
                                                    <v-icon class="edit-icon">mdi-pencil</v-icon>
                                                    <input type="file" accept="image/png, image/jpeg, image/bmp" ref="fileInput"
                                                        style="display: none;" prepend-icon="mdi-camera"
                                                        @change="handleFileUpload" />
                                                </div>
                                            </div>
                                            <div style="margin-top: 150px;">
                                                <v-text-field density="comfortable" rounded="lg" variant="solo"
                                                    v-model="user.name" :error-messages="nameErrorMessages" label="Name"
                                                    placeholder="Enter your name"></v-text-field>

                                                <v-text-field density="comfortable" rounded="lg" variant="solo"
                                                    v-model="user.phone" :error-messages="phoneErrorMessages"
                                                    label="Phone Number" placeholder="Enter your phone number"></v-text-field>
                                            </div>

                                            <p>
                                                <v-row cols="12" class="w-100 mt-4">
                                                    <v-btn class="submit ms-auto me-3" type="update"
                                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                                                        Update
                                                    </v-btn>
                                                </v-row>
                                            </p>
                                        </form>
                                    </v-dialog>
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
// import router from '@/router';
import Swal from 'sweetalert2';

export default {
    name: 'profileVue',

    data: () => ({

        resetdialog: false,
        packageDialog: false,
        editDialog: false,
        visible: false,
        visible1: false,
        nameErrorMessages: [],
        phoneErrorMessages: [],

        items: [],

        user: {
            name: '',
            gmail: '',
            phone: '',
            profilePicture: null
        },
        profilePicture: require('@/assets/img/img_avatar.png'), // Placeholder image
        selectedFile: null,
        profileImage: null,
        change_pw: {
            username: '',
            password: '',
            new_password: ''
        },

        rules: {
            required: value => !!value || 'Required.',
            min: v => v.length >= 6 || 'Min 6 characters',
            emailMatch: () => (`The email and password you entered don't match`),
        },
    }),

    // created(){
    // const loginUserData = JSON.parse(sessionStorage.getItem('login_user'));
    // if (loginUserData !==null ) { 

    //     this.user.name = loginUserData.name;
    //     this.user.phone = loginUserData.phone;
    //     this.user.profilePicture = loginUserData.profileImg
    // }
    // },

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
            axios.get('http://localhost:8083/getLoginUser', {
                params: {
                    registerId: registerId
                }
            })
                .then(response => {
                    this.items = response.data;
                    const profilepic = response.data.profileImg;
                    if (profilepic !== null) {
                        this.profileImage = profilepic;
                        console.log("Profile image exists!");
                    }
                    this.user.name = response.data.name;
                    this.user.phone = response.data.phone;
                    this.user.gmail = response.data.gmail;
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

        validateForm() {
            this.nameErrorMessages = [];
            this.phoneErrorMessages = [];

            if (!this.user.name) {
                this.nameErrorMessages.push('Name is required');
            }

            if (!this.user.phone) {
                this.phoneErrorMessages.push('Phone number is required');
            } else if (this.user.phone.length < 9) {
                this.phoneErrorMessages.push('Phone number must be at least 9 digits');
            }

            return this.nameErrorMessages.length === 0 && this.phoneErrorMessages.length === 0;
        },

        async update() {
            if (!this.validateForm()) {
                return;
            }
            this.user.email = this.user_data.email;

            function httpErrorHandler(error) {
                if (axios.isAxiosError(error)) {
                    const response = error?.response
                    if (response) {
                        const statusCode = response?.status
                        if (statusCode === 404) { console.log("Email missing") }
                        if (statusCode === 500) { console.log("Phone number missing") }
                        console.log("error : ", response);
                    }
                }
            }

            Swal.fire({
                title: 'Uploading...',
                text: 'Updating your information. Please wait...',
                allowOutsideClick: false,
                allowEscapeKey: false,
                didOpen: () => {
                    Swal.showLoading(); // Show loading spinner
                }
            });

            const formData = new FormData();
            formData.append('name', this.user.name);
            formData.append('phone', this.user.phone);
            formData.append('email', this.user.email);
            if (this.selectedFile !== null) {
                formData.append('profileImg', this.selectedFile);
            } else if (this.profileImage !== null) {
                // Convert base64 string to Blob
                const byteString = atob(this.profileImage.split(',')[1]);
                const mimeString = this.profileImage.split(',')[0].split(':')[1].split(';')[0];
                const ab = new ArrayBuffer(byteString.length);
                const ia = new Uint8Array(ab);
                for (let i = 0; i < byteString.length; i++) {
                    ia[i] = byteString.charCodeAt(i);
                }
                const blob = new Blob([ab], { type: mimeString });
                formData.append('profileImg', blob, 'profile.jpg');
            } else {
                formData.append('profileImg', new Blob([]), 'profile.jpg'); // Ensure profileImg is always set
            }

            try {
                const response = await axios.put("http://localhost:8083/profile/update", formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });

                const status = response.status;
                if (status === 200) {

                    let userData = JSON.parse(sessionStorage.getItem('login_user')) || {};
                    userData.name = this.user.name;
                    userData.phone = this.user.phone;
                    userData.profilePicture = this.profileImage;
                    sessionStorage.setItem('login_user', JSON.stringify(userData));
                    Swal.fire({
                        title: 'Profile Change Success',
                        text: 'Your profile is successfully updated',
                        icon: 'success',
                        customClass: {
                            confirmButton: 'myCustomSuccessButton'
                        },
                        buttonsStyling: false,
                        allowOutsideClick: false,
                        allowEscapeKey: false
                    }).then(() => {
                        window.location.reload();
                    });
                }
            } catch (error) {
                httpErrorHandler(error);
            }
        },

        resetPassword() {

            this.change_pw.username = this.items.email;

            function httpErrorHandler(error) {
                if (axios.isAxiosError(error)) {
                    const response = error?.response
                    if (response) {
                        const statusCode = response?.status
                        if (statusCode === 404 || statusCode === 400) { 
                            Swal.fire({
                                title: 'Invaild Information',
                                text: 'Password Update Unsuccessful! Please try again!',
                                icon: 'error',
                                customClass: {
                                confirmButton: 'myCustomButton'
                                },
                                buttonsStyling: false,
                                allowOutsideClick: false,
                                allowEscapeKey: false
                            });
                        }
                    }
                }
            }

            Swal.fire({
                title: 'Processing...',
                text: 'Updating new password. Please wait...',
                allowOutsideClick: false,
                allowEscapeKey: false,
                didOpen: () => {
                    Swal.showLoading();
                }
            });

            axios.put("http://localhost:8083/pwdUpdate", this.change_pw)
                .then(function (response) {
                    const status = JSON.parse(response.status);
                    if (status == '200') {
                        Swal.fire({
                            title: 'Success!',
                            text: 'Password Updated Successfully',
                            icon: 'success',
                            customClass: {
                                confirmButton: 'myCustomSuccessButton'
                            },
                            buttonsStyling: false,
                            allowOutsideClick: false,
                            allowEscapeKey: false
                        }).then(() => {
                            window.location.reload();
                        });
                    }
                })
                .catch(httpErrorHandler)
            this.user.name = '',
                this.change_pw.username = '',
                this.change_pw.password = '',
                this.change_pw.new_password = ''
        },

        // logout() {
        //     Swal.fire({
        //         title: 'Logout',
        //         text: "Are you sure you want to logout?",
        //         icon: 'warning',
        //         showCancelButton: true,
        //         confirmButtonText: 'Logout',
        //         cancelButtonText: 'Cancel',
        //         confirmButtonColor: '#E86F52',
        //         cancelButtonColor: '#999'
        //     }).then((result) => {
        //         if (result.isConfirmed) {
        //             sessionStorage.clear();
        // router.push("/").then(() => {
        //   // Reload the page after navigation
        //   window.location.reload();
        // });
        //         } else if (result.dismiss === Swal.DismissReason.cancel) {
        //             this.$router.push('/userdashboard');
        //         }
        //         else {
        //             this.$router.push('/userdashboard'); // Redirect to dashboard after timeout
        //         }
        //     });
        // }

    }
}

</script>

<style>
.custom-button {
    display: inline-block;
    padding: 10px 20px;
    background-color: #E86F52;
    color: #fff;
    border-radius: 20px;
    width: 100%;
    height: 100%;
    cursor: pointer;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.1);
    /* Custom shadow */
    transition: box-shadow 0.3s ease-in-out;
}

.custom-button:hover {
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.3);
    /* Slightly larger shadow on hover */
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

.profile-pic {
    padding: 8px;
    padding-bottom: 10px;
    justify-content: center;
    align-items: center;
    border-radius: 10px;

    .profile-img1 {
        width: 130px;
        height: 130px;
        /* border-radius: 50%; */
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
        position: absolute;
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

    f .close-btn1 {

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
    width: 100%;
    padding: 1% 10%;
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
                object-fit: cover;
            }

            .profile-img-container {
                padding-top: 10px;
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


@media (max-width: 991px) {
    .user-profile {
        padding: 1%;
    }
}
</style>