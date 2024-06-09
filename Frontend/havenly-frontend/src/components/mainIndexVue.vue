<template>
  <div class="main-index" id="main-index">

    <!-- notification btn start -->
    <!-- <div v-if="this.getUser">
      <v-badge v-show="!btn_display" class="popup-btn" @click="togglePopup" :content="notificationCount" color="red"
        overlap>
        <v-icon large>mdi-bell</v-icon>
      </v-badge>
    </div>

    <v-card v-if="isCardVisible" class="popup-card">
      <h5 class="header">short info</h5><br><br><br>

     
      <div class="close-btn" @click="hideCard">
        <v-icon>mdi-close</v-icon>
      </div>

      <div class="card-data">
        <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg" class="w-100"
          v-model="userData.name" label="Name"></v-text-field>
        <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg" class="w-100"
          v-model="userData.email" label="Gmail"></v-text-field>
        <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg" class="w-100"
          v-model="userData.phone" label="Phone"></v-text-field>
        <v-textarea bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg" class="w-100"
          v-model="userData.description" rows="4" label="Phone"></v-textarea>

      </div>

      <v-btn rounded class="detail-btn" @click="clickPost(userData.post_id)">
        see detail <v-icon class="mt-1">mdi-chevron-double-right</v-icon>
      </v-btn>

    </v-card>

    <div :class="['popup-notification', { showpopup: isPopupVisible, hidepopup: isPopupDisable }]">
      <h5 class="header">Notifications</h5>

      <div class="close-btn" @click="hidePopup">
        <v-icon>mdi-close</v-icon>
      </div>

      <br><br>
      <div class="popup-data">

        <div v-if="filteredOjbs && filteredOjbs.length > 0">
          <div v-for="obj in filteredOjbs" :key="obj.post_id">
            <div class="row box-content">
              <div class="col-1 toggle-btn" :class="{ 'notiActive': activeButton === obj.post_id }"
                @click="getData(obj.name, obj.email, obj.phone, obj.description, obj.post_id)">
                <v-icon>mdi-menu-left</v-icon>
              </div>
              <div class="col-4 p-0">
                <v-img :src="obj.photo_url[0]" class="me-auto" alt="" />
              </div>
              <div class="col-7 p-0 ps-2 right">
                <span>{{ obj.name }}{{ obj.id }}</span> just interested your post.
              </div>
            </div>
          </div>
        </div>
        <div v-else>
          <v-btn color="danger" @click="cleanStorage">cleanStorage</v-btn>
        </div>


      </div>
    </div> -->
    <!-- notification btn end -->

    <div class="for-first-index">
      <firstIndex />
    </div>

    <div class="for-second-tabContent">
      <secondTabContent />
    </div>

    <div class="for-third-Carousel">
      <thirdCarousel />
    </div>

    <div class="for-fourth-package">
      <fourthPackage />
    </div>

    <!-- delete able -->
    <div class="for-fifth-Doc">
      <fifthDoc />
    </div>

    <div class="for-About-in-main">
      <AboutInMain />
    </div>

    <div class="for-contact-page">
      <contactpage />
    </div>



    <button :class="{ show: showBackToTop }" @click="scrollToTop" id="backToTopBtn"><v-icon
        style="transform:rotate(-90deg)">mdi-arrow-right</v-icon></button>
  </div>
</template>

<script>
import firstIndex from './For_MainIndex/firstIndexContent.vue'
import secondTabContent from './For_MainIndex/secondTabContentVue.vue'
import thirdCarousel from './For_MainIndex/thirdItemCarousel.vue'
import fourthPackage from './For_MainIndex/fourthPackageVue.vue'
import fifthDoc from './For_MainIndex/fifthDocContent.vue'

import AboutInMain from '@/components/For_MainIndex/AboutInMain.vue'

import contactpage from './For_MainIndex/ContactVue.vue'

import { ref, watch, onMounted, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { AES } from 'crypto-js';

// import { descriptors } from 'chart.js/dist/core/core.defaults'

export default {
  name: 'mainIndexVue',

  data: () => ({



    // temp Register data 
    registerData: '',

    items: [
      { id: 1, title: 'John Lwin', img: require('@/assets/img/1.jpg'), type: 'panda', name: 'condo' },
      { id: 2, title: 'Lwin moe', img: require('@/assets/img/2.jpg'), type: 'snake', name: 'apartment' },
      { id: 3, title: 'Moe moe', img: require('@/assets/img/3.jpg'), type: 'balloon', name: 'white' },
      { id: 4, title: 'Moe aye', img: require('@/assets/img/4.jpg'), type: 'tiger', name: 'flying' },
      { id: 5, title: 'Thida', img: require('@/assets/img/1.jpg'), type: 'panda', name: 'condo' },
      { id: 6, title: 'Nu Nu', img: require('@/assets/img/2.jpg'), type: 'snake', name: 'apartment' },
      { id: 7, title: 'Aye aye', img: require('@/assets/img/3.jpg'), type: 'balloon', name: 'white' },
      { id: 8, title: 'Hla hla', img: require('@/assets/img/4.jpg'), type: 'tiger', name: 'flying' },

    ],
  }),

  components: {
    firstIndex,
    secondTabContent,
    thirdCarousel,
    fourthPackage,
    fifthDoc,
    contactpage,
    AboutInMain,
  },

  // mounted() {
  //   this.fetchRegisterUser();
  // },


  methods: {

    // async fetchRegisterUser() {
    //   try {
    //     const response = await fetch(`http://localhost:8083/findByMail/${this.getUser.email}`);

    //     if (!response.ok) {
    //       throw new Error(`HTTP error! status: ${response.status}`);
    //     }

    //     this.registerData = await response.json();
    //     console.log("getting Register data : ", this.registerData.register_id);


    //   } catch (error) {
    //     console.error('Error fetching post:', error);
    //   }
    // },

    // encryptId(id) {
    //   const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
    //   const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
    //   return encryptedId
    // },

    // decryptId(encryptedId) {
    //   const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
    //   const decryptedBytes = AES.decrypt(encryptedId, secretKey);
    //   const decryptedId = decryptedBytes.toString(Utf8);
    //   return parseInt(decryptedId, 10);
    // },

    // clickPost(postId) {
    //   console.log("See post id : ", postId);
    //   const encryptData = this.encryptId(postId);

    //   this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
    // },



  },

  setup() {
    const router = useRouter();
    const showBackToTop = ref(false);
    const btn_display = ref(false);
    const isPopupVisible = ref(false);
    const isPopupDisable = ref(false);
    const isCardVisible = ref(false);
    const activeButton = ref(null);
    const notificationCount = ref(0);
    const getUser = ref([]);
    const objs = ref([]);
    const filteredOjbs = ref([]);
    const userData = ref({
      post_id: '',
      name: '',
      email: '',
      phone: '',
      description: ''
    });

    const getData = (name, email, phone, description, index) => {
      activeButton.value = null;
      userData.value.post_id = index;
      userData.value.name = name;
      userData.value.email = email;
      userData.value.phone = phone;
      userData.value.description = description;
      isCardVisible.value = true;
      activeButton.value = index;
    };

    const clickPost = (postId) => {

      // Add the ID of the hidden card to localStorage
      const hiddenCards = JSON.parse(localStorage.getItem('hiddenCards')) || [];
      hiddenCards.push(activeButton.value);
      localStorage.setItem('hiddenCards', JSON.stringify(hiddenCards));

      // Update the filteredOjbs based on the new hiddenCards list
      filteredOjbs.value = filterData(objs.value);

      console.log("Sent Post id : ", postId);
      const enyId = encryptId(postId);

      router.push({ name: 'postDetailView', params: { id: `${enyId} Success` } });
    };

    const encryptId = (id) => {
      const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
      const encryptedId = AES.encrypt(id.toString(), secretKey).toString();
      return encryptedId;
    };

    // const decryptId = (encryptedId) => {
    //   const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
    //   const decryptedBytes = AES.decrypt(encryptedId, secretKey);
    //   const decryptedId = decryptedBytes.toString(Utf8);
    //   return parseInt(decryptedId, 10);
    // };

    const hideCard = () => {
      isCardVisible.value = false;

      // Add the ID of the hidden card to localStorage
      const hiddenCards = JSON.parse(localStorage.getItem('hiddenCards')) || [];
      hiddenCards.push(activeButton.value);
      localStorage.setItem('hiddenCards', JSON.stringify(hiddenCards));

      // Update the filteredOjbs based on the new hiddenCards list
      filteredOjbs.value = filterData(objs.value);

      activeButton.value = null;
    };


    const scrollFunction = () => {
      showBackToTop.value = window.pageYOffset > 1400;
      btn_display.value = window.pageYOffset > 1400;
    };

    const scrollToTop = () => {
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      });
    };

    const cleanStorage = () => {
      localStorage.removeItem('hiddenCards');
    };

    onMounted(() => {
      fetchNotifications();
      window.addEventListener('scroll', scrollFunction);
      getUser.value = JSON.parse(sessionStorage.getItem('login_user'));
      localStorage.removeItem('openTab');
      localStorage.removeItem('adminTab');
      filteredOjbs.value = filterData(objs.value);
    });

    onUnmounted(() => {
      window.removeEventListener('scroll', scrollFunction);
    });

    const togglePopup = () => {
      isPopupVisible.value = true;
      isPopupDisable.value = false;
      btn_display.value = !btn_display.value;
      activeButton.value = null;
    };

    const hidePopup = () => {
      isPopupDisable.value = true;
      isCardVisible.value = false;
      setTimeout(() => {
        btn_display.value = !btn_display.value;
      }, 400);

      // to counter error
      btn_display.value = true;
    };

    const fetchNotifications = () => {
      const user = JSON.parse(sessionStorage.getItem('sub_user'));

      // Make API call to fetch posts from backend
      if (user) {
        const UserId = user.subUserId;
        fetch(`http://localhost:8083/interest/getAllNotiBySubId/${UserId}`)
          .then((response) => response.json())
          .then((data) => {

            data.forEach((obj) => {
              if (obj.posts.sellpost) {
                let imgUrls = Array.isArray(obj.posts.sellpost.image)
                  ? obj.posts.sellpost.image
                  : [obj.posts.sellpost.image];

                objs.value.unshift({
                  id: obj.post_id,
                  register_id: obj.reg_user.register_id,
                  name: obj.reg_user.name,
                  phone: obj.reg_user.phone,
                  email: obj.reg_user.email,
                  description: obj.description,
                  post_id: obj.posts.post_id,
                  photo_url: imgUrls,
                });

              }
            });
            // Initialize notification count with the length of fetched notifications
            filteredOjbs.value = filterData(objs.value);
          })
          .catch((error) => {
            console.error('Error fetching photos:', error);
          });
      } else {
        console.log("No Recently a user Registered!");
      }
    };

    const filterData = (data) => {
      const hiddenCards = JSON.parse(localStorage.getItem('hiddenCards')) || [];
      const filteredOjbs = data.filter(obj => !hiddenCards.includes(obj.post_id));
      notificationCount.value = filteredOjbs.length;
      return filteredOjbs;
    };

    watch(objs, (newObjs) => {
      notificationCount.value = newObjs.length;
    });

    // Watch localStorage for changes
    window.addEventListener('storage', () => {
      filteredOjbs.value = filterData(objs.value);
    });

    return {
      clickPost,
      cleanStorage,
      filteredOjbs,
      filterData,
      getUser,
      activeButton,
      showBackToTop,
      isPopupVisible,
      togglePopup,
      hidePopup,
      isPopupDisable,
      btn_display,
      userData,
      isCardVisible,
      getData,
      hideCard,
      objs,
      scrollToTop,
      notificationCount,
    };

  },


}

</script>


<style lang="scss">
#backToTopBtn {
  display: block;
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 99;
  padding: 16px 17px;
  border-radius: 50%;
  font-size: 22px;
  cursor: pointer;
  background-color: #e97559;
  color: white;
  border: none;
  z-index: 2000;
  font-weight: bold;
  box-shadow: 0px 4px 28px 2px rgba(0, 0, 0, 0.3);
  opacity: 0;
  transform: translateY(-800px);
  transition: all 0.3s ease-in-out;
}

#backToTopBtn:hover {
  background-color: #b13d20;
}

#backToTopBtn.show {
  opacity: 1;
}


.notiActive {
  .v-icon {
    transform: rotate(360deg);
  }
}
</style>
