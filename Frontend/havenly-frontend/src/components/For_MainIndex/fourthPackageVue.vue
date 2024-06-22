<!-- <template>
    <div class="fourth-package">
        <div class="container">
            <div class="fourth-package-data">
                <div class="row">
                    <h3 style="color:#e86f52;" class="ms-4 mb-3 d-block d-sm-none">User Thoughts ..</h3>
                    <div class="col-md-6 col-12">
                        <div class="row">
                            <div class="col-md-7 p-1 col-12">

                                <div class="idea-box">
                                    <v-img :src="img" class="idea-img my-2"></v-img>
                                    <div>
                                        <blockquote>
                                            <span class="ms-5 f-sm">
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
                            <div class="col-md-7 p-1 col-12">

                                <div class="idea-box">
                                    <v-img :src="img3" class="idea-img my-2"></v-img>
                                    <div>
                                        <blockquote>
                                            <span class="ms-5">
                                                The unique features offered by the package are highly valuable and
                                                exceptionally practical, all at reasonable prices, which poses no issue
                                                for me. </span>
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
                                <p class="my-3"><span class="ms-5"></span>Unlock the potential of your real estate
                                    business with our packages.
                                    We offer everything you need to stand out in the real estate world.
                                    Become a subscriber and build your home with us. If you subscribe you will get 5
                                    posts as <span>FREE
                                        TRIAL</span>. So what are you waiting for. Let't Join with us today.</p>
                                <v-btn :elevation="10" to="/subscribe" class="custom-btn">Subscribe now<v-icon
                                        class="custom-icon mt-1">mdi-chevron-double-right</v-icon> </v-btn>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>
</template> -->



<template>
    <div class="fourth-package2">
        <div class="container ">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-12 mb-3">
                    <div class="row">
                        <div class="col-md-7 col-12 mb-3 card-container">


                            <div class="ag-format-container">
                                <div class="ag-courses_box">

                                    <div class="ag-courses_item">
                                        <a href="#" class="ag-courses-item_link">
                                            <div class="ag-courses-item_bg"></div>

                                            <div class="bq-container">
                                                <blockquote>
                                                    <span class="ms-5 f-sm">
                                                        {{ truncateText(`Your website packages are top-notch! They
                                                        strike
                                                        the perfect
                                                        balance
                                                        between style and functionality, catering to a variety of needs.
                                                        It's
                                                        impressive. Great job!`, 120) }}
                                                    </span>
                                                    <footer>
                                                        <cite>
                                                            U Mya
                                                        </cite>
                                                    </footer>
                                                </blockquote>
                                            </div>

                                            <div class="ag-courses-item_date-box">
                                                Date:
                                                <span class="ag-courses-item_date">
                                                    22.06.2024
                                                </span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>


                        </div>
                        <div class="col-md-5 col-12 mb-3 card-container d-none d-md-block">
                            <div class="card card-two w-100 h-auto p-2 border-0">
                                <div class="">
                                    <div class="d-flex">
                                        <img class="w-100" :src="subImg" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5 col-12 mb-3 card-container d-none d-md-block">
                            <div class="card h-auto p-2">

                                <div class="card-two-edit">
                                    <div class="card-body-1 w-100">

                                        <div style="text-align: center;">
                                            <v-rating :model-value="4.8" color="#e86f52" density="compact" size="medium"
                                                half-increments readonly class="mx-auto"></v-rating>

                                            <div class="text-grey">
                                                4.8 of (100+)
                                            </div>
                                            <h5>Over all Ranking</h5>
                                        </div>

                                    </div>

                                    <div class="card-body-2 w-100">

                                        <div style="text-align: center;">
                                            <v-rating v-model="rate_Value" color="#e86f52" density="compact"
                                                size="medium" half-increments class="mx-auto"></v-rating>

                                            <div class="d-flex">
                                                <div class="color-brick m-auto">
                                                    {{ rate_Value }}
                                                </div>
                                                <v-icon v-if="rate_Value > 0" class="edit-btn">mdi-check</v-icon>
                                            </div>
                                            <h5>Give a Rating!</h5>
                                        </div>

                                    </div>
                                </div>


                            </div>
                        </div>
                        <div class="col-md-7 col-12 mb-3 card-container">
                            <!-- <div class="card card-four">
                      <div class="card-body h-100">
                        <h5 class="card-title">Card 4</h5>
                        <p class="card-text">This is some text within card 4.</p>
                        <p class="card-text">Additional content for Card 4.</p>
                      </div>
                    </div> -->

                            <div class="ag-format-container">
                                <div class="ag-courses_box">

                                    <div class="ag-courses_item">
                                        <a href="#" class="ag-courses-item_link">
                                            <div class="ag-courses-item_bg-2"></div>

                                            <!-- <div class="ag-courses-item_title">
                              UX/UI Web-Design&#160;+ Mobile Design
                            </div> -->

                                            <div class="bq-container">
                                                <blockquote>
                                                    <span class="ms-5 f-sm">

                                                        {{ truncateText(`Your website packages are top-notch! They
                                                        strike
                                                        the perfect
                                                        balance
                                                        between style and functionality, catering to a variety of needs.
                                                        It's
                                                        impressive. Great job!`, 100) }}

                                                    </span>
                                                    <footer>
                                                        <cite>
                                                            Daw Hla
                                                        </cite>
                                                    </footer>
                                                </blockquote>
                                            </div>

                                            <div class="ag-courses-item_date-box">
                                                Date:
                                                <span class="ag-courses-item_date">
                                                    22.06.2024
                                                </span>
                                            </div>
                                        </a>
                                    </div>

                                </div>
                            </div>


                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-12">
                    <div class="card h-100 p-3 " style="border: none;">
                        <div class="card-body d-flex justify-content-center align-items-center">
                            <div>
                                <h2 class="card-title color-brick">Some valuable feedback from our Subscribers</h2>
                                <p class="card-text">


                                    <span class="ms-5">
                                        Unlock the potential of your real estate business with our packages. We offer
                                        everything you need to
                                        stand out in the real estate world. Become a subscriber and build your home with
                                        us. If you subscribe
                                        you will get 5 posts as FREE TRIAL. So what are you waiting for. Let't Join with
                                        us today.
                                    </span>

                                </p>
                                <div class="card-action">
                                    <v-btn :elevation="10" to="/subscribe" class="custom-btn">Subscribe now<v-icon
                                            class="custom-icon mt-1">mdi-chevron-double-right</v-icon> </v-btn>
                                </div>
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
        rate_Value: 0,
        img: require('@/assets/img/ava1.jpg'),
        img2: require('@/assets/img/ava2.jpg'),
        img3: require('@/assets/img/ava3.jpg'),
        subImg: require('@/assets/img/subscribe.jpg')
    }),

    methods: {
        truncateText(text, charLimit) {
            if (text.length > charLimit) {
                return text.slice(0, charLimit) + '...';
            }
            return text;
        },
    },
}
</script>