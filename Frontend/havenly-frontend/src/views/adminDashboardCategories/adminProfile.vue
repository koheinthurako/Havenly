<template>
    <div class="admin-profile">
        <div class="col-md-6 col-sm-12 p-2 mx-auto">
            <div class="profile-box h-auto">
                <div class="profile-box-data pb-5">
                    <v-img :src="acc_img" class="profile-img mx-auto m-4" />
                    <form ref="form" fast-fail @submit.prevent="update" class="mx-auto m-2">
                        <v-text-field disabled class="mt-2 m-2" variant="solo" density="comfortable"
                            clear-icon="mdi-close-circle" clearable rounded="lg" v-model="email"
                            label="Email of this account" :rules="[validateGmail]"></v-text-field>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
// import axios from 'axios';
// import Swal from 'sweetalert2';

export default {

    data: () => ({
        acc_img: require('@/assets/img/img_avatar.png'),

        getAdminData: [],
        email:'',
        profileImage: null
    }),

    methods: {
        // User name validation
        validateName(value) {
            if (!value) {
                return 'Required';
            } else if (value.length < 5) {
                return 'Name must be at least 5 characters long';
            }
            return true;
        },

//     handleFileUpload(event) {
//       const file = event.target.files[0];
//       if (file) {
//         this.selectedFile = file;
//         const reader = new FileReader();
//         reader.onload = (e) => {
//           this.profileImage = e.target.result;
//         };
//         reader.readAsDataURL(file);
        
//       }
//     },
//     triggerFileInput() {
//       this.$refs.fileInput.click();
//     },

//     async update(){
    
//             function httpErrorHandler(error) {
//                         if (axios.isAxiosError(error)) {
//                             const response = error?.response
//                             if(response){
//                                 const statusCode = response?.status
//                                 if(statusCode===404){console.log("Email missing")}
//                                 if(statusCode===500){console.log(error)}
//                                 console.log("error : ", response);
//                             }
//                             }
//                     }

//     const formData = new FormData();
      
//       formData.append('email', this.getAdminData.email);
//       if (this.selectedFile !== null) {
//         formData.append('profileImg', this.selectedFile);
//       } else if (this.profileImage !== null) {
//       // Convert base64 string to Blob
//       const byteString = atob(this.profileImage.split(',')[1]);
//       const mimeString = this.profileImage.split(',')[0].split(':')[1].split(';')[0];
//       const ab = new ArrayBuffer(byteString.length);
//       const ia = new Uint8Array(ab);
//       for (let i = 0; i < byteString.length; i++) {
//         ia[i] = byteString.charCodeAt(i);
//       }
//       const blob = new Blob([ab], { type: mimeString });
//       formData.append('profileImg', blob, 'profile.jpg');
//     } else {
//       formData.append('profileImg', new Blob([]), 'profile.jpg'); // Ensure profileImg is always set
//     }
               
//       try {
//     const response = await axios.put("http://localhost:8083/admin/profile/update", formData, {
//       headers: {
//         'Content-Type': 'multipart/form-data'
//       }
//     });

//     const status = response.status;
//     if (status === 200) {
       
//       let userData = JSON.parse(sessionStorage.getItem('admin_user')) || {};
//       userData.email = this.getAdminData.email;
//       sessionStorage.setItem('admin_user', JSON.stringify(userData));
//         Swal.fire({
//                       title: 'Profile Change Success',
//                       text: 'Your profile is successfully updated',
//                       icon: 'success',
//                       customClass: {
//                       confirmButton: 'myCustomSuccessButton'
//                       },
//                       buttonsStyling: false,
//                       allowOutsideClick: false,
//                       allowEscapeKey: false
//                       }).then(() => {
//                         window.location.reload();
//                       });
//     }
//   } catch (error) {
//     httpErrorHandler(error);
//   }
//         },
    },

    mounted() {
        const adminData = JSON.parse(sessionStorage.getItem('admin_user'));
        this.getAdminData = adminData;
        this.email = adminData.email;
        // this.profileImage = adminData.imgUrl;
    }


}

</script>


<style lang="scss">
.admin-profile {
    width: 70%;
    height: 100%;
    margin: auto;

    .profile-box {
        padding: 8px;
        justify-content: center;
        align-items: center;
        border-radius: 10px;
        margin: 20px;
        box-shadow: 0px 6px 20px 2px rgba(0, 0, 0, 0.4);

        background-color: #FEFCFF;

        .profile-box-data {

            .v-img {
                border-radius: 6px;
            }

            .profile-img {
                padding-top:8px;
                width: 130px;
                height: 130px;
                border-radius: 50%;
                margin:left;
                margin-left:10px;
                
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
            .profile-img-container {
                position: relative;
                display: inline-block;
                margin: 20px;
                cursor: pointer;
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