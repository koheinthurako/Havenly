<template>
  <div class="d-flex justify-center" style="height: 100vh">
    <v-container>

      <h3 class="flex" style="height: 50px">Create Ads</h3>
      <br>
      <!-- Form to create a new ad post -->
      <div class="d-flex mb-5">
      <span class="d-flex align-center" @click="openAdsDialog" 
      style="width: 8%; justify-content: center; border: 1px dashed; border-radius: 10%; border-color:#ccc; padding:20px">
              <v-icon class="edit-icon elevation-5">mdi-plus</v-icon>
            </span>
      <span class="d-flex align-center" style="width: 90%; justify-content: right;">
        <h5>Available Ads : {{ availAds }}</h5></span>
        <!-- upload ads dialog start -->
        <v-dialog v-model="adsDialog" class="create-pop-up" persistent>
        <form @submit.prevent="submitAdPost" class="form-edit2">
          <v-row cols="12" class="mx-auto mb-3">
              <h3>Create an Ad</h3>
            </v-row>
          <button class="close-btn" @click="closeAdsDialog">
              <v-icon>mdi-close-circle</v-icon>
            </button>
          <v-text-field
            v-model="title"
            label="Title"
            required
            outlined
          ></v-text-field>
          
          <div class="img-container1" @click="triggerFileInput">
            <v-img
            v-if="imagePreview"
              :src="imagePreview"
              class="img1"
              alt="Ads Picture"
              max-height="300"
              max-width="400"
              contain
            />
            <v-icon v-else class="edit-icon">mdi-plus</v-icon>
            <input
              type="file"
              accept="image/png, image/jpeg, image/bmp"
              ref="fileInput"
              style="display: none;"
              :rules="rules"
              @change="handleFileUpload"
            />
          </div>
          <p>
          <v-row cols="12" class="w-100 mt-4">
              <v-btn class="submit ms-auto me-3"  type="submitAdPost">
                Submit
              </v-btn>
            </v-row></p>
        </form>
      </v-dialog>
      <!-- dialog end -->
    </div>
      <!-- Display ad posts -->
      <div style="max-height: calc(100vh - 150px); overflow-y: auto;">

      <v-row>
        <v-col
          v-for="ad in ads"
          :key="ad.id"
          cols="12"
          md="4"
          class="d-flex"
        >
      <v-card class="d-flex pa-2 w-100">
        <v-img :src="ad.imageUrl || ''" height="250px" width="400px" contain>
          <v-container
            class="fill-height d-flex align-start"
            style="background: rgba(0, 0, 0, 0.5); color: white; cursor:pointer"
          >
            <h4>{{ ad.title }}</h4>
          </v-container>
        </v-img>
        <span class="ad-status">{{ad.status}}</span>
        <v-btn 
          icon 
          class="delete-button" 
          @click="deleteAdPost(ad.ads_post_id)"
        >
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </v-card>
      </v-col>
      </v-row>
    </div>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted} from 'vue';
import router from '@/router';
import Swal from 'sweetalert2';

export default {
  name: 'create_ads',

  data(){
    return{
      adsDialog: false,
      availAds:'',
      // ads:[],
      user:{
        subUserId:'',
        registerId:''
      },
      rules: [
          value => { 
      return !value || !value.length || value[0].size < 5000000 || 'Avatar size should be less than 5 MB!'
          },
],
    }
  },
  methods: {
    openAdsDialog() {
            this.adsDialog = true;
        },
    closeAdsDialog() {
            this.adsDialog = false;
        },
    fetchSubUserInfo() {
                const loginUserData = JSON.parse(sessionStorage.getItem('login_user'));
                const registerId = loginUserData.register_id;
                console.log("registerId to send backend to show subUser informations from rent post : " + registerId)
                axios.get('http://localhost:8083/subscribe/getSubUserInfo', {
                    params: {
                        registerId: registerId
                    }
                })
                .then(response => {
                    console.log(response.data.availAds);
                    this.availAds = response.data.availAds;
                   
                })
                .catch(error => {
                console.error('Error fetching data:', error); // Handle the error
                });
        },

  },
  mounted(){
    this.fetchSubUserInfo();
  },

  setup() {
    const title = ref('');
    const image = ref(null);
    const imagePreview = ref(null);
    const ads = ref([]);
    // const subUserId = ref('');
    // const availAds = ref('');

    const handleFileUpload = (event) => {
      const file = event.target.files[0];
      if (file) {
        image.value = file;
        const reader = new FileReader();
        reader.onload = (e) => {
        imagePreview.value = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    };

    const triggerFileInput = () => {
      document.querySelector('input[type="file"]').click();
    };

    const subUser = JSON.parse(sessionStorage.getItem('sub_user'));
    const subUserId = subUser.subUserId;
    const availAds = subUser.availAds;
    const submitAdPost = async () => {
      if (!title.value || !image.value) {
        alert('Please provide both title and image.');
        return;
      }
      function httpErrorHandler(error) {
                        if (axios.isAxiosError(error)) {
                            const response = error?.response
                            if(response){
                                const statusCode = response?.status
                                if(statusCode===404){console.log("image not found!")}
                                if(statusCode===500){console.log("user not found!")}
                                console.log("error : ", response);
                            }
                            }
                    }
      const formData = new FormData();
      formData.append('image', image.value);
      formData.append('subUserId', subUserId);
      formData.append('title', title.value);

      if(availAds === 0) {
            console.log("no avail ads");
                        Swal.fire({
                            title: 'Buy More Packages!',
                            text: 'Your available ads is 0.',
                            icon: 'info',
                            customClass: {
                            confirmButton: 'myCustomButton'
                            },
                            buttonsStyling: false,
                            allowOutsideClick: false,
                            allowEscapeKey: false
                            }).then(() => {
                                router.push('/package');
                        });
                    }
                  else{
            Swal.fire({
            title: 'Posting...',
            text: 'Your ads is being submitted. Please wait...',
            allowOutsideClick: false,
            allowEscapeKey: false,
            didOpen: () => {
            Swal.showLoading(); // Show loading spinner
            }
        }); 
             
      try {
        const response = await axios.post('http://localhost:8083/ads/create', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        
      if(response.status === 200) {
                    Swal.fire({
                        title: 'Successfully Posted',
                        text: 'Your ad is requested to admin now!',
                        icon: 'success',
                        customClass: {
                        confirmButton: 'myCustomSuccessButton'
                        },
                        buttonsStyling: false,
                        allowOutsideClick: false,
                        allowEscapeKey: false
                    })
                    
                    .then(() => {
                      title.value = '';
                      image.value = null;
                      imagePreview.value = null;
                      fetchAds();
                    });
            } 
      } catch (error) {
        httpErrorHandler(error);
        Swal.fire({
                title: 'Error',
                text: 'There was an error submitting your ad. Please try again.',
                icon: 'error',
                customClass: {
                confirmButton: 'myCustomErrorButton'
                },
                buttonsStyling: false,
                allowOutsideClick: false,
                allowEscapeKey: false
            });
      }
    }
    };

  const deleteAdPost = (adId) => {
    Swal.fire({
                title: 'Delete Ad',
                text: "Are you sure you want to delete your Ad?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Delete',
                cancelButtonText: 'Cancel',
                confirmButtonColor: '#E86F52',
                cancelButtonColor: '#999'
            }).then((result) => {
                if (result.isConfirmed) {
        try {
      axios.delete('http://localhost:8083/ads/delete', {
      params: {
        subUserId: subUserId,
        ads_post_id: adId
      }
    });
                    Swal.fire({
                        title: 'Successfully Deleted',
                        text: 'Your ad is deleted!',
                        icon: 'success',
                        customClass: {
                        confirmButton: 'myCustomSuccessButton'
                        },
                        buttonsStyling: false,
                        allowOutsideClick: false,
                        allowEscapeKey: false
                    })
                    
                    .then(() => {
                      fetchAds();
                    });
    // Fetch the updated list of ads after deletion
  } catch (error) {
    console.error('Error deleting ad post:', error);
  }
                } 
                // else if (result.dismiss === Swal.DismissReason.cancel) {
                //     this.$router.push('/userdashboard');
                // }
              });
};
    const fetchAds = () => {
      
      axios.get(`http://localhost:8083/ads/all/${subUserId}`
    
    )
        .then(response => {
          if (response.data !== null) {
            ads.value = response.data.reverse();            
            console.log("Ads", ads.value);
          }
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
    };
    // const fetchSubUserInfo = () => {
    //   const loginUserData = JSON.parse(sessionStorage.getItem('login_user'));
    //   // subUserId.value = subUserData.subUserId;
    //   // availAds.value = subUserData.availAds;
    //   const registerId = loginUserData.registerId;
    //    console.log(registerId)
    //   axios.get('http://localhost:8083/getSubUserInfo', {
    //     params: {
    //       registerId: registerId
    //     }
    //   })
    //     .then(response => {
    //       if (response.data !== null) {
    //         availAds.value = response.data.availAds;
    //         console.log("Available Ads :", availAds.value);
    //         subUserId.value = response.data.subUserId;
    //       }
    //       else {
    //         console.log("no avail ads");
    //                      router.push('/package');
                      
    //                 }
    //     })
    //     .catch(error => {
    //       console.error('Error fetching data:', error);
    //     });
    // };

    
    onMounted(() => {
      // fetchSubUserInfo();
      fetchAds();
    });
    return {
      title,
      image,
      imagePreview,
      ads,
      // subUserId,
      // availAds,
      submitAdPost,
      deleteAdPost,
      handleFileUpload,
      triggerFileInput
    };
  }
};
</script>

<style scoped>
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
.form-edit2 {
    margin: 0px auto;
    overflow: hidden;
    width: 800px;
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

    .submit{
      color: #fff; 
      padding: 4px 14px; 
      background-color: #E97559; 
      border-radius: 20px; 
      cursor: pointer;
      height:40px;
      width: auto;
    }
    .clear {
        border-radius: 20px;
    }
}
.img-container1 {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 250px;
  border: 1px dashed #ccc;
  cursor: pointer;
}
.edit-icon {
  background-color: #E86F52;
  color: #fff;
  padding: 10px;
  font-size: 48px;
  border-radius: 50%;
  cursor: pointer;
}
.img1 {
  width: 100%;
  height: 100%;
}
.delete-button {
  position: absolute;
  bottom: 20px;
  right: 20px;
  background-color: #E86F52;
  color: white;
  padding: 10px;
  border-radius: 50%;
}
.ad-status {
  position: absolute;
  bottom: 20px;
  left: 20px;
  background-color: #E86F52;
  color: white;
  padding: 10px;
  border-radius: 10%;
}

</style>
