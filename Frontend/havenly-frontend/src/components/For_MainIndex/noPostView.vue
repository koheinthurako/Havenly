<!-- <template>
    <div class="no-post-view">
        <div class="no-post-view-data">

            <div class="row">
                <div class="col-md-6 p-0 h-100">
                    <div class="left-pic">
                        <div class="img-content mx-auto my-auto"></div>
                        
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
</template> -->

<template>
    <div class="no-post-main-container">
        <div class="container">
            <div class="row">
                <!-- Card 1 - Visible on Medium and Larger Screens -->
                <div class="col-md-6 mb-4 d-none d-md-block">
                    <div class="card">
                        <div class="card-body p-0 m-0">
                            <v-img :src="img" cover></v-img>
                        </div>
                    </div>
                </div>

                <!-- Card 2 - Always Visible -->
                <div class="col-md-6 mb-4">
                    <div class="card">
                        <div class="card-body d-flex justify-content-center align-items-center">
                            <div>
                                <h2>Apology for Unavailability of Properties</h2>
                                <p style="text-indent: 60px;" class="my-4">We apologize, but there are no properties
                                    available at this
                                    time. Our team is constantly
                                    updating our listings, so please check back soon for new opportunities. In the
                                    meantime,
                                    feel free to get in touch with us for personalized assistance or to inquire about
                                    upcoming properties. We appreciate your understanding and look forward to helping
                                    you
                                    find the perfect home.</p>


                                <v-btn elevation="4" rounded class="custom-btn" @click="redirect"
                                    style="text-transform:capitalize;">Join
                                    us today
                                    <v-icon class="custom-icon">mdi-chevron-double-right</v-icon></v-btn>
                            </div>
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
        img: require('@/assets/img/p1.jpg')
    }),

    methods: {
        redirect() {
            this.$router.push({ name: 'tempPackage' });
        }
    }
}

</script>

<style scoped>
.no-post-main-container {
    .card {
        border: none !important;
        box-shadow: none !important;

        .custom-btn {
            background-color: #e86f52;
            color: #fff;
        }
    }
}

@media (max-width: 991px) {
    .col-md-6 {
        flex: 0 0 100%;
        max-width: 100%;
    }
}
</style>
