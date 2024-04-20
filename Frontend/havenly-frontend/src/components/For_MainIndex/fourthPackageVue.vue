<template>
    <div class="fourth-package">
        <div class="fourth-package-data">
            <!-- <div class="fourth-package-header mb-3">
                <div class="bar"></div>
                <h3>Choose Your Benefit</h3>
            </div> -->

            <h1 class="text-center text-white mb-5 text-uppercase pb-5">" Choose Your Benefit "</h1>

            <div class="row">

                <div class="card p-0 col-md-3 col-12 text-center">
                    <v-img :src="p3" class="card-img-top" alt="Hello world"></v-img>
                    <div class="card-body">
                        <h5 class="card-title mb-3">Free Trial</h5>
                        <p class="card-text col-10 mx-auto small">Some quick example text to build on the card title and make up the bulk of
                            the
                            card's content.</p>
                        <v-btn :elevation="0" color="red" class="my-3" variant="outlined" @click="openDialog">Check Details</v-btn>
                    </div>
                </div>

                <div class="card p-0 col-md-3 col-12 text-center">
                    <v-img :src="p2" class="card-img-top" alt="Hello world"></v-img>
                    <div class="card-body">
                        <h5 class="card-title mb-3">Normal Package</h5>
                        <p class="card-text col-10 mx-auto small">Some quick example text to build on the card title and make up the bulk of
                            the
                            card's content.</p>
                        <v-btn :elevation="0" color="red" variant="outlined" class="my-3" @click="openDialog">Check Details</v-btn>
                    </div>
                </div>

                <div class="card p-0 col-md-3 col-12 text-center">
                    <v-img :src="p1" class="card-img-top" alt="Hello world"></v-img>
                    <div class="card-body">
                        <h5 class="card-title mb-3">Premium Package</h5>
                        <p class="card-text col-10 mx-auto small">Some quick example text to build on the card title and make up the bulk of
                            the
                            card's content.</p>
                        <v-btn :elevation="0" color="red" class="my-3" @click="openDialog">Check Details</v-btn>
                    </div>
                </div>


            </div>
        </div>

    </div>
</template>

<script>
export default {
    name: 'fourthPackageVue',

    data: () => ({
        dialog: false,

        p1: require('@/assets/img/p1.jpg'),
        p2: require('@/assets/img/p2.jpg'),
        p3: require('@/assets/img/p3.jpg'),
  
    }),

}
</script>