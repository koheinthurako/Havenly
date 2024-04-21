<template>
<div class="main-index">

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

    
</div>
</template>

<script>
import firstIndex from './For_MainIndex/firstIndexContent.vue'
import secondTabContent from './For_MainIndex/secondTabContentVue.vue'
import thirdCarousel from './For_MainIndex/thirdItemCarousel.vue'
import fourthPackage from './For_MainIndex/fourthPackageVue.vue'
import fifthDoc from './For_MainIndex/fifthDocContent.vue'

import contactpage from './For_MainIndex/ContactVue.vue'



export default {
    name: 'mainIndexVue',

    components: {
        firstIndex,
        secondTabContent,
        thirdCarousel,
        fourthPackage,
        fifthDoc,
 
        contactpage,

        
        
    },

    data:()=> ({
        title: 'Hello world',
        sub_title: 'sleep time ;)'
    }),
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

    .v-icon {
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
            color:  #0A66C2;
        }
    }

    .tw {
        &:hover {
            color: #1DA1F2;
        }
    }
}

</style>