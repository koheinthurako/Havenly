<template>
    <div class="commercial-vue">
        <div class="commercial-vue-data">

            <div class="row">
                <div class="col-md-6 p-0 h-100">
                    <div class="left-pic">
                        <div class="img-content mx-auto my-auto"></div>
                        <!-- <div class="left-text">
                            <h2 class="text-center">Thanks for your attention.</h2>
                            <p>We will always trying to focus to make Customer Comfortable using our website.</p>
                        </div> -->
                    </div>
                </div>
                <div class="col-md-6 p-0">
                    <div class="right-part mt-5">
                        <div class="right-data ">
                            <h2>Apology for Unavailability of Properties</h2>
                            <p style="text-indent: 60px;" class="my-4">We apologize, but there are no properties
                                available at this
                                time. Our team is constantly
                                updating our listings, so please check back soon for new opportunities. In the meantime,
                                feel free to get in touch with us for personalized assistance or to inquire about
                                upcoming properties. We appreciate your understanding and look forward to helping you
                                find the perfect home.</p>

                            <!-- <h2>Get ready for an explosion of captivating content.</h2>
                            <p class="my-3"><span class="ms-5"></span>
                                Experience peace of mind and unlock unparalleled value with our product. Your
                                satisfaction and security are our top priorities. Join us today and elevate your
                                experience to new heights.</p>
                            <p class="my-3"><span class="ms-5"></span>Discover a world of possibilities and endless
                                opportunities with our product. We're dedicated to providing you with the tools and
                                support you need to succeed. Trust in us to help you thrive and achieve your goals!</p> -->
                            <v-btn elevation="4" rounded class="custom-btn" to="/package"
                                style="text-transform:capitalize;">Join
                                us today
                                <v-icon class="custom-icon">mdi-chevron-double-right</v-icon></v-btn>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>


<script>
export default {
    name: 'CommercialVue',

    data: () => ({

    }),
}

</script>