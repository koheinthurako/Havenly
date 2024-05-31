<template>
  <div class="d-flex justify-center" style="height: 100vh">
    <v-container>
      <br><br><br>
      <h3 class="flex" style="height: 50px">Create Ads</h3>
      <br>
      <!-- Form to create a new ad post -->
      <div class="d-flex mb-5">
      <span class="d-flex align-center" @click="openAdsDialog" style="width: 5%; justify-content: center;">
              <v-icon class="edit-icon">mdi-plus</v-icon>
            </span>

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
              @change="handleFileUpload"
            />
          </div>
          <p>
          <v-row cols="12" class="w-100 mt-4">
              <v-btn class="submit ms-auto me-3"  type="submitAdPost" style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                Submit
              </v-btn>
            </v-row></p>
          <!-- <v-btn type="submit" color="primary" class="mt-3">Submit</v-btn> -->
        </form>
      </v-dialog>
      <!-- dialog end -->
    </div>
      <!-- Display ad posts -->
      <v-row>
        <v-col
          v-for="ad in ads"
          :key="ad.id"
          cols="3"
          md="4"
          class="d-flex"
        >
      <v-card class="pa-2 w-100">
        <v-img :src="ad.imageUrl || ''" height="250px" width="400px" contain>
          <v-container
            class="fill-height d-flex align-start"
            style="background: rgba(0, 0, 0, 0.5); color: white; cursor:pointer"
          >
            <h4>{{ ad.title }}</h4>
          </v-container>
        </v-img>
      </v-card>
      </v-col>
      </v-row>
      <!-- <v-card class="pa-3" v-for="ad in ads" :key="ad.id">
        <v-img :src="ad.image" height="200px" class="mb-2"></v-img>
        <h3>{{ ad.title }}</h3>
      </v-card> -->
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import router from '@/router';

export default {
  name: 'AdPostComponent',
  data(){
    return{
      adsDialog: false,
      user:{
        subUserId:''
      },
      // items: []
    }
  },
  methods: {
    openAdsDialog() {
            this.adsDialog = true;
        },
    closeAdsDialog() {
            this.adsDialog = false;
        },

  },
  created(){
    const loginUserData = JSON.parse(sessionStorage.getItem('login_user'));
    if (loginUserData !==null ) { 
     if(loginUserData.nrc !== null){
      this.user.subUserId = loginUserData.subUserId;
     }else{
      console.log("User is not subbed!");
       alert("You must be subscribed to view this page!");
        router.push("/subcribe");
     }
    }
    else{ 
      console.log("User is not logged in!");
         alert("You must be logged in to view this page!");
        router.push("/loginakm");
    }
    },
    // fetchAds(){
    //   const loginUserData = JSON.parse(sessionStorage.getItem('login_user'));
    //   const subUserId = loginUserData.subUserId;
    //   axios.get('http://localhost:8083/getAllSubUserAds',{ 
    //         params: {
    //           subUserId: subUserId
    //                   }
    //                 }) 
    //       .then(response => {
    //         if (response.data !== null) {
    //           this.items = response.data; 
    //       console.log("Ads", this.items);
    //     }
    //       })
    //       .catch(error => {
    //         console.error('Error fetching data:', error);
    //       });
    // },
    // mounted(){
    //   this.fetchAds();
    // },
  setup() {
    const title = ref('');
    const image = ref(null);
    const imagePreview = ref(null);
    const ads = ref([]);
    const subUserId = ref('');

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
                                if(statusCode===404){alert("Upload image failed! Please check and upload again!")}
                                if(statusCode===500){alert("Upload ads failed!")}
                                console.log("error : ", response);
                            }
                            }
                    }
      const formData = new FormData();
      formData.append('image', image.value);
      formData.append('subUserId', subUserId.value);
      formData.append('title', title.value);

      try {
        const response = await axios.post('http://localhost:8083/create/adspost', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        const status = response.status;
        if (status === 200) {
      alert("Profile updated successfully!"); 
        // ads.value.push(response.data);
        title.value = '';
        image.value = null;
        imagePreview.value = null;
        fetchAds();
        // window.location.reload();
        }
       
      } catch (error) {
        httpErrorHandler(error);
      }
    };
    const fetchAds = () => {
      const loginUserData = JSON.parse(sessionStorage.getItem('login_user'));
       subUserId.value = loginUserData.subUserId;
      axios.get('http://localhost:8083/subuserads', {
        params: {
          subUserId: subUserId.value
        }
      })
        .then(response => {
          if (response.data !== null) {
            ads.value = response.data;
            console.log("Ads", ads.value);
          }
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
    };

    onMounted(() => {
      fetchAds();
    });
    return {
      title,
      image,
      imagePreview,
      ads,
      subUserId,
      submitAdPost,
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

    .submit,
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
</style>
