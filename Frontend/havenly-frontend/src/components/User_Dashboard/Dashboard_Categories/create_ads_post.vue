<template>
  <div class="create-ads-page-edit">
    <v-container>
      <!-- for desktop start -->
      <div class="row">
        <div class="col-md-7 col-sm-12 p-0 m-0" v-if="displayCreateView">

          <!-- TZH Form -->
          <div class="create-ads-post">

            <div class="header mb-3 row">
              <div class=" left-edit  col-sm-12 col-md-6">
                <div class="specific-edit d-flex align-items-center">
                  <v-icon class="me-2">mdi-information</v-icon>
                  <p class="mt-3">Create Ads</p>
                </div>
              </div>
              <div class=" right-edit col-sm-12 col-md-6">
                <div class="specific-edit d-flex align-items-center">
                  <span>Available ads : </span>
                  <h5 class="text-red m-0">&nbsp; {{ availAds }}</h5>
                </div>
                <v-btn rounded class="toggle-btn2 mb-2" @click="toggleDisplay">show posts</v-btn>
              </div>
            </div>

            <div class="body">

              <div>
                <form @submit.prevent="submitAdPost" class="form-edit3 no-select">
                  <v-row cols="12" class="mx-auto mb-3">
                    <h4 class="color-brick">Create an advertisement</h4>
                  </v-row>
                  <!-- <button class="close-btn" @click="closeAdsDialog">
                    <v-icon>mdi-close-circle</v-icon>
                  </button> -->
                  <v-text-field v-model="title" label="Create a title" clearable required
                    variant="outlined"></v-text-field>

                  <div class="img-container1" @click="triggerFileInput">
                    <v-img rounded v-if="imagePreview" :src="imagePreview" class="img1" alt="Ads Picture"
                      max-height="300" max-width="400" cover />
                    <div v-else class="d-flex justify-content-center align-items-center">
                      <v-icon class="edit-icon me-2">mdi-plus</v-icon>
                      <h5 class="mt-1">choose an image</h5>

                    </div>

                    <input type="file" accept="image/png, image/jpeg, image/bmp" ref="fileInput" style="display: none;"
                      :rules="rules" @change="handleFileUpload" />
                  </div>
                  <p>
                    <v-row cols="12" class="w-100 mt-4 d-flex justify-end">
                      <v-btn v-if="imagePreview" @click="clearImage" rounded class="submit2 bg-dark me-3">
                        Clear
                      </v-btn>
                      <v-btn rounded class="submit2 me-3" type="submitAdPost">
                        Submit
                      </v-btn>

                    </v-row>
                  </p>
                </form>
              </div>

              <!-- <span class="d-flex align-center" @click="openAdsDialog"
                style="width: 8%; justify-content: center; border: 1px dashed; border-radius: 10%; border-color:#ccc; padding:20px">
                <v-icon class="edit-icon elevation-5">mdi-plus</v-icon>
              </span> -->

              <!-- upload ads dialog start -->
              <!-- <v-dialog v-model="adsDialog" class="create-pop-up" persistent>
                
              </v-dialog> -->
              <!-- dialog end -->


            </div>

          </div>

        </div>

        <div class="col-md-5 col-sm-12 p-0 m-0" v-if="displayApproveView">
          <!-- Display post section start -->

          <div class="display-ads-post">

            <div class="header mb-3 row">
              <div class=" left-edit col-sm-12 col-md-12">
                <div class="specific-edit d-flex align-items-center justify-content-center">
                  <v-icon class="me-1">mdi-information</v-icon>
                  <p class="mt-3">Approved ads</p>
                </div>
              </div>
              <div class=" right-edit col-sm-12 only-767">
                <div class="specific-edit d-flex align-items-center">
                  <p class="p-0 m-0 me-2">Approved By</p>
                  <h5 class="color-brick m-0">Admin</h5>
                </div>
                <v-btn rounded class="toggle-btn2 mb-2" @click="toggleDisplay2">show posts</v-btn>
              </div>
            </div>

            <div class="body">

              <div v-if="ads && ads.length > 0">
                <!-- display ads start -->
                <v-row>
                  <v-col v-for="ad in ads" :key="ad.id" cols="12" class="d-flex">
                    <v-card class="d-flex  w-100">
                      <v-img :src="ad.imageUrl || ''" cover>
                        <v-container class="fill-height d-flex align-start text-light"
                          style="background: rgba(0, 0, 0, 0.5); cursor:pointer">
                          <h4>{{ ad.title }}</h4>
                        </v-container>

                      </v-img>
                      <!-- <span class="ad-status">{{ ad.status }}</span> -->
                      <span class="px-3 py-2 ad-status badge rounded-pill text-uppercase  d-inline"
                        style="font-size: 0.8rem; padding: 0.25em 0.5em;">{{ ad.status }}</span>


                      <v-btn icon class="delete-button" @click="deleteAdPost(ad.ads_post_id)">
                        <v-icon>mdi-delete</v-icon>
                      </v-btn>
                    </v-card>
                  </v-col>
                </v-row>
                <!-- display ads end -->

              </div>
              <div v-else class="d-flex justify-content-center align-items-center">


                <div style="width: 100%; height: 280px;" class="d-flex justify-content-center align-items-center"
                  v-if="showLoading">
                  <v-progress-circular v-if="showLoading" indeterminate :size="80" color="#e86f52"
                    :width="7"></v-progress-circular>
                </div>


                <v-alert v-if="!showLoading" text="Currently, there is no advertisements available yet!"
                  title="Post status" type="info"></v-alert>

              </div>


            </div>
          </div>

          <!-- Display post section end -->

        </div>
      </div>
      <!-- for Desktop end -->
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import router from '@/router';
import Swal from 'sweetalert2';

export default {
  name: 'create_ads',

  data() {
    return {

      //fade loading 
      showLoading: false,

      //specific view 
      displayCreateView: true,
      displayApproveView: window.innerWidth >= 768,
      progressCircular: false,


      adsDialog: false,
      availAds: '',
      // ads:[],
      user: {
        subUserId: '',
        registerId: ''
      },
      rules: [
        value => {
          return !value || !value.length || value[0].size < 5000000 || 'Avatar size should be less than 5 MB!'
        },
      ],
    }
  },

  beforeUnmount() {
    window.removeEventListener('resize', this.updateView);
  },


  methods: {
    toggleDisplay() {
      this.displayCreateView = false;
      this.displayApproveView = true;
    },

    toggleDisplay2() {
      this.displayCreateView = true;
      this.displayApproveView = false;
    },

    updateView() {
      this.displayCreateView = true;
      this.displayApproveView = window.innerWidth >= 768;
    },


    openAdsDialog() {
      const user = JSON.parse(sessionStorage.getItem('sub_user'));
      const availAds = user.availAds;
      if (availAds <= 0) {
        this.adsDialog = false;
        Swal.fire({
          title: 'No Ads for your package!',
          text: 'Your available ads is 0.',
          icon: 'error',
          customClass: {
            confirmButton: 'myCustomButton'
          },
          buttonsStyling: false
        })
        return;
      }
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
  mounted() {
    this.fetchSubUserInfo();

    // fake loader
    this.showLoading = true;

    window.addEventListener('resize', this.updateView);
  },

  watch: {
    showLoading(newVal) {
      if (newVal) {
        setTimeout(() => {
          this.showLoading = false;
        }, 2000); // 3 seconds
      }
    },
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

    const clearImage = () => {
      image.value = '';
      imagePreview.value = '';
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
          if (response) {
            const statusCode = response?.status
            if (statusCode === 404) { console.log("image not found!") }
            if (statusCode === 500) { console.log("user not found!") }
            console.log("error : ", response);
          }
        }
      }
      const formData = new FormData();
      formData.append('image', image.value);
      formData.append('subUserId', subUserId);
      formData.append('title', title.value);

      if (availAds === 0) {
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
      else {
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

          if (response.status === 200) {
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
                window.location.reload();
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
        cancelButtonColor: '#999',
        reverseButtons: true,
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
      triggerFileInput,
      clearImage
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

/* min if greater then given width it work */
@media only screen and (min-width: 768px) {
  .toggle-btn2 {
    display: none;
  }
}

@media only screen and (max-width: 768px) {
  .create-ads-page-edit {

    .create-ads-post,
    .display-ads-post {
      .header {
        .left-edit {
          .specific-edit {
            border: 1px solid red;
            display: flex;
            justify-content: center;
          }
        }

        .right-edit {
          border: 2px solid red;
          display: flex;
          justify-content: space-between;

          .toggle-btn2 {
            text-transform: capitalize;
          }
        }
      }
    }

    .create-ads-post {
      .header {
        .right-edit {
          .toggle-btn2 {
            background-color: #e86f52;
          }
        }
      }
    }

    .display-ads-post {
      .header {
        .right-edit {
          .toggle-btn2 {
            background-color: #03c126;
          }
        }
      }
    }

  }
}
</style>
