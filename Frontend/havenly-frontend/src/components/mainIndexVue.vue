<template>
  <div class="main-index" id="main-index">
    <!-- notification btn start -->
    <v-badge
      v-show="!btn_display"
      class="popup-btn"
      @click="togglePopup"
      :content="notificationCount"
      color="red"
      overlap
    >
      <v-icon large>mdi-bell</v-icon>
    </v-badge>

    <v-card v-if="isCardVisible" class="popup-card">
      <h5 class="header">short info</h5><br><br><br>

      <!-- btn part -->
      <div class="close-btn" @click="hideCard">
        <v-icon>mdi-close</v-icon>
      </div>

      <!-- data part -->
      <div class="card-data">
        <v-text-field
          bg-color="#EDEDED"
          readonly
          filled
          variant="outlined"
          density="compact"
          rounded="lg"
          class="w-100"
          v-model="userData.name"
          label="Name"
        ></v-text-field>
        <v-text-field
          bg-color="#EDEDED"
          readonly
          filled
          variant="outlined"
          density="compact"
          rounded="lg"
          class="w-100"
          v-model="userData.email"
          label="Gmail"
        ></v-text-field>
        <v-text-field
          bg-color="#EDEDED"
          readonly
          filled
          variant="outlined"
          density="compact"
          rounded="lg"
          class="w-100"
          v-model="userData.phone"
          label="Phone"
        ></v-text-field>
      </div>

      <v-btn rounded class="detail-btn" @click="gotoDetailView(1)">
        see detail
        <v-icon class="mt-1">mdi-chevron-double-right</v-icon>
      </v-btn>
    </v-card>

    <div :class="['popup-notification', { showpopup: isPopupVisible, hidepopup: isPopupDisable }]">
      <h5 class="header">Notifications</h5>

      <div class="close-btn" @click="hidePopup">
        <v-icon>mdi-close</v-icon>
      </div>

      <br><br>
      <div class="popup-data">
        <div v-for="obj in objs" :key="obj.id">
          <div class="row box-content">
            <div class="col-1 toggle-btn" @click="getData(obj.name, obj.email, obj.phone)">
              <v-icon>mdi-menu-left</v-icon>
            </div>
            <div class="col-4 p-0">
              <v-img :src="obj.photo_url[0]" class="me-auto" alt="" />
            </div>
            <div class="col-7 p-0 ps-2 right">
              <span>{{ obj.name }}</span> just interested your post.
            </div>
          </div>
        </div>
      </div>
    </div>
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

    <div class="for-contact-page">
      <contactpage />
    </div>

    <button :class="{ show: showBackToTop }" @click="scrollToTop" id="backToTopBtn">
      <v-icon style="transform:rotate(-90deg)">mdi-arrow-right</v-icon>
    </button>
  </div>
</template>

<script>
import firstIndex from './For_MainIndex/firstIndexContent.vue';
import secondTabContent from './For_MainIndex/secondTabContentVue.vue';
import thirdCarousel from './For_MainIndex/thirdItemCarousel.vue';
import fourthPackage from './For_MainIndex/fourthPackageVue.vue';
import fifthDoc from './For_MainIndex/fifthDocContent.vue';
import contactpage from './For_MainIndex/ContactVue.vue';

import { ref, onMounted, onUnmounted, watch } from 'vue';

export default {
  name: 'mainIndexVue',
  methods: {
    gotoDetailView(postId) {
      const encryptData = this.encryptId(postId);
      localStorage.setItem('postId', encryptData);
      this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
    },
  },
  setup() {
    const showBackToTop = ref(false);
    const btn_display = ref(false);
    const isPopupVisible = ref(false);
    const isPopupDisable = ref(false);
    const isCardVisible = ref(false);
    const objs = ref([]);
    const userData = ref({
      name: '',
      email: '',
      phone: '',
    });

    const getData = (name, email, phone) => {
      userData.value.name = name;
      userData.value.email = email;
      userData.value.phone = phone;
      isCardVisible.value = true;
    };

    const hideCard = () => {
      isCardVisible.value = false;
    };

    const scrollFunction = () => {
      showBackToTop.value = window.pageYOffset > 1400;
      btn_display.value = window.pageYOffset > 1400;
    };

    const scrollToTop = () => {
      window.scrollTo({
        top: 0,
        behavior: 'smooth',
      });
    };

    onMounted(() => {
      window.addEventListener('scroll', scrollFunction);
    });

    onUnmounted(() => {
      window.removeEventListener('scroll', scrollFunction);
    });

    const togglePopup = () => {
      const user = JSON.parse(sessionStorage.getItem('sub_user'));
      const UserId = user.subUserId;

      objs.value = [];

      // Make API call to fetch posts from backend
      fetch(`http://localhost:8083/interest/getAllNoti/${UserId}`)
        .then((response) => response.json())
        .then((data) => {
          console.log(data);
          data.forEach((obj) => {
            if (obj.posts.testsellpostss) {
              let imgUrls = Array.isArray(obj.posts.testsellpostss.image)
                ? obj.posts.testsellpostss.image
                : [obj.posts.testsellpostss.image];

              console.log(obj);
              objs.value.unshift({
                id: obj.id,
                register_id: obj.reg_user.register_id,
                name: obj.reg_user.name,
                phone: obj.reg_user.phone,
                email: obj.reg_user.email,
                post_id: obj.posts.post_id,
                photo_url: imgUrls,
              });
              console.log(imgUrls);
            }
          });
          // Initialize notification count with the length of fetched notifications
          notificationCount.value = objs.value.length;
        })
        .catch((error) => {
          console.error('Error fetching photos:', error);
        });

      isPopupVisible.value = true;
      isPopupDisable.value = false;
      btn_display.value = !btn_display.value;

      // Reset notification count to zero when notifications are viewed
      notificationCount.value = 0;
    };

    const hidePopup = () => {
      isPopupDisable.value = true;
      isCardVisible.value = false;
      setTimeout(() => {
        btn_display.value = !btn_display.value;
      }, 100);
      // to counter error
      btn_display.value = true;
    };

    const notificationCount = ref(0);

    watch(objs, (newObjs) => {
      notificationCount.value = newObjs.length;
    });

    return {
      showBackToTop,
      scrollToTop,
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
      notificationCount,
    };
  },
  components: {
    firstIndex,
    secondTabContent,
    thirdCarousel,
    fourthPackage,
    fifthDoc,
    contactpage,
  },
};
</script>

<style>
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
  transform: translateY(0);
}
</style>
