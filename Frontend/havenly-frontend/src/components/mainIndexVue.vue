<template>
    <div class="main-index" id="main-index">



        <!-- notification btn start -->
        <v-badge v-show="!btn_display" class="popup-btn" @click="togglePopup" :content="notificationCount" color="red"
            overlap>
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
                <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                    class="w-100" v-model="userData.name" label="Name"></v-text-field>
                <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                    class="w-100" v-model="userData.email" label="Gmail"></v-text-field>
                <v-text-field bg-color="#EDEDED" readonly filled variant="outlined" density="compact" rounded="lg"
                    class="w-100" v-model="userData.phone" label="Phone"></v-text-field>
            </div>

            <v-btn rounded class="detail-btn" @click="gotoDetailView(1)">
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
                <div v-for="(data, index) in items" :key="index">
                    <div class="row box-content">
                        <div class="col-1 toggle-btn" :class="{ 'notiActive': activeButton === index }"
                            @click="getData(data.title, data.type, data.name, index)">
                            <v-icon>mdi-menu-left</v-icon>
                        </div>
                        <div class="col-4 p-0">
                            <v-img :src="data.img" class="me-auto" alt="" />
                        </div>
                        <div class="col-7 p-0 ps-2 right">
                            <span>{{ data.title }}</span> just interested your post.
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

import contactpage from './For_MainIndex/ContactVue.vue'

import { ref, onMounted, onUnmounted } from 'vue';

import AES from 'crypto-js/aes'
import Utf8 from 'crypto-js/enc-utf8';

export default {
    name: 'mainIndexVue',

    data: () => ({

        notificationCount: 8,

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


    methods: {

        encryptId(id) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
            return encryptedId
        },

        decryptId(encryptedId) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(encryptedId, secretKey);
            const decryptedId = decryptedBytes.toString(Utf8);
            return parseInt(decryptedId, 10);
        },
        gotoDetailView(postId) {

            // const encryptedId = this.encryptId(postId);
            // this.$router.push({ name: 'postDetailView', params: { id: encryptedId } });

            const encryptData = this.encryptId(postId);
            sessionStorage.setItem('postId', encryptData);
            this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });

        },

    },



    setup() {
        const showBackToTop = ref(false);
        const btn_display = ref(false);
        const isPopupVisible = ref(false);
        const isPopupDisable = ref(false);
        const isCardVisible = ref(false);
        const activeButton = ref(null);
        const userData = ref({
            name: '',
            email: '',
            phone: ''
        });

        const getData = (name, email, phone, index) => {
            userData.value.name = name;
            userData.value.email = email;
            userData.value.phone = phone;
            isCardVisible.value = true;
            activeButton.value = index;
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
                behavior: 'smooth'
            });
        };

        onMounted(() => {
            window.addEventListener('scroll', scrollFunction);
        });

        onUnmounted(() => {
            window.removeEventListener('scroll', scrollFunction);
        });

        const togglePopup = () => {
            isPopupVisible.value = true;
            isPopupDisable.value = false;
            btn_display.value = !btn_display.value;
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

        return {
            activeButton,
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
            hideCard
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
}

</script>
