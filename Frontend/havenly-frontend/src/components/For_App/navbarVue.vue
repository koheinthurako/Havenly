<template>

  <!-- overlay test start -->
  <div v-if="isOverlayVisible" class="overOverlay" :class="animateOverlayClass"></div>
  <!-- overlay test end -->
  <nav class="navbar navbar-expand-lg p-0 fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <h2>Havenly</h2>
      </a>

      <!-- style one start -->
      <!-- <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button> -->
      <!-- style one end -->

      <!-- style two start -->

      <div v-click-outside="onClickOutside">
        <button class="navbar-toggler" type="button" @click="toggleSidebar">
          <span v-if="!isSidebarActive" class="navbar-toggler-icon"></span>
          <span v-else class="close-icon"><v-icon>mdi-window-close</v-icon></span>
        </button>

        <!-- navbar for mobile view start -->
        <div :class="['navSidebar', { 'activeOne': isSidebarActive }]" class="d-block d-sm-none">
          <ul class="style-two navbar-nav mx-auto">
            <h4 class="mt-2 ms-2" style="color: #e86f52;">Links</h4>
            <li class="items nav-item" @click.stop="isSidebarActive = !isSidebarActive"
              :class="{ sideBarActive: isActive('/') }">
              <router-link to="/" class="item-edit nav-link">
                Home</router-link>
            </li>
            <li class="items nav-item" @click.stop="isSidebarActive = !isSidebarActive"
              :class="{ sideBarActive: isActive('/package') }">
              <router-link to="/package" class="item-edit nav-link">
                Package</router-link>
            </li>
            <li class="items nav-item" @click.stop="isSidebarActive = !isSidebarActive"
              :class="{ sideBarActive: isActive('/subscribe') }">
              <router-link to="/subscribe" class="item-edit nav-link">
                Subscribe</router-link>
            </li>
            <li class="items nav-item" @click.stop="isSidebarActive = !isSidebarActive"
              :class="{ sideBarActive: isActive('/about') }">
              <router-link to="/about" class="item-edit nav-link">
                About</router-link>
            </li>
            <hr class="mx-2 p-0 my-0" style="color:#e86f52; border: 1px solid;">
            <h4 class="mt-2 ms-2" style="color: #e86f52;">User status</h4>
            <li class="items nav-item">
              <div v-if="getUser2">

                <v-expansion-panels @click.stop>
                  <v-expansion-panel>
                    <v-expansion-panel-title>
                      <v-icon class=" me-1">mdi-account-box</v-icon>{{ getUser2.name }}
                    </v-expansion-panel-title>
                    <v-expansion-panel-text class="p-0">
                      <ul class="navbar-nav mx-auto">
                        <li class="items nav-item" :class="{ sideBarActive: isActive('/userDashboard') }">
                          <router-link @click="toggleSidebar" to="/userDashboard" class="item-edit nav-link">
                            <v-icon class="me-1">mdi-account-circle</v-icon>User Profile</router-link>
                        </li>
                      </ul>
                    </v-expansion-panel-text>
                    <v-expansion-panel-text class="p-0">
                      <ul class="navbar-nav mx-auto">
                        <li class="items nav-item" :class="{ sideBarActive: isActive('/userDashboard') }">
                          <div @click="logout" class="item-edit nav-link">
                            <v-icon class="me-1">mdi-logout-variant</v-icon>Logout
                          </div>
                        </li>
                      </ul>
                    </v-expansion-panel-text>
                  </v-expansion-panel>
                </v-expansion-panels>
              </div>
              <div v-else>
                <router-link @click.stop="isSidebarActive = !isSidebarActive" to="/login"
                  :class="{ sideBarActive: isActive('/login') }" class="nav-link item-edit">Login</router-link>
              </div>
            </li>

            <div v-if="getUser2">
              <hr class="mx-2 p-0 my-3" style="color:#e86f52; border: 1px solid;">
              <div class="d-flex ">
                <h4 class="mt-2 ms-2 mb-3" style="color: #e86f52;">Notifications</h4>
                <v-spacer></v-spacer>
                <v-badge class="mt-2 me-3" :content="notificationCount" color="red">
                  <v-icon color="white">mdi-bell</v-icon>
                </v-badge>
              </div>

              <!-- noti start -->

              <div v-if="filteredOjbs && filteredOjbs.length > 0" class="notification-panel">
                <v-expansion-panels>
                  <v-expansion-panel v-for="obj in filteredOjbs" :key="obj.post_id">
                    <v-expansion-panel-title>
                      <div class="row" style="width: 100%;">
                        <div class="col-md-4 col-4">
                          <v-img :src="obj.photo_url[0]" class="me-auto" alt="" />
                        </div>
                        <div class="col-md-8 col-8" style="font-size: 13px;">
                          <span>{{ obj.name }}{{ obj.id }}</span> make interested your post.
                        </div>
                      </div>
                    </v-expansion-panel-title>
                    <v-expansion-panel-text style="background-color: #eee; border: 1px solid red; margin: 0px auto;">

                      <span>
                        Interested user info
                      </span>
                      <hr class="mx-0 px-0">
                      <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                        class="w-100 mb-3" v-model="obj.name" label="Name" hide-details></v-text-field>
                      <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                        class="w-100 mb-3" v-model="obj.email" label="Gmail" hide-details></v-text-field>
                      <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                        class="w-100 mb-3" v-model="obj.phone" label="Phone" hide-details></v-text-field>
                      <v-textarea bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                        class="w-100 mb-3" v-model="obj.description" rows="1" auto-grow
                        label="Description"></v-textarea>
                      <div class="d-flex justify-space-between m-0 p-0" hide-details>
                        <div @click="hideCard(obj.post_id)" style="border-bottom:2px dashed red; cursor:pointer;">
                          remove
                        </div>
                        <div style="border-bottom:2px dashed green; cursor:pointer;"
                          @click.stop="isSidebarActive = !isSidebarActive" @click="clickPost(obj.post_id)">
                          see post <v-icon>mdi-chevron-double-right</v-icon>
                        </div>
                      </div>
                    </v-expansion-panel-text>
                  </v-expansion-panel>
                </v-expansion-panels>


              </div>
              <div v-else>
                <v-btn @click="cleanStorage" class="w-100">
                  Show All notification
                </v-btn>
              </div>
            </div>
            <!-- noti end -->

          </ul>
        </div>
        <!-- navbar for mobile view end -->

      </div>
      <!-- style two end (for mobile view) -->

      <!-- style one start -->
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mx-auto">

          <li class="nav-item">
            <router-link to="/" :class="{ active: isActive('/') }" class=" nav-link">
              Home</router-link>
          </li>

          <li class="nav-item">
            <router-link to="/package" :class="{ active: isActive('/package') }" class=" nav-link">
              Packages</router-link>
          </li>

          <li class="nav-item">
            <router-link to="/subscribe"
              :class="{ 'nav-link': true, active: isNavLinkActive('/subscribe') }">Subscribe</router-link>
          </li>

          <li class="nav-item">
            <router-link to="/about"
              :class="{ 'nav-link': true, active: isNavLinkActive('/about') }">About</router-link>
          </li>

          <li class="nav-item">
            <router-link to="/userdashboard" class="nav-link"
              :class="{ active: isActive('/userdashboard') }">Profile</router-link>
          </li>

          <div v-if="getUser">
            <div class="dropdown">
              <button class="btn btn-success dropdown-toggle nav-link" type="button" data-bs-toggle="dropdown"
                aria-expanded="false">
                Subscribe User
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">SubId : {{ getUser.subUserId }}</a></li>
                <li><a class="dropdown-item" href="#">Name : {{ getUser.name }}</a></li>
                <li><a class="dropdown-item" href="#">Gmail : {{ getUser.email }}</a></li>
                <li><a class="dropdown-item" href="#">Phone : {{ getUser.phone }}</a></li>
              </ul>
            </div>
          </div>

          <div v-if="getUser2">
            <div class="dropdown">
              <button class="btn btn-secondary dropdown-toggle nav-link" type="button" data-bs-toggle="dropdown"
                aria-expanded="false">
                Logined User
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">RegId : {{ getUser2.register_id }}</a></li>
                <li><a class="dropdown-item" href="#">Name : {{ getUser2.name }}</a></li>
                <li><a class="dropdown-item" href="#">Gmail : {{ getUser2.email }}</a></li>
                <li><a class="dropdown-item" href="#">Phone : {{ getUser2.phone }}</a></li>
              </ul>
            </div>
          </div>

        </ul>

        <ul class="navbar-nav">

          <div v-if="getUser2">
            <li class="nav-item">

              <div class="nav-link" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight"
                aria-controls="offcanvasRight">
                <v-badge :content="notificationCount" color="red" overlap>
                  <v-icon large>mdi-bell</v-icon>
                </v-badge>
              </div>
            </li>
          </div>


          <li class="nav-item">
            <div v-if="getUser2">

              <!-- new profile start -->
              <div v-click-outside="onClickOutside2">

                <div v-if="isActive('/userdashboard')">
                  <v-btn rounded class="profile-btn">
                    <p class="m-auto"><v-icon icon="mdi-account-circle" class="me-1"></v-icon>welcome</p>
                  </v-btn>
                </div>
                <div v-else>
                  <v-btn rounded class="profile-btn" v-if="!profileMenu" @click="handleProfileMenu">
                    <p class="m-auto"><v-icon icon="mdi-account-circle" class="me-2"></v-icon>{{ getUser2.name }}</p>
                  </v-btn>

                  <v-btn rounded v-else color="warning" @click="handleProfileMenuClose" class="profile-close-btn">
                    <p class="m-auto"><v-icon icon="mdi-close-circle" class="me-3"></v-icon>close</p>
                  </v-btn>
                </div>

                <v-card elevation="8" class="profile-card-on-nav" :class="[{ 'card-display': profileMenu }]">
                  <div class="top-profile mb-1">
                    <div class="img-container mx-auto">
                      <v-img :src="profilePic" class="w-100" />
                    </div>
                  </div>
                  <div class="header mb-3">
                    <h4 class="m-0 p-0">{{ getUser2.name }}</h4>
                    <p class="m-0 p-0">Normal user</p>
                  </div>
                  <div class="action-btns">
                    <v-btn @click="gotoDashboard" rounded elevation="10" class="dash-btn">
                      <v-icon icon="mdi-view-dashboard" class="me-2"></v-icon>Dashboard
                    </v-btn>

                    <v-btn @click="logout" icon size="large" elevation="12" class="out-btn">
                      <v-icon icon="mdi-logout"></v-icon>
                    </v-btn>
                  </div>
                </v-card>
              </div>


              <!-- new profile end -->

            </div>

            <div v-else>
              <router-link to="/login" class="nav-link">Login</router-link>
            </div>
          </li>


        </ul>

      </div>
      <!-- style one end -->

    </div>
  </nav>

  <!-- style two start -->

  <!-- <div v-click-outside="onClickOutside" :class="['navSidebar', { 'activeOne': isSidebarActive }]">
    <ul class="style-two navbar-nav mx-auto">
      <h4 class="mt-2 ms-2" style="color: #e86f52;">Links</h4>
      <li class="items nav-item" :class="{ sideBarActive: isActive('/') }">
        <router-link to="/" class="item-edit nav-link">
          Home</router-link>
      </li>
      <li class="items nav-item" :class="{ sideBarActive: isActive('/package') }">
        <router-link to="/package" class="item-edit nav-link">
          Package</router-link>
      </li>
      <li class="items nav-item" :class="{ sideBarActive: isActive('/subscribe') }">
        <router-link to="/subscribe" class="item-edit nav-link">
          Subscribe</router-link>
      </li>
      <li class="items nav-item" :class="{ sideBarActive: isActive('/about') }">
        <router-link to="/about" class="item-edit nav-link">
          About</router-link>
      </li>
      <hr class="mx-2 p-0 my-0" style="color:#e86f52; border: 1px solid;">
      <h4 class="mt-2 ms-2" style="color: #e86f52;">User status</h4>
      <li class="items nav-item">
        <div v-if="getUser2">

          <v-expansion-panels @click.stop>
            <v-expansion-panel>
              <v-expansion-panel-title>
                <v-icon class=" me-1">mdi-account-box</v-icon>{{ getUser2.name }}
              </v-expansion-panel-title>
              <v-expansion-panel-text class="p-0">
                <ul class="navbar-nav mx-auto">
                  <li class="items nav-item" :class="{ sideBarActive: isActive('/userDashboard') }">
                    <router-link @click="toggleSidebar" to="/userDashboard" class="item-edit nav-link">
                      <v-icon class="me-1">mdi-account-circle</v-icon>User Profile</router-link>
                  </li>
                </ul>
              </v-expansion-panel-text>
              <v-expansion-panel-text class="p-0">
                <ul class="navbar-nav mx-auto">
                  <li class="items nav-item" :class="{ sideBarActive: isActive('/userDashboard') }">
                    <router-link @click="logout" class="item-edit nav-link">
                      <v-icon class="me-1">mdi-logout-variant</v-icon>Logout</router-link>
                  </li>
                </ul>
              </v-expansion-panel-text>
            </v-expansion-panel>
          </v-expansion-panels>
        </div>

        <div v-else>
          <router-link to="/login" :class="{ sideBarActive: isActive('/login') }"
            class="nav-link item-edit">Login</router-link>
        </div>
      </li>

      <hr class="mx-2 p-0 my-3" style="color:#e86f52; border: 1px solid;">
      <h4 class="mt-2 ms-2" style="color: #e86f52;">Notifications</h4>

      <li class="items nav-item" :class="{ sideBarActive: isActive('/subscribe') }">
        <router-link to="/subscribe" class="item-edit nav-link">
          Subscribe</router-link>
      </li>

    </ul>
  </div> -->
  <!-- style two end -->

  <!-- offcanvas start -->
  <div class="offcanvas offcanvas-end offcanvas-edit" tabindex="-1" id="offcanvasRight"
    aria-labelledby="offcanvasRightLabel">
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="offcanvasRightLabel">Notifications</h5>
      <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">

      <div v-if="filteredOjbs && filteredOjbs.length > 0">
        <v-expansion-panels rounded="none">
          <v-expansion-panel v-for="obj in filteredOjbs" :key="obj.post_id">
            <v-expansion-panel-title>
              <div class="row" style="width: 100%;">
                <div class="col-md-4">
                  <v-img :src="obj.photo_url[0]" class="me-auto" alt="" />
                </div>
                <div class="col-md-8">
                  <span>{{ obj.name }}</span> make interested your post.
                </div>
              </div>
            </v-expansion-panel-title>
            <v-expansion-panel-text style="background-color: #eee; border: 1px solid red; margin: 0px auto;">

              <span>
                Interested user info
              </span>
              <hr class="mx-0 px-0">
              <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                class="w-100 mb-3" v-model="obj.name" label="Name" hide-details></v-text-field>
              <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                class="w-100 mb-3" v-model="obj.email" label="Gmail" hide-details></v-text-field>
              <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                class="w-100 mb-3" v-model="obj.phone" label="Phone" hide-details></v-text-field>
              <v-textarea bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                class="w-100 mb-3" v-model="obj.description" rows="1" auto-grow label="Description"></v-textarea>
              <div class="d-flex justify-space-between m-0 p-0" hide-details>
                <div @click="hideCard(obj.post_id)" style="border-bottom:2px dashed red; cursor:pointer;">
                  remove
                </div>
                <div data-bs-dismiss="offcanvas" style="border-bottom:2px dashed green; cursor:pointer;"
                  @click="clickPost(obj.post_id)">
                  see post <v-icon>mdi-chevron-double-right</v-icon>
                </div>
              </div>
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>


      </div>
      <div v-else>
        <v-btn @click="cleanStorage" class="w-100">
          Show All notification
        </v-btn>
      </div>


      <!-- end -->

    </div>
  </div>
  <!-- offcanvas end -->


</template>

<script>
import { useRoute } from 'vue-router';
import { useRouter } from 'vue-router';
import { ref, watch, onMounted, onUnmounted } from 'vue';
import { AES } from 'crypto-js';
import Swal from 'sweetalert2';

export default {
  name: 'navbarVue',

  setup() {
    const route = useRoute();
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


    const isNavLinkActive = (path) => {
      return route.path === path;
    };

    const clickPost = (postId) => {
      const enyId = encryptId(postId);

      router.push({ name: 'postDetailView', params: { id: `${enyId} Success` } });
    };

    const encryptId = (id) => {
      const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
      const encryptedId = AES.encrypt(id.toString(), secretKey).toString();
      return encryptedId;
    };

    const hideCard = (index) => {
      // isCardVisible.value = false;

      activeButton.value = null;
      activeButton.value = index;

      // Add the ID of the hidden card to localStorage
      const hiddenCards = JSON.parse(localStorage.getItem('hiddenCards')) || [];
      hiddenCards.push(activeButton.value);
      localStorage.setItem('hiddenCards', JSON.stringify(hiddenCards));

      // Update the filteredOjbs based on the new hiddenCards list
      filteredOjbs.value = filterData(objs.value);

      // activeButton.value = null;
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

      // Update the filteredOjbs based on the new hiddenCards list
      filteredOjbs.value = filterData(objs.value);
    };

    onMounted(() => {
      fetchNotifications();
      window.addEventListener('scroll', scrollFunction);
      getUser.value = JSON.parse(sessionStorage.getItem('login_user'));

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
      isPopupDisable.value = false;
      isCardVisible.value = false;
    };

    const fetchNotifications = () => {
      const user = JSON.parse(sessionStorage.getItem('sub_user'));

      // Make API call to fetch posts from backend
      if (user) {
        const UserId = user.subUserId;
        fetch(`http://localhost:8083/interest/getAllNotiBySubId/${UserId}`)
          .then((response) => response.json())
          .then((data) => {
            // Create a set to store unique post IDs
            const uniquePostIds = new Set();
            data.forEach((obj) => {
              // Check if the post ID is unique
              if (!uniquePostIds.has(obj.posts.post_id)) {
                uniquePostIds.add(obj.posts.post_id);

                let imgUrls;
                if (obj.posts.sellpost) {
                  imgUrls = Array.isArray(obj.posts.sellpost.image)
                    ? obj.posts.sellpost.image
                    : [obj.posts.sellpost.image];
                } else if (obj.posts.rentpost) {
                  imgUrls = Array.isArray(obj.posts.rentpost.image)
                    ? obj.posts.rentpost.image
                    : [obj.posts.rentpost.image];
                }

                // Add the notification to the array
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
      activeButton,
      showBackToTop,
      isPopupVisible,
      togglePopup,
      hidePopup,
      isPopupDisable,
      btn_display,
      // userData,
      isCardVisible,
      // getData,
      hideCard,
      objs,
      scrollToTop,
      notificationCount,
      isNavLinkActive
    };

  },

  data: () => ({

    isOverlayVisible: false,
    animateOverlayClass: '',


    profilePic: require("@/assets/img/ava1.jpg"),

    profileMenu: false,
    isSidebarActive: false,
    getUser: [],
    getUser2: [],
    activeDataLink: '',
    newuser: {
      initials: 'JD',
      fullName: 'John Doe',
      email: 'john.doe@doe.com',
    },

    items: [
      { title: 'Click Me' },
      { title: 'Click Me' },
      { title: 'Click Me' },
      { title: 'Click Me 2' },
    ],
  }),


  mounted() {
    this.getUser = JSON.parse(sessionStorage.getItem('sub_user'));
    this.getUser2 = JSON.parse(sessionStorage.getItem('login_user'));
  },


  methods: {

    handleProfileMenu() {
      this.profileMenu = true;

      setTimeout(() => {

        this.isOverlayVisible = true;
        this.animateOverlayClass = 'overlay-animate';
      }, 170);
    },

    handleProfileMenuClose() {
      this.animateOverlayClass = 'overlay-animate-reverse';

      setTimeout(() => {
        this.isOverlayVisible = false;
        this.isCardVisible = false;
        this.animateOverlayClass = '';
      }, 200);

      setTimeout(() => {
        this.profileMenu = false;
      }, 220);
    },

    gotoDashboard() {
      // first hide the profile card
      this.animateOverlayClass = 'overlay-animate-reverse';

      setTimeout(() => {
        this.isOverlayVisible = false;
        this.isCardVisible = false;
        this.animateOverlayClass = '';
      }, 200);

      setTimeout(() => {
        this.profileMenu = false;
      }, 220);

      setTimeout(() => {
        this.$router.push({ name: 'User_dashboard' });
      }, 250);

    },

    goToExplain(get) {
      // this.$router.push({ name: 'About' });
      this.$router.push({ name: 'About', params: { href: `${get} explain` } });
    },

    toggleSidebar() {
      this.isSidebarActive = !this.isSidebarActive;

    },

    // onClickOutside(event) {
    //   // Check if the click is outside the sidebar
    //   const sidebarElement = this.$refs.navSidebar;
    //   if (this.isSidebarActive && sidebarElement && !sidebarElement.contains(event.target)) {
    //     this.isSidebarActive = false;
    //   }

    //   // Check if the click is outside the profile menu
    //   const profileMenuElement = this.$refs.profileMenu;
    //   if (this.profileMenu && profileMenuElement && !profileMenuElement.contains(event.target)) {
    //     this.profileMenu = false;
    //   }
    // },

    onClickOutside(event) {
      if (this.isSidebarActive && !this.$el.contains(event.target)) {
        this.isSidebarActive = false;
      }

    },

    onClickOutside2(event) {
      if (this.profileMenu && !this.$el.contains(event.target)) {
        this.animateOverlayClass = 'overlay-animate-reverse';

        setTimeout(() => {
          this.isOverlayVisible = false;
          this.isCardVisible = false;
          this.animateOverlayClass = '';
        }, 200);

        setTimeout(() => {
          this.profileMenu = false;
        }, 220);
      }

    },

    isActive(route) {
      return this.$route.path === route;
    },

    // isLoggedIn() {
    //   // Check if user information is stored in session storage
    //   console.log("User is included in session storage");
    //   const user = sessionStorage.getItem('login_user');
    //   return !!user; // Return true if user is logged in, false otherwise
    // },

    logout() {
      // first hide the profile card
      this.animateOverlayClass = 'overlay-animate-reverse';

      setTimeout(() => {
        this.isOverlayVisible = false;
        this.isCardVisible = false;
        this.animateOverlayClass = '';
      }, 200);

      setTimeout(() => {
        this.profileMenu = false;
      }, 220);

      setTimeout(() => {
        Swal.fire({
          title: 'Are you sure?',
          text: 'You will be logged out!',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes, log me out!'
        }).then((result) => {
          if (result.isConfirmed) {
            sessionStorage.removeItem('login_user');
            window.location.reload();
          }
        });
      }, 270);


    },
  }
};
</script>

<style lang="scss" scoped>
.active {
  background-color: #e86f52 !important;
  color: #fff !important;
}

.v-menu__content {
  z-index: 2000 !important;
  /* Adjust as needed */
}

.overOverlay {
  position: fixed;
  top: 0;
  right: 0;
  left: 107%;
  bottom: -1.6%;
  width: 0;
  height: 0;
  background: rgba(0, 0, 0, 0.3);
  z-index: 2000;
  border-radius: 0 0 10px 10px;
  pointer-events: none;
  transform: translate(-50%, -50%);

  pointer-events: all;
}

.overlay-animate {
  animation: expandOverlay 0.3s both;
}

.overlay-animate-reverse {
  animation: shrinkOverlay 0.4s both;
}

@keyframes expandOverlay {
  from {
    width: 250px;
    height: 255px;

  }

  to {
    width: 220vw;
    height: 400vh;

  }
}

@keyframes shrinkOverlay {
  from {
    width: 220vw;
    height: 400vh;

  }

  to {
    width: 250px;
    height: 255px;

  }
}
</style>