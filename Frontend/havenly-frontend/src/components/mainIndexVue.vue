<template>
    <div class="main-index" id="main-index">

        <div class="public-content">
            <v-icon class="fb">mdi-facebook</v-icon><br>
            <v-icon class="my-4 li">mdi-linkedin</v-icon><br>
            <v-icon class="tw">mdi-twitter</v-icon>
        </div>

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

export default {
    name: 'mainIndexVue',


    setup() {
        const showBackToTop = ref(false);

        const scrollFunction = () => {
            showBackToTop.value = window.pageYOffset > 1400;
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

        return {
            showBackToTop,
            scrollToTop
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

<style>
.public-content {
    position: fixed;
    z-index: 1000;
    left: 0;
    top: 30%;
    padding: 16px 10px;
    background-color: rgba(255, 255, 255, 0.6);
    backdrop-filter: blur(40px);
    -webkit-backdrop-filter: blur(40px);
    border-top-right-radius: 10px;
    border-bottom-right-radius: 10px;

    /* .v-icon {
        font-size: 32px;
        cursor: pointer;
        transition: all 0.2s ease-in-out;
    }

    .fb {
        &:hover {
            color: #1877F2;
        }
    }

    .li {
        &:hover {
            color: #0A66C2;
        }
    }

    .tw {
        &:hover {
            color: #1DA1F2;
        }
    } */
}

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
    background-color: #E97559;
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