<template>
<div class="main-index">
    
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
    <div class="for-fourContact">
        <fourContact />
    </div>

    <div class="for-contact-page">
        <contactpage />
    </div>

    <div class="for-footer-page">
        <footerpage />
    </div>
    
</div>
</template>

<script>
import firstIndex from './For_MainIndex/firstIndexContent.vue'
import secondTabContent from './For_MainIndex/secondTabContentVue.vue'
import thirdCarousel from './For_MainIndex/thirdItemCarousel.vue'
import fourthPackage from './For_MainIndex/fourthPackageVue.vue'
import fourContact from './For_MainIndex/fourContactVue.vue'
import contactpage from './For_MainIndex/ContactVue.vue'
import footerpage from './For_MainIndex/footerVue.vue'

export default {
    name: 'mainIndexVue',

    components: {
        firstIndex,
        secondTabContent,
        thirdCarousel,
        fourthPackage,
        contactpage,
        footerpage,
        fourContact
    },

    data:()=> ({
        title: 'Hello world',
        sub_title: 'sleep time ;)'
    }),
}

</script>