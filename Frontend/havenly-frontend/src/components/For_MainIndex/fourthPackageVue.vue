<template>
    <div class="fourth-package">
        <div class="container">
            <div class="fourth-package-data">
                <div class="row">
                    <div class="col-md-6 col-12">
                        <div class="row">
                            <div class="col-md-7 p-1 col-12">

                                <div class="idea-box">
                                    <v-img :src="img" class="idea-img my-2"></v-img>
                                    <div>
                                        <blockquote>
                                            <span class="ms-4 f-sm">
                                                Your website packages are top-notch! They strike the perfect balance
                                                between style and functionality, catering to a variety of needs. It's
                                                impressive. Great job!
                                            </span>
                                            <footer>
                                                <cite>
                                                    Daw Hla
                                                </cite>
                                            </footer>
                                        </blockquote>
                                    </div>
                                </div>

                            </div>
                            <div class="col-md-5 p-0 d-none d-md-block">

                                <div class="coll-box ms-2">
                                    <div class="logo">
                                        <v-icon>mdi-shield-check</v-icon>
                                    </div>

                                    <div class="data">
                                        <div class="fw-bold">100+ Subscribes Members</div>
                                        <div class="d-flex">
                                            <img class="data-img" :src="img" alt="">
                                            <span class="d-block">
                                                <h5>Daw Hla</h5>
                                                <p class="text-grey">Top seller</p>
                                            </span>
                                        </div>

                                        <div class="d-flex">
                                            <img class="data-img" :src="img2" alt="">
                                            <span class="d-block">
                                                <h5>U Hla</h5>
                                                <p class="text-grey">Most Seller</p>
                                            </span>
                                        </div>

                                        <div class="d-flex">
                                            <img class="data-img" :src="img3" alt="">
                                            <span class="d-block">
                                                <h5>U Maung</h5>
                                                <p class="text-grey">Most Seller</p>
                                            </span>
                                        </div>

                                    </div>


                                </div>


                            </div>
                            <div class="col-md-5 p-0 d-none d-md-block">

                                <div class="rating-box ms-auto">
                                    <div style="text-align: center;">
                                        <v-rating :model-value="4.8" color="red" density="compact" size="medium"
                                            half-increments readonly class="mx-auto"></v-rating>

                                        <div class="text-grey">
                                            4.8 of (100+)
                                        </div>
                                        <h4>Over all Ranking</h4>
                                    </div>
                                </div>


                            </div>
                            <div class="col-md-7 col-12">

                                <div class="idea-box">
                                    <v-img :src="img3" class="idea-img my-2"></v-img>
                                    <div>
                                        <blockquote>
                                            <span class="ms-5">
                                                Package features are very simple and useful.
                                            </span>
                                            <footer>
                                                <cite>
                                                    U Maung
                                                </cite>
                                            </footer>
                                        </blockquote>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-12">

                        <div class="package-check">
                            <div class="package-data my-auto">
                                <h1>Some valuable feedback from our Subscribers</h1>
                                <p class="my-3"><span class="ms-5"></span>Unlock the potential of your real estate business with our packages. 
                                    We offer everything you need to stand out in the real estate world. 
                                    Become a subscriber and build your home with us. Get Started with a FREE TRIAL.</p>
                                <v-btn :elevation="20" to="/subscribe" class="custom-btn">Subscribe now<v-icon class="custom-icon mt-1 ms-1">mdi-chevron-double-right</v-icon> </v-btn>
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
    name: 'fourthPackage',

    data: () => ({
        img: require('@/assets/img/ava1.jpg'),
        img2: require('@/assets/img/ava2.jpg'),
        img3: require('@/assets/img/ava3.jpg'),
    }),
}
</script>